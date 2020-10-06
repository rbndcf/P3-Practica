package model;

import java.util.*;

import model.Orientation;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para 
 */

public class Ship {
	
	private static final int BOUNDING_SQUARE_SIZE = 5;
	private static final int HIT_VALUE = -1;
	private static final int CRAFT_VALUE = 1;
	private char symbol;
	private String name;
	
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
	
	private Orientation or;
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
		if(pos == null)
			return null;
		else
			return new Coordinate(pos);
	}
	
	/**
	 * @param position del Ship
	 * Posicion en la que esta el barco que llama a setPosition()
	 */
	public void setPosition(Coordinate position) {
		pos = position;
	}
	
	/**
	 * @return el nombre del Ship
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
				int insideShapeX = i % 5;
				int insideShapeY = i / 5;
				
				absPositions.add(new Coordinate(insideShapeX + c.get(0), insideShapeY + c.get(1)));
				
				System.out.println(insideShapeX + " " + insideShapeY);
			}
		}
		
		return absPositions;
	}
	
	public Set<Coordinate> getAbsolutePositions(){
		
	}
	
	public boolean hit(Coordinate c) {
		
	}
	
	public boolean isShotDown() {
		
	}
	
	public boolean isHit(Coordinate c) {
		
	}
	
	public String toString() {
		
	}
}