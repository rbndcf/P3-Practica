package model;

import model.aircraft.*;
import model.ship.*;

public class CoordinateFactory{
	/**
	 * @param coords Coordenadas
	 * @return Coordinate2D o Coordinate3D
	 * Recibe varias coordenadas y devuelve Coordinate2D o Coordinate3D según la cantidad de estas. O una Excepcion si son menos de 2 o mas de 3 coordenadas
	 */
	public static Coordinate createCoordinate(int... coords) {
		if(coords.length == 2)
			return new Coordinate2D(coords[0], coords[1]);
		
		else if(coords.length == 3)
			return new Coordinate3D(coords[0], coords[1], coords[2]);
		
		else
			throw new IllegalArgumentException();
	}
}
