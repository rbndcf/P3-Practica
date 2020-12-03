package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.aircraft.Bomber;
import model.ship.Carrier;

public class CraftPreTest {

	Craft carrier, bomber;
	
	@Before
	public void setUp() throws Exception {
		carrier = new Carrier(Orientation.EAST);
		bomber = new Bomber(Orientation.SOUTH);
	}
	
	@Test
	public void testGetShapeIndex() {
		try {
			carrier.getShapeIndex(null);
		} catch (NullPointerException e) {
			System.err.println(e.toString());
		} catch (Exception e) {
			fail("Excepcio incorrecta");
		}
	}
	
	@Test
	public void testGetAbsolutePositionsCoor() {
		try {
			carrier.getAbsolutePositions(null);
		} catch (NullPointerException e) {
			System.err.println(e.toString());
		} catch (Exception e) {
			fail("Excepcio incorrecta");
		}
	}
	
	@Test
	public void testGetAbsolutePositions() {
		try {
			carrier.getAbsolutePositions();
		} catch (NullPointerException e) {
			System.err.println(e.toString());
		} catch (Exception e) {
			fail("Excepcio incorrecta");
		}
	}

}