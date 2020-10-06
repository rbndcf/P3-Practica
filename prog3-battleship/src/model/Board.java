package model;

import java.util.*;

public class Board {
	public final char HIT_SYMBOL;
	public final char WATER_SYMBOL;
	public final char NOTSEEN_SYMBOL;
	
	private final int MAX_BOARD_SIZE;
	private final int MIN_BOARD_SIZE;
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	
	public Board(int size) {
		
	}
	
	public int getSize() {
		
	}
	
	public boolean checkCoordinate(Coordinate c) {
		
	}
	
	public boolean addShip(Ship ship, Coordinate position) {
		
	}
	
	public Ship getShip(Coordinate c) {
		
	}
	
	public boolean isSeen(Coordinate c) {
		
	}
	
	public CellStatus hit(Coordinate c) {
		
	}
	
	public boolean areAllCraftsDestroyed() {
		
	}
	
	public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		
	}
	
	public Set<Coordinate> getNeighborhood(Coordinate c){
		
	}
	
	public String Show(boolean unveil) {
		
	}
	
	public String toString() {
		
	}
}