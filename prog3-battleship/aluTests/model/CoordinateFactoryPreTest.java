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

	//TODO
	/* Crea coordenadas correctas con el método createCoordinate y comprueba 
	 * que se han creado bien
	 */
	@Test
	public void testCreateCoordinateOk() {
		fail ("Realiza el test createCoordinateOk");
	}
	
	//TODO
	/* Comprueba que en los distintos casos de creación de coordenadas incorrectas
	 * createCoordinateException lanza la excepción IllegalArgument exception
	 */
	@Test
	public void testCreateCoordinateException() {
		try {
		   CoordinateFactory.createCoordinate(-1);
		   fail("Error: debió lanzarse la excepción IllegalArgumentException");
		} catch (IllegalArgumentException e1) {
			fail ("comprueba otro caso más");
		}
	}

}
