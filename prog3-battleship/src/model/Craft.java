package model;

import java.util.*;
import model.exceptions.*;
import model.ship.*;
import model.aircraft.*;

/**
 * 
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos como la antigua clase Ship, para poder crear Crafts y controlar tanto su nombre, simbolo y orientación, además de su
 * posición, además esta clase será refinada por sus dos subclases Ship y Aircraft, los cuales manejaran el shape y demás.
 */
/**
 * @author ruben
 *
 */
public abstract class Craft {
	/**
	 * @param HIT_VALUE valor cuando el ship es alcanzado
	 */
	private static final int HIT_VALUE = -1;
	/**
	 * @param CRAFT_VALUE valor cuando el ship no es alcanzado
	 */
	private static final int CRAFT_VALUE = 1;
	/**
	 * @param symbol simbolo del barco
	 */
	private char symbol;
	/**
	 * @param name nombre del barco
	 */
	private String name;
	/**
	 * @param or orientación del barco
	 */
	private Orientation or;
	/**
	 * @param pos posición del barco
	 */
	private Coordinate pos;
	
	/**
	 * @param shape distintas formas que puede tener el barco
	 */
	protected int shape[][];
	
	/**
	 * @param BOUNDING_SQUARE_SIZE tamaño de la caja de colisiones del ship
	 */
	public static final int BOUNDING_SQUARE_SIZE = 5;

	/**
	 * @param o Orientación
	 * @param s Símbolo
	 * @param n Nombre
	 * Constructor por parámetros
	 */
	public Craft(Orientation o, char s, String n) {
		name = n;
		symbol = s;
		or = o;
	}

	/**
	 * @return null si no tiene posicion o pos.copy() si tiene posicion
	 * Devuelve la posición que tiene asignada el Ship que llama a getPosition() o
	 * null si no se le ha asignado posición
	 */
	public Coordinate getPosition() {
		if(pos == null) return null;
		else return pos.copy();
	}

	/**
	 * @param position del Ship
	 * Se establece la posicion en la que esta el barco que llama a setPosition()
	 */
	public void setPosition(Coordinate position) {
		pos = position.copy();
	}

	/**
	 * @return el nombre del Ship
	 * Devuelve el nombre del Ship que llama a getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return orientation
	 * Devuelve la orientación del Ship que llama a getOrientation()
	 */
	public Orientation getOrientation() {
		return or;
	}

	/**
	 * @return symbol
	 * Devuelve el simbolo del Ship que llama a getSymbol()
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * @return shape del Ship
	 * Devuelve el shape del Ship que llama a la funcion getShape()
	 */
	public int[][] getShape() {
		return shape;
	}

	/**
	 * @param c Coordenada que recibe
	 * @return su ShapeIndex
	 * @throws NullPointerException cuando la coordenada c es nula
	 * Devuelve el ShapeIndex de la coordenada que recibe por parametros del Ship que llama a
	 * la función getShapeIndex()
	 */
	public int getShapeIndex(Coordinate c) {
		Objects.requireNonNull(c);
		
		return c.get(1) * BOUNDING_SQUARE_SIZE + c.get(0);
	}

	/**
	 * @param c Coordenada que recibe
	 * @return Set de Coordinates
	 * @throws NullPointerException si la coordenada c es nula
	 * Recibe un Coordinate y devuelve la posición del Ship sobre el tablero respecto de esas coordenadas
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate c) {
		Objects.requireNonNull(c);
		
		Set<Coordinate> absPositions = new HashSet<Coordinate>();
		
		for(int i = 0 ; i < shape[or.ordinal()].length ; i++) {
			if(shape[or.ordinal()][i] == CRAFT_VALUE || shape[or.ordinal()][i] == HIT_VALUE) {
				int insideShapeX = i % BOUNDING_SQUARE_SIZE;
				int insideShapeY = i / BOUNDING_SQUARE_SIZE;
				
				if(c instanceof Coordinate2D)
					absPositions.add(CoordinateFactory.createCoordinate(insideShapeX + c.get(0), insideShapeY + c.get(1)));
				else if(c instanceof Coordinate3D)
					absPositions.add(CoordinateFactory.createCoordinate(insideShapeX + c.get(0), insideShapeY + c.get(1), c.get(2)));
			}
		}
		
		return absPositions;
	}

	/**
	 * @return Set de Coordinates
	 * @throws NullPointerException si el barco no ha sido posicionado
	 * Utiliza la coordenada del Ship y llama a getAbsolutePositions con dicha Coordenada y devuelve la posición sobre el tablero 
	 * respecto de esas coordenadas
	 */
	public Set<Coordinate> getAbsolutePositions() {
		Objects.requireNonNull(this.getPosition());
		return this.getAbsolutePositions(this.getPosition().copy());
	}

	/**
	 * @param c Coordenada absoluta
	 * @return true si es hit, false si no
	 * @throws CoordinateAlreadyHitException cuando la coordenada ya ha sido alcanzada anteriormente
	 * Recibe una coordenada absoluta, si en ella se encuentra un barco que no ha sido alcanzado antes en esa posicion, 
	 * actualiza su estado y devuelve true, si ya fue alcanzado o no hay barco devuelve false.
	 */
	public boolean hit(Coordinate c) throws CoordinateAlreadyHitException{
		Objects.requireNonNull(this.getPosition());
		
		Set<Coordinate> Coords = new HashSet<Coordinate>();
		Coords = this.getAbsolutePositions();
		
		if(Coords.contains(c)) {
			int pos = this.getShapeIndex(CoordinateFactory.createCoordinate(c.get(0) - this.getPosition().get(0), c.get(1) - this.getPosition().get(1)));
			
			if(shape[or.ordinal()][pos] == CRAFT_VALUE) {
				shape[or.ordinal()][pos] = HIT_VALUE;
				return true;
			}
			
			else if(shape[or.ordinal()][pos] == HIT_VALUE)
				throw new CoordinateAlreadyHitException(c);
			
			else return false;	
		}
		
		else return false;
	}

	/**
	 * @return estado del barco
	 * Si el barco ha sido destruido, devuelve true, si no ha sido disparado en todas sus posiciones devuelve false
	 */
	public boolean isShotDown() {
		boolean check = true;
		
		for(int i = 0 ; i < 25 ; i++)
			if(shape[or.ordinal()][i] == CRAFT_VALUE)
				check = false;
		
		return check;
	}

	/**
	 * @param c Coordenada que queremos comprobar
	 * @return Estado de la coordenada
	 * @throws NullPointerException si la coordenada que recibe es nula
	 * Comprueba si un barco en esa coordenada ha sido alcanzado, si ha sido alcanzado devuelve true y si no devuelve false
	 */
	public boolean isHit(Coordinate c) {
		Objects.requireNonNull(c);
		
		int pos = (c.get(1) - this.getPosition().get(1)) * BOUNDING_SQUARE_SIZE + (c.get(0) - this.getPosition().get(0));
		
		if(shape[or.ordinal()][pos] == HIT_VALUE) return true;
		else return false;
	}

	/**
	 * @return cadena de texto
	 * Devuelve una cadena con el nombre, la orientación, y la representacion "grafica" del barco que llama a toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int BOUNDING_SQUARE_SIZE_toString = BOUNDING_SQUARE_SIZE + 2;
		
		sb.append(name + " (" + or + ")");
		
		for(int i = 0 ; i < (BOUNDING_SQUARE_SIZE_toString) ; i++) {
			sb.append("\n");
			
			for(int j = 0 ; j < (BOUNDING_SQUARE_SIZE_toString) ; j++) {
				if(i == 0 || i == BOUNDING_SQUARE_SIZE_toString - 1) {
					if(j == 0)
						sb.append(" ");
					else if(j != BOUNDING_SQUARE_SIZE_toString - 1)
						sb.append("-");
				}
				
				else if(j == 0 || j == BOUNDING_SQUARE_SIZE_toString - 1)
					sb.append("|");
				
				else {
					int pos = this.getShapeIndex(CoordinateFactory.createCoordinate(j-1, i-1));
					
					if(shape[or.ordinal()][pos] == 0)
						sb.append(" ");
					
					else if(shape[or.ordinal()][pos] == CRAFT_VALUE)
						sb.append(symbol);
					
					else if(shape[or.ordinal()][pos] == HIT_VALUE)
						sb.append(Board.HIT_SYMBOL);
				}
			}
		}
		
		return sb.toString();
	}

	/**
	 * @return valor del Craft
	 * getter de Value
	 */
	public abstract int getValue();
}