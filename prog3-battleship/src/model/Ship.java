package model;

import java.util.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para controlar los valores de cada barco, crearlos, posicionarlos, saber su posición
 * y su estado (No hit, hit o shot down), saber su orientación y su forma (shape), además de poder obtener cada 
 * uno de sus elementos como symbol, name, posición, etc.
 */
public class Ship {
	
	/**
	 * @param BOUNDING_SQUARE_SIZE tamaño de la caja de colisiones del ship
	 */
	private static final int BOUNDING_SQUARE_SIZE = 5;
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
	 * @param shape distintas formas que puede tener el barco
	 */
	private int shape[][] = new int[][] {
          { 0, 0, 0, 0, 0,               // NORTH    ·····
            0, 0, 1, 0, 0,               //          ··#··
            0, 0, 1, 0, 0,               //          ··#··
            0, 0, 1, 0, 0,               //          ..#..
            0, 0, 0, 0, 0},              //          ·····

          { 0, 0, 0, 0, 0,               // EAST     ·····
            0, 0, 0, 0, 0,               //          ·····
            0, 1, 1, 1, 0,               //          ·###·
            0, 0, 0, 0, 0,               //          ·····
            0, 0, 0, 0, 0},              //          ·····

          { 0, 0, 0, 0, 0,               // SOUTH    ·····
            0, 0, 1, 0, 0,               //          ··#··
            0, 0, 1, 0, 0,               //          ··#··
            0, 0, 1, 0, 0,               //          ..#..
            0, 0, 0, 0, 0},              //          ·····

          { 0, 0, 0, 0, 0,               // WEST     ·····
            0, 0, 0, 0, 0,               //          ·····
            0, 1, 1, 1, 0,               //          ·###·
            0, 0, 0, 0, 0,               //          ·····
            0, 0, 0, 0, 0}				 //          ·····
	}; 
	
	/**
	 * @param or orientación del barco
	 */
	private Orientation or;
	/**
	 * @param pos posición del barco
	 */
	private Coordinate pos;            
	
	/**
	 * @param o Orientación
	 * @param s Símbolo
	 * @param n Nombre
	 * Constructor por parámetros
	 */
	public Ship(Orientation o, char s, String n) {
		name = n;
		symbol = s;
		or = o;
	}
	
	/**
	 * @return null si no tiene posicion o Coordinate(pos) si tiene posicion
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
		pos = position;
	}
	
	/**
	 * @return el nombre del Ship
	 * Devuelve el nombre del Ship que llama a getName()
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return orientacion
	 * Devuelve la orientación del Ship que llama a getOrientation()
	 */
	public Orientation getOrientation() {
		return or;
	}
	
	/**
	 * @return simbolo
	 * Devuelve el simbolo del Ship que llama a getSymbol()
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * @return shape del Ship
	 * Devuelve el shape del Ship que llama a la funcion getShape()
	 */
	public int[][] getShape(){
		return shape;
	}
	
	/**
	 * @param c Coordenada que recibe
	 * @return su ShapeIndex
	 * Devuelve el ShapeIndex de la coordenada que recibe por parametros del Ship que llama a
	 * la función getShapeIndex()
	 */
	public int getShapeIndex(Coordinate c) {
		return c.get(1) * BOUNDING_SQUARE_SIZE + c.get(0);
	}
	
	/**
	 * @param c Coordenada que recibe
	 * @return Set de Coordinates
	 * Recibe un Coordinate y devuelve la posición del Ship sobre el tablero respecto de esas coordenadas
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate c){
		Set<Coordinate> absPositions = new HashSet<Coordinate>();
		
		for(int i = 0 ; i < shape[or.ordinal()].length ; i++) {
			if(shape[or.ordinal()][i] == CRAFT_VALUE || shape[or.ordinal()][i] == HIT_VALUE) {
				int insideShapeX = i % BOUNDING_SQUARE_SIZE;
				int insideShapeY = i / BOUNDING_SQUARE_SIZE;
				
				absPositions.add(new Coordinate(insideShapeX + c.get(0), insideShapeY + c.get(1)));
			}
		}
		
		return absPositions;
	}
	
	/**
	 * @return Set de Coordinates
	 * Utiliza la coordenada del Ship y llama a getAbsolutePositions con dicha Coordenada y devuelve la posición sobre el tablero 
	 * respecto de esas coordenadas
	 */
	public Set<Coordinate> getAbsolutePositions(){
		return this.getAbsolutePositions(new Coordinate(this.getPosition()));
	}
	
	/**
	 * @param c Coordenada absoluta
	 * @return true si es hit, false si no
	 * Recibe una coordenada absoluta, si en ella se encuentra un barco que no ha sido alcanzado antes en esa posicion, 
	 * actualiza su estado y devuelve true, si ya fue alcanzado o no hay barco devuelve false.
	 */
	public boolean hit(Coordinate c) {
		if(this.getPosition() == null)
			return false;
		
		Set<Coordinate> Coords = new HashSet<Coordinate>();
		Coords = this.getAbsolutePositions();
		
		if(Coords.contains(c)) {
			int pos = this.getShapeIndex(new Coordinate(c.get(0) - this.getPosition().get(0), c.get(1) - this.getPosition().get(1)));
			
			if(shape[or.ordinal()][pos] == CRAFT_VALUE) {
				shape[or.ordinal()][pos] = HIT_VALUE;
				return true;
			}
			
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
	 * Comprueba si un barco en esa coordenada ha sido alcanzado, si ha sido alcanzado devuelve true y si no devuelve false
	 */
	public boolean isHit(Coordinate c) {
		if(this.getPosition() == null)
			return false;
		
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
					int pos = this.getShapeIndex(new Coordinate(j-1, i-1));
					
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
}