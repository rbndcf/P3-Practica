package model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.Board;
import model.CellStatus;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.aircraft.Board3D;
import model.exceptions.BattleshipException;
import model.exceptions.io.BattleshipIOException;
import model.ship.Board2D;

public class IPlayerTest {
	
	final static String DIRFILES = "test/files/";
	final int SIZE = 7;
	Board board2d, board3d;
	Craft craft;
	IPlayer ipr, ipf;
	@Before
	public void setUp() throws Exception {
		board2d =  new Board2D(SIZE);
		board3d =  new Board3D(SIZE);
		ipr = PlayerFactory.createPlayer("Saul","65");
		ipf = PlayerFactory.createPlayer("Monica", DIRFILES+"ShipsFile1.in");
		
	}

	/* Se comprueba que putCrafts se ha modificado para incluir el nombre del subpaquete ship o aircraft
	 * 
	 */
	@Test
	public void testPutCrafts() throws BattleshipIOException {
		PlayerRandom ip = (PlayerRandom) ipr;
		ip.putCrafts(board3d);
		craft = CraftFactory.createCraft("ship.Carrier",Orientation.SOUTH);
		for (int i=1; i<SIZE-1; i++) 
			assertEquals("(0,"+i+",0)",craft.toString(), board3d.getCraft(CoordinateFactory.createCoordinate(0,i,0)).toString());
		
		craft = CraftFactory.createCraft("ship.Cruiser",Orientation.SOUTH);
		for (int i=2; i<SIZE-2; i++) 
			assertEquals("(3,"+i+",1)",craft.toString(), board3d.getCraft(CoordinateFactory.createCoordinate(3,i,1)).toString());
		
		craft = CraftFactory.createCraft("ship.Battleship",Orientation.SOUTH);
		for (int i=3; i<SIZE; i++) 
			assertEquals("(4,"+i+",4)",craft.toString(), board3d.getCraft(CoordinateFactory.createCoordinate(4,i,4)).toString());
		
		craft = CraftFactory.createCraft("ship.Destroyer",Orientation.NORTH);
		for (int i=5; i<SIZE; i++) 
			assertEquals("(0,"+i+",6)",craft.toString(), board3d.getCraft(CoordinateFactory.createCoordinate(0,i,6)).toString());
		
		craft = CraftFactory.createCraft("aircraft.Transport",Orientation.SOUTH);
		for (int i=2; i<SIZE; i++) {
			assertEquals("(4,"+i+",6)",craft.toString(), board3d.getCraft(CoordinateFactory.createCoordinate(4,i,6)).toString());
		    if ((i>2)&&(i<6))
		    	assertEquals("("+i+",4,6)",craft.toString(), board3d.getCraft(CoordinateFactory.createCoordinate(i,4,6)).toString());
		}
		assertEquals(craft.toString(), board3d.getCraft(CoordinateFactory.createCoordinate(2,3,6)).toString());
		assertEquals(craft.toString(), board3d.getCraft(CoordinateFactory.createCoordinate(6,3,6)).toString());
	}

	/* Se crea un PlayerFile y se ponen los Crafts en un Board2D. Se van leyendo y realizando los disparos y 
	 * comparando getLastShotStatus con el resultado que debe de dar.
	 */
	@Test
	public void testNextShoot() throws BattleshipException {
		ipf = PlayerFactory.createPlayer("Monica", DIRFILES+"ShipsFile2.in");
		ipf.putCrafts(board2d);
		ipf.nextShoot(board2d);
		assertEquals(CellStatus.WATER, ipf.getLastShotStatus());
		ipf.nextShoot(board2d);
		assertEquals(CellStatus.HIT, ipf.getLastShotStatus());
		ipf.nextShoot(board2d);
		assertEquals(CellStatus.WATER, ipf.getLastShotStatus());
		ipf.nextShoot(board2d);
		assertEquals(CellStatus.HIT, ipf.getLastShotStatus());
		ipf.nextShoot(board2d);
		assertEquals(CellStatus.HIT, ipf.getLastShotStatus());
		ipf.nextShoot(board2d);
		assertEquals(CellStatus.DESTROYED, ipf.getLastShotStatus());		
	}

	/* Se comprueban todos los casos en que getLastShotStatus() tiene que devolver null
	 * 
	 */
	@Test
	public void testGetLastShotStatusNull() throws  BattleshipException {
		//Para PlayerFile
		assertNull(ipf.getLastShotStatus());
		try {
			ipf.nextShoot(board2d);
			fail("Error, no se lanzó BattleshipIOException");
		} catch (BattleshipIOException e) {
			assertNull(ipf.getLastShotStatus());
		}
		ipf = PlayerFactory.createPlayer("Monica", DIRFILES+"ShipsFile2.in");
		ipf.putCrafts(board2d);
		for (int i=0; i<6; i++)
			ipf.nextShoot(board2d);
		ipf.nextShoot(board2d);
		assertNull(ipf.getLastShotStatus());
		
		//Para PlayerRandom
		assertNull(ipr.getLastShotStatus());	
	}

}
