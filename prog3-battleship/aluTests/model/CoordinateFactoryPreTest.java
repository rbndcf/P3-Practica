package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

public class CoordinateFactoryPreTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	/* Crea coordenadas correctas con el método createCoordinate y comprueba 
	 * que se han creado bien
	 */
	@Test
	public void testCreateCoordinateOk() {
		Coordinate c2d = CoordinateFactory.createCoordinate(4, 5);
		Coordinate c3d = CoordinateFactory.createCoordinate(3,4,5);
		
		assertTrue(c2d instanceof Coordinate2D);
		assertTrue(c3d instanceof Coordinate3D);
	}
	
	/* Comprueba que en los distintos casos de creación de coordenadas incorrectas
	 * createCoordinateException lanza la excepción IllegalArgument exception
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCoordinateException() {
		try {
		   CoordinateFactory.createCoordinate(-1);
		   fail("Error: debió lanzarse la excepción IllegalArgumentException");
		} catch (IllegalArgumentException e1) {
			CoordinateFactory.createCoordinate(5, 7, 8, 3);
		}
	}

}
