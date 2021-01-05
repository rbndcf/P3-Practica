package model.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import model.Board;
import model.Craft;
import model.Game;
import model.Orientation;
import model.aircraft.Board3D;
import model.aircraft.Coordinate3D;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.io.gif.AnimatedGIF;
import model.ship.Battleship;
import model.ship.Board2D;
import model.ship.Carrier;
import model.ship.Coordinate2D;
import model.ship.Cruiser;
import model.ship.Destroyer;


public class VisualiserGIFTest {

	final String DIRFILES = "aluTests/files/";
	Game game;
	IPlayer player1, player2; 
	Board board1, board2;
	Craft destroyer, carrier, battleship, cruiser;
	
	
	@Before
	public void setUp() throws Exception {
		board1 = new Board2D(10);
		board2 = new Board2D(10);
		player1 = PlayerFactory.createPlayer("Raul","46");
		player2 = PlayerFactory.createPlayer("Carol","21");
		destroyer = new Destroyer(Orientation.EAST);
		carrier = new Carrier(Orientation.SOUTH);
		battleship = new Battleship(Orientation.NORTH);
		cruiser = new Cruiser(Orientation.WEST);
	}

	/* Se comprueba que pasándo un game null al constructor visualserGIF se
	 * lanza NullPointerException. Se comprueba también la relación de asociación entre 
	 * VisualiserGIF y Game usando la reflexión.
	 */
	@Test
	public void testVisualiserGIF() throws Exception {
		game = new Game(board1, board2, player1, player2);
		try {
			new VisualiserGIF(null);
			fail("Error: debería haberse lanzado NullPointerException");
		} catch (NullPointerException e) {
			IVisualiser vc= new VisualiserGIF(game);
			assertSame(game, (Game) getClassField(vc,"game"));
			assertNotNull((AnimatedGIF)getClassField(vc,"agif"));
		}
	}

	/* Se aplica show a un Game con 2 Board2D con el juego sin empezar.
	 * Se compara el fichero GIF que genera show, con el GIF que debe salir */
	@Test
	public void testShowEmpty2D() throws IOException {
		game = new Game(board1, board2, player1, player2);
		VisualiserGIF vg = new VisualiserGIF(game);
		vg.show();
		vg.close();
		/* IMPORTANTE: Si el siguiente assert fuera erróneo, para comprobar ficheros de salida 
		 * con la solución, ejecutar solo este test por separado y comprobar luego las diferencias.
		 */
		assertTrue ("Ejecuta el test de forma aislada",compareFiles(DIRFILES+"emptyBoard2D.gif","files/output.gif"));
	}

	/* Se aplica show a un Game con 2 Board3D con el juego sin empezar.
	 * Se compara el fichero GIF que genera show, con el GIF que debe salir */
	@Test
	public void testShowEmpty3D() throws IOException {
		board1 = new Board3D(10);
		board2 = new Board3D(10);
		game = new Game(board1, board2, player1, player2);
		VisualiserGIF vg = new VisualiserGIF(game);
		vg.show();
		vg.close();
		/* IMPORTANTE: Si el siguiente assert fuera erróneo, para comprobar ficheros de salida 
		 * con la solución, ejecutar solo este test por separado y comprobar luego las diferencias.
		 */
		assertTrue ("Ejecuta el test de forma aislada",compareFiles(DIRFILES+"emptyBoard3D.gif","files/output.gif"));
	}

	/* Se crean tableros 2D con posicionamiento aleatorio de los Ships. Se disparan
	 * sobre ambos trableros. Se comprueba que el gif obtenido es igual al gif solución.
	 * Tableros iniciales.
	 * Board1		Board2
	  ----------   ----------
	 |  ®®®®®   | |          |
     |          | |          |
     |          | |  ®®®®® Ω |
     |          | |        Ω |
     |     ØØØ  | |          |
     |          | |          |
     |     ΩΩ  O| |       O  |
     |         O| |       O  |
     |         O| |   ØØØ O  |
     |         O| |       O  |
      ----------   ----------
      * Tableros tras los disparos:
     Board1				Board2
     ? ®®®®® ??			?????????
	 ?       ??			??????????
	 ??????????			??•••• •? 
	 ? ????  ??			??????????
	 ?? ???•???			????????? 
	 ??? ?? ?  			?????   ??
	 ???? ?•? O			????? O ? 		
	 ?????  ? O			?     O ??	
	 ?????? ? O			  ØØØ O ? 
              O			?     O ??
	 */
	@Test
	public void testShow2DWithShipsAndHits() throws IOException, InvalidCoordinateException, 
	    NextToAnotherCraftException, OccupiedCoordinateException, CoordinateAlreadyHitException {
		
		game = new Game(board1, board2, player1, player2);
		game.start();
		HitsInBoard1_2D();
		HitsInBoard2_2D();
		System.out.println(board1.show(false));
		VisualiserGIF vg = new VisualiserGIF(game);
		vg.show();
		vg.close();
		/* IMPORTANTE: Si el siguiente assert fuera erróneo, para comprobar ficheros de salida 
		 * con la solución, ejecutar solo este test por separado y comprobar luego las diferencias.
		 */
		assertTrue (compareFiles(DIRFILES+"Show2D.gif","files/output.gif"));
	}

/* Se crean tableros 3D con posicionamiento aleatorio de los Aircrafts. Se disparan
 * sobre ambos trableros. Se comprueba que el gif obtenido es igual al gif solución.
 * Tableros iniciales.
 * Board1
 ----------------------------- 
|     |     |  Ø  |     | ®   |
|    O|     |  Ø  |     | ®   |
|    O|     |  Ø  |     | ®   |
|    O|     |     |     | ®   |
|ΩΩ  O|     |     |     | ®   |
 -----------------------------
 * Board2
 -----------------------------
|     |     |O    |     |     |
|     |     |O    |     |     |
|     |     |O   Ω|     |  ØØØ|
|®®®®®|     |O   Ω|     |     |
|     |     |     |     |     |
 -----------------------------
 * Tableros tras los disparos
 * Board1
 ??  |     |? Ø ?|?   ?|?????
? ? O|?    |? Ø ?|?   ?|?•???
??  O|?    |? Ø ?|?   ?|?•???
??? O|?    |?   ?|?   ?|?•???
??? O|???  |?? ??|??? ?|?•???

 * Board2
 ????|  ? ?|O ?? |  ???|?????
? ???|  ? ?|O ?? |     |?    
     |     |O ??•|     |? ØØØ
®®®®®|     |O ???|     |?    
     |     |  ???|  ?? |?????
	  */
	@Test
	public void testShow3DWithShipsAndHits() throws IOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException, CoordinateAlreadyHitException {
		board1 = new Board3D(5);
		board2 = new Board3D(5);
		game = new Game(board1, board2, player1, player2);
		game.start();
		HitsInBoard1_3D();
		HitsInBoard2_3D();
		VisualiserGIF vg = new VisualiserGIF(game);
		vg.show();
		vg.close();
		/* IMPORTANTE: Si el siguiente assert fuera erróneo, para comprobar ficheros de salida 
		 * con la solución, ejecutar solo este test por separado y comprobar luego las diferencias.
		 */
		assertTrue (compareFiles(DIRFILES+"Show3D.gif","files/output.gif"));
	}
	
	
	
	
	/*************************
	 * FUNCIONES AUXILIARES
	 *************************/
	// Dispara sobre el tablero board1 2D
	void HitsInBoard1_2D() throws InvalidCoordinateException, NextToAnotherCraftException, 
	   OccupiedCoordinateException, CoordinateAlreadyHitException   {
		/*board1.addCraft(destroyer,new Coordinate2D(1,7));
		board1.addCraft(carrier, new Coordinate2D(7,0));
		board1.addCraft(battleship, new Coordinate2D(-2,-1));
		board1.addCraft(cruiser, new Coordinate2D(3,3));*/
		
		for (int i=2;i<7;i++) board1.hit(new Coordinate2D(i,0));
		for (int i=0;i<10; i++) board1.hit(new Coordinate2D(i,9));
		for (int i=3;i<8; i++) board1.hit(new Coordinate2D(6,i));
		for (int i=6;i<9; i++) board1.hit(new Coordinate2D(9,i));
		for (int i=1;i<7; i++) board1.hit(new Coordinate2D(i,i+2));	
	}

	// Dispara sobre el tablero board2 2D
	void HitsInBoard2_2D() throws InvalidCoordinateException, NextToAnotherCraftException, 
	   OccupiedCoordinateException, CoordinateAlreadyHitException   {
		for (int i=6;i<10;i++) board2.hit(new Coordinate2D(6,i));
		for (int i=0;i<5; i++) board2.hit(new Coordinate2D(i,8));
		for (int i=2;i<8; i++) board2.hit(new Coordinate2D(i,2));
		board1.hit(new Coordinate2D(7,3));
		for (int i=0;i<5; i++) board2.hit(new Coordinate2D(9,i*2));
	}
	
	// Dispara sobre el tablero board1 3D
	void HitsInBoard1_3D() throws InvalidCoordinateException, NextToAnotherCraftException, 
	   OccupiedCoordinateException, CoordinateAlreadyHitException   {
		/*board1.addCraft(destroyer,new Coordinate2D(1,7));
		board1.addCraft(carrier, new Coordinate2D(7,0));
		board1.addCraft(battleship, new Coordinate2D(-2,-1));
		board1.addCraft(cruiser, new Coordinate2D(3,3));*/
		
		for (int i=1;i<5;i++) board1.hit(new Coordinate3D(4,i,0));
		for (int i=0;i<3; i++) board1.hit(new Coordinate3D(i,i,0));
		for (int i=4;i>=0; i--) {
			board1.hit(new Coordinate3D(i,i,1));
			board1.hit(new Coordinate3D(3,i,3));
		}
		for (int i=0;i<5; i++) board1.hit(new Coordinate3D(2,i,2));
		for (int i=1;i<5; i++) board1.hit(new Coordinate3D(1,i,4));	
	}
	
	// Dispara sobre el tablero board2 3D
	void HitsInBoard2_3D() throws InvalidCoordinateException, NextToAnotherCraftException, 
	   OccupiedCoordinateException, CoordinateAlreadyHitException   {
		/*board1.addCraft(destroyer,new Coordinate2D(1,7));
		board1.addCraft(carrier, new Coordinate2D(7,0));
		board1.addCraft(battleship, new Coordinate2D(-2,-1));
		board1.addCraft(cruiser, new Coordinate2D(3,3));*/
		
		for (int i=0;i<5;i++) board2.hit(new Coordinate3D(i,3,0));
		for (int i=2;i>=0; i--) board2.hit(new Coordinate3D(i,i,0));
		for (int i=0;i<5; i++) {
			board2.hit(new Coordinate3D(i,i,3));
			board2.hit(new Coordinate3D(3,i,1));
		}
		for (int i=0;i<5; i++) board2.hit(new Coordinate3D(0,i,2));
		for (int i=0;i<3; i++) board2.hit(new Coordinate3D(4,i,2));
		for (int i=1;i<5; i++) board2.hit(new Coordinate3D(i,2,4));	
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
	
	//Obtiene un atributo de una clase
		Object getClassField(Object obj, String atribute) throws Exception {
		
			// Sacamos el atributo privado (seen) de la clase (Board) 
	        Field field = obj.getClass().getDeclaredField(atribute);
	        // Indicamos que el atributo es accesible
	        field.setAccessible(true);
	        // Obtenemos el valor del atributo (seen)
	        return (Object)field.get(obj);
		}
}
