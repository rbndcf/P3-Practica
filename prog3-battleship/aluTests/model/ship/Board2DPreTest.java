package model.ship;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Orientation;
import model.aircraft.Coordinate3D;
import model.exceptions.BattleshipException;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;

public class Board2DPreTest {
	Ship destroyer, carrier, battleship;
	Board board2D;
	
	static String sboard0, sboard1, sboard2, sboard3;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sboard0 = "??????\n" + 
				  "??????\n" + 
				  "??????\n" + 
				  "??????\n" + 
				  "??????\n" + 
				  "??????";
		
		sboard1 = "O    ®\n"
				+ "O    ®\n"
				+ "O    ®\n"
				+ "O    ®\n"
				+ "     ®\n"
				+ "  ΩΩ  "; 
		
		sboard2 = "•    •\n" + 
				  "•    •\n" + 
				  "•    •\n" + 
				  "•    •\n" + 
				  "     •\n" + 
				  "  ••  ";
		
		sboard3 = "O ?? ®\n" + 
				  "O ?? ®\n" + 
				  "O ?? ®\n" + 
				  "O ?? ®\n" + 
				  "     ®\n" + 
				  "? ΩΩ  ";
	}

	@Before
	public void setUp() throws Exception {
		board2D = new Board2D(6);
		destroyer = new Destroyer(Orientation.EAST);
		carrier = new Carrier(Orientation.SOUTH);
		battleship = new Battleship(Orientation.NORTH);
	}
	
	@Test
	public void testGetSize( ) {
		 assertEquals(6,board2D.getSize());
		 board2D = new Board2D(17);
		 assertEquals(17, board2D.getSize());
	}

	@Test
	public void testCheckCoordinateOk() {
		assertTrue(board2D.checkCoordinate(new Coordinate2D(0,0)));
		assertTrue(board2D.checkCoordinate(new Coordinate2D(0,5)));
		assertTrue(board2D.checkCoordinate(new Coordinate2D(5,0)));
		assertTrue(board2D.checkCoordinate(new Coordinate2D(5,5)));
	}
	
	/* Añade los Crafts correctamente en el board tal como aparecen:
	 *  ------
	 * |O	 ®|
	 * |O    ®|
	 * |O	 ®|
	 * |O	 ®|
	 * |	 ®|
	 * |  ΩΩ  |
	 *  ------
	 */
	@Test
	public void testAddCraftOk() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException  {
		board2D.addCraft(battleship, CoordinateFactory.createCoordinate(new int[] {-2, -1}));
		board2D.addCraft(carrier, CoordinateFactory.createCoordinate(new int[] {3, 0}));
		board2D.addCraft(destroyer, CoordinateFactory.createCoordinate(new int[] {1, 3}));
		assertEquals("E1 en testAddCraftOK", sboard1, board2D.show(true));
		//fail("Realiza el test");		
	}
	
	/* Comprueba checkCoordinate con Coordinates fuera de los límites del
	 * tablero.
	 */
	@Test
	public void testCheckCoordinateOutOfLimits() throws InvalidCoordinateException {
		assertFalse(board2D.checkCoordinate(CoordinateFactory.createCoordinate(new int[] {10, 10})));
		//fail("Realiza el test");
	}
	
	/* Se comprueba que al pasarle una Coordinate3D a checkCoordinate aplicado sobre
	 * un Board2D, se lanza la excepción IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCheckCoordinateException() {
		assertTrue(board2D.checkCoordinate(new Coordinate3D(3,4,0)));
		
	}

	/* Posiciona los Crafts en el tablero según aparecen en testAddCraftOk. 
	 * Compara que board.show(true) genera lo mismo que sboard1 y
	 * board.show(false) lo mismo que sboard0
	 */
	@Test
	public void testShow1() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		testAddCraftOk();
		assertEquals("E1 en testShow1", sboard0, board2D.show(false));
		assertEquals("E2 en testShow1", sboard1, board2D.show(true));
	}
	
	/* Posiciona los Crafts según testAddCraftOk y después dispara sobre ellos 
	 * hundiéndolos todos. Comprueba que show(true) y show(false) coincide
	 * con sboard2 y sboard3 respectivamente.
	 */
	@Test
	public void testShow2() throws InvalidCoordinateException, CoordinateAlreadyHitException, OccupiedCoordinateException, NextToAnotherCraftException {
		testAddCraftOk();
		
		Set<Coordinate> absCarrier = carrier.getAbsolutePositions(CoordinateFactory.createCoordinate(new int[] {3, 0}));
		Set<Coordinate> absDestroyer = destroyer.getAbsolutePositions(CoordinateFactory.createCoordinate(new int[] {1, 3}));
		Set<Coordinate> absBattleship = battleship.getAbsolutePositions(CoordinateFactory.createCoordinate(new int[] {-2, -1}));
		
		for(Coordinate it : absBattleship)
			board2D.hit(it.copy());
		
		for(Coordinate it : absCarrier)
			board2D.hit(it.copy());
		
		for(Coordinate it : absDestroyer) 
			board2D.hit(it.copy());
		
		assertEquals("E1 en testShow2", sboard2, board2D.show(true));
		assertEquals("E2 en testShow2", sboard3, board2D.show(false));
	}
	
	/* Realiza disparos fuera del board y comprueba que se lanza la excepción
	 * InvalidCoordinateException
	 */
	@Test
	public void testHitInvalidCoordinate() throws CoordinateAlreadyHitException, InvalidCoordinateException {
		try {
			board2D.hit(CoordinateFactory.createCoordinate(new int[] {10, 10}));
			fail("Error: debería haber lanzado la excepción InvalidCoordinateException");
		} catch(InvalidCoordinateException e) {
			System.err.println(e.toString());
		}
		//fail("Realiza el test");
	}
	
	/* Se comprueba que al disparar sobre una Coordenada3D se lanza la excepción 
	 * IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testHitIllegalArgument() throws CoordinateAlreadyHitException, InvalidCoordinateException {
				board2D.hit(new Coordinate3D(3,-1, 7));
	}
	
	/* Posiciona los Crafts según testAddCraftOk().
	 * Se intenta añadir un Craft Destroyer (en la imagen inferior aparecen con 'DD' para
	 * indicar dónde se intenta poner) en la que, parte está fuera del tablero y
	 * parte en una posición ocupada por un  Battleship. Se comprueba que se lanza la
	 * excepción InvalidCoordinateException
	 *    ------
	 *   |O	   ®|
	 *   |O    ®|
	 *   |O	   ®|
	 *   DD    ®|
	 *   |	   ®|
	 *   |  ΩΩ  |
	 *    ------
	 */
	@Test
	public void testAddCraftOutOccupied() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		Coordinate2D c=new Coordinate2D(-2,1); 
		testAddCraftOk();
		//fail("Posiciona los Crafts en el board según testAddCraftOk()");
		try {		
			board2D.addCraft(destroyer, c);
			fail("Error: debería haberlanzado la excepción InvalidCoordinateException");
		} catch (InvalidCoordinateException e) {
			assertNotNull (board2D.getCraft(new Coordinate2D(0,3)));
			assertTrue(board2D.getCraft(new Coordinate2D(0,3)).getClass().getName()=="model.ship.Battleship");
		} catch (NextToAnotherCraftException | OccupiedCoordinateException e) {
			fail ("Error. Se esperaba InvalidCoordinateException "
					+ "pero se produjo la excepcion "+e.getClass().getName());
		} 
	}
	
	/* Posiciona los Crafts según testAddCraftOk().
	 * Intenta añadir un Craft Destroyer en la que, parte está fuera del tablero y
	 * parte en una posición próxima a otro Craft del tablero. 
	 * Comprueba que se lanza la excepción InvalidCoordinateException 
	 */
	@Test
	public void testAddCraftOutNextTo() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		testAddCraftOk();
		try {
			board2D.addCraft(destroyer, CoordinateFactory.createCoordinate(new int[] {-1, 3}));
		} catch(BattleshipException e) {
			System.err.println(e.toString());
		}
		//fail("Completa el test");
	}
	
	/* Posiciona los Crafts según testAddCraftOk().
	 * Intenta añadir un Craft Destroyer dentro del tablero en la que, parte está colisionando y
	 * parte en una posición próxima a otro Craft del tablero. 
	 * Comprueba que se lanza la excepción OccupiedCoordinateException
	 */
	@Test
	public void testAddCraftColisionNextTo() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		testAddCraftOk();
		try {
			board2D.addCraft(destroyer, CoordinateFactory.createCoordinate(new int[] {3, 2}));
		} catch(BattleshipException e) {
			System.err.println(e.toString());
		}
		//fail("Completa el test");
	}
	
	/* Posiciona los Crafts según testAddCraftOk().
	 * Intenta añadir un Craft Battleship en la que, parte está fuera del tablero, parte está
	 * colisionando y parte en una posición próxima a otro Craft del tablero. 
	 * Comprueba que se lanza la excepción InvalidCoordinateException
	 */
	@Test
	public void testAddCraftOutColisionNextTo() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		testAddCraftOk();
		try {
			board2D.addCraft(battleship, CoordinateFactory.createCoordinate(new int[] {3, 3}));
		} catch(BattleshipException e) {
			System.err.println(e.toString());
		}
		//fail("Completa el test");
	}
	
}