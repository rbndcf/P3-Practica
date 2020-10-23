package model;

import java.util.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para controlar el tablero de juego de nuestro Battleship. Aqui tendremos el tamaño del tablero, los simbolos que representan el
 * Show(), las casillas que hemos descubierto, y los barcos que hay en el tablero con cada posicion que ocupan, además de poder añadir barcos y sus coordenadas 
 * al tablero, saber el numero de barcos que se han añadido y cuantos se han destruido.
 */
public class Board {
	
	
	/**
	 * @param HIT_SYMBOL simbolo cuando se ha dado a un barco
	 */
	public static final char HIT_SYMBOL = '•';
	/**
	 * @param WATER_SYMBOL simbolo del agua
	 */
	public static final char WATER_SYMBOL = ' ';
	/**
	 * @param NOTSEEN_SYMBOL simbolo cuando no se ha visto una posicion
	 */
	public static final char NOTSEEN_SYMBOL = '?';
	
	/**
	 * @param MAX_BOARD_SIZE tamaño maximo del board
	 */
	private static final int MAX_BOARD_SIZE = 20;
	/**
	 * @param MIN_BOARD_SIZE tamaño minimo del board
	 */
	private static final int MIN_BOARD_SIZE = 5;
	/**
	 * @param size tamaño del board
	 */
	private int size;
	/**
	 * @param numCrafts numero de Crafts en el board
	 */
	private int numCrafts;
	/**
	 * @param destroyedCrafts numero de Crafts destruidos
	 */
	private int destroyedCrafts;
	
	/**
	 * @param board mapa donde se almacenan todos los barcos y sus coordenadas absolutas que hay en el board
	 */
	private Map<Coordinate, Craft> board = new HashMap<Coordinate, Craft>();
	/**
	 * @param seen set de coordenadas que han sido disparadas o de alrededor en un barco destruido
	 */
	private Set<Coordinate> seen = new HashSet<Coordinate>();
	
	/**
	 * @param size tamaño del board
	 * Constructor por parametros de board
	 */
	public Board(int size) {
		if(size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE) {
			this.size = MIN_BOARD_SIZE;
			System.err.println("Error in Board.Board, size " + size + " is out of max or min size");
		}
		
		else
			this.size = size;
		
		numCrafts = 0;
		destroyedCrafts = 0;	
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
	 * @param Craft que se añade 
	 * @param position donde se añade su shape
	 * @return true si se añade faslse si no
	 * Recibe un Craft y un Coordinate, si cumple los requisitos se añade el Craft en dicha Coordinate y devuelve true, si no lo
	 * cumple devuelve false y no se añade
	 */
	public boolean addCraft(Craft craft, Coordinate position) {
		boolean check = true;
		
		for(Coordinate c1 : craft.getAbsolutePositions(position)) {
			if(c1.get(0) < 0 || c1.get(0) >= size || c1.get(1) < 0 || c1.get(1) >= size) {
				System.err.println("Error in Board.addCraft, position " + c1.toString() + " is out of the board");
				check = false;	
			}
			
			else if(board.containsKey(c1)) {
				System.err.println("Error in Board.addCraft, position " + c1.toString() + " is already occupied");
				check = false;
			}
			
			else {
				for(Coordinate c : this.getNeighborhood(craft, position))
					if(board.containsKey(c) && board.get(c) != craft) {
						System.err.println("Error in Board.addCraft, position " + c.toString() + " is next to another craft");
						check = false;
						break;
					}
			}
			if(!check)
				break;
		}
		
		if(check) {
			craft.setPosition(position);
			numCrafts++;
			
			for(Coordinate c1 : craft.getAbsolutePositions(position))
				board.put(c1, craft);
		}
		
		return check;
	}
	
	/**
	 * @param c Coordenada del barco
	 * @return el Craft
	 * Devuelve el Craft que se encuentra en la Coordinate que recibe
	 */
	public Craft getCraft(Coordinate c) {
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
			System.err.println("Error in Board.hit, position " + c.toString() + "is out of the Board");
			return CellStatus.WATER;
		}
		
		else if(board.containsKey(c)) {
			seen.add(new Coordinate(c));
			
			if(board.get(c).isHit(c))
				return CellStatus.WATER;
				
			else
				board.get(c).hit(c);
			
			
			if(board.get(c).isShotDown()) {
				destroyedCrafts++;
				for(Coordinate c1 : this.getNeighborhood(board.get(c)))
					if(!seen.contains(c1))
						seen.add(new Coordinate(c1));
				
				return CellStatus.DESTROYED;
			}
			else return CellStatus.HIT;
		}
		
		else {
			seen.add(new Coordinate(c));
			return CellStatus.WATER;
		}
	}
	
	/**
	 * @return Estado de los barcos
	 * Si todos los barcos han sido destruidos devuelve true, en caso contrario devuelve false
	 */
	public boolean areAllCraftsDestroyed() {
		if(numCrafts == destroyedCrafts) return true;
		else return false;
	}
	
	/**
	 * @param craft Barco del que queremos el neighborhood
	 * @param position Posición del barco
	 * @return Coordenadas vecinas
	 * Recibe el barco del cual quiere saberse el neighborhood y la Coordinate en la cual se encuentra, y devuelve todas las coordenadas
	 * vecinas al barco, eliminando las del propio barco
	 */
	public Set<Coordinate> getNeighborhood(Craft craft, Coordinate position){
		Set<Coordinate> s = new HashSet<Coordinate>();
		Set<Coordinate> c = new HashSet<Coordinate>();
		
		c = craft.getAbsolutePositions(position);
		
		if(c == null)
			return s;
		
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
	}
	
	/**
	 * @param s1 Barco del que queremos el neighborhood
	 * @return neighborhood
	 * Recibe un barco sin Coordiangte y llama a neighborhood con el mismo barco pero usando como posición la del propio barco
	 */
	public Set<Coordinate> getNeighborhood(Craft s1){
		if(s1.getPosition() == null) return new HashSet<Coordinate>();
		else return this.getNeighborhood(s1, s1.getPosition());
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
				Coordinate c = new Coordinate(j, i);
				
				if(unveil) {
					if(board.containsKey(c)) {
						if(board.get(c).isHit(c))
							sb.append(HIT_SYMBOL);
							
						else
							sb.append(board.get(c).getSymbol());
					}
					
					else
						sb.append(WATER_SYMBOL);
				}
				
				else {
					if(seen.contains(c)) {
						if(board.containsKey(c) && board.get(c).isShotDown())
							sb.append(board.get(c).getSymbol());
						
						else if(board.containsKey(c))
							sb.append(HIT_SYMBOL);
						
						else
							sb.append(WATER_SYMBOL);
					}
					
					else
						sb.append(NOTSEEN_SYMBOL);
				}
			}
			
			if(i < (size - 1))
				sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * @return string del board
	 * Devuelve una cadena con el tamaño del board, el numero de barcos y el numero de barcos destruidos
	 */
	public String toString() {
		return "Board " + size + "; crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
	}
}