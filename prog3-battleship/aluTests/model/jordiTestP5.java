package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.aircraft.Bomber;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.ship.Battleship;
import model.ship.Carrier;
import model.ship.Cruiser;
import model.ship.Destroyer;
import model.ship.Mine;

public class jordiTestP5 {
	Craft craft;
	
	// ------------------------------------------------------------
	// Revisiones varias
	// ------------------------------------------------------------

	// Comprobamos si los valores de los crafts son correctos:
	@Test
	public void testCraftValues() {
		Orientation o = Orientation.NORTH;
		craft = new Destroyer(o);
		assertEquals(craft.getValue(), 3);
		craft = new Cruiser(o);
		assertEquals(craft.getValue(), 5);
		craft = new Battleship(o);
		assertEquals(craft.getValue(), 6);
		craft = new Carrier(o);
		assertEquals(craft.getValue(), 8);
		craft = new Fighter(o);
		assertEquals(craft.getValue(), 10);
		craft = new Bomber(o);
		assertEquals(craft.getValue(), 15);
		craft = new Transport(o);
		assertEquals(craft.getValue(), 18);
	}
	
	// Comprobamos la Reflect:
	@Test
	public void testShipReflect1() {
		Orientation o = Orientation.NORTH;
		craft = new Mine(o);
		assertEquals(craft.getValue(), 1);
		Craft craft2 = CraftFactory.createCraft("ship.Mine", o);
		assertEquals(craft.toString(), craft2.toString());
	}
}
