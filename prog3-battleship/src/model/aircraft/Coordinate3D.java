package model.aircraft;

import java.util.*;
import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para poder controlar las coordenadas de tres dimensiones que utilizaremos a lo largo de las practicas, además refinará
 * la clase Coordinate.java en los constructores y las clases copy(), adjacentCoordinates() y toString() para las coordenadas en tres dimensiones, 
 * que tendrán las componentes x, y, z. 
 */
public class Coordinate3D extends Coordinate{
	/**
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @param z Coordenada Z
	 * Constructor por parámetros de coordenadas 3D
	 */
	public Coordinate3D(int x, int y, int z) {
		super(3);
		this.set(0, x);
		this.set(1, y);
		this.set(2, z);
	}
	
	/**
	 * @param c Coordenada que se va a copiar
	 * Constructor de copia de Coordenadas 3D
	 */
	public Coordinate3D(Coordinate c) {
		super(c);
	}
	
	/**
	 * @return copia de la coordenada
	 * Devuelve una copia de la coordenada que llama a copy()
	 */
	public Coordinate copy() {
		return new Coordinate3D(this);
	}
	
	/**
	 * @return Set de coordenadas
	 * Devuelve las coordenadas 3D adjacentes a la coordenada que llama a adjacentCoordinates()
	 */
	public Set<Coordinate> adjacentCoordinates(){
		Set<Coordinate> coords = new HashSet<Coordinate>();
		
		for(int x = -1 ; x < 2 ; x++)
			for(int y = -1 ; y < 2 ; y++)
				for(int z = -1 ; z < 2 ; z++)
					if(x != 0 || y != 0 || z != 0)
						coords.add(CoordinateFactory.createCoordinate(this.get(0)+x, this.get(1)+y, this.get(2)+z));
		
		return coords;
	}
	
	/**
	 * @return las coordenadas del objeto Coordinate en formato (X, Y, Z)
	 * Coge las dos coordenadas del objeto Coordinate3D y las devuelve en forma de String con formato (X, Y, Z)
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("(");
		
		for(int i = 0 ; i < 3 ; i++) {
			sb.append(this.get(i));
			
			if(i != 2)
				sb.append(", ");
		}
		
		sb.append(")");
		
		return sb.toString();
	}
}