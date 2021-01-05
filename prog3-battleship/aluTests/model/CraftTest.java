package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CraftTest {

	Craft craft;
	Orientation orientation;
	@Test
	public void testGetValues() {
		for (Orientation orientation : Orientation.values()) {
			craft = CraftFactory.createCraft("ship.Destroyer", orientation);
			assertEquals(3, craft.getValue());
			craft = CraftFactory.createCraft("ship.Cruiser", orientation);
			assertEquals(5, craft.getValue());
			craft = CraftFactory.createCraft("ship.Battleship", orientation);
			assertEquals(6, craft.getValue());
			craft = CraftFactory.createCraft("ship.Carrier", orientation);
			assertEquals(8, craft.getValue());
			craft = CraftFactory.createCraft("aircraft.Fighter", orientation);
			assertEquals(10, craft.getValue());
			craft = CraftFactory.createCraft("aircraft.Bomber", orientation);
			assertEquals(15, craft.getValue());
			craft = CraftFactory.createCraft("aircraft.Transport", orientation);
			assertEquals(18, craft.getValue());
		}
	}

}
