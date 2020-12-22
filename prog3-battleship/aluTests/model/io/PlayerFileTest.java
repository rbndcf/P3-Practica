package model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
import model.ship.Coordinate2D;

public class PlayerFileTest {
	final static String DIRFILES = "aluTests/files/";
	final int SIZE = 6;
	static String sboard0, sboard00, sboard01, sboard1, sboard2;
	Board board2d, board3d;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		sboard0 = "      \n"
				+ "      \n"
				+ "      \n"
				+ "      \n"
				+ "      \n"
				+ "      "; 
		sboard00 = "      |      |      |      |      |      \n" + 
				   "      |      |      |      |      |      \n" + 
				   "      |      |      |      |      |      \n" + 
				   "      |      |      |      |      |      \n" + 
				   "      |      |      |      |      |      \n" + 
				   "      |      |      |      |      |      ";
		
		sboard01 = "      |      |      |    ΩΩ|      |      \n" + 
				   "   ®  |      |  ⇄   |      |      | ⇶⇶   \n" + 
				   "   ®  |      |  ⇄   |      |      | ⇶    \n" + 
				   "   ®  |      | ⇄⇄⇄  |      |      |⇶⇶⇶⇶  \n" + 
				   "   ®  |      |  ⇄   |      |      | ⇶    \n" + 
				   "   ®  |      |      |      |      | ⇶⇶   ";
		
		sboard1 = "O    ®\n"
				+ "O    ®\n"
				+ "O    ®\n"
				+ "O    ®\n"
				+ "     ®\n"
				+ "  ΩΩ  "; 
		
		sboard2 = "     ®\n"
				+ "     ®\n"
				+ "     ®\n"
				+ "     ®\n"
				+ "     ®\n"
				+ "  ΩΩ  ";
	}
	
	@Before
	public void setUp() throws Exception {
		board2d =  new Board2D(SIZE);
		board3d =  new Board3D(SIZE);
	}

	@Test
	public void testPlayerFileNullPointerException() throws FileNotFoundException {
		new PlayerFile(null,new BufferedReader(new FileReader(DIRFILES+"ShipsOk1.in")));
	}


	@Test
	public void testGetName() throws BattleshipIOException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsOk1.in");
		assertEquals("Saul (PlayerFile)",ip.getName());
	}

	//TESTS PARA putCraft
	/* Lee un fichero vacío */
	@Test
	public void testPutCraftsEmptyFile() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"CraftEmpty.in");
		ip.putCrafts(board2d);
		compareLines(sboard0, board2d.show(true));
	}
	
	/* Fichero que solo contiene puts */
	@Test
	public void testPutCraftsOk1() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsOk1.in");
		ip.putCrafts(board2d);
		compareLines(sboard1, board2d.show(true));
	}
	
	/* Fichero que contiene un endput al final*/
	@Test
	public void testPutCraftsOk2() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsOk2.in");
		ip.putCrafts(board2d);
		compareLines(sboard1, board2d.show(true));
	}
	
	/* Fichero que contiene un exit al final */
	@Test
	public void testPutCraftsOk3() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsOk3.in");
		ip.putCrafts(board2d);
		compareLines(sboard1, board2d.show(true));
	}
	
	/* Hay un put después de un endput. Se comprueba que no se puso el Craft. Se vuelve a comprobar 
	 * lo mismo con un exit poniéndolo al inicio */
	@Test
	public void testPutCraftsOk4() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsOk4_1.in");
		ip.putCrafts(board3d);
		compareLines(sboard01, board3d.show(true));
		ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsOk4_2.in");
		ip.putCrafts(board3d);
		compareLines(sboard01, board3d.show(true));
	}	
	
	/* Aparece al final el comando shoot sin que aparezca un endput antes*/
	@Test
	public void testPutCraftsWrong1() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong1.in");
		try {
		   ip.putCrafts(board2d);
		   fail("Error: se debió lanzar BattleshipIOException");
		} catch (BattleshipIOException e) {
			assertNotNull(e.getMessage());
			assertTrue (e.getMessage().length()>1);
			compareLines(sboard1,board2d.show(true));
		}
	}
	
	/* Hay una línea donde aparece un Aircraft y una Coordinate3D y se intenta poner en un Board2D */
	@Test
	public void testPutCraftsWrong2() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong2.in");
		try {
		  ip.putCrafts(board2d);
		  fail("Error: se debió lanzar IllegalArgumentException");
		}catch (IllegalArgumentException e) {
			compareLines(sboard0, board2d.show(true));
		}
		
	}
	
	/* Hay una línea donde aparece un Ship y una Coordinate2D y se intenta poner en un Board3D */
	@Test
	public void testPutCraftsWrong3() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong3.in");
		try {
		  ip.putCrafts(board3d);
		  fail("Error: no se lanzó BattleshipIOException");
		}catch (IllegalArgumentException e) {
			compareLines(sboard00, board3d.show(true));
		}
	}
	
	/* Hay una línea donde la orden es incorrecta (puts) */
	@Test
	public void testPutCraftsWrong4() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong4.in");
		try {
		  ip.putCrafts(board2d);
		  fail("Error: no se lanzó BattleshipIOException");
		} catch (BattleshipIOException e) {
		   compareLines(sboard2, board2d.show(true));
		   assertTrue (e.getMessage().length() > 1);
		}
	}
	
	/* Hay una línea donde aparecen dos orientaciones */
	@Test
	public void testPutCraftsWrong5() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong5.in");
		try {
		  ip.putCrafts(board2d);
		  fail("Error: no se lanzó BattleshipIOException");
		} catch (BattleshipIOException e) {
		   compareLines(sboard2, board2d.show(true));
		}
	}
	
	/* En un fichero hay una línea con solo comando y Craft*/
	@Test
	public void testPutCraftsWrong6() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong6.in");
		try {
		  ip.putCrafts(board2d);
		  fail("Error: no se lanzó BattleshipIOException");
		} catch (BattleshipIOException e) {
		   compareLines(sboard2, board2d.show(true));
		}
	}
	
	/* Ordenes con varios espacios en blanco y tabuladores. Se añaden los ships y al final se añade
	 * un ship que colisiona, está próximo a otro, y cae fuera del tablero */
	@Test
	public void testPutCraftsWrong7() throws BattleshipIOException,  NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong7.in");
		try {
		  ip.putCrafts(board2d);
		} catch (InvalidCoordinateException e) {
		   compareLines(sboard1, board2d.show(true));
		}
	}
	
	/* Hay una línea put con solo 1 componente para la Coordinate y en otro fichero otra con 4 componentes */
	@Test
	public void testPutCraftsWrong8() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong8_1.in");
		try {
		  ip.putCrafts(board2d);
		  fail("Error: no se lanzó BattleshipIOException");
		} catch (BattleshipIOException e) {
		   compareLines(sboard2, board2d.show(true));
		}
		ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong8_2.in");
		try {
		  ip.putCrafts(board2d);
		  fail("Error: no se lanzó BattleshipIOException");
		}catch (BattleshipIOException e) {
			compareLines(sboard2, board2d.show(true));
		} 
	}

	/* Se pasa un Board null a putCrafts */
	@Test(expected=NullPointerException.class)
	public void testPutCraftsNullPointerException() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong8_1.in");
		ip.putCrafts(null);
	}
	
	
	/* En un fichero hay una línea con coordenadas no numéricas */
	@Test
	public void testPutCraftsWrong9() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong9.in");
		try {
		  ip.putCrafts(board2d);
		  fail("Error: no se lanzó BattleshipIOException");
		} catch (BattleshipIOException e) {
		   compareLines(sboard2, board2d.show(true));
		}
	}
	
	/* En un fichero hay una línea con una orientación del Craft errónea */
	@Test
	public void testPutCraftsWrong10() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong10.in");
		try {
		  ip.putCrafts(board2d);
		  fail("Error: no se lanzó BattleshipIOException");
		} catch (BattleshipIOException e) {
		   compareLines(sboard2, board2d.show(true));
		}
	}
	
	/* Se pasan ficheros donde, en uno hay un posicionamiento de un Craft fuera del tablero, otro con un 
	 * solapamiento de Crafts y otro con dos Crafts próximos
	 *  */
	@Test
	public void testPutCraftsWrong11() throws Exception {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong11_occupied.in");
		try {
			  ip.putCrafts(board2d);
			  fail("Error: no se lanzó OccupiedCoordinateException");
		} catch (OccupiedCoordinateException e) {
			   compareLines(sboard2, board2d.show(true));
		}
		setUp();
		ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong11_out.in");
		try {
			  ip.putCrafts(board2d);
			  fail("Error: no se lanzó InvalidCoordinateException");
		} catch (InvalidCoordinateException e) {
			   compareLines(sboard2, board2d.show(true));
		}
		setUp();
		ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong11_next.in");
		try {
			  ip.putCrafts(board2d);
			  fail("Error: no se lanzó NextToAnotherCraftException");
		} catch (NextToAnotherCraftException e) {
			   compareLines(sboard2, board2d.show(true));
			   assertTrue (e.getMessage().length() > 1);
		}		 
	}
	
	//TESTS PARA nextShoot
	@Test
	public void testNextShootOk1() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		Coordinate[] vc = new Coordinate[5];
		for (int i=0; i<5; i++) vc[i]=new Coordinate2D(i,i+1);
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootOk1.in");
		Coordinate c;
		int i=0;
		while ((c=ip.nextShoot(board2d))!=null) {
			assertEquals(vc[i],c);
			i++;
		}
	}
	
	/* Se realizan disparos antes y después de un exit. Se comprueba que los posteriores
	 * no se han tomado en cuenta.
	 */
	@Test
	public void testNextShootOk2() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		Coordinate[] vc = new Coordinate[5];
		for (int i=0; i<5; i++) vc[i]=new Coordinate2D(i,i+1);
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootOk2.in");
		int i=0;
		boolean exit=false;
		Coordinate c=CoordinateFactory.createCoordinate(0,0);
		while (c!=null && !exit) {
			c=ip.nextShoot(board2d);
			if ( c!=null) { 
			   assertEquals(vc[i],c);
			   i++;
			}
			else exit=true;
		}
		assertEquals(3,i);
	}
	
	/*Lee disparos en un fichero vacío.*/
	@Test
	public void testNextShootEmpty() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootEmpty.in");
		assertNull(ip.nextShoot(board2d));
	}
	
    /* Lee una orden put y otra endput */
	@Test(expected=BattleshipIOException.class)
	public void testNextShootWrong1() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootWrong1.in");
		try {
		  ip.nextShoot(board2d);
		  fail("Error: no se lanzó BattleshipIOException");
		}catch (BattleshipIOException e) {
			assertTrue (e.getMessage().length() > 1);
			ip.nextShoot(board2d);
		}
	}
	
	/* Lee una orden incorrecta, una orden sin coordenadas, una con 1 coordenada y 
	 * otra con 4 coordenadas*/
	@Test
	public void testNextShootWrong2() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootWrong2.in");
		Coordinate c=CoordinateFactory.createCoordinate(0,0);
		int cont=0;
		while (c!=null)
		try {
			c=ip.nextShoot(board2d);
			if (c!=null)
			  fail("Error: no se lanzó BattleshipIOException");	
		}catch (BattleshipIOException e) {
				assertTrue (e.getMessage().length() > 1);	
				cont++;
		}
		assertEquals(4,cont);
	}
	
	/* Lee 4 órdenes con coordenadas no numéricas */
	@Test
	public void testNextShootWrong3() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootWrong3.in");
		Coordinate c=CoordinateFactory.createCoordinate(0,0);
		int cont=0;
		while (c!=null)
		try {
			c=ip.nextShoot(board2d);
			if (c!=null) fail("Error: no se lanzó BattleshipIOException");
		}catch (BattleshipIOException e) {
				assertTrue (e.getMessage().length() > 1);
				cont++;
		}
		assertEquals(cont,4);
	}
	
	 /* Se posiciona un Craft sobre un tablero.
	  * Se leen 7 disparos a coordenadas fuera del tablero. Se debe lanzar InvalidCoordinateException en todos los
	  * casos. Luego se dispara dos veces sobre mismas posiciones del Craft. Se debe lanzar CoordinateAlreadyHitException
	  * en los segundos disparos.	  
	  */
	 @Test
	public void testNextShootWrong4() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootWrong4.in");
		Coordinate c=CoordinateFactory.createCoordinate(0,0);
		int cont=0;
		ip.putCrafts(board3d);
		//Disparos fuera del tablero
		for (int i=0; i<7; i++) {
		  try {
			c=ip.nextShoot(board3d);
			if (c!=null) fail("Error: no se lanzó InvalidCoordinateException");
		  }catch (InvalidCoordinateException e) {
				assertTrue (e.getMessage().length() > 1);
		  }
		}
		//Se realizan 4 disparos al Craft
		for (int i=0; i<4; i++) {
			c=ip.nextShoot(board3d);
		}
		//Se vuelve a disparar sobre las mismas posiciones del Craft
		while (c!=null) {
			try {
				c=ip.nextShoot(board3d);
				if (c!=null) fail("Error: no se lanzó CoordinateAlreadyHitException");
			  }catch (CoordinateAlreadyHitException e) {
					assertTrue (e.getMessage().length() > 1);
					cont++;
			  }	
		}
		assertEquals(cont,4);
	}
	 
	 
	/* Se pasa un Board null a nextShoot. Debe lanzar NullPointerException */
	@Test(expected=NullPointerException.class)
	public void testNextShootNullPointerException() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootOk1.in");
        ip.nextShoot(null);
	}
	
	/* Se le pasa por parámetro un fichero que no existe */
	@Test(expected=BattleshipIOException.class)
	public void testNextShootFileNotExist() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"NoExisteFichero.in");
        ip.nextShoot(null);
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
