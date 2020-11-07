package model;

import java.util.*;
import model.exceptions.*;
import model.ship.*;
import model.aircraft.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para controlar el tablero de juego de nuestro Battleship. Aqui tendremos el tamaño del tablero, los simbolos que representan el
 * Show(), las casillas que hemos descubierto, y los barcos que hay en el tablero con cada posicion que ocupan, además de poder añadir barcos y sus coordenadas 
 * al tablero, saber el numero de barcos que se han añadido y cuantos se han destruido.
 */
public abstract class Board {
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
	 * @param Board_SEPARATOR separador para el show 
	 */
	public static final char Board_SEPARATOR = '|';
	/**
	 * @param MAX_BOARD_SIZE tamaño maximo del board
	 */
	public static final int MAX_BOARD_SIZE = 20;
	/**
	 * @param MIN_BOARD_SIZE tamaño minimo del board
	 */
	public static final int MIN_BOARD_SIZE = 5;
	
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
		Objects.requireNonNull(size);
		if(size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE)
			throw new IllegalArgumentException();
		
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
	 * checkCoordinate(Coordinate) dependerá de la dimensión del board
	 */
	public abstract boolean checkCoordinate(Coordinate c);
	
	/**
	 * @param craft que se añade 
	 * @param position donde se añade su shape
	 * @return true si se añade faslse si no
	 * @throws InvalidCoordinateException cuando una coordenada esté fuera del board
	 * @throws OccupiedCoordinateException cuando se intente colocar un craft en una posición ya ocupada anteriormente
	 * @throws NextToAnotherCraftException cuando se intente colocar un craft en una posición colindante con otro craft
	 * Recibe un Craft y un Coordinate, si cumple los requisitos se añade el Find en dicha Coordinate y devuelve true, si no lo
	 * cumple devuelve false y no se añade
	 */
	public boolean addCraft(Craft craft, Coordinate position) throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		for(Coordinate c1 : craft.getAbsolutePositions(position))
			if(!this.checkCoordinate(c1))
				throw new InvalidCoordinateException(c1);
		
		for(Coordinate c1 : craft.getAbsolutePositions(position))
			if(board.containsKey(c1))
				throw new OccupiedCoordinateException(c1);
			
		for(Coordinate c : this.getNeighborhood(craft, position))
			if(board.containsKey(c) && board.get(c) != craft)
				throw new NextToAnotherCraftException(c);
		
		craft.setPosition(position);
		numCrafts++;
			
		for(Coordinate c1 : craft.getAbsolutePositions(position))
			board.put(c1, craft);
		
		return true;
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
	 * @return Estado en el que queda la coordenada
	 * @throws InvalidCoordinateException cuando la coordenada no se encuentre dentro del board
	 * @throws CoordinateAlreadyHitException cuando la coordenada ya haya sido alcanzada anteriormente
	 * Se dispara a 1 coordenada, si no hay ningún barco se devuelve WATER, si hay un barco se devuelve HIT, y si era la
	 * ultima casilla sin alcanzar del barco se devuelve DESTROYED
	 */
	public CellStatus hit(Coordinate c) throws InvalidCoordinateException, CoordinateAlreadyHitException{
		if(this.checkCoordinate(c)) {
			seen.add(c);
			
			if(board.containsKey(c)) {
				
				if(board.get(c).isHit(c))
					throw new CoordinateAlreadyHitException(c);
				
				else {
					board.get(c).hit(c);
					
					if(board.get(c).isShotDown()) {
						destroyedCrafts++;
						
						for(Coordinate c1 : this.getNeighborhood(board.get(c)))
							seen.add(c1);
						
						return CellStatus.DESTROYED;
					}
					return CellStatus.HIT;
				}
			}
			else 
				return CellStatus.WATER;
		}
		else
			throw new InvalidCoordinateException(c);
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
		Objects.requireNonNull(craft);
		
		Set<Coordinate> s = new HashSet<Coordinate>();
		Set<Coordinate> c = new HashSet<Coordinate>();
		
		c = craft.getAbsolutePositions(position);
		
		if(c == null)
			return s;
		
		for(Coordinate c1 : c) {
			Set<Coordinate> c2 = new HashSet<Coordinate>();
			c2 = c1.adjacentCoordinates();
			
			if(position instanceof Coordinate2D) {
				for(Coordinate c3 : c2)
					if(!s.contains(c3) && c3.get(0) >= 0 && c3.get(0) < size && c3.get(1) >= 0 && c3.get(1) < size)
						s.add(c3);
			}
			
			else if(position instanceof Coordinate3D) {
				for(Coordinate c3 : c2)
					if(!s.contains(c3) && c3.get(0) >= 0 && c3.get(0) < size && c3.get(1) >= 0 && c3.get(1) < size && c3.get(2) >= 0 && c3.get(2) < size)
						s.add(c3);
			}
		}
		
		for(Coordinate c1 : c)
			s.remove(c1);
			
		return s;
	}
	
	/**
	 * @param craft Barco del que queremos el neighborhood
	 * @return neighborhood
	 * Recibe un barco sin Coordiangte y llama a neighborhood con el mismo barco pero usando como posición la del propio barco
	 */
	public Set<Coordinate> getNeighborhood(Craft craft){
		Objects.requireNonNull(craft);
		return this.getNeighborhood(craft, craft.getPosition());
	}
	
	/**
	 * @param unveil Modo de vision
	 * @return El tablero de forma "grafica"
	 * Recibe el parametro que indica si se ve como el dueño o el adversario, y segun ese parametro se muestran
	 * todas las posiciones del board o solo las que ha visto el adversario y tapando las que no ha visto con
	 * NOTSEEN_SYMBOL que equivale a "?"
	 * el show dependerá de si es es un Board2D o un Board3D
	 */
	public abstract String show(boolean unveil);
	
	/**
	 * @return string del board
	 * Devuelve una cadena con el tamaño del board, el numero de barcos y el numero de barcos destruidos
	 */
	public String toString() {
		return "Board " + size + "; crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
	}
}