package model;

import static org.junit.Assert.*;

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
		cd2 = new Coordinate2D(-10,7);
		cd3 = new Coordinate3D(15, 8, -2);
	}

	/* Comprueba set y get modificando algunas de las Coordinates creadas en setUp() 
	 */
	@Test
	public void testSetGet() {
		Coordinate c2 = new Coordinate2D((Coordinate2D)cd2);
		Coordinate c3 = new Coordinate3D((Coordinate3D)cd3);
		
		cd2.set(0, 15);
		cd2.set(1, 25);
		assertTrue(cd2.get(0) == 15);
		assertTrue(cd2.get(1) == 25);
		assertNotEquals(cd2, c2);
		
		cd3.set(0, 15);
		cd3.set(1, 25);
		cd3.set(2, 35);
		assertTrue(cd3.get(0) == 15);
		assertTrue(cd3.get(1) == 25);
		assertTrue(cd3.get(2) == 35);
		assertNotEquals(cd3, c3);
	}
	
	/* Comprueba que set lanza IllegalArgumentException cuando el componente
	 * no es correcto
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSetIllegalArgumentException() {
		try {
			cd2.set(9, 5);
			fail("Error: No se lanzó IllegalArgumentException");
		}
		catch(IllegalArgumentException e) {
			cd3.set(-3, 5);
		}
	}
	
	/* Comprueba que get lanza IllegalArgumentException cuando el componente
	 * no es correcto
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testGetIllegalArgumentException() {
		try {
			cd2.get(9);
			fail("Error: No se lanzó IllegalArgumentException");
		}
		catch(IllegalArgumentException e) {
			cd3.get(-3);
		}
	}
	
	/* Comprobación de la sumas entre coordenadas de dimensiones distintas*/
	@Test
	public void testAdd2Dand3D() {
		Coordinate aux2d = new Coordinate2D (5,15);
		Coordinate aux3d = new Coordinate3D (5,15,-2);
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
		Coordinate aux2d = new Coordinate2D (-25,-1);
		Coordinate aux3d = new Coordinate3D (25,1,-2);
		assertEquals ("c2+c3", aux2d, cd2.subtract(cd3));
		assertEquals ("c3+c2", aux3d, cd3.subtract(cd2));
		assertNotEquals ("aux2d!=cd2", aux2d, cd2);
		assertNotEquals ("aux3d!=cd3", aux2d, cd3);
	}

	/* Comprueba que al intentar restar a una Coordinate el valor null, se lanza
	 * la excepción NullPointerException 
	 */
	@Test(expected=NullPointerException.class)
	public void testSubtractNullPointerException() {
		try {
			cd2.subtract(null);
			fail ("Error: No se lanzó la excepción NullPointerException");
		} catch (NullPointerException e) {
			cd3.subtract(null);
		}
	}
}