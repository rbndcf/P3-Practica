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

public class TransportPreTest {
	Aircraft transportN, transportE, transportS, transportW;
	static List<Coordinate> north;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
		 { 0, 0, 1, 0, 0,
		   0, 0, 1, 0, 0,	
		   0, 1, 1, 1, 0,	
		   1, 0, 1, 0, 1,
		   0, 0, 1, 0, 0},
		 { 0, 1, 0, 0, 0,
		   0, 0, 1, 0, 0,	
		   1, 1, 1, 1, 1,	
		   0, 0, 1, 0, 0,
		   0, 1, 0, 0, 0},
		 { 0, 0, 1, 0, 0,
		   1, 0, 1, 0, 1,	
		   0, 1, 1, 1, 0,	
		   0, 0, 1, 0, 0,
		   0, 0, 1, 0, 0},
		 { 0, 0, 0, 1, 0,
		   0, 0, 1, 0, 0,	
		   1, 1, 1, 1, 1,	
		   0, 0, 1, 0, 0,
		   0, 0, 0, 1, 0}}; 
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			for (int i=0; i < 5; i++) {
				north.add(new Coordinate3D(2,i,0));	
				if (i<2) {
					north.add(new Coordinate3D(i,3-i,0));
					north.add(new Coordinate3D(i+3,i+2,0));
				}
			}
			
			sNorth ="Transport (NORTH)\n"
					+ " -----\n"
					+ "|  ⇋  |\n"
					+ "|  ⇋  |\n"
					+ "| ⇋⇋⇋ |\n"
					+ "|⇋ ⇋ ⇋|\n"
					+ "|  ⇋  |\n"
					+ " -----";
			sEast = "Transport (EAST)\n"
					+ " -----\n"
					+ "| ⇋   |\n"
					+ "|  ⇋  |\n"
					+ "|⇋⇋⇋⇋⇋|\n"
					+ "|  ⇋  |\n"
					+ "| ⇋   |\n"
					+ " -----";
			sSouth ="Transport (SOUTH)\n"
					+ " -----\n"
					+ "|  ⇋  |\n"
					+ "|⇋ ⇋ ⇋|\n"
					+ "| ⇋⇋⇋ |\n"
					+ "|  ⇋  |\n"
					+ "|  ⇋  |\n"
					+ " -----";
			sWest = "Transport (WEST)\n"
					+ " -----\n"
					+ "|   ⇋ |\n"
					+ "|  ⇋  |\n"
					+ "|⇋⇋⇋⇋⇋|\n"
					+ "|  ⇋  |\n"
					+ "|   ⇋ |\n"
					+ " -----";
	}		    
		    
		    
		    
	@Before
	public void setUp() throws Exception {
		transportN = new Transport(Orientation.NORTH);
		transportE = new Transport(Orientation.EAST);
		transportS = new Transport(Orientation.SOUTH);
		transportW = new Transport(Orientation.WEST);
		
	}

	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = transportN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}

	//TODO
	/* Comprueba que las orientaciones de los Transport creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals("Error 1 en testGetOrientation", Orientation.NORTH, transportN.getOrientation());
		assertEquals("Error 2 en testGetOrientation", Orientation.EAST, transportE.getOrientation());
		assertEquals("Error 3 en testGetOrientation", Orientation.SOUTH, transportS.getOrientation());
		assertEquals("Error 4 en testGetOrientation", Orientation.WEST, transportW.getOrientation());
	}

	@Test
	public void testGetSymbol() {
		assertEquals('⇋', transportN.getSymbol());
	}
	
	//TODO
	/* Se comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		Set<Coordinate> esperades00 = new HashSet<Coordinate> ();
		
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 0, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 1, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 2, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 3, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {2, 4, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {1, 2, 5}));	
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {3, 2, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {0, 3, 5}));
		esperades00.add(CoordinateFactory.createCoordinate(new int[] {4, 3, 5}));			
		assertEquals("Error 1 en testGetAbsolutePositionNorth", esperades00, transportN.getAbsolutePositions(CoordinateFactory.createCoordinate(new int[] {0, 0, 5})));
		
		Set<Coordinate> esperades55 = new HashSet<Coordinate>();
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 5}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 6}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 7}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 8}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {7, 9}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {6, 7}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {8, 7}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {5, 8}));
		esperades55.add(CoordinateFactory.createCoordinate(new int[] {9, 8}));
		assertEquals("Error 2 en testGetAbsolutePositionsNorth", esperades55, transportN.getAbsolutePositions(CoordinateFactory.createCoordinate(new int[] {5, 5})));
	}
		
	//TODO
	/* Comprueba que toString() para cada Transport creado en el setUp coincide con 
	 * los correspondientes strings creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals("E1 en testToString", sNorth, transportN.toString());
		assertEquals("E2 en testToString", sEast, transportE.toString());
		assertEquals("E3 en testToString", sSouth, transportS.toString());
		assertEquals("E4 en testToString", sWest, transportW.toString());
	}

}
