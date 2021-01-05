package model;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import model.aircraft.Board3D;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;
import model.io.PlayerFactory;
import model.io.VisualiserFactory;
import model.ship.Board2D;

public class GameTestP4 {

	final String DIRFILES = "aluTests/files/";
	Game game;
	IPlayer player1, player2; 
	Board board1, board2;
	
	@Before
	public void setUp() throws Exception {
		player1= PlayerFactory.createPlayer("Julia", DIRFILES+"ShipsFile1.in");
		player2= PlayerFactory.createPlayer("Raul", "21");
		board1 = new Board2D(7);
		board2 = new Board2D(7);
		game = new Game(board1, board2, player1, player2);
	}

	@Test(expected=NullPointerException.class)
	public void testGameNullPointerException() throws BattleshipIOException {
		try {
		  new Game(null, board2, player1, player2);
		  fail ("Error: se debió lanzar NullPointerException");
		} catch (NullPointerException e) {
		}
		try {
			  new Game(board1, null, player1, player2);
			  fail ("Error: se debió lanzar NullPointerException");
		} catch (NullPointerException e) {
		}
		try {
			  new Game(board1, board2, null, player2);
			  fail ("Error: se debió lanzar NullPointerException");
		} catch (NullPointerException e) {
		}
		new Game(board1, board2, player1, null);
		fail ("Error: se debió lanzar NullPointerException");
	}

	/*Prueba de los getPlayers y su relación de asociación con Game */
	@Test
	public void testGetPlayers() {
		assertSame(player1,game.getPlayer1());
		assertSame(player2,game.getPlayer2());
	}

	/*Prueba de los getBoards y su relación de asociación con Game */
	@Test
	public void testGetBoards() {
		assertSame(board1, game.getBoard1());
		assertSame(board2, game.getBoard2());
	}

	@Test
	public void testStartGetPlayerLastShoot() {
		assertNull(game.getPlayerLastShoot());
		game.start();
		assertNull(game.getPlayerLastShoot());
		assertFalse(game.gameEnded());
		game.playNext();
		assertSame(player1,game.getPlayerLastShoot());
		assertFalse(game.gameEnded());
		game.playNext();
		assertSame(player2,game.getPlayerLastShoot());
		assertFalse(game.gameEnded());
		game.playNext();
		assertSame(player1,game.getPlayerLastShoot());
		assertFalse(game.gameEnded());
	}

	/* Se inicia la partida con Game.start usando ficheros que ponen Crafts de forma incorrecta.
	 * También con ficheros con órdenes incorrectas. En todos los casos se debe lanzar la excepción
	 * RuntimeException
	 */
	@Test
	public void testStartWithExceptions() throws BattleshipIOException {
		//Pone Craft en posición ocupada
		player1= PlayerFactory.createPlayer("Julia", DIRFILES+"ShipsWrong11_occupied.in");
		player2= PlayerFactory.createPlayer("Raul", "21");
		game = new Game(board1, board2, player1, player2);
		try {
			game.start();
			fail("Error, se debío lanzar RuntimeException");
		} catch (RuntimeException e) { }
		
		//Pone Craft en posición próxima a otro Craft
		board1 = new Board2D(7);
		board2 = new Board2D(7);
		player1= PlayerFactory.createPlayer("Julia", DIRFILES+"ShipsWrong11_next.in");
		game = new Game(board1, board2, player1, player2);
		try {
			game.start();
			fail("Error, se debío lanzar RuntimeException");
		} catch (RuntimeException e) { }
		
		//Pone Craft en posición fuera de tablero.
		board1 = new Board2D(7);
		board2 = new Board2D(7);
		player1= PlayerFactory.createPlayer("Julia", DIRFILES+"ShipsWrong11_out.in");
		game = new Game(board1, board2, player1, player2);
		try {
			game.start();
			fail("Error, se debío lanzar RuntimeException");
		} catch (RuntimeException e) { }
		
		//Fichero con una orden incorrecta
		board1 = new Board2D(7);
		board2 = new Board2D(7);
		player1= PlayerFactory.createPlayer("Julia", DIRFILES+"ShipsWrong4.in");
		game = new Game(board1, board2, player1, player2);
		try {
			game.start();
			fail("Error, se debío lanzar RuntimeException");
		} catch (RuntimeException e) { }
		
	}
	
	
	/* Se crea un PlayerFile con una orden de disparo incorrecta y otra con disparo a una coordenada 
	 * fuera del tablero. En los dos casos se debe lanzar la excepción RuntimeException
	 */
	@Test(expected=RuntimeException.class)
	public void testPlayNextWithExceptions() throws BattleshipIOException {
		//Pone Craft en posición ocupada
		player1= PlayerFactory.createPlayer("Julia", DIRFILES+"ShootOk3.in");
		player2= PlayerFactory.createPlayer("Raul", DIRFILES+"ShootWrong5.in");
		game = new Game(board1, board2, player1, player2);

		try {
		  game.start();
		  game.playNext();
		  game.playNext();
		  fail("Error, se debío lanzar RuntimeException");
		} catch (RuntimeException e) {
		  	  game.playNext();
		}
	}

	/* Se imprime el game en sus tres estados: Not Started, Ongoing, Ended
	 * 
	 */
	@Test
	public void testToString() throws BattleshipIOException {
		player1= PlayerFactory.createPlayer("Julia", DIRFILES+"GameFile1.in");
		player2= PlayerFactory.createPlayer("Raul", DIRFILES+"GameFile2.in");
		game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("Console", game);
		compareLines (readFromFile(DIRFILES+"GameNotStarted.sol").toString(),game.toString());
		game.start();
		game.playNext();
		game.playNext();
		compareLines (readFromFile(DIRFILES+"OngoingGame.sol").toString(),game.toString());
		for (int i=0; i<13; i++) game.playNext();
		compareLines (readFromFile(DIRFILES+"GameEnded.sol").toString(),game.toString());
		assertTrue(game.gameEnded());
	}
	
	/* Partida 0. Un jugador es aleatorio y otro a través de fichero. Tablero 3D
	 * de tamaño 7. Antes del último disparo que le daría el triunfo a Paul hay un exit. Se comprueba que la partida
	 * no ha acabado.
	 */
	@Test
	public void testPlayGameNotEnded() throws BattleshipIOException {
		final String outFile = "test/files/testPlayGame0.alu";
		player1= PlayerFactory.createPlayer("Lorena", "1");
		player2= PlayerFactory.createPlayer("Paul", DIRFILES+"testPlayGameNotEnded.in");
		board1 = new Board3D(7);
		board2 = new Board3D(7);
		game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("Console", game);
		game.playGame(iv);
		assertFalse(game.gameEnded());
		
	}
	
	
	/* Partida 1. Ambos jugadores colocan sus Ships a partir de la misma semilla. Deben generar tableros 2D
	 * iguales. Partida aleatoria.
	 */
	@Test
	public void testPlayGame1() throws BattleshipIOException {
		final String outFile = DIRFILES+"testPlayGame1.alu";
		player1= PlayerFactory.createPlayer("Mary", "15");
		player2= PlayerFactory.createPlayer("Raul", "15");
		board1 = new Board2D(5);
		board2 = new Board2D(5);
		game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("Console", game);
		PrintStream ps = standardIO2File(outFile);
		if (ps!=null) {
			game.playGame(iv);
			assertTrue(game.gameEnded());
			System.setOut(System.out); //Reestablecemos la salida standard
			ps.close();
		} 
		else fail("Error: no se pudo crear el fichero "+outFile);
		
		//Se compara salida del alumno con la solución
		StringBuilder sbSolution=readFromFile(DIRFILES+"testPlayGame1.sol");
		StringBuilder sbStudent=readFromFile(DIRFILES+"testPlayGame1.alu");
		compareLines(sbSolution.toString(),sbStudent.toString());
		
	}
	
	/* Partida 2. Un jugador es aleatorio y otro a través de fichero. Tablero 3D
	 * de tamaño 7. Gana Paul y su último disparo será en la Coordinate (6, 3, 6)
	 * A partir de ahí ya no se dispara más.
	 */
	@Test
	public void testPlayGame2() throws BattleshipIOException {
		final String outFile = "aluTests/files/testPlayGame2.alu";
		player1= PlayerFactory.createPlayer("Lorena", "1");
		player2= PlayerFactory.createPlayer("Paul", DIRFILES+"testPlayGame2.in");
		board1 = new Board3D(7);
		board2 = new Board3D(7);
		game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("Console", game);
		PrintStream ps = standardIO2File(outFile);
		if (ps!=null) {
			game.playGame(iv);
			assertTrue(game.gameEnded());
			System.setOut(System.out); //Reestablecemos la salida standard
			ps.close();
		} 
		else fail("Error: no se pudo crear el fichero "+outFile);
		
		//Se compara salida del alumno con la solución
		StringBuilder sbSolution=readFromFile(DIRFILES+"testPlayGame2.sol");
		StringBuilder sbStudent=readFromFile(DIRFILES+"testPlayGame2.alu");
		compareLines(sbSolution.toString(),sbStudent.toString());
	}

	/* Partida 3. Dos jugadores a través de ficheros. Tableros 2D
	 * de tamaño 15. Gana Sara y su último disparo será en la Coordinate (14, 7)
	 * A partir de ahí ya no se dispara más.
	 * TABLEROS INICIALES
	 *      SARA    			LAURA
	 *  ---------------   --------------- 
	 * |           ®   | | ΩΩ           Ø|
 	   | OOOO Ø    ®  O| |              Ø|
       | 	  Ø    ®  O| |     ®  ΩΩ    Ø|
       |	  Ø    ®  O| |ØØØ  ®         |
   	   |   ΩΩ      ®  O| |     ®    ØØØ  |
       |        	   | |     ®         |
	   | ®®®®®    Ω    | |OOOO ®        Ω|
       |		  Ω  Ø | |        ΩΩ    Ω|
 	   | ØØØ         Ø | |               |
       |			 Ø | |               |
 	   | ΩΩ    O       | |O  Ø   ®®®®®   |
       |	   O    Ω  | |O  Ø           |
       |	   O    Ω  | |O  Ø  Ω        |
	   |ΩΩ     O       | |O     Ω   OOOO |
       |           ØØØ | |               |
        ---------------   ---------------
	 */
	@Test
	public void testPlayGame3() throws BattleshipIOException {
		final String outFile = "aluTests/files/testPlayGame3.alu";
		player1= PlayerFactory.createPlayer("Sara", DIRFILES+"testPlayGame3Sara.in");
		player2= PlayerFactory.createPlayer("Laura", DIRFILES+"testPlayGame3Laura.in");
	
		board1 = new Board2D(15);
		board2 = new Board2D(15);
		game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("Console", game);
		PrintStream ps = standardIO2File(outFile);
		if (ps!=null) {
			game.playGame(iv);
			System.setOut(System.out); //Reestablecemos la salida standard
			ps.close();
		} 
		else fail("Error: no se pudo crear el fichero "+outFile);
		
		//Se compara salida del alumno con la solución
		StringBuilder sbSolution=readFromFile(DIRFILES+"testPlayGame3.sol");
		StringBuilder sbStudent=readFromFile(DIRFILES+"testPlayGame3.alu");
		compareLines(sbSolution.toString(),sbStudent.toString());
	}
	
	
	/* Partida 4. Dos jugadores aleatorios. Tablero 3D
	 * de tamaño 10. 
	 */
	@Test
	public void testPlayGame4() throws BattleshipIOException {
		final String outFile = DIRFILES+"testPlayGame4.alu";
		player1= PlayerFactory.createPlayer("Lorena", "34");
		player2= PlayerFactory.createPlayer("Samuel", "55");
		board1 = new Board3D(10);
		board2 = new Board3D(10);
		game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("Console", game);
		PrintStream ps = standardIO2File(outFile);
		if (ps!=null) {		
			game.playGame(iv);
			System.setOut(System.out); //Reestablecemos la salida standard
			ps.close();
		} 
		else fail("Error: no se pudo crear el fichero "+outFile);
		
		//Se compara salida del alumno con la solución
		StringBuilder sbSolution=readFromFile(DIRFILES+"testPlayGame4.sol");
		StringBuilder sbStudent=readFromFile(DIRFILES+"testPlayGame4.alu");
		compareLines(sbSolution.toString(),sbStudent.toString());
	}
	
	
	/* Partida 4. Dos jugadores aleatorios. Tablero 3D
	 * de tamaño 10. 
	 */
	@Test
	public void testPlayGame5() throws BattleshipIOException, IOException {
		final String outFile = DIRFILES+"testPlayGame5.alu";
		player1= PlayerFactory.createPlayer("Silvia", "7");
		player2= PlayerFactory.createPlayer("Alex", DIRFILES+"GameFile3.in");
		board1 = new Board3D(7);
		board2 = new Board3D(7);
		game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("GIF", game);
		game.playGame(iv);
		//Se compara salida del alumno con la solución
		assertTrue(compareFiles(DIRFILES+"testPlayGame5.gif","files/output.gif"));
	}
	
	
	/*************************
	 * FUNCIONES AUXILIARES
	 *************************/
	//Redirección de la salida estandard a un fichero	
	public static PrintStream standardIO2File(String fileName){

        if(fileName.equals("")){//Si viene vacío usamos este por defecto
            fileName="C:\\javalog.txt";
        }
        PrintStream ps=null;
        try {
            //Creamos un printstream sobre el archivo.
            ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(fileName))),true);
            //Redirigimos salida estandar
            System.setOut(ps);
           // System.setErr(ps);
        } catch (FileNotFoundException ex) {
            System.err.println("Se ha producido una excepción FileNotFoundException");
        }
        return ps;
    }

	//Lee la solución de un fichero y la devuelve en un StringBuilder	
	private StringBuilder readFromFile(String file) {
		Scanner sc=null;
		try {
				sc = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		while (sc.hasNext()) 
			sb.append(sc.nextLine()+"\n");			
		sc.close();
		return (sb);
	}
	
	//Compara dos Strings línea a línea
	private void  compareLines(String expected, String result) {
		String exp[]=expected.split("\n");
		String res[]=result.split("\n");
		boolean iguales = true;
		if (exp.length!=res.length) 
			fail("Cadena esperada de tamaño ("+exp.length+") distinto a la resultante ("+res.length+")");
		for (int i=0; i<exp.length && iguales; i++) {
			 if (! exp[i].contains("Action by")) {
				 assertEquals("linea "+i, exp[i].trim(),res[i].trim());
			 }
		}
	}
	
	 String getFileExtension(String name) {
	        int extIndex = name.lastIndexOf(".");

	        if (extIndex == -1) {
	            return "";
	        } else {
	            return name.substring(extIndex + 1);
	        }
	    }
	 
	 //Compara dos ficheros gifs
	 boolean compareFiles(String f1, String f2) throws IOException {
		String comando = null;
		if (! getFileExtension(f1).equals("gif"))
			throw new IOException("Error: No es un fichero gif");
		
		if (!new File(f1).exists())  {
			System.out.println("El fichero "+f1+ "no existe.");
			return false;
		}
		if (!new File(f2).exists())  {
			System.out.println("El fichero "+f2+ "no existe.");
			return false;
		}
		try {
			comando = new String("cmp -b "+f1+" "+f2);
			// Ejecutamos el comando definido
			Process p = Runtime.getRuntime().exec(comando);
     
			// Instanciamos un lector del buffer para mostrar resultado
			BufferedReader resultado = new BufferedReader(new InputStreamReader(p.getInputStream()));
			// System.out.println("Resultado del comando:");
			String diferencias = resultado.readLine();
			if (diferencias!=null && diferencias.length()!=0) {
				while (diferencias!= null){
					System.out.println(diferencias);
					diferencias=resultado.readLine();
				}
				return false;
			} 
			else return true;
		} catch (IOException ex) {
       return false;
		}
	}
}
