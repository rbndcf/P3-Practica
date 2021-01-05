package model.score;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import model.Board;
import model.CellStatus;
import model.CoordinateFactory;
import model.exceptions.BattleshipException;
import model.exceptions.CoordinateAlreadyHitException;
import model.io.IPlayer;
import model.io.PlayerFactory;
import model.ship.Board2D;

public class HitScoreTest {

	final String DIRFILES = "test/files/";
	HitScore scLaura;
	HitScore scPeter;
	IPlayer playerLaura, playerPeter;
	Board boardLaura;
	
	@Before
	public void setUp() throws Exception {
		playerLaura = PlayerFactory.createPlayer("Laura", DIRFILES+"ShipsFile1.in");
		playerPeter= PlayerFactory.createPlayer("Peter", "21");
		scLaura = new HitScore(playerLaura);
		scPeter = new HitScore(playerPeter);
		boardLaura = new Board2D(7);
	}

	/* Comprobación del constructor */
	@Test
	public void testHitScore() throws Exception {
		Score<CellStatus> score = scLaura; 
		assertSame(playerLaura, ((IPlayer) getSuperClassField(score, "player")));
		assertEquals(0, score.getScore(),0.0001);
		
		score = scPeter; 
		assertSame(playerPeter, ((IPlayer) getSuperClassField(score, "player")));
		assertEquals(0, score.getScore(),0.0001);
	}

	@Test(expected=NullPointerException.class)
	public void testCraftScoreNullPointerException() throws NullPointerException {
		scLaura = new HitScore(null);
		
	}
	
	/* 
	 * Se comprueba que el marcador de Laura es igual a sí mismo y mayor que el de Peter. 
	 * Se pasan varios estados de disparos al agua en los respectivos scores y se 
	 * comprueba que los scores están igual que al principio.
	 * Se pasan varios estados HIT y/o DESTROYED al score de Laura y se comprueba que 
	 * ahora el score de Laura es mayor que el de Peter. 
	 * Se pasan varios estados HIT y/o DESTROYED al score de Peter y se comprueba que
	 * hasta el HIT/DESTROYED 3º el score de Laura es mayor que el de Peter, mayor en el 4º 
	 * por nombre y menor tras el 5º HIT de Peter.
	 */
	@Test
	public void testCompareTo() throws BattleshipException {
	
		assertTrue(scLaura.compareTo(scLaura)==0);
		assertTrue(scLaura.compareTo(scPeter)<0);
		//Realizamos disparos al agua. Los marcadores deben seguir iguales a 0
		for (int i=0; i<6; i++) {
			scPeter.score(CellStatus.WATER);
			scLaura.score(CellStatus.WATER);
		}
		
	  	assertTrue(scPeter.compareTo(scLaura)>0);
		//Laura realiza 4 disparos sobre un Craft de Peter
		for (int i=0; i<4; i++) {
			scLaura.score(CellStatus.HIT);
			assertTrue(scPeter.compareTo(scLaura)>0);
			assertTrue(scLaura.compareTo(scPeter)<0);
		}
				
		//Peter realiza 3 disparos sobre un Craft de Laura.
		for (int i=0; i<3; i++) {
				scPeter.score(CellStatus.DESTROYED);
				assertTrue(scPeter.compareTo(scLaura)>0);
				assertTrue(scLaura.compareTo(scPeter)<0);			
		}
		//El 4º disparo de Peter iguala a Laura en valor, pero por nombre, el de Laura tiene
		//un marcador mayor 
		scPeter.score(CellStatus.HIT);
		assertTrue(scPeter.compareTo(scLaura)>0);
			
		//El 5º disparo, supera a Laura
		scPeter.score(CellStatus.DESTROYED);
		assertTrue(scPeter.compareTo(scLaura)<0);
		assertTrue(scLaura.compareTo(scPeter)>0);
	}

	
	/* Se analizan los valores que van tomando los distintos scores tras pasarle
	 * a score los distintos valores de CellStatus	 */
	@Test
	public void testScoreCellStatus() throws BattleshipException {
				
		assertEquals(0,scPeter.getScore(),0.01);
		scPeter.score(CellStatus.WATER);
		assertEquals(0,scPeter.getScore(),0.01);
		scPeter.score(CellStatus.HIT);
		assertEquals(1,scPeter.getScore(),0.01);
		scPeter.score(CellStatus.HIT);
		assertEquals(2,scPeter.getScore(),0.01);
		scPeter.score(CellStatus.DESTROYED);
		assertEquals(3,scPeter.getScore(),0.01);
		
		for (int i=0; i<5; i++) {
			//hit
			scPeter.score(CellStatus.HIT);
			//water
			scPeter.score(CellStatus.WATER);
			assertEquals(4+i,scPeter.getScore(),0.01);
		}
	}
	
	/* Se comprueban los valores de los distintos scores tras varios disparos al Board de Laura
	 * Los valores que se van obteniendo son los mismos que en testScoreCellStatus()
	 */
	@Test
	public void testScoreCellStatus2() throws BattleshipException {
		playerLaura.putCrafts(boardLaura);
		
		assertEquals(0,scPeter.getScore(),0.01);
		scPeter.score(boardLaura.hit(CoordinateFactory.createCoordinate(1,1)));
		assertEquals(0,scPeter.getScore(),0.01);
		scPeter.score(boardLaura.hit(CoordinateFactory.createCoordinate(0,0)));
		assertEquals(1,scPeter.getScore(),0.01);
		try {
			scPeter.score(boardLaura.hit(CoordinateFactory.createCoordinate(0,0)));
			fail("Error, se debía haber lanzado CoordinateAlreadyHitException");
		} catch (CoordinateAlreadyHitException e) {
		    assertEquals(1,scPeter.getScore(),0.01);
		}
		scPeter.score(boardLaura.hit(CoordinateFactory.createCoordinate(0,1)));
		assertEquals(2,scPeter.getScore(),0.01);
		scPeter.score(boardLaura.hit(CoordinateFactory.createCoordinate(0,2)));
		assertEquals(3,scPeter.getScore(),0.01);
		
		for (int i=0; i<5; i++) {
			//hit
			scPeter.score(boardLaura.hit(CoordinateFactory.createCoordinate(5,i)));
			//water
			scPeter.score(boardLaura.hit(CoordinateFactory.createCoordinate(i,4)));
			assertEquals(4+i,scPeter.getScore(),0.01);
		}
		
	}

	/* Se comprueba toString sobre scLaura y que inicialmente es: "Laura: 0"
	 * Se pasan varios estados HIT/DESTROYED/WATER al score de Peter y se comprueba que
	 * la salida va cambiando de valor cuando no es WATER
	 */
	@Test
	public void testToString() throws BattleshipException{
	
		compareScores ("Laura(PlayerFile):0",scLaura.toString());
	
		scLaura.score(CellStatus.WATER);
		compareScores ("Laura(PlayerFile):0",scLaura.toString());

		scLaura.score(CellStatus.HIT);
		compareScores ("Laura(PlayerFile):1",scLaura.toString());
		
		for (int i=1; i<5; i++) {
			//Destroy Battleship
		    scLaura.score(CellStatus.DESTROYED);
		    //water
		    scLaura.score(CellStatus.WATER);
		    //hit Carrier
		    scLaura.score(CellStatus.HIT);
			compareScores ("Laura(PlayerFile):"+(i*2+1),scLaura.toString());
		}		
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
