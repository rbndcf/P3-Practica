package model.aircraft;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.Orientation;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.ship.Battleship;
import model.ship.Board2D;
import model.ship.Carrier;
import model.ship.Coordinate2D;
import model.ship.Destroyer;
import model.ship.Ship;

public class Board3DPreTest {
	Aircraft bomberE, bomberS,fighterW,fighter1S, fighter2S, transportN;
	//Board2D board2D;
	Board board3D;
	final int TAM = 7;
	final int MAX_BOARD_SIZE = 20;
	final int MIN_BOARD_SIZE = 5;
	static String sboard00,sboard01, sboard02, sboard03, sboard04;
	
	Coordinate3D b1, f2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		sboard00 = "???????|???????|???????|???????|???????|???????|???????\n" + 
				  "???????|???????|???????|???????|???????|???????|???????\n" + 
			      "???????|???????|???????|???????|???????|???????|???????\n" + 
				  "???????|???????|???????|???????|???????|???????|???????\n" + 
				  "???????|???????|???????|???????|???????|???????|???????\n" + 
				  "???????|???????|???????|???????|???????|???????|???????\n" + 
				  "???????|???????|???????|???????|???????|???????|???????";
		
		sboard01 = "     ⇄ |       |       |       | ⇄     |       | ⇄     \n" + 
				   "     ⇄ |       |       |       |⇄⇄⇄⇄   |       | ⇄     \n" + 
				   "    ⇄⇄⇄| ⇶⇶    |       |       | ⇄     |       |⇄⇄⇄ ⇋  \n" + 
				   "     ⇄ |  ⇶    |       |   ⇶   |       |       | ⇄  ⇋  \n" + 
				   "       |⇶⇶⇶⇶   |       | ⇶ ⇶ ⇶ |       |       |   ⇋⇋⇋ \n" + 
				   "       |  ⇶    |       | ⇶⇶⇶⇶⇶ |       |       |  ⇋ ⇋ ⇋\n" + 
				   "       | ⇶⇶    |       |   ⇶   |       |       |    ⇋  ";
		
		sboard02 = " ??????|???????| ??????| ??????|???? ??|???????|???????\n" + 
				   "? ?????|???????|? ?????| ??????|???? ??|???????|???????\n" + 
				   "?? ????|???????|?? ????| ??????|???? ??|???????|???????\n" + 
				   "??? ???|???????|??? ???| ??????|???? ??|???????|???????\n" + 
				   "???? ??|???????|???? ??| ??????|???? ??|???????|???????\n" + 
				   "       |???????|????? ?| ??????|???? ??|???????|???????\n" + 
				   "?????? |???????|?????? | ??????|???? ??|???????|???????";
		
		sboard03 = "???? ⇄ |????   |???????|???????|???????|???????|???????\n" + 
				   "???  ⇄ |???    |???????|???????|???????|???????|???????\n" + 
			       "??? ⇄⇄⇄|??•    |???????|???????|???????|???????|???????\n" + 
				   "???  ⇄ |??•    |???????|???????|???????|???????|???????\n" + 
				   "????   |??•?   |???????|???????|???????|???????|???????\n" + 
				   "???????|??•????|???????|???????|???????|???????|???????\n" + 
				   "???????|??•????|???????|???????|???????|???????|???????";
		
		sboard04 = "     • |       |       |       | ⇄     |       | ⇄     \n" + 
				   "     • |       |       |       |⇄⇄⇄⇄   |       | ⇄     \n" + 
				   "    •••| ⇶•    |       |       | ⇄     |       |⇄⇄⇄ ⇋  \n" + 
				   "     • |  •    |       |   ⇶   |       |       | ⇄  ⇋  \n" + 
				   "       |⇶⇶•⇶   |       | ⇶ ⇶ ⇶ |       |       |   ⇋⇋⇋ \n" + 
				   "       |  •    |       | ⇶⇶⇶⇶⇶ |       |       |  ⇋ ⇋ ⇋\n" + 
				   "       | ⇶•    |       |   ⇶   |       |       |    ⇋  ";
	}

	@Before
	public void setUp() throws Exception {
		//board2D = new Board3D(TAM);
		board3D = new Board3D(TAM);
		bomberE = new Bomber(Orientation.EAST);
		bomberS = new Bomber(Orientation.SOUTH);
		fighterW = new Fighter(Orientation.WEST);
		transportN = new Transport(Orientation.NORTH);
		fighter1S = new Fighter(Orientation.SOUTH);
		fighter2S = new Fighter(Orientation.SOUTH);
		
		
	}

	/* Comprueba que al crear un Board3D con tamaño en los límites no produce excepción. 
	 * Y que si sales fuera de ellos se produce la excepción IllegalArgumentException.
	 */
	@Test
	public void testBoard3D() {
		Board board;
		board = new Board3D(MAX_BOARD_SIZE);
		assertEquals (MAX_BOARD_SIZE, board.getSize());
		board = new Board3D(MIN_BOARD_SIZE);
		assertEquals (MIN_BOARD_SIZE, board.getSize());
		try {
			board = new Board3D(50);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		try {
			board = new Board3D(-50);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		//fail ("Comprueba que lanza la excepción IllegalArgumentException con tamaño fuera de los límites");
	}
	
	/* Se comprueba getSize() */
	@Test
	public void testGetSize( ) {
		 assertEquals(7,board3D.getSize());
		 board3D = new Board3D(17);
		 assertEquals(17, board3D.getSize());
	}
	
	/* Se comprueba checkCoordinate para Coordinate3D en los límites devuelve true */
	@Test
	public void testCheckCoordinateOk() {
		assertTrue(board3D.checkCoordinate(new Coordinate3D(	0,		0,		0)));
		assertTrue(board3D.checkCoordinate(new Coordinate3D(	0,		0,	TAM-1)));
		assertTrue(board3D.checkCoordinate(new Coordinate3D(	0,	TAM-1,		0)));
		assertTrue(board3D.checkCoordinate(new Coordinate3D(	0,	TAM-1,	TAM-1)));
		assertTrue(board3D.checkCoordinate(new Coordinate3D(TAM-1,		0,		0)));
		assertTrue(board3D.checkCoordinate(new Coordinate3D(TAM-1,		0,	TAM-1)));
		assertTrue(board3D.checkCoordinate(new Coordinate3D(TAM-1,	TAM-1,		0)));
		assertTrue(board3D.checkCoordinate(new Coordinate3D(TAM-1,	TAM-1,	TAM-1)));
	}
	
	/* Comprueba que checkCoordinate(Coordinate) para Coordinate fuera de los límites
	 * devuelve false.
	 */
	@Test
	public void testCheckCoordinateOutOfLimits() {
		assertFalse(board3D.checkCoordinate(new Coordinate3D(0-TAM,	0-TAM,	0-TAM)));
		assertFalse(board3D.checkCoordinate(new Coordinate3D(0-TAM,	0-TAM,	TAM+1)));
		assertFalse(board3D.checkCoordinate(new Coordinate3D(0-TAM,	TAM+1,	0-TAM)));
		assertFalse(board3D.checkCoordinate(new Coordinate3D(0-TAM,	TAM+1,	TAM+1)));
		assertFalse(board3D.checkCoordinate(new Coordinate3D(TAM+1,	0-TAM,	0-TAM)));
		assertFalse(board3D.checkCoordinate(new Coordinate3D(TAM+1,	0-TAM,	TAM+1)));
		assertFalse(board3D.checkCoordinate(new Coordinate3D(TAM+1,	TAM+1,	0-TAM)));
		assertFalse(board3D.checkCoordinate(new Coordinate3D(TAM+1,	TAM+1,	TAM+1)));
		
	}
	
	/* checkCoordinate para una Coordinate2D en un Board3D */
	@Test(expected=IllegalArgumentException.class)
	public void testCheckCoordinateException() {
		assertTrue(board3D.checkCoordinate(new Coordinate2D(3, 4)));
	}
	
	/* Añade los Aircraft correctamente en el board, tal como aparecen a continuación:
	 *|     ⇄ |       |       |       | ⇄     |       | ⇄     |
     *|     ⇄ |       |       |       |⇄⇄⇄⇄   |       | ⇄     |
     *|    ⇄⇄⇄| ⇶⇶    |       |       | ⇄     |       |⇄⇄⇄ ⇋  |
     *|     ⇄ |  ⇶    |       |   ⇶   |       |       | ⇄  ⇋  |
     *|       |⇶⇶⇶⇶   |       | ⇶ ⇶ ⇶ |       |       |   ⇋⇋⇋ |
     *|       |  ⇶    |       | ⇶⇶⇶⇶⇶ |       |       |  ⇋ ⇋ ⇋|
     *|       | ⇶⇶    |       |   ⇶   |       |       |    ⇋  |
	 */
	@Test
	public void testAddCraftOk() {
		Coordinate f1 = new Coordinate3D(-1, 0, 6);
		f2 = new Coordinate3D(3, 0, 0);
		Coordinate fW = new Coordinate3D(-1, -1, 4);
		b1 = new Coordinate3D(0, 2, 1);
		Coordinate b2 = new Coordinate3D(1, 3, 3);
		Coordinate tN = new Coordinate3D(2, 2, 6);
		
		try {
			board3D.addCraft(fighter1S, f2);
			board3D.addCraft(bomberE, b1);
			board3D.addCraft(fighterW, fW);
			board3D.addCraft(fighter2S, f1);
			board3D.addCraft(bomberS, b2);
			board3D.addCraft(transportN, tN);
		} catch (InvalidCoordinateException | NextToAnotherCraftException | OccupiedCoordinateException e) {
			fail ("Error. Se produjo la excepcion " + (e.getClass()).getName());
		}
		//System.out.println(board3D.show(true));
		assertEquals("E1 en testAddCraftOk", sboard01, board3D.show(true));
	}
	
	/* Tras poner los Craft como se indica en testAddCraftOk(), comprueba que Board.show para true y false
	 * coincide con los String sboard01 y sboard00 respectivamente.
	 */
	@Test
	public void testShow1() {
		testAddCraftOk();
		System.out.println(this.board3D.show(true));
		System.out.println(this.board3D.show(false));
		assertEquals("E1 en testShow1", sboard01, board3D.show(true));
		assertEquals("E2 en testShow1", sboard00, board3D.show(false));
	}


	
	/* Posicionan los Aircraft en el board tal como se indica en testAddCraftOk().
	/* Se intenta poner un Transport, indicado con el símbolo 'T' para diferenciarlo del resto,
	 * en la posición que aparece en la imagen inferior (z=0)
	 * Gran parte queda fuera y solo una colisiona con el Fighter. Se comprueba que:
	 * 1- se lanza la excepción InvalidCoordinateException y no otra.
	 * 2- que no se ha puesto el Transport
	 * 3- que sigue existiendo el Fighter.
	 * 
		   T 
		   T
		  TTT
	     T T T       |       |       | ⇄     |       | ⇄     
           T |       |       |       |⇄⇄⇄⇄   |       | ⇄     
          ⇄⇄⇄| ⇶⇶    |       |       | ⇄     |       |⇄⇄⇄ ⇋  
           ⇄ |  ⇶    |       |   ⇶   |       |       | ⇄  ⇋  
             |⇶⇶⇶⇶   |       | ⇶ ⇶ ⇶ |       |       |   ⇋⇋⇋ 
             |  ⇶    |       | ⇶⇶⇶⇶⇶ |       |       |  ⇋ ⇋ ⇋
             | ⇶⇶    |       |   ⇶   |       |       |    ⇋  
	 */
	@Test
	public void testAddCraftOutOccupied() {
		Coordinate3D c = new Coordinate3D(3, -3, 0); 
		Aircraft transportN = new Transport(Orientation.NORTH);
		testAddCraftOk();
		Set<Craft> nausDelTaulerOriginal = new HashSet<Craft> ();
		String oShow = board3D.show(true);
		
		for(int i = 0; i < TAM; i++)
			for(int j = 0; j < TAM; j++)
				for(int k = 0; k < TAM; k++)
					nausDelTaulerOriginal.add(board3D.getCraft(CoordinateFactory.createCoordinate(i, j, k)));
		
		try {		
			board3D.addCraft(transportN, c);
			fail("Error: no se produjo la excepción InvalidCoordinateException ");
		} catch (InvalidCoordinateException e) {
			//Comprobamos que todo sigue igual
			Set<Craft> nausDelTaulerException = new HashSet<Craft> ();
			
			for(int i = 0; i < TAM; i++)
				for(int j = 0; j < TAM; j++)
					for(int k = 0; k < TAM; k++)
						nausDelTaulerException.add(board3D.getCraft(CoordinateFactory.createCoordinate(i, j, k)));
			
			assertEquals(nausDelTaulerOriginal, nausDelTaulerException);
			assertEquals(oShow, board3D.show(true));
			
		} catch (NextToAnotherCraftException | OccupiedCoordinateException e) {
			fail ("Error. Se esperaba InvalidCoordinateException "
					+ "pero se produjo la excepcion " + e.getClass().getName());
		} 
		
		/*
		 * Per a la pròxima:
		 * show(true) del original ha de ser igual que el del final
		 */
	}

	/* Posiciona los Aircraft en el board tal como se indica en testAddCraftOk().
	/* Intenta poner un Aircraft en una posición en la que parte de ese Aircraft 
	 * queda fuera y parte queda próximo a otro Aircraft ya existente en el board. 
     * Comprueba que:
	 * 1- se lanza la excepción InvalidCoordinateException y no otra
	 * 2- que no se ha puesto el Aircraft 
	 */
	/* Añade los Aircraft correctamente en el board, tal como aparecen a continuación:
	 *|     ⇄ |       |       |       | ⇄     |       | ⇄     |
     *|     ⇄ |       |       |       |⇄⇄⇄⇄   |       | ⇄     |
     *|    ⇄⇄⇄| ⇶⇶    |       |       | ⇄     |       |⇄⇄⇄ ⇋  |
     *|     ⇄ |  ⇶    |       |   ⇶   |       |       | ⇄  ⇋  |
     *|       |⇶⇶⇶⇶   |       | ⇶ ⇶ ⇶ |       |       |   ⇋⇋⇋ |
     *|       |  ⇶    |       | ⇶⇶⇶⇶⇶ |       |       |  ⇋ ⇋ ⇋|
     *|       | ⇶⇶    |       |   ⇶   |       |       |    ⇋  |
	 */
	@Test
	public void testAddCraftOutNextTo() throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		testAddCraftOk();
		// Añadir uno como el de k 0 a k 3 pero una j más arriba.
		// Fighter1S
		Craft av = new Fighter(Orientation.SOUTH);
		String oBoard = board3D.show(true);
		try {
			board3D.addCraft(av, new Coordinate3D(3, -1, 3));
		} catch (InvalidCoordinateException e) {
			System.err.println(e.toString());
		} catch (OccupiedCoordinateException | NextToAnotherCraftException e) {
			System.err.println(e.toString());
		}
		assertEquals(oBoard, board3D.show(true));
		//fail ("Completa el test");
	}
	
		
	
	/* Posiciona los Aircraft en el board tal como se indica en testAddCraftOk().
	/* Intenta poner un Aircraft en una posición en la que parte de ese Aircraft
	 * colisiona con otro Aircraft ya existente y parte queda próximo a otro Aircraft
	 * también existente en el board. Comprueba que:
	 * 1- se lanza la excepción OccupiedCoordinateException y no otra.
	 * 2- que no se ha puesto el Aircraft
	 * 3- que sigue existiendo el Aircraft con el que colisionaba.*/
	@Test
	public void testAddCraftColisionNextTo() throws InvalidCoordinateException, NextToAnotherCraftException {
		testAddCraftOk();
		String oShow = board3D.show(true);
		Craft fighterEast = new Fighter(Orientation.EAST);
		
		try {
			board3D.addCraft(fighterEast, CoordinateFactory.createCoordinate(2, -1, 6));
		} catch (OccupiedCoordinateException e) {
			assertEquals(oShow, board3D.show(true));
		} catch (InvalidCoordinateException | NextToAnotherCraftException e) {
			fail("Llançament de exepcions incorrecte");
		}
		//fail("Completa el test");
	}
	
	
	/* Se posicionan los Aircraft en el board tal como se indica en testAddCraftOk().
	/* Se intenta poner un Transport, indicado con el símbolo 'T' para diferenciarlo del resto,
	 * en una posición en la que parte de ese Transport queda fuera del board, parte colisiona con 
	 * un Bomber ya existente, y parte queda próximo a dos Aircraft (Bomber y Fighter) también 
	 * existentes en el board. 
	 * Se comprueba que:
	 * 1- se lanza la excepción InvalidCoordinateException y no otra.
	 * 2- que no se ha puesto el Aircraft
	 * 3- que sigue existiendo el Aircraft con el que colisionaba.
	 * 
	 * 		   T
	 *     ⇄ | T     |       |       | ⇄     |       | ⇄     
     *     ⇄ |TTT    |       |       |⇄⇄⇄⇄   |       | ⇄     
     *    ⇄⇄⇄T T T   |       |       | ⇄     |       |⇄⇄⇄ ⇋  
     *     ⇄ | T⇶    |       |   ⇶   |       |       | ⇄  ⇋  
     *       |⇶⇶⇶⇶   |       | ⇶ ⇶ ⇶ |       |       |   ⇋⇋⇋ 
     *       |  ⇶    |       | ⇶⇶⇶⇶⇶ |       |       |  ⇋ ⇋ ⇋
     *       | ⇶⇶    |       |   ⇶   |       |       |    ⇋  
	 */
	@Test
	public void testAddCraftOutColisionNextTo() {
		testAddCraftOk();
		Aircraft transportN = new Transport(Orientation.NORTH);
		try {
			board3D.addCraft(transportN, new Coordinate3D(-1, -1, 0));
			fail("Error: no se produjo la excepción InvalidCoordinateException ");
		} catch (InvalidCoordinateException e1) {
			assertNotNull (board3D.getCraft(new Coordinate3D(1, 2, 1)));
			assertTrue(board3D.getCraft(new Coordinate3D(1, 2, 1)).getClass().getName() == "model.aircraft.Bomber");
			assertEquals(b1, board3D.getCraft(new Coordinate3D(1, 2, 1)).getPosition());
			assertNull(transportN.getPosition());
			assertNull(board3D.getCraft(new Coordinate3D(1,0,1)));
		} catch (NextToAnotherCraftException | OccupiedCoordinateException e2) {
			fail ("Error. Se esperaba OccupiedCoordinateException "
					+ "pero se produjo la excepcion "+e2.getClass().getName());
		} 
	}
	
	/* Se añaden los Aircraft en el board tal como se indica en testAddCraftOk().
	 * Comprueba para un Aicraft cualquiera del board que getCraft(Coordinate), en todas sus Coordinates, 
	 * devuelve dicho Aircraft. Dispara en todas sus posiciones hundiéndolo y vuelve a comprobar que
	 * dicho Aircraft sigue existiendo.
	 */
	@Test
	public void testGetCraft() throws InvalidCoordinateException, CoordinateAlreadyHitException {
		testAddCraftOk();
		Set<Coordinate> possicionsCraft = new HashSet<Coordinate> ();
		possicionsCraft.add(new Coordinate3D(5, 0, 0));
		possicionsCraft.add(new Coordinate3D(5, 1, 0));
		possicionsCraft.add(new Coordinate3D(5, 2, 0));
		possicionsCraft.add(new Coordinate3D(5, 3, 0));
		possicionsCraft.add(new Coordinate3D(4, 2, 0));
		possicionsCraft.add(new Coordinate3D(6, 2, 0));
		
		Craft craftAnt = board3D.getCraft(new Coordinate3D(5, 0, 0));
		
		assertEquals(fighter1S, craftAnt);
		for(Coordinate it : possicionsCraft) {
			assertSame(craftAnt, board3D.getCraft(it));
			assertNotNull(craftAnt);
			craftAnt = board3D.getCraft(it);
		}
		
		for(Coordinate it : possicionsCraft) board3D.hit(it);
		
		craftAnt = board3D.getCraft(new Coordinate3D(5, 0, 0));
		for(Coordinate it : possicionsCraft) {
			assertSame(craftAnt, board3D.getCraft(it));
			craftAnt = board3D.getCraft(it);
		}
		//fail("Realiza el test");
	}

	/* Se añaden los Aircraft en el board tal como se indica en testAddCraftOk().
	 * Se comprueba que antes de disparar sobre uno de ellos, en sus posiciones, isSeen devuelve false
	 * Tras disparar sobre dichas posiciones, se comprueba que isSeen ahora devuelve true.
	 */
	@Test
	public void testIsSeenHits() throws InvalidCoordinateException, CoordinateAlreadyHitException {
		testAddCraftOk();
		for (int i=2; i<7; i++) 
			assertFalse(board3D.isSeen(new Coordinate3D(2, i, 1)));
		shotsAtBomberZ1();
		for (int i=2; i<7; i++) 
			assertTrue(board3D.isSeen(new Coordinate3D(2, i, 1)));
	}
	
	
	
	/* Se comprueba que para un Board3D de tamaño 10, si posicionáramos el bomberE, fighter1S y transportN en la Coordinate3D(1,2,1)
	 * el método board.getNeighborhood(Craft, new Coordinate(1,2,1)) devuelve sets con 92, 66 y 96 Coordinates respectivamente.
	 */
	@Test
	public void testGetNeighborhoodCraftCoordinate1() throws InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		Board board2D = new Board3D(10);
		Set<Coordinate> neighborhood = board2D.getNeighborhood(bomberE, new Coordinate3D(1, 2, 1));
		assertEquals(92, neighborhood.size());
		neighborhood = board2D.getNeighborhood(fighter1S, new Coordinate3D(1, 2, 1));
		assertEquals(66, neighborhood.size());
		neighborhood = board2D.getNeighborhood(transportN, new Coordinate3D(1, 2, 1));
		assertEquals(96, neighborhood.size());
	}
	/* Comprueba que poniendo distintos Aircrafts en otras posiciones limítrofes, incluso fuera del tablero, getNeigborhood devuelve
	 * sets con un número de Coordinates correctas.
	 * Ten en cuenta que las posiciones fuera del tablero no se deben añadir al set.
	 */
	@Test
	public void testGetNeighborhoodCraftCoordinate2() throws InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		Craft tran = new Transport(Orientation.NORTH);
		board3D.addCraft(tran, CoordinateFactory.createCoordinate(new int[] {0, 0, 0}));
		System.out.println(board3D.show(true));
		assertEquals("E1 en testGetNeighborhoodCraftCoordinate2", 49, board3D.getNeighborhood(tran).size());
		//fail("Realiza el test");
	}
	
	
	/* Se añaden los Aircraft en el board tal como se indica en testAddCraftOk().
	 * Se hacen disparos al agua.
	 * Se comprueba que show(true) y show(false) genera los Strings sboard01 y sboard02 respectivamente.
	 */
	@Test
	public void testHit1() throws InvalidCoordinateException, CoordinateAlreadyHitException {
		testAddCraftOk();
		assertEquals(sboard00,board3D.show(false));
		shotsIntoWater();
		assertEquals(sboard01,board3D.show(true));
		assertEquals(sboard02,board3D.show(false));
		System.out.println(board3D.show(true));
		System.out.println(board3D.show(false));
	}
	
	/* Añade los Aircraft en el board tal como se indica en testAddCraftOk().
	 * Dispara sobre algunos de los Aircraft, destruyendo alguno; y comprueba que show(true) muestra los disparos 
	 * realizados a los Aircraft y que show(false) también los muestra excepto los destruídos que muestra el Aircraft
	 * y además marca como vistos sus coordenadas vecinas.
	 */
	@Test
	public void testHit2() throws InvalidCoordinateException, CoordinateAlreadyHitException  {
		testAddCraftOk();
		Set<Coordinate> possicionsCraft = new HashSet<Coordinate> ();
		possicionsCraft.add(new Coordinate3D(5, 0, 0));
		possicionsCraft.add(new Coordinate3D(5, 1, 0));
		possicionsCraft.add(new Coordinate3D(5, 2, 0));
		possicionsCraft.add(new Coordinate3D(5, 3, 0));
		possicionsCraft.add(new Coordinate3D(4, 2, 0));
		possicionsCraft.add(new Coordinate3D(6, 2, 0));
		
		for(Coordinate it : possicionsCraft) board3D.hit(it);
		
		shotsAtBomberZ1();
		
		assertEquals("E1 en testHit2", sboard03, board3D.show(false));
		assertEquals("E2 en testHit2", sboard04, board3D.show(true));
		
		/*
		 * Si peta ací, possiblement serà per una errata en el hit. Quan disparem a un avió, 
		 * s'han de mostrar les possicions que hi ha al seu voltant. També estan al voltant d'ell les possicions que hi ha sobre i sota d'ell.
		 */
	}
	
	/* Realiza disparos fuera del Board y comprueba que se lanza la excepción InvalidCoordinateException
	 * 
	 */
	@Test
	public void testHitOutOfBoard() throws InvalidCoordinateException, CoordinateAlreadyHitException {
		try {
			board3D.hit(CoordinateFactory.createCoordinate(new int[] {TAM + 1, TAM + 1, TAM + 1}));
			fail("Error: debería haber lanzado la excepción InvalidCoordinateException");
		} catch (InvalidCoordinateException e) {
			System.err.println(e.toString());
		}
		//fail("Realiza el test");
	}

	/* Realiza disparos repetidos a los Aircrafts y comprueba que se lanza la excepción CoordinateAlreadyHitException
	 * 
	 */
	@Test
	public void testCoodinateAlreadyHitOnBoard() throws InvalidCoordinateException, CoordinateAlreadyHitException {
		testAddCraftOk();
		Set<Coordinate> possicionsCraft = new HashSet<Coordinate> ();
		possicionsCraft.add(new Coordinate3D(5, 0, 0));
		possicionsCraft.add(new Coordinate3D(5, 1, 0));
		possicionsCraft.add(new Coordinate3D(5, 2, 0));
		possicionsCraft.add(new Coordinate3D(5, 3, 0));
		possicionsCraft.add(new Coordinate3D(4, 2, 0));
		possicionsCraft.add(new Coordinate3D(6, 2, 0));
		
		for(Coordinate it : possicionsCraft) board3D.hit(it);
		
		try {
			for(Coordinate it : possicionsCraft) board3D.hit(it);
			fail("No ha saltat la excepció");
		} catch (CoordinateAlreadyHitException e) {
			System.err.println(e.toString());
		}
		
		//fail("Realiza el test");
	}
	
	
	
	
/***************************************************************/	
	//Funciones auxiliares

	void shotsAtBomberZ1() throws InvalidCoordinateException, CoordinateAlreadyHitException{
		//Tocado
		for (int i=2; i<7; i++) 
			board3D.hit(new Coordinate3D(2, i, 1));
	}
	
	void shotsIntoWater() throws InvalidCoordinateException, CoordinateAlreadyHitException{
		for (int i=0; i<7; i++) {
			board3D.hit(new Coordinate3D(i,i,2));
			board3D.hit(new Coordinate3D(i,i,0));
			board3D.hit(new Coordinate3D(i,5,0));
			board3D.hit(new Coordinate3D(0,i,3));
			board3D.hit(new Coordinate3D(4,i,4));
		}
	}
	
}