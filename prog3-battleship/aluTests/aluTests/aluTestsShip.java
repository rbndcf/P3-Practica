package aluTests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Coordinate; 
import model.Ship; 
import model.Orientation;

public class aluTestsShip {
	final static int BOUNDING_SQUARE_SIZE = 5;
	static ArrayList<Coordinate> north, east, south, west;
    static String sNorth, sEast, sSouth, sWest;
	Ship bergantin, goleta, fragata, galeon;
    final static int shape[][] = new int[][] {
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,
	    	0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    0, 1, 1, 1, 0,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 1, 0, 0,	
		    0, 0, 1, 0, 0,	
		    0, 0, 1, 0, 0,
		    0, 0, 0, 0, 0},
		  { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
		    0, 1, 1, 1, 0,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0}}; 
		
		    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		    //Coordinates relativas de las distintas orientaciones de un Ship
			north = new ArrayList<Coordinate>();
			east = new ArrayList<Coordinate>();
			south = new ArrayList<Coordinate>();
			west = new ArrayList<Coordinate>();
			for (int i=1; i < 4; i++) {
				north.add(new Coordinate(2,i));
				east.add(new Coordinate(i,2));
				south.add(new Coordinate(2,i));
				west.add(new Coordinate(i,2));
			}
			
			 sEast = "Bergantín (EAST)\n -----\n|     |\n|     |\n| BBB |\n|     |\n|     |\n -----";
			 sNorth ="Goleta (NORTH)\n -----\n|     |\n|  G  |\n|  G  |\n|  G  |\n|     |\n -----";
			 sSouth ="Galeón (SOUTH)\n -----\n|     |\n|  A  |\n|  A  |\n|  A  |\n|     |\n -----";
			 sWest = "Fragata (WEST)\n -----\n|     |\n|     |\n| FFF |\n|     |\n|     |\n -----";
	}	    
	
	@Before
	public void setUp() throws Exception {
		bergantin = new Ship(Orientation.EAST,'B',"Bergantín");
		goleta = new Ship(Orientation.NORTH,'G',"Goleta");
		fragata = new Ship(Orientation.WEST,'F',"Fragata");
		galeon = new Ship(Orientation.SOUTH,'A',"Galeón");
	}

	/* Comprueba que la posición inicial de un Ship es null.
	 * Comprueba también que getPosition hace copia defensiva. Para ello:
	 * 1- Posiciona el Ship en una Coordinate concreta. 
	 * 2- Comprueba que la posición del Ship y la Coordinate son iguales, pero no
	 *    tienen la misma referencia.
	 */
	@Test
	public void testGetPosition() {
		assertNull(bergantin.getPosition());
		
		bergantin.setPosition(new Coordinate(1,1));
		
		assertEquals(bergantin.getPosition(), new Coordinate(1,1));
	}

	/* Se comprueba que getName() es correcto. 
	 */
	@Test
	public void testGetName() {
		assertEquals ("Bergantín",bergantin.getName());
		assertEquals ("Fragata",fragata.getName());
	}

	/* Se comprueba que getOrientation() es correcto.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals (Orientation.NORTH, goleta.getOrientation());
		assertEquals (Orientation.EAST, bergantin.getOrientation());
		assertEquals (Orientation.SOUTH, galeon.getOrientation());
		assertEquals (Orientation.WEST, fragata.getOrientation());
	}

	/* Se comprueba que getSymbol() es correcto.
	 */
	@Test
	public void testGetSymbol() {
		assertEquals ('B',bergantin.getSymbol());
		assertEquals ('G',goleta.getSymbol());
		assertEquals ('A',galeon.getSymbol());
		assertEquals ('F',fragata.getSymbol());
	}

	/* Se comprueba que la matriz shape del alumno es correcta 
	 */
	@Test
	public void testGetShape() {
		int [][] shapeAux = goleta.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals("shape["+i+"]["+j+"]==shapeAux["+i+"]["+j+"]", shape[i][j],shapeAux[i][j]);
	}

	/* Comprueba, para todas las coordenadas relativas, que getShapeIndex(Coordinate c):
	 * 1- Devuelve un valor entre 0 y 24 (ambos inclusives)
	 * 2- Los correspondientes valores dentro de shape[][] para las distintas orientaciones y 
	 *    los distintos valores devueltos por getShapeIndex, son correctos.
	 */
	@Test
	public void testGetShapeIndex() {
		for(int i = 0 ; i < BOUNDING_SQUARE_SIZE ; i++) {
			for(int j = 0 ; j < BOUNDING_SQUARE_SIZE ; j++) {
				Coordinate c = new Coordinate(i, j);
				
				if(bergantin.getShapeIndex(c) > 24 || bergantin.getShapeIndex(c) < 0)
					fail("Valores incorrectos");
				
				int SIcheck = j * BOUNDING_SQUARE_SIZE + i;
				assertEquals(bergantin.getShapeIndex(c), SIcheck);
			}
		}
	}

	/* Comprueba que las posiciones absolutas que devuelve el método 
	 * getAbsolutePositions(Coordinate) de un barco con orientación NORTH 
	 * a partir de una Coordinate son correctas. Ten en cuenta que el 
	 * ArrayList estático 'north', declarado al principio, contiene 
	 * las Coordinates relativas del barco para la orientación NORTH.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		
		Coordinate c1 = new Coordinate(13,27);
		
		goleta.setPosition(new Coordinate(10,24));
		
		Set<Coordinate> pos = goleta.getAbsolutePositions(c1);
		
		assertTrue(pos.contains(new Coordinate(15, 28)));
		assertTrue(pos.contains(new Coordinate(15, 29)));
		assertTrue(pos.contains(new Coordinate(15, 30)));
		
		
		Set<Coordinate> pos2 = goleta.getAbsolutePositions();
		
		assertTrue(pos2.contains(new Coordinate(12, 25)));
		assertTrue(pos2.contains(new Coordinate(12, 26)));
		assertTrue(pos2.contains(new Coordinate(12, 27)));
	}
	
	/* Se dispara a un Ship que todavía no ha sido posicionado. Se comprueba que
	 * hit devuelve false
	 */
	@Test
	public void testHitShipPositionNull() {
		Coordinate c1 = new Coordinate(2,1);
		assertFalse(goleta.hit(c1));
	}
	
	/* Posiciona un Ship en una Coordinate y realiza disparos al agua.
	 * Comprueba que hit devuelve siempre false
	 */
	@Test
	public void testHitWater() {
		goleta.setPosition(new Coordinate(0,0));
		
		assertFalse(goleta.hit(new Coordinate(0,0)));
		assertFalse(goleta.hit(new Coordinate(0,1)));
		assertFalse(goleta.hit(new Coordinate(0,2)));
		assertFalse(goleta.hit(new Coordinate(0,3)));
		assertFalse(goleta.hit(new Coordinate(0,4)));

		assertFalse(goleta.hit(new Coordinate(1,0)));
		assertFalse(goleta.hit(new Coordinate(1,1)));
		assertFalse(goleta.hit(new Coordinate(1,2)));
		assertFalse(goleta.hit(new Coordinate(1,3)));
		assertFalse(goleta.hit(new Coordinate(1,4)));

		assertFalse(goleta.hit(new Coordinate(2,0)));
		assertFalse(goleta.hit(new Coordinate(2,4)));

		assertFalse(goleta.hit(new Coordinate(3,0)));
		assertFalse(goleta.hit(new Coordinate(3,1)));
		assertFalse(goleta.hit(new Coordinate(3,2)));
		assertFalse(goleta.hit(new Coordinate(3,3)));
		assertFalse(goleta.hit(new Coordinate(3,4)));

		assertFalse(goleta.hit(new Coordinate(4,0)));
		assertFalse(goleta.hit(new Coordinate(4,1)));
		assertFalse(goleta.hit(new Coordinate(4,2)));
		assertFalse(goleta.hit(new Coordinate(4,3)));
		assertFalse(goleta.hit(new Coordinate(4,4)));
		
	}
	
	/* Posiciona un Ship en una Coordinate, y realiza los primeros disparos a 
	 * las posiciones del Ship y comprueba que hit devuelve true. 
	 * Vuelve a disparar a las mismas posiciones y comprueba que hit ahora 
	 * devuelve false.
	 */
	@Test
	public void testHitShip() {
		goleta.setPosition(new Coordinate(0,0));

		assertTrue(goleta.hit(new Coordinate(2,1)));
		assertTrue(goleta.hit(new Coordinate(2,2)));
		assertTrue(goleta.hit(new Coordinate(2,3)));

		assertFalse(goleta.hit(new Coordinate(2,1)));
		assertFalse(goleta.hit(new Coordinate(2,2)));
		assertFalse(goleta.hit(new Coordinate(2,3)));
	}

	/* Comprueba que:
	 * 1- isShotDown() devuelve false tras realizar disparos a todas las posiciones del
	 *    Ship excepto una. 
	 * 2- isShotDown() devuelve true tras disparar a la única posición no dañada.
	 */
	@Test
	public void testIsShotDown2() {
		goleta.setPosition(new Coordinate(0,0));

		goleta.hit(new Coordinate(2,1));
		goleta.hit(new Coordinate(2,2));
		
		assertFalse(goleta.isShotDown());
		
		goleta.hit(new Coordinate(2,3));
		
		assertTrue(goleta.isShotDown());
	}

	/* Comprueba que:	
	 * 1- isHit sobre las Coordinates de un Ship devuelve false.
	 * 2- isHit sobre las Coordinates de un Ship devuelve true después 
	 *    de disparar sobre ellas (hit) 
	 *     
	 */
	@Test
	public void testIsHit() {
		goleta.setPosition(new Coordinate(0,0));

		assertFalse(goleta.isHit(new Coordinate(2,1)));
		assertFalse(goleta.isHit(new Coordinate(2,2)));
		assertFalse(goleta.isHit(new Coordinate(2,3)));

		goleta.hit(new Coordinate(2,1));
		goleta.hit(new Coordinate(2,1));
		goleta.hit(new Coordinate(2,1));

		assertTrue(goleta.isHit(new Coordinate(2,1)));
		assertTrue(goleta.isHit(new Coordinate(2,1)));
		assertTrue(goleta.isHit(new Coordinate(2,1)));
	}

	/* Se comprueba que las salidas de los distintos Ships en distintas orientaciones
	 * son correctas
	 */
	@Test
	public void testToString() {
		assertEquals(sNorth,goleta.toString());
		assertEquals(sSouth,galeon.toString());
		assertEquals(sEast,bergantin.toString());
		assertEquals(sWest,fragata.toString());
	}
}
