package model;

import org.junit.Before;
import org.junit.Test;

import model.aircraft.Bomber;
import model.ship.Carrier;

public class CraftPreTest {

	Craft carrier, bomber;
	
	@Before
	public void setUp() throws Exception {
		carrier = new Carrier(Orientation.EAST);
		bomber = new Bomber(Orientation.SOUTH);
	}

	//TODO 
	/* Realiza los tests en los que se comprueba el lanzamiento de
	 * la excepción NullPointerException en los métodos getShapeIndex,
	 * getAbsolutePositions(Coordinate) y getAbsolutePositions
	 */

}
