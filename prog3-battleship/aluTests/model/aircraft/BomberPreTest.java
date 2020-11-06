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

public class BomberPreTest {
	Aircraft bomberN, bomberE, bomberS, bomberW;
	static List<Coordinate> north;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
	    { 0, 0, 0, 0, 0,
	      0, 0, 1, 0, 0,	
	      1, 1, 1, 1, 1,	
	      1, 0, 1, 0, 1,
	      0, 0, 1, 0, 0},
	    { 0, 1, 1, 0, 0,
		  0, 0, 1, 0, 0,	
		  1, 1, 1, 1, 0,	
		  0, 0, 1, 0, 0,
		  0, 1, 1, 0, 0},
	    { 0, 0, 1, 0, 0,
		  1, 0, 1, 0, 1,	
		  1, 1, 1, 1, 1,	
		  0, 0, 1, 0, 0,
		  0, 0, 0, 0, 0},
		{ 0, 0, 1, 1, 0,
		  0, 0, 1, 0, 0,	
		  0, 1, 1, 1, 1,	
		  0, 0, 1, 0, 0,
		  0, 0, 1, 1, 0}
	}; 
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			for (int i=0; i < 5; i++) {
				north.add(new Coordinate3D(i,2,0));	
				if (i<4) {
					north.add(new Coordinate3D(2,i+1,0));
				}
				if ((i==0)||(i==4)) {
					north.add(new Coordinate3D(i,3,0));
				}
			}
			sNorth = "Bomber (NORTH)\n"
					+ " -----\n"
					+ "|     |\n"
					+ "|  ⇶  |\n"
					+ "|⇶⇶⇶⇶⇶|\n"
					+ "|⇶ ⇶ ⇶|\n"
					+ "|  ⇶  |\n"
					+ " -----";
			
			sEast = "Bomber (EAST)\n"
					+ " -----\n"
					+ "| ⇶⇶  |\n"
					+ "|  ⇶  |\n"
					+ "|⇶⇶⇶⇶ |\n"
					+ "|  ⇶  |\n"
					+ "| ⇶⇶  |\n"
					+ " -----";
			sSouth ="Bomber (SOUTH)\n"
					+ " -----\n"
					+ "|  ⇶  |\n"
					+ "|⇶ ⇶ ⇶|\n"
					+ "|⇶⇶⇶⇶⇶|\n"
					+ "|  ⇶  |\n"
					+ "|     |\n"
					+ " -----";
			sWest = "Bomber (WEST)\n"
					+ " -----\n"
					+ "|  ⇶⇶ |\n"
					+ "|  ⇶  |\n"
					+ "| ⇶⇶⇶⇶|\n"
					+ "|  ⇶  |\n"
					+ "|  ⇶⇶ |\n"
					+ " -----";
	}		    
		    
		    
		    
	@Before
	public void setUp() throws Exception {
		bomberN = new Bomber(Orientation.NORTH);
		bomberE = new Bomber(Orientation.EAST);
		bomberS = new Bomber(Orientation.SOUTH);
		bomberW = new Bomber(Orientation.WEST);
		
	}

	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = bomberN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}

	/* Comprueba que las orientaciones de los Bomber creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals("Error 1 en testGetOrientation", Orientation.NORTH, bomberN.getOrientation());
		assertEquals("Error 2 en testGetOrientation", Orientation.EAST, bomberE.getOrientation());
		assertEquals("Error 3 en testGetOrientation", Orientation.SOUTH, bomberS.getOrientation());
		assertEquals("Error 4 en testGetOrientation", Orientation.WEST, bomberW.getOrientation());
	}

	@Test
	public void testGetSymbol() {
		assertEquals('⇶', bomberN.getSymbol());
	}

	/* Comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		Set<Coordinate> esperades00 = new HashSet<Coordinate> ();
		
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 1, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {0, 2, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {1, 2, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 2, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {3, 2, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {4, 2, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {0, 3, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 3, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {4, 3, 5}));		
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 4, 5}));	
		assertEquals("Error 1 en testGetAbsolutePositionNorth", esperades00, bomberN.getAbsolutePositions(CoordinateFactory.createCoordinate(new int[] {0, 0, 5})));
		
		Set<Coordinate> esperades55 = new HashSet<Coordinate>();
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 6}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {5, 7}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {6, 7}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 7}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {8, 7}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {9, 7}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {5, 8}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 8}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {9, 8}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 9}));
		assertEquals("Error 2 en testGetAbsolutePositionsNorth", esperades55, bomberN.getAbsolutePositions(CoordinateFactory.createCoordinate(new int[] {5, 5})));
	}
		
	/* Comprueba que toString() para cada Bomber creado en el setUp coincide con 
	 * los correspondientes strings creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals("E1 en testToString", sNorth, bomberN.toString());
		assertEquals("E2 en testToString", sEast, bomberE.toString());
		assertEquals("E3 en testToString", sSouth, bomberS.toString());
		assertEquals("E4 en testToString", sWest, bomberW.toString());
	}

}