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
	
	/**
	 * @param ship que se añade 
	 * @param position donde se añade su shape
	 * @return true si se añade faslse si no
	 * Recibe un Ship y un Coordinate, si cumple los requisitos se añade el Ship en dicha Coordinate y devuelve true, si no lo
	 * cumple devuelve false y no se añade
	 */
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
			numCrafts++;
			
			for(Coordinate c1 : ship.getAbsolutePositions(position))
				board.put(c1, ship);
		}
		
		return check;
	}
	
	/**
	 * @param c Coordenada del barco
	 * @return el Ship
	 * Devuelve el Ship que se encuentra en la Coordinate que recibe
	 */
	public Ship getShip(Coordinate c) {
		return board.get(c);
	}
	
	/**
	 * @param c Coordenada que queremos comprobar
	 * @return Return si se ha visto
	 * Recibe una coordenada y devuelve true si ha sido vista por el adversario o false si no ha sido vista
	 */
	public boolean isSeen(Coordinate c) {
		if(seen.contains(c)) return true;
		else return false;
	}
	
	/**
	 * @param c Coordenada a la que se dispara
	 * @return Estado del sitio
	 * Se dispara a 1 coordenada, si no hay ningún barco se devuelve WATER, si hay un barco se devuelve HIT, y si era la
	 * ultima casilla sin alcanzar del barco se devuelve DESTROYED
	 */
	public CellStatus hit(Coordinate c) {
		if(c.get(0) < 0 || c.get(0) >= size || c.get(1) < 0 || c.get(1) >= size) {
			System.err.println();
			return CellStatus.WATER;
		}
		
		else if(board.containsKey(c)) {
			seen.add(c);
			
			if(!board.get(c).isHit(c))
				board.get(c).hit(c);
			
			if(board.get(c).isShotDown()) {
				destroyedCrafts++;
				for(Coordinate c1 : this.getNeighborhood(board.get(c)))
					if(!seen.contains(c1))
						seen.add(c1);
				
				return CellStatus.DESTROYED;
			}
			else return CellStatus.HIT;
		}
		
		else {
			seen.add(c);
			return CellStatus.WATER;
		}
	}
	
	/**
	 * @return Estado de los barcos
	 * Si todos los barcos han sido destruidos devuelve true, en caso contrario devuelve false
	 */
	public boolean areAllCraftsDestroyed() {
		if(numCrafts == destroyedCrafts)
			return true;
		else
			return false;
	}
	
	/**
	 * @param ship Barco del que queremos el neighborhood
	 * @param position Posición del barco
	 * @return Coordenadas vecinas
	 * Recibe el barco del cual quiere saberse el neighborhood y la Coordinate en la cual se encuentra, y devuelve todas las coordenadas
	 * vecinas al barco, eliminando las del propio barco
	 */
	public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		Set<Coordinate> s = new HashSet<Coordinate>();
		Set<Coordinate> c = new HashSet<Coordinate>();
		
		c = ship.getAbsolutePositions(position);
		
		for(Coordinate c1 : c) {
			Set<Coordinate> c2 = new HashSet<Coordinate>();
			c2 = c1.adjacentCoordinates();
			
			for(Coordinate c3 : c2)
				if(!s.contains(c3) && c3.get(0) >= 0 && c3.get(0) < size && c3.get(1) >= 0 && c3.get(1) < size)
					s.add(c3);
		}
		
		for(Coordinate c1 : c)
			s.remove(c1);
			
		return s;
		
		// Esto lo tenía hecho sin acordarme del adjacentCoordinates()
		/*Set<Coordinate> s1 = new HashSet<Coordinate>();
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
		
		return s1;*/
	}
	
	/**
	 * @param s1 Barco del que queremos el neighborhood
	 * @return neighborhood
	 * Recibe un barco sin Coordiangte y llama a neighborhood con el mismo barco pero usando como posición la del propio barco
	 */
	public Set<Coordinate> getNeighborhood(Ship s1){
		Set<Coordinate> c = this.getNeighborhood(s1, s1.getPosition());
		
		return c;
	}
	
	/**
	 * @param unveil Modo de vision
	 * @return El tablero de forma "grafica"
	 * Recibe el parametro que indica si se ve como el dueño o el adversario, y segun ese parametro se muestran
	 * todas las posiciones del board o solo las que ha visto el adversario y tapando las que no ha visto con
	 * NOTSEEN_SYMBOL que equivale a "?"
	 */
	public String show(boolean unveil) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < size ; i++) {
			for(int j = 0 ; j < size ; j++) {
				if(this.isSeen(new Coordinate(j,i))) {
					if(board.containsKey(new Coordinate(j,i))) {
						Ship s1 = board.get(new Coordinate(j,i));
						sb.append(s1.getSymbol());
					}
					else
						sb.append(WATER_SYMBOL);	
				}
		
				else if(unveil) {
					if(board.containsKey(new Coordinate(j,i))) {
						Ship s1 = board.get(new Coordinate(j,i));
						sb.append(s1.getSymbol());
					}
					else
						sb.append(WATER_SYMBOL);
				}
		
				else
					sb.append(NOTSEEN_SYMBOL);
			}
			if(i != size - 1)
				sb.append("\n");	
		}
			
		
		return sb.toString();
	}
	
	/**
	 * @return string del board
	 * Devuelve una cadena con el tamaño del board, el numero de barcos y el numero de barcos destruidos
	 */
	public String toString() {
		String sb = "Board " + size + "; crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
		
		return sb;
	}
}