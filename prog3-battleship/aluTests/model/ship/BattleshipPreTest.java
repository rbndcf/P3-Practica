package model.ship;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.Orientation;
import model.exceptions.CoordinateAlreadyHitException;

public class BattleshipPreTest {
	final static int BOUNDING_SQUARE_SIZE = 5;
	Ship battleshipN, battleshipE, battleshipS, battleshipW;
	static List<Coordinate> north, east, south, west;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,
	    	0, 0, 1, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    0, 1, 1, 1, 1,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,
	    	0, 0, 1, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    0, 1, 1, 1, 1,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0}}; 
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			east = new ArrayList<Coordinate>();
			south = new ArrayList<Coordinate>();
			west = new ArrayList<Coordinate>();
		
			for (int i=1; i < 5; i++) {
				north.add(new Coordinate2D(2,i));
			}
			 sEast = "Battleship (EAST)\n"
		        		+ " -----\n"
		        		+ "|     |\n"
		        		+ "|     |\n"
		        		+ "| OOOO|\n"
		        		+ "|     |\n"
		        		+ "|     |\n"
		        		+ " -----";
				sNorth ="Battleship (NORTH)\n"
						+ " -----\n"
						+ "|     |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ " -----";
				sSouth ="Battleship (SOUTH)\n"
						+ " -----\n"
						+ "|     |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ "|  O  |\n"
						+ " -----";
				sWest = "Battleship (WEST)\n"
						+ " -----\n"
						+ "|     |\n"
						+ "|     |\n"
						+ "| OOOO|\n"
						+ "|     |\n"
						+ "|     |\n"
						+ " -----";
	}		    
		    
		    
		    
	@Before
	public void setUp() throws Exception {
		battleshipN = new Battleship(Orientation.NORTH);
		battleshipE = new Battleship(Orientation.EAST);
		battleshipS = new Battleship(Orientation.SOUTH);
		battleshipW = new Battleship(Orientation.WEST);	
	}
	
	/* Se comprueba la composición entre Ship y Coordinate. Para ello se
	 * crea un objeto Coordinate, posicionamos un Ship a esa Coordinate.
	 * Comprobamos que esa Coordinate y la posición del Ship son iguales.
	 * Modificamos la Coordinate y comprobamos que ésta y la posición del
	 * Ship ya no son iguales 
	 */
	@Test
	public void testSetPosition() {
		Coordinate pos = CoordinateFactory.createCoordinate(7, 4);
	     
		//Comprobamos la composición entre Ship y Coordinate
		battleshipE.setPosition(pos);
		assertEquals (pos, battleshipE.getPosition());
		pos = new Coordinate2D(-2, -24);
		assertNotEquals(pos, battleshipE.getPosition());
		
		
		//Modificamos posición y comprobamos de nuevo
		pos = CoordinateFactory.createCoordinate(-17, -2);
		battleshipE.setPosition(pos);	
		assertEquals(pos, battleshipE.getPosition());
		pos = new Coordinate2D(12, 34);
		assertNotEquals(pos, battleshipE.getPosition());
	}

	/* Se comprueba que la posición inicial de un Ship es null.
	 * Comprobamos que getPosition hace copia defensiva: Para ello
	 * se posiciona el Ship en una Coordinate concreta. Se comprueba 
	 * que la posición del Ship y la Coordinate son iguales, pero no
	 * tienen la misma referencia.
	 */
	@Test
	public void testGetPosition() {
		Coordinate pos = battleshipE.getPosition();
		//Inicialmente la position del ship debe ser null 
		assertNull(pos);
		
		//Comprobamos que getPosition hace copia defensiva
		Coordinate pos1 = CoordinateFactory.createCoordinate(7, 4);
		battleshipE.setPosition(pos1);
		Coordinate pos2 = battleshipE.getPosition();
		assertNotSame (pos1, pos2);
		assertEquals(pos1, pos2);
	}
	
	@Test
	public void testGetName() {
		assertEquals ("Battleship", battleshipE.getName());
		assertEquals ("Battleship", battleshipW.getName());
	}

	/* Comprueba que las orientaciones de los Battleship creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals("E1 en testGetOrientation", Orientation.NORTH, battleshipN.getOrientation());
		assertEquals("E2 en testGetOrientation", Orientation.EAST, battleshipE.getOrientation());
		assertEquals("E3 en testGetOrientation", Orientation.SOUTH, battleshipS.getOrientation());
		assertEquals("E4 en testGetOrientation", Orientation.WEST, battleshipW.getOrientation());
	}

	@Test
	public void testGetSymbol() {
		assertEquals('O', battleshipN.getSymbol());
	}
	
	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = battleshipN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}
	
	/* Se comprueba, para todas las coordenadas relativas, que getShapeIndex(Coordinate):
	 * 1- Devuelve un valor entre 0 y 24 (ambos inclusives)
	 * 2- El correspondiente valor de x dentro de shape[][] para las distintas orientaciones, es correcto.
	 */
	@Test
	public void testGetShapeIndex() {
		Craft cruiserN = new Cruiser(Orientation.NORTH);
		Coordinate c;
		int x;
		for (int i=0; i<BOUNDING_SQUARE_SIZE; i++)
			for (int j=0; j<BOUNDING_SQUARE_SIZE; j++) {
				c = CoordinateFactory.createCoordinate(i, j);
				x = cruiserN.getShapeIndex(c);
				assertTrue ("0<="+x+"<=24", (0<=x) && (x<=24));
				if ( (x==7)||(x==12)||(x==17) ) {
					assertTrue("Shape[NORTH]["+x+"]==1", cruiserN.getShape()[Orientation.NORTH.ordinal()][x]==1);
					assertTrue("Shape[SOUTH]["+x+"]==1", cruiserN.getShape()[Orientation.SOUTH.ordinal()][x]==1);
				}
				else {
					assertTrue("Shape[NORTH]["+x+"]==1", cruiserN.getShape()[Orientation.NORTH.ordinal()][x]==0);
					assertTrue("Shape[SOUTH]["+x+"]==1", cruiserN.getShape()[Orientation.SOUTH.ordinal()][x]==0);
				}
				if ( (x>10)&&(x<14) ) {
					assertTrue("Shape[EAST]["+x+"]==1", cruiserN.getShape()[Orientation.EAST.ordinal()][x]==1);
					assertTrue("Shape[WEST]["+x+"]==1", cruiserN.getShape()[Orientation.WEST.ordinal()][x]==1);
				}
				else {
					assertTrue("Shape[EAST]["+x+"]==1", cruiserN.getShape()[Orientation.EAST.ordinal()][x]==0);
					assertTrue("Shape[WEST]["+x+"]==1", cruiserN.getShape()[Orientation.WEST.ordinal()][x]==0);
				}
				
			}
	}

	/* Comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		Coordinate c1 = CoordinateFactory.createCoordinate(13, 27);
		Set<Coordinate> pos = battleshipN.getAbsolutePositions(c1);
		for (Coordinate c : north)
			assertTrue("Valores Absolutos posiciones c1+" + c, pos.contains(c.add(c1)));
	}
	
	/* Se comprueba que las posiciones absolutas para la orientación EARTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsEast() {
		Coordinate c1 = CoordinateFactory.createCoordinate(0, 0);
		Set<Coordinate> pos = battleshipE.getAbsolutePositions(c1);
		for (Coordinate c: east) {
			assertTrue("Valores Absolutos posiciones East+c1", pos.contains(c.add(c1)));
		}
	}
	
	/* Se comprueba que las posiciones absolutas para la orientación SOUTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsSouth() {
		Coordinate c1 = CoordinateFactory.createCoordinate(300,700);
		Set<Coordinate> pos = battleshipS.getAbsolutePositions(c1);
		for (Coordinate c: south)
			assertTrue("Valores Absolutos posiciones South+c1", pos.contains(c.add(c1)));
	}

	/* Se comprueba que las posiciones absolutas para la orientación WEST a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsWest() {
		Coordinate c1 = CoordinateFactory.createCoordinate(-11,-11);
		Set<Coordinate> pos = battleshipW.getAbsolutePositions(c1);
		for (Coordinate c: east) {
				assertTrue("Valores Absolutos posiciones East+c1", pos.contains(c.add(c1)));
		}
	}
	
	/* Se posiciona varios Ship en una Coordinate.
	 * Se comprueba que sus posiciones absolutas son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsShips() {	// ESTOY AQUI
		
		Coordinate c1 = CoordinateFactory.createCoordinate(119, -123);
		getAbsolutePositionsShip(c1, battleshipN, north);
		getAbsolutePositionsShip(c1, battleshipS, south);
		getAbsolutePositionsShip(c1, battleshipW, west);
		getAbsolutePositionsShip(c1, battleshipE, east);
	} 
	
	/* Se dispara a un Ship que todavía no ha sido posicionado. Se comprueba que
	 * hit devuelve false
	 */
	@Test
	public void testHitShipPositionNull() throws CoordinateAlreadyHitException {
		Coordinate c1 = CoordinateFactory.createCoordinate(2, 1);
		try {
			battleshipN.hit(c1);
		} catch(NullPointerException e) {
			System.out.println("Exepció correcta en testHitShipPositionNull: " + e.toString());
		} catch (Exception e) {
			fail("Exepció incorrecta");
		}
	}
	
	/* Se posiciona un Ship en una Coordinate y se realizan disparos al agua.
	 * Se comprueba que hit devuelve siempre false
	 */
	@Test
	public void testHitWater() throws CoordinateAlreadyHitException {
		Coordinate objective = new Coordinate2D(5, 5);
		battleshipN.setPosition(objective);
		assertFalse("Fallo 1 en testHitWater",	battleshipN.hit(objective));
		assertFalse("Fallo 2 en testHitWater",	battleshipN.hit(objective.add(CoordinateFactory.createCoordinate(1, 0))));
		assertFalse("Fallo 3 en testHitWater",	battleshipN.hit(objective.add(CoordinateFactory.createCoordinate(0, 1))));
		//assertFalse("Has disparado al barco",	goleta.hit(objective.add(new Coordinate(2, 2))));
		//fail ("Realiza el test HitWater");
	}
	
	/* Se posiciona un Ship en una Coordinate, y se realizan primeros disparos a 
	 * las posiciones del Ship y se comprueba que hit devuelve true. Se vuelve a 
	 * disparar a las mismas posiciones y se comprueba que hit ahora devuelve false.
	 */
	@Test
	public void testHitShip() throws CoordinateAlreadyHitException {
		Coordinate c1 = CoordinateFactory.createCoordinate(2,1);
		battleshipN.setPosition(c1);
		for (int j=2; j<5; j++) {
		   assertTrue(battleshipN.hit(CoordinateFactory.createCoordinate(4,j)));
		   try {
			   assertFalse(battleshipN.hit(CoordinateFactory.createCoordinate(4,j)));
		   } catch (CoordinateAlreadyHitException e) {
			   System.err.println(e.toString());
		   }
		}
	}

	/* Se comprueba que:
	 * 1- isShotDown() a un Ship sin posicionar devuelve false.
	 * 2- isShotDown() devuelve false tras posicionar un Ship en una Coordinate.
	 * */
	@Test
	public void testIsShotDown1() {
		Coordinate c1 = CoordinateFactory.createCoordinate(2, 1);
		assertFalse(battleshipE.isShotDown());
		battleshipE.setPosition(c1);
		assertFalse(battleshipE.isShotDown());  
	}
	
	/* Se comprueba que:
	 * 1- isShotDown() devuelve false tras realizar disparos a todas las posiciones del
	 *    Ship excepto una. 
	 * 2- isShotDown() devuelve true tras disparar a la única posición no dañada.
	 * 
	 */
	@Test
	public void testIsShotDown2() throws CoordinateAlreadyHitException {
		Coordinate c1 = CoordinateFactory.createCoordinate(2, 1);
		battleshipE.setPosition(c1);
		for (int j=3; j<7; j++) {
			battleshipE.hit(CoordinateFactory.createCoordinate(j, 3));
		   if (j != 6) assertFalse(battleshipE.isShotDown());
		   else assertTrue(battleshipE.isShotDown());
		}
		
	}

	/* Se comprueba que:
	 * 1- isHit en un Ship no posicionado devuelve false.
	 * 2- isHit sobre una Coordinate en una posición fuera
	 *    de un Ship ya posicionado, devuelve false.
	 */
	@Test
	public void testIsHit1() {
		Coordinate c = new Coordinate2D(2,1);
		//Ship no posicionado
		try {
			this.battleshipE.isHit(c) ;
			fail ("Debió lanzar NullPointerException");
		} catch (NullPointerException e) {
			this.battleshipE.setPosition(c);
			//Ship posicionado. Coordinate c en agua
			assertFalse(this.battleshipE.isHit(c));
		} 
	}
	
	/* Se comprueba que:	
	 * 1- isHit sobre las Coordinates de un Ship devuelve false.
	 * 2- isHit sobre las Coordinates de un Ship devuelve true después 
	 *    de disparar sobre ellas (hit) 
	 *     
	 */
	@Test
	public void testIsHit() throws CoordinateAlreadyHitException {
		Coordinate c = CoordinateFactory.createCoordinate(2, 1);
		battleshipE.setPosition(c);
		//Preguntamos en ship antes de disparar y despues de disparar
		for (int j=3; j<6; j++) {
		   c = CoordinateFactory.createCoordinate(j,3);
		   assertFalse(battleshipE.isHit(c));
		   battleshipE.hit(c);
		   assertTrue(battleshipE.isHit(c));
		}
	}

	/* Comprueba que toString() para cada Battleship creado en el setUp coincide con 
	 * los correspondientes String creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals(sNorth, battleshipN.toString());
		assertEquals(sSouth, battleshipS.toString());
		assertEquals(sEast, battleshipE.toString());
		assertEquals(sWest, battleshipW.toString());
	}

	//FUNCIONES DE APOYO
	
	void getAbsolutePositionsShip(Coordinate cpos, Ship ship, List<Coordinate> orient) {
		ship.setPosition(cpos);
		Set<Coordinate> pos = ship.getAbsolutePositions();
		for (Coordinate c : orient)
			assertTrue("Valores Absolutos posiciones c1+" + c, pos.contains(c.add(cpos)));
	}
}