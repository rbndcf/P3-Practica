package model.ship;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Coordinate;
import model.CoordinateFactory;
import model.Orientation;

public class CarrierPreTest {
	Ship carrierN, carrierE, carrierS, carrierW;
	static List<Coordinate> north;
	static String sNorth, sEast, sSouth, sWest;
	final static int shape[][] = new int[][] {
		  { 0, 0, 1, 0, 0,
		   	0, 0, 1, 0, 0,	
		   	0, 0, 1, 0, 0,	
		   	0, 0, 1, 0, 0,
		   	0, 0, 1, 0, 0},
		  { 0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0,	
		    1, 1, 1, 1, 1,	
		    0, 0, 0, 0, 0,
		    0, 0, 0, 0, 0},
		  { 0, 0, 1, 0, 0,
		   	0, 0, 1, 0, 0,	
		   	0, 0, 1, 0, 0,	
		   	0, 0, 1, 0, 0,
		   	0, 0, 1, 0, 0},
		  { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
			1, 1, 1, 1, 1,	
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0}};  
		    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			north = new ArrayList<Coordinate>();
			for (int i=0; i < 5; i++) {
				north.add(new Coordinate2D(2,i));
			}
			 sEast = "Carrier (EAST)\n -----\n"
		        		+ "|     |\n"
		        		+ "|     |\n"
		        		+ "|®®®®®|\n"
		        		+ "|     |\n"
		        		+ "|     |\n"
		        		+ " -----";
				sNorth ="Carrier (NORTH)\n -----\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ " -----";
				sSouth ="Carrier (SOUTH)\n"
						+ " -----\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ "|  ®  |\n"
						+ " -----";
				sWest = "Carrier (WEST)\n"
						+ " -----\n"
						+ "|     |\n"
						+ "|     |\n"
						+ "|®®®®®|\n"
						+ "|     |\n"
						+ "|     |\n"
						+ " -----";	
	}		    
		    
		        
	@Before
	public void setUp() throws Exception {
		carrierN = new Carrier(Orientation.NORTH);
		carrierE = new Carrier(Orientation.EAST);
		carrierS = new Carrier(Orientation.SOUTH);
		carrierW = new Carrier(Orientation.WEST);
		
	}

	/* Comprobación de shape del alumno */
	@Test
	public void testGetShape() {
		int [][] shapeAux = carrierN.getShape();
		for (int i=0; i< shape.length; i++) 
			for (int j=0; j<shape[i].length; j++)
				assertEquals(shape[i][j],shapeAux[i][j]);
	}

	/* Comprueba que las orientaciones de los Carrier creados en el setUp son 
	 * correctas.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals("E1 en testGetOrientation", Orientation.NORTH, carrierN.getOrientation());
		assertEquals("E2 en testGetOrientation", Orientation.EAST, carrierE.getOrientation());
		assertEquals("E3 en testGetOrientation", Orientation.SOUTH, carrierS.getOrientation());
		assertEquals("E4 en testGetOrientation", Orientation.WEST, carrierW.getOrientation());
	}

	@Test
	public void testGetSymbol() {
		assertEquals('®', carrierN.getSymbol());
	}

	/* Comprueba que las posiciones absolutas para la orientación NORTH a partir de
	 * una Coordinate son correctas.
	 */
	@Test
	public void testGetAbsolutePositionsNorth() {
		Coordinate c1 = CoordinateFactory.createCoordinate(13, 27);
		Set<Coordinate> pos = carrierN.getAbsolutePositions(c1);
		for (Coordinate c : north)
			assertTrue("Valores Absolutos posiciones c1+" + c, pos.contains(c.add(c1)));	
	}
		

	//TODO
	/* Comprueba que toString() para cada Carrier creado en el setUp coincide con 
	 * los correspondientes String creados en setUpBeforeClass()
	 */
	@Test
	public void testToString() {
		assertEquals(sNorth, carrierN.toString());
		assertEquals(sSouth, carrierS.toString());
		assertEquals(sEast, carrierE.toString());
		assertEquals(sWest, carrierW.toString());
	}

}