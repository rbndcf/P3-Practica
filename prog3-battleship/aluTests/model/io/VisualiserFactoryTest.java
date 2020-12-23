package model.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Board;
import model.Game;
import model.exceptions.io.BattleshipIOException;
import model.ship.Board2D;

public class VisualiserFactoryTest {
	Game game;
	IPlayer player1, player2; 
	Board board1, board2;
	
	@Before
	public void setUp() throws Exception {
		player1= PlayerFactory.createPlayer("Gregory", "3");
		player2= PlayerFactory.createPlayer("Raul", "1");
		board1 = new Board2D(5);
		board2 = new Board2D(5);
		game = new Game(board1, board2, player1, player2);
	}

	
	@Test
	public void testCreateVisualiserConsole() throws BattleshipIOException {
		IVisualiser iv=VisualiserFactory.createVisualiser("Console", game);
		assertEquals("VisualiserConsole",iv.getClass().getSimpleName());	
	}

	@Test
	public void testCreateVisualiserGIF() throws BattleshipIOException {
		IVisualiser iv=VisualiserFactory.createVisualiser("GIF", game);
		assertEquals("VisualiserGIF",iv.getClass().getSimpleName());
	}
	
	@Test
	public void testCreateVisualiserError() throws BattleshipIOException {
		
		assertNull (VisualiserFactory.createVisualiser("GIFs", game));
		assertNull (VisualiserFactory.createVisualiser("gif", game));
		assertNull (VisualiserFactory.createVisualiser("console", game));
		assertNull (VisualiserFactory.createVisualiser("CONSOLE", game));
	}
}
