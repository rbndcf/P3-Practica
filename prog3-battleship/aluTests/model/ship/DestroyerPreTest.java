package model.ship;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Coordinate;
import model.Orientation;

public class DestroyerPreTest {
	Ship destroyerN, destroyerE, destroyerS, destroyerW;
	static List<Coordinate> north;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    0, 1, 1, 0, 0,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    0, 1, 1, 0, 0,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0}}; 
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			for (int i=1; i < 3; i++) {
				north.add(new Coordinate2D(2,i));
			}
	       
			sNorth ="Destroyer (NORTH)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|  Ω  |\n"
					+ "|  Ω  |\n"
					+ "|     |\n"
					+ "|     |\n"
					+ " -----";
			sEast = "Destroyer (EAST)\n"
		       		+ " -----\n"
		       		+ "|     |\n"
		       		+ "|     |\n"
		       		+ "| ΩΩ  |\n"
		       		+ "|     |\n"
		       		+ "|     |\n"
		       		+ " -----";
			sSouth ="Destroyer (SOUTH)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|  Ω  |\n"
					+ "|  Ω  |\n"
					+ "|     |\n"
					+ "|     |\n"
					+ " -----";
			sWest = "Destroyer (WEST)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|     |\n"
					+ "| ΩΩ  |\n"
					+ "|     |\n"
					+ "|     |\n"
					+ " -----";
	}		    
		    
		    
		    
	@Before
	public void setUp() throws Exception {
		destroyerN = new Destroyer(Orientation.NORTH);
		destroyerE = new Destroyer(Orientation.EAST);
		destroyerS = new Destroyer(Orientation.SOUTH);
		destroyerW = new Destroyer(Orientation.WEST);
		
	}

	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = destroyerN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}

	//TODO
	/* Comprueba que las orientaciones de los Destroyer creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals(Orientation.NORTH, destroyerN.getOrientation());
		assertEquals(Orientation.EAST, destroyerE.getOrientation());
		assertEquals(Orientation.SOUTH, destroyerS.getOrientation());
		assertEquals(Orientation.WEST, destroyerW.getOrientation());
	}

	@Test
	public void testGetSymbol() {
		assertEquals('Ω', destroyerN.getSymbol());
	}

	//TODO
	/* Comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		Set<Coordinate> absPos = new HashSet<Coordinate>();
		absPos = destroyerN.getAbsolutePositions(new Coordinate2D(0,0));
		
		assertTrue(absPos.contains(new Coordinate2D(2,1)));
		assertTrue(absPos.contains(new Coordinate2D(2,2)));
	}


	//TODO
	/* Comprueba que toString() para cada Destroyer creado en el setUp coincide con 
	 * los correspondientes String creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals(destroyerN.toString(), sNorth);
		assertEquals(destroyerE.toString(), sEast);
		assertEquals(destroyerS.toString(), sSouth);
		assertEquals(destroyerW.toString(), sWest);
	}

}
