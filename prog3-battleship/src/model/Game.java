package model;

import java.util.*;

import model.io.*;
import model.exceptions.*;
import model.exceptions.io.*;

public class Game {
	private boolean gameStarted;
	private int nextToShoot;
	private int shootCounter;
	
	private IPlayer player1;
	private IPlayer player2;
	private Board board1;
	private Board board2;
	
	public Game(Board b1, Board b2, IPlayer p1, IPlayer p2) {
		Objects.requireNonNull(b1);
		Objects.requireNonNull(b2);
		Objects.requireNonNull(p1);
		Objects.requireNonNull(p2);	
		
		player1 = p1;
		player2 = p2;
		board1 = b1;
		board2 = b2;
		
		gameStarted = false;
	}
	
	public IPlayer getPlayer1() {
		return player1;
	}
	
	public IPlayer getPlayer2() {
		return player2;
	}
	
	public IPlayer getPlayerLastShoot() {
		if(nextToShoot == 0)
			return null;
		else if(nextToShoot == 1)
			return player2;
		else
			return player1;
	}
	
	public Board getBoard1() {
		return board1;
	}
	
	public Board getBoard2() {
		return board2;
	}
	
	public void start() {
		try {
			player1.putCrafts(board1);
			player2.putCrafts(board2);
		}
		catch(Exception e) {
			throw new RuntimeException();
		}
		
		gameStarted = true;
		shootCounter = 0;
		nextToShoot = 0;
	}
	
	public boolean gameEnded() {
		if((board1.areAllCraftsDestroyed() || board2.areAllCraftsDestroyed()) && gameStarted)
			return true;
		else return false;
	}
	
	public boolean playNext() {
		Coordinate c;

		if(nextToShoot == 1 || nextToShoot == 0) {
			try {
				c = player1.nextShoot(board2);
				if(!Objects.isNull(c)) {
					nextToShoot = 2;
					shootCounter++;
					
					return true;
				}
				else return false;
			}
			catch(BattleshipIOException | InvalidCoordinateException e) {
				throw new RuntimeException();
			}
			catch(CoordinateAlreadyHitException e) {
				nextToShoot = 2;
				shootCounter++;
				
				System.out.println("Action by " + player1.getName() + e.getMessage());
				
				return true;
			}
		}
		
		else {
			try {
				c = player2.nextShoot(board1);
				if(!Objects.isNull(c)) {
					nextToShoot = 2;
					shootCounter++;
					
					return true;
				}
				else return false;
			}
			catch(BattleshipIOException | InvalidCoordinateException e) {
				throw new RuntimeException();
			}
			catch(CoordinateAlreadyHitException e) {
				nextToShoot = 2;
				shootCounter++;
				
				System.out.println("Action by " + player2.getName() + e.getMessage());
				
				return true;
			}
		}
	}
	
	public void playGame(IVisualiser visualiser) {
		boolean go = true;
		
		this.start();
		visualiser.show();
		
		do {
			go = this.playNext();
			
			if(go)
				visualiser.show();
			else {
				visualiser.close();
				break;
			}
				
		}while(go);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if(!this.gameStarted)
			sb.append("=== GAME NOT STARTED ===\n");
		else if(this.gameEnded())
			sb.append("=== GAME ENDED ===\n");
		else
			sb.append("=== ONGOING GAME ===\n");
			
		sb.append("==================================\n");
		sb.append(player1.getName() + "\n");
		sb.append("==================================\n");
		sb.append(board1.show(false) + "\n");
		sb.append("==================================\n");
		sb.append(player2.getName() + "\n");
		sb.append("==================================\n");
		sb.append(board2.show(false) + "\n");
		sb.append("==================================\n");
		sb.append("Number of shots: " + this.shootCounter + "\n");
		
		if(this.gameEnded()) {
			if(board1.areAllCraftsDestroyed())
				sb.append(player2.getName() + " wins\n");
			else if(board2.areAllCraftsDestroyed())
				sb.append(player1.getName() + " wins\n");
		}
		
		return sb.toString();
	}
}
