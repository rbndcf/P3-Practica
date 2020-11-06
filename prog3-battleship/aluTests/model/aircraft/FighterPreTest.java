package model.aircraft;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Coordinate;
import model.CoordinateFactory;
import model.Orientation;

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

	/* Comprueba que las orientaciones de los Fighter creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals("Error 1 en testGetOrientation", Orientation.NORTH, fighterN.getOrientation());
		assertEquals("Error 2 en testGetOrientation", Orientation.EAST, fighterE.getOrientation());
		assertEquals("Error 3 en testGetOrientation", Orientation.SOUTH, fighterS.getOrientation());
		assertEquals("Error 4 en testGetOrientation", Orientation.WEST, fighterW.getOrientation());
	}

	@Test
	public void testGetSymbol() {
		assertEquals('⇄', fighterN.getSymbol());
	}

	/* Se comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		Set<Coordinate> esperades00 = new HashSet<Coordinate> ();
		
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 1, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 2, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 3, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 4, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {1, 2, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {3, 2, 5}));	
		assertEquals("Error 1 en testGetAbsolutePositionNorth", esperades00, fighterN.getAbsolutePositions(CoordinateFactory.createCoordinate(new int[] {0, 0, 5})));
		
		Set<Coordinate> esperades55 = new HashSet<Coordinate>();
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 6}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 7}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 8}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 9}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {6, 7}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {8, 7}));
		assertEquals("Error 2 en testGetAbsolutePositionsNorth", esperades55, fighterN.getAbsolutePositions(CoordinateFactory.createCoordinate(new int[] {5, 5})));
	}

	/* Comprueba que toString() para cada Fighter creado en el setUp coincide con 
	 * los correspondientes strings creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals("E1 en testToString", sNorth, fighterN.toString());
		assertEquals("E2 en testToString", sEast, fighterE.toString());
		assertEquals("E3 en testToString", sSouth, fighterS.toString());
		assertEquals("E4 en testToString", sWest, fighterW.toString());
	}

}