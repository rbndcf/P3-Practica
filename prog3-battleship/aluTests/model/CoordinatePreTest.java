package model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

public class CoordinatePreTest {
	Coordinate cd2, cd3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		cd2 = new Coordinate2D(-10, 7);
		cd3 = new Coordinate3D(15, 8, -2);
	}

	/* Comprueba set y get modificando algunas de las Coordinates creadas en setUp() 
	 */
	@Test
	public void testSetGet() {
		for(int i = 0; i<5; i++) {
			int value2D = 0;
			try {
				value2D = cd2.get(i);
			} catch(IllegalArgumentException ed21) {
				System.err.println(ed21.toString());
			}
			try {
				value2D++;
				cd2.set(i, value2D);
			} catch(IllegalArgumentException ed22) {
				System.err.println(ed22.toString());
			}
			
			int value3D = 0;
			try {
				value3D = cd3.get(i);
			} catch(IllegalArgumentException ed31) {
				System.err.println(ed31.toString());
			}
			try {
				value3D++;
				cd3.set(i, value3D);
			} catch(IllegalArgumentException ed32) {
				System.err.println(ed32.toString());
			}
		}
		//fail("Realiza la comprobación del correcto funcionamiento de set y get");
	}
	
	/* Comprueba que set lanza IllegalArgumentException cuando el componente
	 * no es correcto
	 */
	@Test
	public void testSetIllegalArgumentException() {
		try {
			cd2.set(5, 5);
		} catch(IllegalArgumentException e) {
			System.err.println(e.toString());
		}
		//fail("Realiza el test SetIllegalArgumentException");
	}
	
	/* Comprueba que get lanza IllegalArgumentException cuando el componente
	 * no es correcto
	 */
	@Test
	public void testGetIllegalArgumentException() {
		try {
			@SuppressWarnings("unused")
			int value = cd2.get(5);
		} catch(IllegalArgumentException e) {
			System.err.println(e.toString());
		}
		//fail("Realiza el test SetIllegalArgumentException");
	}
	
	/* Comprobación de la sumas entre coordenadas de dimensiones distintas*/
	@Test
	public void testAdd2Dand3D() {
		Coordinate aux2d = new Coordinate2D (5, 15);
		Coordinate aux3d = new Coordinate3D (5, 15, -2);
		assertEquals ("c2+c3", aux2d, cd2.add(cd3));
		assertEquals ("c3+c2", aux3d, cd3.add(cd2));
		assertNotEquals ("aux2d!=cd2", aux2d, cd2);
		assertNotEquals ("aux3d!=cd3", aux2d, cd3);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddNullPointerException() {
		try {
			cd2.add(null);
			fail ("Error: No se lanzó la excepción NullPointerException");
		} catch (NullPointerException e) {
			cd3.add(null);
		}
	}
	
	/* Comprueba la correcta resta entre Coordinates de distinta dimensión */
	@Test
	public void testsubtract2Dand3D() {
		// -10, 7
		// 15, 8, -2
		Coordinate aux2d = new Coordinate2D (-25, -1);
		Coordinate aux3d = new Coordinate3D (25, 1, -2);
		assertEquals ("c2+c3", aux2d, cd2.subtract(cd3));
		assertEquals ("c3+c2", aux3d, cd3.subtract(cd2));
		assertNotEquals ("aux2d!=cd2", aux2d, cd2);
		assertNotEquals ("aux3d!=cd3", aux2d, cd3);
		//fail("Realiza el test");
	}

	/* Comprueba que al intentar restar a una Coordinate el valor null, se lanza
	 * la excepción NullPointerException 
	 */
	@Test(expected=NullPointerException.class)
	public void testSubtractNullPointerException() {
		try {
			cd2.subtract(null);
		} catch(NullPointerException npe) {
			cd3.subtract(null);
		}
		//fail("Realiza el test");
	}
	
	/*
	 * Comprueba que devuelve las coordenadas vecinas en 2D correctas:
	 */
	@Test
	public void testAdjacent2D() {
		Coordinate c = new Coordinate2D(-3,5);
		Set<Coordinate> setcoord = c.adjacentCoordinates();
		for (int i=-1; i<2; i++) 
			for (int j=-1; j<2; j++) 
		      if ((i==0)&&(j==0))
		    	  assertFalse(setcoord.contains(new Coordinate2D(c.get(0)+i, c.get(1)+j)));
		      else
		    	  assertTrue(setcoord.contains(new Coordinate2D(c.get(0)+i, c.get(1)+j)));
		//fail("pdte implementar");
	}
	
	/*
	 * Comprueba que devuelve las coordenadas vecinas en 3D correctas:
	 */
	@Test
	public void testAdjacent3D() {
		Coordinate c = new Coordinate3D(-3, 5, 7);
		Set<Coordinate> setcoord = c.adjacentCoordinates();
		for(int k = -1; k<2; k++) {
			for (int i=-1; i<2; i++) 
				for (int j=-1; j<2; j++) 
			      if ((i==0)&&(j==0)&&(k==0))
			    	  assertFalse(setcoord.contains(new Coordinate3D(c.get(0)+i, c.get(1)+j, c.get(2)+k)));
			      else
			    	  assertTrue(setcoord.contains(new Coordinate3D(c.get(0)+i, c.get(1)+j, c.get(2)+k)));
		}
		//fail("pdte implementar");
	}
	
	/*
	 * Comprueba que devuelve las coordenadas vecinas en 3D correctas:
	 */
	@Test
	public void testAdjacent3D_2() {
		Coordinate c = new Coordinate3D(0, 0, 0);
		Set<Coordinate> setcoord = c.adjacentCoordinates();
		Set<Coordinate> esperades = new HashSet<Coordinate> ();
		
		// A la altura:
		esperades.add(new Coordinate3D(-1, -1, 0));
		esperades.add(new Coordinate3D(0, -1, 0));
		esperades.add(new Coordinate3D(1, -1, 0));
		esperades.add(new Coordinate3D(-1, 0, 0));
		// esperades.add(new Coordinate3D(0, 0, 0));
		esperades.add(new Coordinate3D(1, 0, 0));
		esperades.add(new Coordinate3D(-1, 1, 0));
		esperades.add(new Coordinate3D(0, 1, 0));
		esperades.add(new Coordinate3D(1, 1, 0));
		
		// Baix:
		esperades.add(new Coordinate3D(-1, -1, -1));
		esperades.add(new Coordinate3D(0, -1, -1));
		esperades.add(new Coordinate3D(1, -1, -1));
		esperades.add(new Coordinate3D(-1, 0, -1));
		esperades.add(new Coordinate3D(0, 0, -1));
		esperades.add(new Coordinate3D(1, 0, -1));
		esperades.add(new Coordinate3D(-1, 1, -1));
		esperades.add(new Coordinate3D(0, 1, -1));
		esperades.add(new Coordinate3D(1, 1, -1));
		
		// Dalt:
		esperades.add(new Coordinate3D(-1, -1, 1));
		esperades.add(new Coordinate3D(0, -1, 1));
		esperades.add(new Coordinate3D(1, -1, 1));
		esperades.add(new Coordinate3D(-1, 0, 1));
		esperades.add(new Coordinate3D(0, 0, 1));
		esperades.add(new Coordinate3D(1, 0, 1));
		esperades.add(new Coordinate3D(-1, 1, 1));
		esperades.add(new Coordinate3D(0, 1, 1));
		esperades.add(new Coordinate3D(1, 1, 1));
		
		assertEquals("E1", esperades.size(), setcoord.size());
		assertEquals("E2", esperades, setcoord);
		
		//fail("pdte implementar");
	}

}