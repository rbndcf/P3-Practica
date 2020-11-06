package mains;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.Craft;
import model.Orientation;
import model.aircraft.Aircraft;
import model.aircraft.Board3D;
import model.aircraft.Coordinate3D;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.ship.Battleship;
import model.ship.Board2D;
import model.ship.Carrier;
import model.ship.Coordinate2D;
import model.ship.Cruiser;
import model.ship.Destroyer;
import model.ship.Ship;

public class MainP3 {
	private static void printAbsolutePositions(Craft crf, Coordinate pos) {
		System.out.print("Absolute positions: ");
		for (Coordinate c: crf.getAbsolutePositions(pos)) {
			System.out.print(c+" ");
		}
		System.out.println("");
	}
	
	private static void printNeighbouringPositions(Craft crf, Board board) {
		System.out.print("Neighbouring positions: ");
		for (Coordinate c: board.getNeighborhood(crf)) {
			System.out.print(c+" ");
		}
		System.out.println("");
	}
	
	private static void mainBoard2D() {
		Board2D board2d = null;
		System.out.println("=== Board 2D ===");
		System.out.println(new Cruiser(Orientation.NORTH));
		System.out.println(new Cruiser(Orientation.EAST));
		System.out.println(new Cruiser(Orientation.SOUTH));
		System.out.println(new Cruiser(Orientation.WEST));
		
		System.out.println(new Carrier(Orientation.NORTH));
		System.out.println(new Carrier(Orientation.EAST));
		System.out.println(new Carrier(Orientation.SOUTH));
		System.out.println(new Carrier(Orientation.WEST));
		
		System.out.println(new Battleship(Orientation.NORTH));
		System.out.println(new Battleship(Orientation.EAST));
		System.out.println(new Battleship(Orientation.SOUTH));
		System.out.println(new Battleship(Orientation.WEST));
		
		System.out.println(new Destroyer(Orientation.NORTH));
		System.out.println(new Destroyer(Orientation.EAST));
		System.out.println(new Destroyer(Orientation.SOUTH));
		System.out.println(new Destroyer(Orientation.WEST));
		
		System.out.println("======================================");
		board2d = new Board2D(8);
		System.out.println(board2d.show(false));
		System.out.println("======================================");
		System.out.println(board2d.show(true));				
		System.out.println("======================================");
		
		Ship ship;
		Coordinate2D pos;
		
		ship = new Cruiser(Orientation.EAST);
		pos = new Coordinate2D(-1,-1);
		
		Ship shipCruiser = ship;
				
		try {
			System.out.println("Adding ship at " + pos);
			System.out.println(ship);
	
			board2d.addCraft(ship, pos);
		} catch (NextToAnotherCraftException  | OccupiedCoordinateException | InvalidCoordinateException e) {
			System.out.println(e.getMessage());
		}
		printAbsolutePositions(ship,pos);
		System.out.println("======================================");
		System.out.println(board2d.show(true));				
		System.out.println("======================================");
			
		
		ship = new Cruiser(Orientation.WEST);
		pos = new Coordinate2D(-2,-1);
		try {	
			System.out.println("Adding ship  at " + pos);
			System.out.println(ship);
			
			board2d.addCraft(ship, pos);
		} catch (NextToAnotherCraftException  | OccupiedCoordinateException | InvalidCoordinateException e) {
			System.out.println(e.getMessage());
		}
		printAbsolutePositions(ship,pos);
		System.out.println("======================================");
		System.out.println(board2d.show(true));				
		System.out.println("======================================");
		
		
		ship = new Cruiser(Orientation.WEST);
		pos = new Coordinate2D(-1,-1);
		try {
			System.out.println("Adding ship " + ship.getName() + " at " + pos);
			board2d.addCraft(ship, pos);
		} catch (NextToAnotherCraftException  | OccupiedCoordinateException | InvalidCoordinateException e) {
			System.out.println(e.getMessage());
		}
		printAbsolutePositions(ship,pos);
		
		ship = new Cruiser(Orientation.NORTH);
		pos = new Coordinate2D(0,4);
		try {
			System.out.println("Adding ship  at " + pos);
			System.out.println(ship);
			
			board2d.addCraft(ship, pos);
		} catch (NextToAnotherCraftException  | OccupiedCoordinateException | InvalidCoordinateException e) {
			System.out.println(e.getMessage());
		}
		printAbsolutePositions(ship, pos);
		System.out.println("======================================");
		System.out.println(board2d.show(true));				
		System.out.println("======================================");
		
		
		ship = new Carrier (Orientation.NORTH);
		pos = new Coordinate2D(2,5);
		try {
			System.out.println("Adding ship " + ship.getName() + " at " + pos);
			board2d.addCraft(ship, pos);
		} catch (NextToAnotherCraftException  | OccupiedCoordinateException | InvalidCoordinateException e) {
			System.out.println(e.getMessage());
		}
		printAbsolutePositions(ship,pos);
		
		System.out.println("======================================");
		System.out.println(board2d.show(true));
		System.out.println("======================================");
		
		CellStatus s;
		Coordinate c;
		c=new Coordinate2D(0,0);
		try {
			s=board2d.hit(c);
			System.out.println("Shooting ship at " +c+"; result: "+s);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			System.out.println("Shooting ship at " +c+"; result: "+e.getMessage());
		}
		
		c=new Coordinate2D(0,2);
		try {
			s=board2d.hit(c);
			System.out.println("Shooting ship at " +c+"; result: "+s);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			System.out.println("Shooting ship at " +c+"; result: "+e.getMessage());
		}
		
		c=new Coordinate2D(1,2);
		try {
			s=board2d.hit(c);
			System.out.println("Shooting ship at " +c+"; result: "+s);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			System.out.println("Shooting ship at " +c+"; result: "+e.getMessage());
		}
		
		c=new Coordinate2D(1,1);
		try {
			s=board2d.hit(c);
			System.out.println("Shooting ship at " +c+"; result: "+s);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			System.out.println("Shooting ship at " +c+"; result: "+e.getMessage());
		}
		
		try {
			s=board2d.hit(c);
			System.out.println("Shooting ship at " +c+"; result: "+s);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			System.out.println("Shooting ship at " +c+"; result: "+e.getMessage());
		}
		
		c=new Coordinate2D(2,6);
		try {
			s=board2d.hit(c);
			System.out.println("Shooting ship at " +c+"; result: "+s);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			System.out.println("Shooting ship at " +c+"; result: "+e.getMessage());
		}
		
		System.out.println(board2d.show(false));
		System.out.println("--------------------------");
		System.out.println(board2d.show(true));
		
		System.out.println("Ship "+shipCruiser.getName()+" looks like:\n"+shipCruiser);
		
		c=new Coordinate2D(1,0);
		try {
			s=board2d.hit(c);
			System.out.println("Shooting ship at " +c+"; result: "+s);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			System.out.println("Shooting ship at " +c+"; result: "+e.getMessage());
		}
		
		System.out.println(board2d.show(false));
		
		System.out.println("Ship "+shipCruiser.getName()+" looks like:\n"+shipCruiser);
		
		try {
			c=new Coordinate2D(3,6);
			s=board2d.hit(c);
			System.out.println("Shooting ship at " +c+"; result: "+s);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			System.out.println("Shooting ship at " +c+"; result: "+e.getMessage());
		}
		
		System.out.println(board2d.show(false));
		
		System.out.println("Are all ships destroyed? "+ board2d.areAllCraftsDestroyed());
		
		try {
			c=new Coordinate2D(1,6);
			s=board2d.hit(c);
			System.out.println("Shooting ship at " +c+"; result: "+s);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			System.out.println("Shooting ship at " +c+"; result: "+e.getMessage());
		}
		
		System.out.println(board2d.show(false));
		
		System.out.println("Are all ships destroyed? "+ board2d.areAllCraftsDestroyed());	
		
		System.out.println(board2d.show(true));
	}
	
	private static void mainBoard3D() {
		Board3D board3d = null;
		System.out.println("=== Board 3D ===");
		System.out.println(new Fighter(Orientation.NORTH));
		System.out.println(new Fighter(Orientation.EAST));
		System.out.println(new Fighter(Orientation.SOUTH));
		System.out.println(new Fighter(Orientation.WEST));

		System.out.println(new Fighter(Orientation.NORTH));
		System.out.println(new Fighter(Orientation.EAST));
		System.out.println(new Fighter(Orientation.SOUTH));
		System.out.println(new Fighter(Orientation.WEST));
		
		System.out.println(new Transport(Orientation.NORTH));
		System.out.println(new Transport(Orientation.EAST));
		System.out.println(new Transport(Orientation.SOUTH));
		System.out.println(new Transport(Orientation.WEST));
		
		System.out.println("======================================");
		board3d = new Board3D(8);
		System.out.println(board3d.show(false));	

		Aircraft plane;
		Coordinate3D pos;
		
		plane = new Fighter(Orientation.EAST);
		pos = new Coordinate3D(-1,1,0);
		
		try {
			System.out.println("Adding plane at " + pos);
			System.out.println(plane);
			board3d.addCraft(plane, pos);
			printAbsolutePositions(plane,pos);
			printNeighbouringPositions(plane, board3d);
		} catch (NextToAnotherCraftException  | OccupiedCoordinateException | InvalidCoordinateException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("======================================");
		System.out.println(board3d.show(true));
		System.out.println("======================================");
		
		plane = new Fighter(Orientation.EAST);
		pos = new Coordinate3D(-1,1,0);
		try {
			System.out.println("Adding plane " + plane.getName() + " at " + pos);
			board3d.addCraft(plane, pos);
		} catch (NextToAnotherCraftException  | OccupiedCoordinateException | InvalidCoordinateException e) {
			System.out.println(e.getMessage());
		}
		
		plane = new Fighter(Orientation.EAST);
		pos = new Coordinate3D(-1,1,1);
		try {
			System.out.println("Adding plane " + plane.getName() + " at " + pos);
			board3d.addCraft(plane, pos);
		} catch (NextToAnotherCraftException  | OccupiedCoordinateException | InvalidCoordinateException e) {
			System.out.println(e.getMessage());
		}
		
		plane = new Fighter(Orientation.EAST);
		pos = new Coordinate3D(1,1,2);
		try {
			System.out.println("Adding plane at " + pos);
			System.out.println(plane);
			board3d.addCraft(plane, pos);
			printAbsolutePositions(plane,pos);
			printNeighbouringPositions(plane, board3d);
		} catch (NextToAnotherCraftException  | OccupiedCoordinateException | InvalidCoordinateException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("======================================");
		System.out.println(board3d.show(true));
		System.out.println("======================================");
		
		Coordinate c=new Coordinate3D(3,4,2);
		try {
			System.out.println("Shooting aircraft at " +c);
			CellStatus s=board3d.hit(c);
			System.out.println("Result: "+s);
			System.out.println("Shooting aircraft at " +c);
			s=board3d.hit(c);
			System.out.println("Result: "+s);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			System.out.println("Result: "+e.getMessage());
		}
		System.out.println(board3d.show(false));
		System.out.println("-----------------------------------------");
		System.out.println(board3d.show(true));
		
		try {
			c=new Coordinate3D(2,3,2);
			CellStatus s=board3d.hit(c);
			System.out.println("Shooting aircraft at " +c+"; result: "+s);
			c=new Coordinate3D(3,3,2);
			s=board3d.hit(c);
			System.out.println("Shooting aircraft at " +c+"; result: "+s);
			c=new Coordinate3D(3,2,2);
			s=board3d.hit(c);
			System.out.println("Shooting aircraft at " +c+"; result: "+s);
			c=new Coordinate3D(4,3,2);
			s=board3d.hit(c);
			System.out.println("Shooting aircraft at " +c+"; result: "+s);
			System.out.println(board3d.show(false));
			c=new Coordinate3D(3,1,2);
			s=board3d.hit(c);
			System.out.println("Shooting aircraft at " +c+"; result: "+s);
			System.out.println(board3d.show(false));
			System.out.println("--------------------------------------");
			System.out.println(board3d.show(true));
			
			System.out.println(".......................");
			c=new Coordinate3D(1,3,2);
			s=board3d.hit(c);
			System.out.println("Shooting aircraft at " +c+"; result: "+s);
			System.out.println(board3d.show(false));
			System.out.println("--------------------------------------");
			System.out.println(board3d.show(true));
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static void main(String[] args) {
		mainBoard2D();
		System.out.println("\n%-%-%-%-%-%-%-%-%-%-%-%-%-%-%\n");
		mainBoard3D();
	}

}