package model.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Board;
import model.Game;
import model.exceptions.io.BattleshipIOException;
import model.ship.Board2D;

public class VisualiserFactoryPreTest {
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

	/* Crea un VisualiserConsole con createVisualiser. Comprueba que
	 * efectivamente se ha creado un objeto VisualiserConsole 
	 */
	@Test
	public void testCreateVisualiserConsole() throws BattleshipIOException {
		IVisualiser vi = VisualiserFactory.createVisualiser("Console", game);
		assertEquals("testCreateVisualiserConsole: The class is not the same", vi.getClass(), VisualiserConsole.class);
	}

	/* Crea un VisualiserGIF con createVisualiser. Comprueba que
	 * efectivamente se ha creado un objeto VisualiserGIF 
	 */
	@Test
	public void testCreateVisualiserGIF() throws BattleshipIOException {
		IVisualiser vi = VisualiserFactory.createVisualiser("GIF", game);
		assertEquals("testCreateVisualiserGIF: The class is not the same", vi.getClass(), VisualiserGIF.class);
	}
	
	/* Pasale nulos a los parámetros de VisualiserFactory
	 * 
	 */
	@Test
	public void testVisualiserFactoryWithNull() {
		assertNull("Error en cadena vacía: ", VisualiserFactory.createVisualiser("", game));
		try {
			assertNull("Error en cadena nula: ", VisualiserFactory.createVisualiser(null, game));
		} catch (NullPointerException e) {
			// OK
		}
		assertNull("Error en game null: ", VisualiserFactory.createVisualiser("VisualiserConsole", null));
	}
	
}