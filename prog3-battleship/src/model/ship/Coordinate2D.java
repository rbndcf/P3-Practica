package model.ship;

import java.util.*;
import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para poder controlar las coordenadas de dos dimensiones que utilizaremos a lo largo de las practicas, además refinará
 * la clase Coordinate.java en los constructores y las clases copy(), adjacentCoordinates() y toString() para las coordenadas en dos dimensiones, 
 * que solo tengan las componentes x, y. 
 */
public class Coordinate2D extends Coordinate{
	/**
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * Constructor por parametros de Coordenadas 2D
	 */
	public Coordinate2D(int x, int y) {
		super(2);
		this.set(0, x);
		this.set(1, y);
	}
	
	/**
	 * @param c Coordenada que se va a copiar
	 * Constructor de copia de Coordenadas 2D
	 */
	public Coordinate2D(Coordinate2D c) {
		super(c);
	}
	
	/**
	 * @return copia de la coordenada
	 * Devuelve una copia de la coordenada que llama a copy()
	 */
	public Coordinate copy() {
		return new Coordinate2D(this);
	}
	
	/**
	 * @return Set de coordenadas
	 * Devuelve las coordenadas 2D adjacentes a la coordenada que llama a adjacentCoordinates()
	 */
	public Set<Coordinate> adjacentCoordinates(){
		Set<Coordinate> coords = new HashSet<Coordinate>();
		
		for(int y = -1 ; y < 2 ; y++) 
			for(int x = -1 ; x < 2 ; x++) 
				if(x != 0 || y != 0) 
					coords.add(CoordinateFactory.createCoordinate(this.get(0) + x, this.get(1) + y));
		
		return coords;
	}
	
	/**
	 * @return las coordenadas del objeto Coordinate en formato (X, Y)
	 * Coge las dos coordenadas del objeto Coordinate2D y las devuelve en forma de String con formato (X, Y)
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("(");

		for(int i = 0 ; i < 2 ; i++){
			sb.append(this.get(i));

			if(i == 0)
				sb.append(", ");
		}

		sb.append(")");

		return sb.toString();
	}
}