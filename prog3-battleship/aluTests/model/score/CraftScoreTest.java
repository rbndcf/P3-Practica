package model.score;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.io.IPlayer;
import model.io.PlayerFactory;

public class CraftScoreTest {
	
	final String DIRFILES = "aluTests/files/";
	CraftScore scLaura;
	CraftScore scPeter;
	IPlayer playerLaura, playerPeter;
	
	@Before
	public void setUp() throws Exception {
		playerLaura = PlayerFactory.createPlayer("Laura", DIRFILES+"ShipsFile1.in");
		playerPeter= PlayerFactory.createPlayer("Peter", "21");
		scLaura = new CraftScore(playerLaura);
		scPeter = new CraftScore(playerPeter);
	}

	/* Comprobación del constructor y getScore */
	@Test
	public void testCraftScoreAndGetScore() throws Exception {
		Score<Craft> score = scLaura; //Implementan la herencia ???
		assertSame(playerLaura, ((IPlayer) getSuperClassField(score, "player")));
		assertEquals(0, score.getScore(),0.0001);
		
		score = scPeter; //Implementan la herencia ???
		assertSame(playerPeter, ((IPlayer) getSuperClassField(score, "player")));
		assertEquals(0, score.getScore(),0.0001);
	}

	/* Comprobación del lanzamiento de NullPointerException cuando se pasa al constructor
	 * null como parámetro.
	 */
	@Test(expected=NullPointerException.class)
	public void testCraftScoreNullPointerException() throws NullPointerException {
		scLaura = new CraftScore(null);
		
	}
	
	/* Se crean algunos Crafts. Inicialmente se aplica score sobre los Scores scLaura y 
	 * scPeter con el mismo Craft. Se comprueba que el marcador de Peter es igual a sí mismo
	 * y menor que el de Laura
	 * Se aplica sobre uno de ellos con score, un Craft que incremente su puntuación.
	 * Se comprueba ahora que el que ha aumentado, si es el que invoca a compareTo da un
	 * valor negativo y si es el menor el que lo invoca, da un valor positivo.
	 */
	@Test
	public void testCompareTo() {
		assertTrue(scPeter.compareTo(scPeter)==0);
		assertTrue(scPeter.compareTo(scLaura)>0);
		scPeter.score(CraftFactory.createCraft("ship.Battleship", Orientation.NORTH));
		scLaura.score(CraftFactory.createCraft("ship.Battleship", Orientation.WEST));
		assertTrue(scLaura.compareTo(scPeter)<0);
		scPeter.score(CraftFactory.createCraft("ship.Destroyer", Orientation.SOUTH));
		assertTrue(scPeter.compareTo(scLaura)<0);
		assertTrue(scLaura.compareTo(scPeter)>0);
		
	}

	
	/* Se crean varios Crafts. Con cada uno, se crean las puntuaciones (score) sobre el 
	 * CraftScore scLaura y se comprueba con getScore() que  los valores que se van
	 * obteniendo se van acumulando sucesivamente. 
	 */
	@Test
	public void testScoreCraft() {
		assertEquals(0,scLaura.getScore(),0.01);
		scLaura.score(CraftFactory.createCraft("ship.Battleship", Orientation.NORTH));
		assertEquals(6,scLaura.getScore(),0.01);
		scLaura.score(CraftFactory.createCraft("ship.Destroyer", Orientation.SOUTH));
		assertEquals(9,scLaura.getScore(),0.01);
		scLaura.score(CraftFactory.createCraft("ship.Cruiser", Orientation.EAST));
		assertEquals(14,scLaura.getScore(),0.01);
		scLaura.score(CraftFactory.createCraft("ship.Carrier", Orientation.WEST));
		assertEquals(22,scLaura.getScore(),0.01);
		scLaura.score(CraftFactory.createCraft("aircraft.Bomber", Orientation.EAST));
		assertEquals(37,scLaura.getScore(),0.01);
		scLaura.score(CraftFactory.createCraft("aircraft.Fighter", Orientation.NORTH));
		assertEquals(47,scLaura.getScore(),0.01);
		scLaura.score(CraftFactory.createCraft("aircraft.Transport", Orientation.SOUTH));
		assertEquals(65,scLaura.getScore(),0.01);
	}

	

	
	/* Se comprueba toString sobre scPeter y se comprueba que inicialmente es: "Peter: 0"
	 * Se crean varios Crafts y se aplica el método score sobre scPeter. Se comprueba que
	 * la salida va cambiando de valor.
	 */
	@Test
	public void testToString() {
			
		compareScores ("Peter(PlayerRandom):0",scPeter.toString());
	
		scPeter.score(CraftFactory.createCraft("aircraft.Transport", Orientation.SOUTH));
		compareScores ("Peter(PlayerRandom):18",scPeter.toString());

		scPeter.score(CraftFactory.createCraft("ship.Cruiser", Orientation.EAST));
		compareScores ("Peter(PlayerRandom):23",scPeter.toString());

		scPeter.score(CraftFactory.createCraft("aircraft.Bomber", Orientation.NORTH));
		compareScores ("Peter(PlayerRandom):38.0",scPeter.toString());			
	}

	
	
	/*************************
	 * FUNCIONES AUXILIARES
	 *************************/
	
	
	//Obtiene un atributo de la superclase de la clase a la que pertenece obj
	Object getSuperClassField(Object obj, String atribute) throws Exception {
		
		// Sacamos el atributo privado (seen) de la clase (Board) 
        Field field = obj.getClass().getSuperclass().getDeclaredField(atribute);
        // Indicamos que el atributo es accesible
        field.setAccessible(true);
        // Obtenemos el valor del atributo (seen)
        return (Object)field.get(obj);
	}
	
	
	/* Para las salidas Score.toString() compara los valores impresos
	 * de los Scores hasta una precisión de 0.01
	 * 
	 */
	void compareScores(String expected, String result ) {
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
			assertEquals(removeSpaces(expected), removeSpaces(result));	
				
	}	
	
	String removeSpaces (String str) {
		String exp[]=str.split(" ");
		String nstr=new String("");
		for (String s: exp) {
			if (! s.equals(" ") ) nstr+=s; 
		}
		return nstr;
	}

}
