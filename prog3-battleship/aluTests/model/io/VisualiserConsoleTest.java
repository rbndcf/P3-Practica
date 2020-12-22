package model.io;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import model.Board;
import model.Game;
import model.aircraft.Board3D;
import model.ship.Board2D;

public class VisualiserConsoleTest {

	final String DIRFILES = "aluTests/files/";
	Game game,gamerr1, gamerr2;
	IPlayer player1, player2; 
	Board board1, board2;

	@Before
	public void setUp() throws Exception {
		board1 = new Board2D(10);
		board2 = new Board3D(5);
		player1 = PlayerFactory.createPlayer("Raul","46");
		player2 = PlayerFactory.createPlayer("Carol","21");
	}

	/* Se comprueba que pasándo un game null al constructor visualserConsole se
	 * lanza NullPointerException. Se comprueba también la relación de asociación entre 
	 * VisualiserConsole y Game usando la reflexión.
	 */
	@Test
	public void testVisualiserConsole() throws Exception {
		game = new Game(board1, board2, player1, player2);
		try {
			new VisualiserConsole(null);
			fail("Error: debería haberse lanzado NullPointerException");
		} catch (NullPointerException e) {
			IVisualiser vc= new VisualiserConsole(game);
			assertSame(game, (Game) getClassField(vc,"game"));
		}
	}

	/* Se comprueba que show invoca a game.toString() y lo muestra en
	 * pantalla para dos tableros, uno 2D y otro 3D, para una partida no empezada */
	@Test
	public void testShow() {
		final String outFile = DIRFILES+"testShow.alu";
		game = new Game(board1, board2, player1, player2);
		VisualiserConsole vc= new VisualiserConsole(game);
		
		PrintStream ps = standardIO2File(outFile);
		if (ps!=null) {
			vc.show();
			System.setOut(System.out); //Reestablecemos la salida standard
			ps.close();
		}
		else fail("Error: no se pudo crear el fichero ");
		//Comparamos salida del alumno con la solución
		StringBuilder sbSolution=readFromFile(DIRFILES+"testShow.sol");
		StringBuilder sbStudent=readFromFile(DIRFILES+"testShow.alu");
		compareLines(sbSolution.toString(),sbStudent.toString());
	}


	
	/*************************
	 * FUNCIONES AUXILIARES
	 *************************/

	private void  compareLines(String expected, String result) {
		String exp[]=expected.split("\n");
		String res[]=result.split("\n");
		if (exp.length!=res.length) 
			fail("Cadena esperada de tamaño ("+exp.length+") distinto a la resultante ("+res.length+")");
		for (int i=0; i<exp.length; i++) {
			 				 assertEquals("linea "+i, exp[i],res[i]);
		}
	}
	//Redirección de la salida standard a un fichero	
	public static PrintStream standardIO2File(String fileName){

        if(fileName.equals("")){//Si viene vacío usamos este por defecto
            fileName="C:\\javalog.txt";
        }
        PrintStream ps=null;
        try {
            //Creamos un printstream sobre el archivo.
            ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(fileName))),true);
            //Redirigimos salida estándar
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
