package model;

import java.util.*;

import model.io.*;
import model.exceptions.*;
import model.exceptions.io.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para controlar las diferentes partes del juego, tanto su constructor como los getters, además del inicializador (start()), la
 * función para saber si el juego a acabado, la siguiente jugada, para pasarlo a texto o la función para que el programa juegue (playGame()).
 */
public class Game {
	/**
	 * @param gameStarted estado del inicio de la partida
	 */
	private boolean gameStarted;
	/**
	 * @param nextToShoot jugador que debe disparar después
	 */
	private int nextToShoot;
	/**
	 * @param shootCounter cantidad de disparos realizados
	 */
	private int shootCounter;
	
	/**
	 * @param player1 jugador 1
	 */
	private IPlayer player1;
	/**
	 * @param player2 jugador 2
	 */
	private IPlayer player2;
	/**
	 * @param board1 tablero del jugador 1
	 */
	private Board board1;
	/**
	 * @param board2 tablero del jugador 2
	 */
	private Board board2;
	
	/**
	 * @param b1 tablero del jugador 1
	 * @param b2 tablero del jugador 2
	 * @param p1 jugador 1
	 * @param p2 jugador 2
	 * Constructor por parámetros
	 */
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
	
	/**
	 * @return player1
	 * Devuelve el jugador 1
	 */
	public IPlayer getPlayer1() {
		return player1;
	}
	
	/**
	 * @return player2
	 * Devuelve el jugador 2
	 */
	public IPlayer getPlayer2() {
		return player2;
	}
	
	/**
	 * @return player1, player2 or null
	 * Devuelve el ultimo jugador que ha disparado, o null si nadie ha disparado
	 */
	public IPlayer getPlayerLastShoot() {
		if(nextToShoot == 0)
			return null;
		else if(nextToShoot == 1)
			return player2;
		else
			return player1;
	}
	
	/**
	 * @return board1
	 * Devuelve el tablero del jugador 1
	 */
	public Board getBoard1() {
		return board1;
	}
	
	/**
	 * @return board2
	 * Devuelve el tablero del jugador 2
	 */
	public Board getBoard2() {
		return board2;
	}
	
	/**
	 * Inicia la partida posicionando los barcos de cada jugador en su tablero, además de iniciar gameStarted, shootCounter y nextToShoot
	 */
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
	
	/**
	 * @return estado de la partida
	 * Devuelve true si la partida a acabado y false si no ha acabado o no ha empezado
	 */
	public boolean gameEnded() {
		if((board1.areAllCraftsDestroyed() || board2.areAllCraftsDestroyed()) && gameStarted)
			return true;
		else return false;
	}
	
	/**
	 * @return estado de la jugada
	 * Devuelve falso si el jugador no ha podido disparar, si ha disparado, aunque la coordenada fuera alcanzada anteriormente, devuelve true
	 */
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
					nextToShoot = 1;
					shootCounter++;
					
					return true;
				}
				else return false;
			}
			catch(BattleshipIOException | InvalidCoordinateException e) {
				throw new RuntimeException();
			}
			catch(CoordinateAlreadyHitException e) {
				nextToShoot = 1;
				shootCounter++;
				
				System.out.println("Action by " + player2.getName() + e.getMessage());
				
				return true;
			}
		}
	}
	
	/**
	 * @param visualiser IVisualiser que utiliza
	 * Inicia la partida, y queda llamando a playNext() hasta que devuelva false o el juego haya acabado
	 */
	public void playGame(IVisualiser visualiser) {
		this.start();
		visualiser.show();
		
		while(!this.gameEnded()) {
			if(this.playNext())
				visualiser.show();
			else if(this.gameEnded())
				visualiser.close();
			else {
				visualiser.close();
				break;
			}
		}
	}
	
	/**
	 * @return String del estado de la partida
	 * Convierte a una String la partida mostrando el estado, los tableros y el nombre de cada juador
	 */
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
		sb.append("Number of shots: " + this.shootCounter);
		
		if(this.gameEnded()) {
			if(board1.areAllCraftsDestroyed())
				sb.append("\n" + player2.getName() + " wins");
			else if(board2.areAllCraftsDestroyed())
				sb.append("\n" + player1.getName() + " wins");
		}
		
		return sb.toString();
	}
}
