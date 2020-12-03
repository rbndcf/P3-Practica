package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

public class CoordinateFactoryPreTest {
	Coordinate coor2D, coor3D;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		coor2D = new Coordinate2D(3, 2);
		coor3D = new Coordinate3D(3, 2, 1);
	}

	/* Crea coordenadas correctas con el método createCoordinate y comprueba 
	 * que se han creado bien
	 */
	@Test
	public void testCreateCoordinateOk() {
		assertEquals("Error 1 en testCreateCoordinateOk", coor2D, CoordinateFactory.createCoordinate(new int[] {3, 2}));
		assertEquals("Error 2 en testCreateCoordinateOk", coor3D, CoordinateFactory.createCoordinate(new int[] {3, 2, 1}));
	}
	
	/* Comprueba que en los distintos casos de creación de coordenadas incorrectas
	 * createCoordinateException lanza la excepción IllegalArgument exception
	 */
	@Test
	public void testCreateCoordinateException() {
		try {
			CoordinateFactory.createCoordinate(-1);
			fail("Error: debió lanzarse la excepción IllegalArgumentException");
		} catch (IllegalArgumentException e1) {
			System.err.println(e1.toString());
		}
		
		try {
			CoordinateFactory.createCoordinate(new int[] {0, 0, 0, 0, 0});
			fail("Error: debió lanzarse la excepción IllegalArgumentException");
		} catch (IllegalArgumentException e2) {
			System.err.println(e2.toString());
		}
	}

}