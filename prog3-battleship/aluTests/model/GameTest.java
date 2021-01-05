package model;

import static org.junit.Assert.*;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.aircraft.Board3D;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;
import model.io.PlayerFactory;
import model.io.VisualiserFactory;
import model.ship.Board2D;

public class GameTest {

	final String DIRFILES = "test/files/";
	
	static String sGetScoreInfo00 = "Player 1\n" + 
			"HitScore: Belén (PlayerFile): 0\n" + 
			"CraftScore: Belén (PlayerFile): 0\n" + 
			"--------------\n" + 
			"Player 2\n" + 
			"HitScore: Sergio (PlayerFile): 0\n" + 
			"CraftScore: Sergio (PlayerFile): 0";
	static String sGetScoreInfo01 = "Player 1\n" + 
			"HitScore: Belén (PlayerFile): 7\n" + 
			"CraftScore: Belén (PlayerFile): 9\n" + 
			"--------------\n" + 
			"Player 2\n" + 
			"HitScore: Sergio (PlayerFile): 6\n" + 
			"CraftScore: Sergio (PlayerFile): 3";
	static String sGetScoreInfo02 = "Player 1\n" + 
			"HitScore: Belén (PlayerFile): 12\n" + 
			"CraftScore: Belén (PlayerFile): 17\n" + 
			"--------------\n" + 
			"Player 2\n" + 
			"HitScore: Sergio (PlayerFile): 12\n" + 
			"CraftScore: Sergio (PlayerFile): 17";
	
	static String sGetScoreInfo10 = "Player 1\n" + 
			"HitScore: Julia (PlayerRandom): 0\n" + 
			"CraftScore: Julia (PlayerRandom): 0\n" + 
			"--------------\n" + 
			"Player 2\n" + 
			"HitScore: Raul (PlayerRandom): 0\n" + 
			"CraftScore: Raul (PlayerRandom): 0";
	static String sGetScoreInfo11 = "Player 1\n" + 
			"HitScore: Julia (PlayerRandom): 18\n" + 
			"CraftScore: Julia (PlayerRandom): 3\n" + 
			"--------------\n" + 
			"Player 2\n" + 
			"HitScore: Raul (PlayerRandom): 10\n" + 
			"CraftScore: Raul (PlayerRandom): 9";
	
	static String sGetScoreInfo12 = "Player 1\n" + 
			"HitScore: Julia (PlayerRandom): 24\n" + 
			"CraftScore: Julia (PlayerRandom): 37\n" + 
			"--------------\n" + 
			"Player 2\n" + 
			"HitScore: Raul (PlayerRandom): 11\n" + 
			"CraftScore: Raul (PlayerRandom): 17";
	
	static String sGame00 = "Player 1\n" + 
			"HitScore: Belén (PlayerFile): 0\n" + 
			"CraftScore: Belén (PlayerFile): 0\n" + 
			"--------------\n" + 
			"Player 2\n" + 
			"HitScore: Laura (PlayerRandom): 0\n" + 
			"CraftScore: Laura (PlayerRandom): 0";
	
	static String sGame01 = "Player 1\n" + 
			"HitScore: Belén (PlayerFile): 20\n" + 
			"CraftScore: Belén (PlayerFile): 32\n" + 
			"--------------\n" + 
			"Player 2\n" + 
			"HitScore: Laura (PlayerRandom): 9\n" + 
			"CraftScore: Laura (PlayerRandom): 0";
	
	static String sGame1 ="Player 1\n" + 
			"HitScore: Sara (PlayerFile): 44\n" + 
			"CraftScore: Sara (PlayerFile): 69\n" + 
			"--------------\n" + 
			"Player 2\n" + 
			"HitScore: Laura (PlayerFile): 42\n" + 
			"CraftScore: Laura (PlayerFile): 56";
	static String sGame2 = "Player 1\n" + 
			"HitScore: Sara (PlayerRandom): 39\n" + 
			"CraftScore: Sara (PlayerRandom): 65\n" + 
			"--------------\n" + 
			"Player 2\n" + 
			"HitScore: Laura (PlayerRandom): 35\n" + 
			"CraftScore: Laura (PlayerRandom): 37";
	
	IPlayer player1, player2;
	Board board1, board2;
	Game game;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		player1= PlayerFactory.createPlayer("Belén", DIRFILES+"ShipsFile3.in");
		player2= PlayerFactory.createPlayer("Sergio",  DIRFILES+"ShipsFile3.in");
		board1 = new Board2D(7);
		board2 = new Board2D(7);
		game = new Game(board1, board2, player1, player2);
	}

	/* Se comprueba que se han iniciado los distintos scores en el constructor de Game
	 * 
	 */
	@Test
	public void testGame() {
		assertNotNull(game.getHitScorePlayer1());
		assertNotNull(game.getCraftScorePlayer1());
		assertNotNull(game.getHitScorePlayer1());
		assertNotNull(game.getCraftScorePlayer1());
	}

	/* Se parte de unos jugadores, del setUp(), que tienen los mismos Craft en las mismas posiciones sus
	 * respectivos tableros2D de tamaño 7, y van a realizar disparos idénticos. Se comprueba que los scores
	 * incialmente están a 0. 
	 * Se realizan varios lanzamientos y se va comprobando que los resultados son los que se pasan
	 * en el método auxiliar compareScores(int hitScoreP1, int hitScoreP2, int craftScoreP1, int craftScoreP2)
	 */
	@Test
	public void testPlayNext1() throws BattleshipIOException {
		player2= PlayerFactory.createPlayer("Sergio",  DIRFILES+"ShipsFile3.in");
		game.start();
		
		compareScores(0,0,0,0);
		for (int i=0; i<6; i++) game.playNext();
		
		compareScores(1,1,0,0);
		for (int i=0; i<5; i++) game.playNext();
		compareScores(4,3,3,0);
		for (int i=0; i<6; i++) game.playNext();
		compareScores(7,6,9,3);
		for (int i=0; i<6; i++) game.playNext();
		compareScores(8,7,9,9);
		for (int i=0; i<6; i++) game.playNext();
		compareScores(11,10,9,9);
		for (int i=0; i<6; i++) game.playNext();
		compareScores(12,12,17,17);
	}

	//Lo mismo que testPlayNext1() pero con Players aleatorios
	@Test
	public void testPlayNext2() throws BattleshipIOException {
		player1= PlayerFactory.createPlayer("Julia", "123");
		player2= PlayerFactory.createPlayer("Raul",  "321");
		
		game = new Game(board1, board2, player1, player2);
		game.start();
		compareScores(0,0,0,0);
		for (int i=0; i<6; i++) game.playNext();
		compareScores(1,1,0,0);
		for (int i=0; i<12; i++) game.playNext();
		compareScores(2,3,0,0);
		for (int i=0; i<24; i++) game.playNext();
		compareScores(7,7,0,5);
		for (int i=0; i<48; i++) game.playNext();
		compareScores(10,8,5,5);
		for (int i=0; i<96; i++) game.playNext();
		compareScores(11,12,8,14);
		for (int i=0; i<192; i++) game.playNext();
		compareScores(14,14,22,22);
	}
	
	//Comprobación de getScoreInfo después de realizar varias jugadas con playNext()
	@Test
	public void testGetScoreInfo0() throws BattleshipIOException {
		player2= PlayerFactory.createPlayer("Sergio",  DIRFILES+"ShipsFile3.in");
		game.start();
		compareScores(sGetScoreInfo00,game.getScoreInfo());
		
		for (int i=0; i<17; i++) game.playNext();
		compareScores(sGetScoreInfo01,game.getScoreInfo());
		
		for (int i=0; i<18; i++) game.playNext();
		compareScores(sGetScoreInfo02,game.getScoreInfo());
	}
	
	
	@Test
	public void testGetScoreInfo1() throws BattleshipIOException {
		player1= PlayerFactory.createPlayer("Julia", "123");
		player2= PlayerFactory.createPlayer("Raul",  "321");
		board1 = new Board3D(6);
		board2 = new Board3D(6);
		game = new Game(board1, board2, player1, player2);
		game.start();
		compareScores(sGetScoreInfo10,game.getScoreInfo());
		for (int i=0; i<500; i++) game.playNext();
		compareScores(sGetScoreInfo11,game.getScoreInfo());
		for (int i=0; i<1053; i++) game.playNext();
		compareScores(sGetScoreInfo12,game.getScoreInfo());
	}

	/* Realización de varias partidas y comprobación posterior de los resultados
	 */
	@Test
	public void testPlayGame0() throws BattleshipIOException {
		
		final String outFile = DIRFILES+"testPlayGame2.alu";
		player2= PlayerFactory.createPlayer("Laura", "1");
		player1= PlayerFactory.createPlayer("Belén", DIRFILES+"testPlayGame2.in");
		board1 = new Board3D(7);
		board2 = new Board3D(7);
		game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("Console", game);
		compareScores(sGame00, game.getScoreInfo());
		game.playGame(iv);
		compareScores(20,9,32,0);
		compareScores(sGame01,game.getScoreInfo());	
			
	}
	
	@Test
	public void testPlayGame1() throws BattleshipIOException {
		player1= PlayerFactory.createPlayer("Sara", "test/files/testPlayGame3Sara.in");
		player2= PlayerFactory.createPlayer("Laura", "test/files/testPlayGame3Laura.in");
	
		board1 = new Board2D(15);
		board2 = new Board2D(15);
		game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("Console", game);
		game.playGame(iv);
		compareScores(sGame1,game.getScoreInfo());	
		compareScores(44,42,69,56);	
	}
	
	@Test
	public void testPlayGame2() throws BattleshipIOException {
		player1= PlayerFactory.createPlayer("Sara", "77");
		player2= PlayerFactory.createPlayer("Laura", "98");
	
		board1 = new Board3D(10);
		board2 = new Board3D(10);
		game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("Console", game);
		game.playGame(iv);
		compareScores(sGame2,game.getScoreInfo());	
		compareScores(39,35,65,37);	
	}
	
	/********************************************
	 * FUNCIONES AUXILIARES
	 * ******************************************
	 */
	void compareScores(int hitScoreP1, int hitScoreP2, int craftScoreP1, int craftScoreP2) {
		assertEquals(hitScoreP1,game.getHitScorePlayer1().getScore());
		assertEquals(hitScoreP2,game.getHitScorePlayer2().getScore());
		assertEquals(craftScoreP1,game.getCraftScorePlayer1().getScore());
		assertEquals(craftScoreP2,game.getCraftScorePlayer2().getScore());
	}
	
	/* Para las salidas game.getScoreInfo() compara los valores impresos
	 * de los Scores hasta una precisión de 0.01
	 * 
	 */
	private void compareScores(String expected, String result ) {
		String ex[]= expected.split(":");
		String re[]= result.split(":");
		if (ex.length!=re.length) fail("Lineas distintas");
		if (ex.length==2) {
			if (removeSpaces(ex[0]).equals(removeSpaces(re[0]))) {
				double ed = Double.parseDouble(ex[1]);
				double rd = Double.parseDouble(re[1]);
		
				assertEquals(ex[0],ed,rd,0.01);
			}
			else fail("Nombres jugadores distintos: esperado=<"+ex[0].trim()+"> obtenido=<"+re[0].trim()+">");
		}
		else
			assertEquals(removeSpaces(expected),removeSpaces(result));		
	}	
	
	private String removeSpaces (String str) {
		String exp[]=str.split(" ");
		String nstr=new String("");
		for (String s: exp) {
			if (! s.equals(" ") ) nstr+=s; 
		}
		return nstr;
	}
}
