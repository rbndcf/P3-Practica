package model;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class aluTestsBoard {

	static final int MAX_BOARD_SIZE = 20;
	static final int MIN_BOARD_SIZE = 5;
	final static int DIM = 10;
	Board board;
	Ship fragata, galeon, bergantin, goleta;
	static String sboardEmpty,sboard, sboardHide1, sboardHits1,
				sboardHits2,sboardHits3, sboardHide2; //= new String();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sboardHide1 = "?????\n?????\n?????\n?????\n?????";
		
		sboardHide2 = "A ??•\n" + 
			      "A ?? \n" + 
			      "A  ??\n" + 
			      "   ?•\n" + 
			      "?•??•";
		
		sboardEmpty = "     \n     \n     \n     \n     ";
		
		sboard="A FFF\n" + 
			   "A    \n" + 
			   "A   G\n" + 
			   "    G\n" + 
			   "BBB G";
		
		sboardHits1 ="• FFF\n" + 
				     "•    \n" + 
				     "•   G\n" + 
				     "    G\n" + 
				     "BBB G";
		
		sboardHits2 ="• FF•\n" + 
				     "•    \n" + 
				     "•   G\n" + 
				     "    •\n" + 
				     "BBB •";
		
		sboardHits3 = "• FF•\n" + 
				      "•    \n" + 
				      "•   G\n" + 
				      "    •\n" + 
				      "B•B •";
		
	}

	@Before
	public void setUp() throws Exception {
		fragata = new Ship(Orientation.WEST,'F',"Barbanegra");
		galeon = new Ship(Orientation.SOUTH,'A',"Francis Drake");
		bergantin = new Ship(Orientation.EAST,'B',"Benito Soto");
		goleta = new Ship(Orientation.NORTH,'G',"Hook");
		board = new Board(DIM);
		
	}

	/* Comprueba los límites del tamaño en el constructor,
	 * tanto dentro como justo fuera. Comprueba que al superarlos
	 * el tamaño que toma el Board es el mínimo 
	 */
	@Test
	public void testBoardGetSize() {
		//Dentro de los límites
		board = new Board(MIN_BOARD_SIZE+1);
		assertEquals(MIN_BOARD_SIZE+1,board.getSize());
		board = new Board(MAX_BOARD_SIZE-1);
		assertEquals(MAX_BOARD_SIZE-1,board.getSize());
		board = new Board(MIN_BOARD_SIZE - 1);
		assertEquals(MIN_BOARD_SIZE, board.getSize());
		board = new Board(MAX_BOARD_SIZE + 1);
		assertEquals(MIN_BOARD_SIZE, board.getSize());
	}
	
	/* Se comprueba checkCoordinate en los límites del tamaño 
	 * del Board 
	 */
	@Test
	public void testCheckCoordinate() {
		final int SIZE = 15;
		Board board = new Board(SIZE);
		assertFalse(board.checkCoordinate(new Coordinate(0,SIZE)));
		assertFalse(board.checkCoordinate(new Coordinate(-1,SIZE-1)));
		assertFalse(board.checkCoordinate(new Coordinate(-1,SIZE)));
		assertFalse(board.checkCoordinate(new Coordinate(SIZE,0)));
		assertFalse(board.checkCoordinate(new Coordinate(SIZE-1,-1)));
		assertFalse(board.checkCoordinate(new Coordinate(SIZE,-1)));
		assertTrue(board.checkCoordinate(new Coordinate(0,SIZE-1)));
		assertTrue(board.checkCoordinate(new Coordinate(SIZE-1,0)));
		
	}

	/* Posicionamientos correctos entre Ships. Posiciona de forma correcta 
	 * los 4 ships galeon, fragata, goleta y bergantín y comprueba que  se
	 * han posicionado los Ships en el Board.
	 */
	@Test
	public void testAddShipsOk() {
		assertTrue(board.addShip(galeon, new Coordinate(0,1)));
		for (int i=2; i<5; i++)	
			assertNotNull("x,y = 2,"+i, board.getShip(new Coordinate(2,i)));
		
		assertTrue(board.addShip(goleta, new Coordinate(5,1)));
		for (int i=7; i<5; i++)	
			assertNotNull("x,y = 7,"+i,board.getShip(new Coordinate(7,i)));
		
		assertTrue(board.addShip(fragata, new Coordinate(0,6)));
		for (int i=1; i<4; i++)	
			assertNotNull("x,y = " + i + ", 8", board.getShip(new Coordinate(i,8)));
		
		assertTrue(board.addShip(bergantin, new Coordinate(5,6)));
		for (int i=6; i<9; i++)	
			assertNotNull("x,y = " + i + ", 8", board.getShip(new Coordinate(i,8)));
	}

	/* Posiciona los 4 Ships fuera del tablero y comprueba que
	 * addShip devuelve false y que además no se han posicionado
	 * los Ships en el Board
	 */
	@Test
	public void testAddShipsOutOfBoard() {
		assertFalse(board.addShip(bergantin, new Coordinate(7,3)));
		assertFalse(board.addShip(galeon, new Coordinate(-2,7)));
		assertFalse(board.addShip(fragata, new Coordinate(9,9)));
		assertFalse(board.addShip(goleta, new Coordinate(3,8)));
	}
	
	/* Posiciona un Ship junto a otro y comprueba que addShip devuelve
	 * false y que además no se ha posicionado el Ship en el Board
	 */
	@Test
	public void testAddShipNextOther() {
		assertTrue(board.addShip(galeon, new Coordinate(0,1)));
		assertFalse(board.addShip(goleta, new Coordinate(1,0))); 
	}
	
	/* Se posiciona un Ship en una Coordinate.
	 * 1- Prueba getShip en una Coordinate que no contiene al Ship
	 * 2- Prueba getShip en todas las posiciones que ocupa el Ship
	 */
	@Test
	public void testGetShip() {
		assertTrue(board.addShip(fragata, new Coordinate(3,1)));
		
		assertNull(board.getShip(new Coordinate(0,0)));
		assertNotNull(board.getShip(new Coordinate(4,3))); 
		assertNotNull(board.getShip(new Coordinate(5,3)));
		assertNotNull(board.getShip(new Coordinate(6,3)));
	}
	
    /* Se comprueba isSeen antes y después de disparar al agua
     * en un Board sin Ships */
	@Test
	public void testIsSeen1() {
		for (int i=0; i<board.getSize(); i++)
			for (int j=0; j<board.getSize(); j++) {
				assertFalse(board.isSeen(new Coordinate(i,j)));
				board.hit(new Coordinate(i,j));
				assertTrue(board.isSeen(new Coordinate(i,j)));
			}	
	}

  /* Posiciona un Ship en el Board y comprueba isSeen 
   * antes y después de disparar a las distintas partes del Ship.
   * Cuando el Ship se ha hundido entonces comprueba que las
   * Coordinates vecinas del Ship también se han marcado como
   * vistas */
	@Test
	public void testIsSeen2() {
		assertTrue(board.addShip(goleta, new Coordinate(0,0)));

		assertFalse(board.isSeen(new Coordinate(2,1)));
		assertFalse(board.isSeen(new Coordinate(2,2)));
		assertFalse(board.isSeen(new Coordinate(2,3)));

		assertEquals(CellStatus.HIT, board.hit(new Coordinate(2,1)));
		assertEquals(CellStatus.HIT, board.hit(new Coordinate(2,2)));
		assertEquals(CellStatus.DESTROYED, board.hit(new Coordinate(2,3)));
		
		for(int i = 1 ; i < 4 ; i++)
			for(int j = 0 ; j < 5 ; j++)
				assertTrue(board.isSeen(new Coordinate(i,j)));
	}

	/* Coloca un Ship en el Board en una Coordinate. Comprueba que:
	 * 1- al disparar (hit) sobre las posiciones alrededor del Ship el 
	 *    resultado es WATER.
	 * 2- al disparar (hit) sobre las posiciones del Ship, excepto la última,
	 *    el resultado es HIT.
	 * 3- al disparar (hit) sobre la última posición del Ship, el resultado 
	 *    es DESTROYED
	 * 
	 */
	@Test
	public void testHit() {
		assertTrue(board.addShip(fragata, new Coordinate(0,0)));

		assertEquals(CellStatus.WATER, board.hit(new Coordinate(0,1)));
		assertEquals(CellStatus.WATER, board.hit(new Coordinate(1,1)));
		assertEquals(CellStatus.WATER, board.hit(new Coordinate(2,1)));
		assertEquals(CellStatus.WATER, board.hit(new Coordinate(3,1)));
		assertEquals(CellStatus.WATER, board.hit(new Coordinate(4,1)));

		assertEquals(CellStatus.WATER, board.hit(new Coordinate(0,2)));
		assertEquals(CellStatus.WATER, board.hit(new Coordinate(4,2)));

		assertEquals(CellStatus.WATER, board.hit(new Coordinate(0,3)));
		assertEquals(CellStatus.WATER, board.hit(new Coordinate(1,3)));
		assertEquals(CellStatus.WATER, board.hit(new Coordinate(2,3)));
		assertEquals(CellStatus.WATER, board.hit(new Coordinate(3,3)));
		assertEquals(CellStatus.WATER, board.hit(new Coordinate(4,3)));

		assertEquals(CellStatus.HIT, board.hit(new Coordinate(1,2)));
		assertEquals(CellStatus.HIT, board.hit(new Coordinate(2,2)));
		
		assertEquals(CellStatus.DESTROYED, board.hit(new Coordinate(3,2)));
	}

	/* Comprueba que:
	 * 1- en un Board sin Ships, areAllCraftsDestroyed devuelve true.
	 * 2- al posicionar dos Ships en el Board, tras cada posicionamiento,
	 *    areAllCraftsDestroyed devuelve false.
	 * 3- tras cada disparo sobre el primer Ship, hundiéndolo, areAllCraftsDestroyed 
	 *    devuelve false.
	 * 4- tras cada disparo sobre el segundo Ship, areAllCraftsDestroyed devuelve
	 *    false, excepto tras el último disparo que debe devolver true.
	 * 5- añade un nuevo Ship, entonces areAllCraftsDestroyed debe devolver
	 *    false.
	 */
	@Test
	public void testAreAllCraftsDestroyed() {
		assertTrue("numCrafts=destroyedCrafts=0",board.areAllCraftsDestroyed());
		
		board.addShip(galeon, new Coordinate(0,1));
		assertFalse("numCrafts=1; destroyedCrafts=0",board.areAllCraftsDestroyed());
		board.addShip(fragata, new Coordinate(3,1));
		assertFalse("numCrafts=2; destroyedCrafts=0",board.areAllCraftsDestroyed());

		assertEquals(CellStatus.HIT, board.hit(new Coordinate(2,2)));
		assertFalse("numCrafts=2; destroyedCrafts=0", board.areAllCraftsDestroyed());
		assertEquals(CellStatus.HIT, board.hit(new Coordinate(2,3)));
		assertFalse("numCrafts=2; destroyedCrafts=0", board.areAllCraftsDestroyed());
		assertEquals(CellStatus.DESTROYED, board.hit(new Coordinate(2,4)));
		assertFalse("numCrafts=2; destroyedCrafts=1", board.areAllCraftsDestroyed());

		assertEquals(CellStatus.HIT, board.hit(new Coordinate(4,3)));
		assertFalse("numCrafts=2; destroyedCrafts=1", board.areAllCraftsDestroyed());
		assertEquals(CellStatus.HIT, board.hit(new Coordinate(5,3)));
		assertFalse("numCrafts=2; destroyedCrafts=1", board.areAllCraftsDestroyed());
		assertEquals(CellStatus.DESTROYED, board.hit(new Coordinate(6,3)));
		assertTrue("numCrafts=2; destroyedCrafts=2", board.areAllCraftsDestroyed());
		
		board.addShip(bergantin, new Coordinate(4,4));
		assertFalse("numCrafts=3; destroyedCrafts=2", board.areAllCraftsDestroyed());
	}

	/* Se comprueba getNeighborhood(Ship) donde el Ship y todas sus 
	 * Coordinate vecinas están dentro de Board.
	 */
	@Test
	public void testGetNeighborhoodShipCompletelyIn1() {
		board.addShip(fragata, new Coordinate(5,1));
		Set<Coordinate> neighborhood = board.getNeighborhood(fragata);
		assertEquals(12,neighborhood.size());
		for (int i=5; i<10; i++) {
			for (int j=2; j<4; j++) {
				if ((j==3) && (i>=6)&&(i<=8))
					assertFalse("x,y = "+i+","+j,neighborhood.contains(new Coordinate(i,j)));
				else 
					assertTrue("x,y = "+i+","+j,neighborhood.contains(new Coordinate(i,j)));	
			}
		}
		
		board.addShip(fragata, new Coordinate(5,1));
		Set<Coordinate> neighborhood2 = board.getNeighborhood(fragata, new Coordinate(3,1));
  		for (int i=5; i<10; i++) {
			for (int j=2; j<4; j++) {
				if ((j==3) && (i>=6)&&(i<=8))
					assertFalse("x,y = "+i+","+j,neighborhood2.contains(new Coordinate(i-2,j)));
				else 
					assertTrue("x,y = "+i+","+j,neighborhood2.contains(new Coordinate(i-2,j)));	
			}
		}
	}
	
	/* Comprueba:
	 * 1- getNeighborhood(Ship) para un Ship que no se ha puesto en el Board 
	 *    debe devolver un Set vacío.
	 * 
	 * 2- getNeighborhood(Ship, Coordinate) donde el Ship sale de los límites
	 *    del Board. El conjunto de Coordinate vecinas solo recoge aquellas
	 *    que están dentro del Board
	 */
	@Test
	public void testGetNeighborhoodShipOutOfBounds() {
		assertTrue(board.addShip(fragata, new Coordinate(6,7)));
		
		Set<Coordinate> nb = board.getNeighborhood(fragata);
		assertEquals(5, nb.size());
	}
	
	/* Se crea un tablero de tamaño 5 sin Ships. Se comprueba que lo devuelto
	 * por show(true) y show(false) es correcto.
	 * 
	 */
	@Test
	public void testShowBoardEmty() {
		board = new Board(5);
		String hideShips = board.show(false);
		assertEquals(sboardHide1,hideShips);
		String showShips = board.show(true);
		assertEquals(sboardEmpty,showShips);
	}
	
	/* Se crea un tablero de tamaño 5.
	 * 1- Se añaden los 4 ships en las posiciones indicadas en la variable estática 'sboard'
	 *    definida en setUp().
	 * 2- Se comprueba que show(false) devuelve lo mismo que la variable estática sboardHide1
	 *    y que show(true) lo mismo que el contenido de la variable estática 'sboard' 
	 */
	@Test
	public void testShowBoardWithShips() {
		board = new Board(5);
		
		board.addShip(galeon, new Coordinate(-2,-1));
		board.addShip(fragata, new Coordinate(1,-2));
		board.addShip(goleta,  new Coordinate(2,1));
		board.addShip(bergantin, new Coordinate(-1,2));
		
		assertEquals(sboardHide1, board.show(false));
		assertEquals(sboard, board.show(true));

	}	
	
	/* Añade los 4 Ships del setUp() en el Board y comprueba toString() con
	 * la salida correcta.
	 */
	@Test
	public void testToString1() {
		assertTrue(board.addShip(bergantin, new Coordinate(0,0)));
		assertTrue(board.addShip(fragata, new Coordinate(5,0)));
		assertTrue(board.addShip(galeon, new Coordinate(0,5)));
		assertTrue(board.addShip(goleta, new Coordinate(5,5)));
		assertEquals("Board 10; crafts: 4; destroyed: 0",board.toString());
	}
	
    /* Se toma el ejemplo del test testAreAllCraftsDestroyed().
     * 1- Destruye el galeón y comprueba que la salida que debe dar es correcta.
     * 2- Realiza disparos sobre la fragata comprobando que las salidas que debe
     *    dar son correctas.
    */
	@Test
	public void testToString2() {		
		board.addShip(galeon, new Coordinate(0,1));

		assertEquals(CellStatus.HIT, board.hit(new Coordinate(2,2)));
		assertEquals("Board 10; crafts: 1; destroyed: 0",board.toString());
		assertEquals(CellStatus.HIT, board.hit(new Coordinate(2,3)));
		assertEquals("Board 10; crafts: 1; destroyed: 0",board.toString());
		assertEquals(CellStatus.DESTROYED, board.hit(new Coordinate(2,4)));
		assertEquals("Board 10; crafts: 1; destroyed: 1",board.toString());
	}

}