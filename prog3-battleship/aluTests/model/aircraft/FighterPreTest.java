package model.aircraft;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Coordinate;
import model.Orientation;
import model.ship.Coordinate2D;

public class FighterPreTest {
	Aircraft fighterN, fighterE, fighterS, fighterW;
	static List<Coordinate> north;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
		{ 0, 0, 0, 0, 0,
	      0, 0, 1, 0, 0,	
	      0, 1, 1, 1, 0,	
	      0, 0, 1, 0, 0,
	      0, 0, 1, 0, 0},
	    { 0, 0, 0, 0, 0,
		  0, 0, 1, 0, 0,	
		  1, 1, 1, 1, 0,	
		  0, 0, 1, 0, 0,
		  0, 0, 0, 0, 0},
	    { 0, 0, 1, 0, 0,
		  0, 0, 1, 0, 0,	
		  0, 1, 1, 1, 0,	
		  0, 0, 1, 0, 0,
		  0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0,
		  0, 0, 1, 0, 0,	
		  0, 1, 1, 1, 1,	
		  0, 0, 1, 0, 0,
		  0, 0, 0, 0, 0}};  
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			for (int i=0; i < 4; i++) {
				north.add(new Coordinate3D(2,i+1,0));	
				if ((i==1)||(i==3)) {
					north.add(new Coordinate3D(i,2,0));
				}
			}
			sNorth = "Fighter (NORTH)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|  ⇄  |\n"
					+ "| ⇄⇄⇄ |\n"
					+ "|  ⇄  |\n"
					+ "|  ⇄  |\n"
					+ " -----";
			
			sEast = "Fighter (EAST)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|  ⇄  |\n"
					+ "|⇄⇄⇄⇄ |\n"
					+ "|  ⇄  |\n"
					+ "|     |\n"
					+ " -----";
			sSouth ="Fighter (SOUTH)\n"
					+ " -----\n"
					+ "|  ⇄  |\n"
					+ "|  ⇄  |\n"
					+ "| ⇄⇄⇄ |\n"
					+ "|  ⇄  |\n"
					+ "|     |\n"
					+ " -----";
			sWest = "Fighter (WEST)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|  ⇄  |\n"
					+ "| ⇄⇄⇄⇄|\n"
					+ "|  ⇄  |\n"
					+ "|     |\n"
					+ " -----";
	}		    
		    
		    
		    
	@Before
	public void setUp() throws Exception {
		fighterN = new Fighter(Orientation.NORTH);
		fighterE = new Fighter(Orientation.EAST);
		fighterS = new Fighter(Orientation.SOUTH);
		fighterW = new Fighter(Orientation.WEST);
		
	}

	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = fighterN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}

	//TODO
	/* Comprueba que las orientaciones de los Fighter creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		fail("Realiza el test");
	}

	@Test
	public void testGetSymbol() {
		assertEquals('⇄', fighterN.getSymbol());
	}

	//TODO
	/* Se comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		fail("Realiza el test");
	}

	//TODO
	/* Comprueba que toString() para cada Fighter creado en el setUp coincide con 
	 * los correspondientes strings creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		fail("Realiza el test");
	}

}
