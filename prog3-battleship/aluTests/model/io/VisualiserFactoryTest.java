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
	public void testCreateVisualiserConsole()  {
		IVisualiser iv=VisualiserFactory.createVisualiser("Console", game);
		assertEquals("VisualiserConsole",iv.getClass().getSimpleName());	
	}

	@Test
	public void testCreateVisualiserGIF()  {
		IVisualiser iv=VisualiserFactory.createVisualiser("GIF", game);
		assertEquals("VisualiserGIF",iv.getClass().getSimpleName());
	}

	/* Comprueba que se crean visualizadores desconocidos en tiempo de compilación pero conocidos en tiempo de ejecución */
	@Test
	public void testCreateVisualiserQT()  {
		IVisualiser iv=VisualiserFactory.createVisualiser("QT", game);
		assertTrue(iv instanceof VisualiserQT);
	}



	@Test
	public void testCreateVisualiserError2()  {
	  try {
		assertNull (VisualiserFactory.createVisualiser("gif", game));
	  }
	  catch(NoClassDefFoundError error) {
		  // en algunos casos, puede que Class.forName lance este error y no la de ClassNotFoundException
		  // lo capturamos aquí y damos por bueno el test si es el caso.
	  }
	}


}
