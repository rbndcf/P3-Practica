package model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.aircraft.Board3D;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.ship.Board2D;

public class PlayerRandomTest {

	static Coordinate[] vc2d, vc3d;
	Board board2d,board3d;
	static String sboard2d, sboard3d;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 vc2d = new Coordinate[5];
		 vc3d = new Coordinate[5];
		 vc2d[0]=CoordinateFactory.createCoordinate(8,5);
		 vc2d[1]=CoordinateFactory.createCoordinate(3,1);
		 vc2d[2]=CoordinateFactory.createCoordinate(1,9);
		 vc2d[3]=CoordinateFactory.createCoordinate(8,0);
		 vc2d[4]=CoordinateFactory.createCoordinate(2,7);
		 
		 vc3d[0]=CoordinateFactory.createCoordinate(8, 8, 1);
		 vc3d[1]=CoordinateFactory.createCoordinate(9, 9, 8);
		 vc3d[2]=CoordinateFactory.createCoordinate(8, 1, 0);
		 vc3d[3]=CoordinateFactory.createCoordinate(8, 6, 0);
		 vc3d[4]=CoordinateFactory.createCoordinate(1, 2, 4);
		 
		 sboard2d = "          \n" + 
		 			"          \n" + 
		 			"O         \n" + 
		 			"O  Ø      \n" + 
		 			"O  Ø      \n" + 
		 			"O  Ø    ® \n" + 
		 			"        ® \n" + 
		 			"Ω       ® \n" + 
		 			"Ω       ® \n" + 
		 			"        ® ";
		 
		 sboard3d = "          |          |          |          |          |          |          |          |          |          \n" + 
		 			"          |          |          |          |          |          |          |          |          |          \n" + 
		 			"          |   ØØØ    |          |          |          |          |          |          |          |          \n" + 
		 			"          |          |          |          |          |        O |          |          |          |          \n" + 
		 			"          |          |          |          |          |        O |          |          |          |          \n" + 
		 			"          |          |          |          |          |        O | ⇋        |          |          |     ⇶    \n" + 
		 			"          |          |          |        ΩΩ|          |        O |  ⇋       |          |          |   ⇶⇶⇶⇶⇶  \n" + 
		 			"     ⇄    |          |          |          |          |          |⇋⇋⇋⇋⇋     |          |          |   ⇶ ⇶ ⇶  \n" + 
		 			"   ⇄⇄⇄⇄   |          |          |          |   ®®®®®  |          |  ⇋       |          |          |     ⇶    \n" + 
		 			"     ⇄    |          |          |          |          |          | ⇋        |          |          |          ";
		 		
	}
	
	
	
	@Before
	public void setUp() throws Exception {
		board2d = new Board2D(10);
		board3d = new Board3D(10);
	}

	/* Se crea un PlayerRandom se aplica sobre él el método nextShoot para 5 Coordinates
	 * de un Board2D de tamaño 10 y 5 Coordinates para un board3D también de tamaño 10. 
	 * Se comprueba que las Coordinates generadas son correctas */
	@Test
	public void testNextShoot() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = new PlayerRandom("Rosa", 47);
		for (int i=0; i<5; i++) {
			assertEquals("vc2d["+i+"]==ip.nextShoot",vc2d[i],ip.nextShoot(board2d));
		}		
		for (int i=0; i<5; i++) {
			assertEquals("vc3d["+i+"]==ip.nextShoot",vc3d[i],ip.nextShoot(board3d));
		}
	}

	/* Se crea un PlayerRandom con semilla 47 y se pone aleatoriamente los Ships en un Board2D y 
	 * se comprueba que el el tablero resultante es igual al sboard2d definido en setUpBeforeClass */
	@Test
	public void testPutCrafts2D() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = new PlayerRandom("Rosa", 47);
		ip.putCrafts(board2d);
		compareLines(sboard2d,board2d.show(true));
	}
	
	/* Se crea un PlayerRandom con semilla 55 y se pone aleatoriamente los Ships en un Board2D y 
	 * se comprueba que el el tablero resultante es igual al sboard3d definido en setUpBeforeClass */
	@Test
	public void testPutCrafts3D() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = new PlayerRandom("Laura", 55);
		ip.putCrafts(board3d);
		compareLines(sboard3d,board3d.show(true));
	}
	
	/* Se pasa null como parámetro a putCrafts. Se comprueba que se lanza la excepción NullPointerException */
	@Test(expected=NullPointerException.class)
	public void testPutCraftsNullPointerException() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = new PlayerRandom("Rosa", 47);
		ip.putCrafts(null);
	}

	@Test
	public void testGetName() throws BattleshipIOException {
		IPlayer ip = PlayerFactory.createPlayer("Saul","55");
		assertEquals("Saul (PlayerRandom)",ip.getName());
	}
	
	/* ************************************************
	 * FUNCIONES AUXILIARES
	 **************************************************/
	private void  compareLines(String expected, String result) {
		String exp[]=expected.split("\n");
		String res[]=result.split("\n");
		if (exp.length!=res.length) 
			fail("Cadena esperada de tamaño ("+exp.length+") distinto a la resultante ("+res.length+")");
		for (int i=0; i<exp.length; i++) {
			 				 assertEquals("linea "+i, exp[i],res[i]);
		}
	}
}
