package model;

import java.util.*;

public class Board {
	public static final char HIT_SYMBOL = '•';
	public static final char WATER_SYMBOL = ' ';
	public static final char NOTSEEN_SYMBOL = '?';
	
	private static final int MAX_BOARD_SIZE = 20;
	private static final int MIN_BOARD_SIZE = 5;
	private int size;
	private int numCrafts = 0;
	private int destroyedCrafts = 0;
	
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
			if(c1.get(0) < 0 || c1.get(0) >= size || c1.get(1) < 0 || c1.get(1) >= size) {
				System.err.println();
				check = false;	
			}
			
			else if(board.containsKey(c1)) {
				System.err.println();
				check = false;
			}
			
			else
				for(Coordinate c : this.getNeighborhood(ship, position))
					if(board.containsKey(c) && board.get(c) != ship)
						check = false;
		}
		
		if(check) {
			ship.setPosition(position);
			
			for(Coordinate c1 : ship.getAbsolutePositions(position))
				board.put(c1, ship);
			
			return true;
		}
		
		else return false;
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
		Set<Coordinate> s1 = new HashSet<Coordinate>();
		Set<Coordinate> c1 = new HashSet<Coordinate>();
		
		c1 = ship.getAbsolutePositions(position);
		
		for(Coordinate c : c1)
			for(int i = -1 ; i < 2 ; i++) 
				for(int j = -1 ; j < 2 ; j++) {
					Coordinate c2 = new Coordinate(c.get(0) + i, c.get(1) + j);
					
					if(!s1.contains(c2) && c2.get(0) >= 0 && c2.get(0) < size && c2.get(1) >= 0 && c2.get(1) < size)
						s1.add(c2);
				}
		
		for(Coordinate c : c1)
			s1.remove(c);
		
		return s1;
	}
	
	public Set<Coordinate> getNeighborhood(Ship s1){
		Set<Coordinate> c = this.getNeighborhood(s1, s1.getPosition());
		
		return c;
	}
	
	public String show(boolean unveil) {
		
	}
	
	public String toString() {
		
	}
}