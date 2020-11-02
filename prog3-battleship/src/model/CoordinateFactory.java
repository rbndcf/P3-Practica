package model;

import model.aircraft.*;
import model.ship.*;

public class CoordinateFactory{
	public static Coordinate createCoordinate(int... coords) {
		if(coords.length == 2)
			return new Coordinate2D(coords[0], coords[1]);
		
		else if(coords.length == 3)
			return new Coordinate3D(coords[0], coords[1], coords[2]);
		
		else
			throw new IllegalArgumentException();
	}
}
