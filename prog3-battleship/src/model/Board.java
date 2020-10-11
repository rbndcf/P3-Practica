package model;

import java.util.*;

public class Board {
	public static final char HIT_SYMBOL = '•';
	public static final char WATER_SYMBOL = ' ';
	public static final char NOTSEEN_SYMBOL = '?';
	
	private static final int MAX_BOARD_SIZE = 20;
	private static final int MIN_BOARD_SIZE = 5;
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	
	private Map<Coordinate, Ship> board = new HashMap<Coordinate, Ship>();
	private Set<Coordinate> seen = new HashSet<Coordinate>();
	
	/**
	 * @param size tamaño del board
	 * Constructor por parametros de board
	 */
	public Board(int size) {
		if(size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE) {
			this.size = MIN_BOARD_SIZE;
			System.err.println();
		}
		
		else {
			this.size = size;
			numCrafts = 0;
			destroyedCrafts = 0;	
		}
	}
	
	/**
	 * @return size
	 * Devuelve el size del Board
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * @param c Coordenada
	 * @return Estado de la coordenada
	 * Devuelve true si la coordenada entra en el Board, o false si no entra
	 */
	public boolean checkCoordinate(Coordinate c) {
		if(c.get(0) < 0 || c.get(0) > size-1 || c.get(1) < 0 || c.get(1) > size-1) return false;
		else return true;
	}
	
	public boolean addShip(Ship ship, Coordinate position) {
		boolean check = true;
		
		for(Coordinate c1 : ship.getAbsolutePositions(position)) {
			System.out.println(c1.get(0) + " " + c1.get(1));
			
			if(c1.get(0) < 0 || c1.get(0) >= size || c1.get(1) < 0 || c1.get(1) >= size) {
				System.out.println("Entroo");
				check = false;	
			}
		}
		
		System.out.println(check);
		
		if(check) {
			for(Coordinate c1 : ship.getAbsolutePositions(position))
				board.put(c1, ship);
			
			return true;
		}
		
		else return false;
		
		/*if(position.get(0) < 0 || (position.get(0) + 4) > size || position.get(1) < 0 || (position.get(1) + 4) > size) {
			System.err.println();
			return false;
		}
		else {
			for(Coordinate c1 : ship.getAbsolutePositions(position))
				board.put(c1, ship);

			return true;
		}*/
	}
	
	public Ship getShip(Coordinate c) {
		return board.get(c);
	}
	
	public boolean isSeen(Coordinate c) {
		
	}
	
	public CellStatus hit(Coordinate c) {
		
	}
	
	public boolean areAllCraftsDestroyed() {
		
	}
	
	public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		
	}
	
	public Set<Coordinate> getNeighborhood(Ship s1){
		
	}
	
	public String show(boolean unveil) {
		
	}
	
	public String toString() {
		
	}
}