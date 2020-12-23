package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.ship.*;
import model.aircraft.*;

public class CraftFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	/* Se comprueba la correcta creación de todos los Craft para todas las orientaciones */
	@Test
	public void testCreateCraftOk() {
		Craft craft;
		for (Orientation orient : Orientation.values()) {
			craft = CraftFactory.createCraft("ship.Battleship", orient);
			assertTrue (craft instanceof Battleship );
			craft = CraftFactory.createCraft("ship.Carrier", orient);
			assertTrue (craft instanceof Carrier );
			craft = CraftFactory.createCraft("ship.Cruiser", orient);
			assertTrue (craft instanceof Cruiser );
			craft = CraftFactory.createCraft("ship.Destroyer", orient);
			assertTrue (craft instanceof Destroyer );
			craft = CraftFactory.createCraft("aircraft.Bomber", orient);
			assertTrue (craft instanceof Bomber );
			craft = CraftFactory.createCraft("aircraft.Fighter", orient);
			assertTrue (craft instanceof Fighter );
			craft = CraftFactory.createCraft("aircraft.Transport", orient);
			assertTrue (craft instanceof Transport );
		}
	}

	/* Se comprueba que createCraft devuelve null si el Craft es desconocido o null */
	@Test
	public void testCreateCraftWrong() {
		
		 for (Orientation orient : Orientation.values()) {
			assertNull( CraftFactory.createCraft("ship.BATTLESHIP", orient));
			assertNull (CraftFactory.createCraft("ship.CarrieR", orient));
			assertNull (CraftFactory.createCraft("ship.Cruisera", orient));
			assertNull (CraftFactory.createCraft("ship.Destroyered", orient));
			assertNull (CraftFactory.createCraft("aircraft.Bomberd", orient));
			assertNull (CraftFactory.createCraft("aircraft.Fightery", orient));
			assertNull (CraftFactory.createCraft("aircraft.Transporter", orient));
		 }
	}
}
