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
			craft = CraftFactory.createCraft("Battleship", orient);
			assertTrue (craft instanceof Battleship );
			craft = CraftFactory.createCraft("Carrier", orient);
			assertTrue (craft instanceof Carrier );
			craft = CraftFactory.createCraft("Cruiser", orient);
			assertTrue (craft instanceof Cruiser );
			craft = CraftFactory.createCraft("Destroyer", orient);
			assertTrue (craft instanceof Destroyer );
			craft = CraftFactory.createCraft("Bomber", orient);
			assertTrue (craft instanceof Bomber );
			craft = CraftFactory.createCraft("Fighter", orient);
			assertTrue (craft instanceof Fighter );
			craft = CraftFactory.createCraft("Transport", orient);
			assertTrue (craft instanceof Transport );
		}
	}

	/* Se comprueba que createCraft devuelve null si el Craft es desconocido o null */
	@Test
	public void testCreateCraftWrong() {
		
		 for (Orientation orient : Orientation.values()) {
			assertNull( CraftFactory.createCraft("BATTLESHIP", orient));
			assertNull (CraftFactory.createCraft("CarrieR", orient));
			assertNull (CraftFactory.createCraft("Cruisera", orient));
			assertNull (CraftFactory.createCraft("Destroyered", orient));
			assertNull (CraftFactory.createCraft("Bomberd", orient));
			assertNull (CraftFactory.createCraft("Fightery", orient));
			assertNull (CraftFactory.createCraft("Transporter", orient));
		 }
	}
}
