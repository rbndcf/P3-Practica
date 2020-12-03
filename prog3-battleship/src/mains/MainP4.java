package mains;

import model.Board;
import model.Coordinate;
import model.Game;
import model.aircraft.Board3D;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;
import model.io.PlayerFactory;
import model.io.PlayerRandom;
import model.io.VisualiserConsole;
import model.io.VisualiserFactory;
import model.io.VisualiserGIF;
import model.ship.Board2D;
import model.ship.Coordinate2D;

public class MainP4 {

	private static void mainBoard3DPlayerFile() {
		IPlayer player1 = null;
		IPlayer player2 = null;
		Board b1 = new Board3D(6);
		Board b2 = new Board3D(6);
		
		try {
			player1 = PlayerFactory.createPlayer("John","files/playerfile-john.txt");
			player2 = PlayerFactory.createPlayer("Mary","files/playerfile-mary.txt");
		} catch (BattleshipIOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			player1.putCrafts(b1);
			player2.putCrafts(b2);
		} catch (InvalidCoordinateException | NextToAnotherCraftException | OccupiedCoordinateException	| BattleshipIOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		// We process all shots by player 1 at board 2
		try {
			Coordinate c;
			do {
				c=player1.nextShoot(b2);
				if (c!=null) {
					System.out.println(c);
					System.out.println(b2.show(false));
					System.out.println("--------------------------------");
				}
			} while(c != null);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException | BattleshipIOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("=============================");
		
		// We process all shots by player 2 at board 1
		try {
			Coordinate c;
			do {
				c=player2.nextShoot(b1);
				if (c!=null) {
					System.out.println(c);
					System.out.println(b1.show(false));
					System.out.println("--------------------------------");
				}
			} while(c != null);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException | BattleshipIOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("=============================");
		System.out.println(b1.show(true));
		System.out.println("--------------------------------");
		System.out.println(b2.show(true));
	}
	
	
	public static void main(String[] args) {
		mainBoard3DPlayerFile();
		
		try {
			IPlayer player1 = PlayerFactory.createPlayer("John","files/playerfile-john.txt");
			IPlayer player2 = PlayerFactory.createPlayer("Mary","files/playerfile-mary.txt");
			Board b1 = new Board3D(6);
			Board b2 = new Board3D(6);
			
			Game game = new Game(b1, b2, player1, player2);

			IVisualiser visualiser = VisualiserFactory.createVisualiser("GIF", game);
			
			game.playGame(visualiser);
			
			System.out.println("Done");
		} catch (BattleshipIOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}