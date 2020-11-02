package model.ship;

import java.util.*;
import model.*;

public class Coordinate2D extends Coordinate{
	/**
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * Constructor por parametros de Coordenadas 2D
	 */
	public Coordinate2D(int x, int y) {
		super(2, x, y);
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
		
		for(int i = -1 ; i < 2 ; i++) 
			for(int j = -1 ; j < 2 ; j++) 
				if(i != 0 || j != 0) 
					coords.add(CoordinateFactory.createCoordinate(i, j));
		
		return coords;
	}
	
	/**
	 * @return las coordenadas del objeto Coordinate en formato (X, Y)
	 * Coge las dos coordenadas del objeto Coordinate y las devuelve en forma de String con formato (X, Y)
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