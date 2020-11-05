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
	//TODO
	@Test
	public void testSetGet() {
		fail("Realiza la comprobación del correcto funcionamiento de set y get");
	}
	
	//TODO
	/* Comprueba que set lanza IllegalArgumentException cuando el componente
	 * no es correcto
	 */
	@Test
	public void testSetIllegalArgumentException() {
		fail("Realiza el test SetIllegalArgumentException");
	}
	
	//TODO
	/* Comprueba que get lanza IllegalArgumentException cuando el componente
	 * no es correcto
	 */
	@Test
	public void testGetIllegalArgumentException() {
		fail("Realiza el test SetIllegalArgumentException");
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
	
	//TODO
	/* Comprueba la correcta resta entre Coordinates de distinta dimensión */
	@Test
	public void testsubtract2Dand3D() {
		fail("Realiza el test");
	}

	//TODO
	/* Comprueba que al intentar restar a una Coordinate el valor null, se lanza
	 * la excepción NullPointerException 
	 */
	@Test
	public void testSubtractNullPointerException() {
		fail("Realiza el test");
	}


}
