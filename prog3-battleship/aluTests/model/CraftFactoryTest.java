package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.ship.*;
import model.aircraft.*;

public class CraftFactoryTest {

	String[] badNames = { "Battleship", "Carrier", "Cruiser", "Destroyer", "Bomber", "Fighter", "Transport" };

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

	/* Se comprueba que la factoría es capaz de crear tipos de barcos desconocidos en tiempo de compilación */
	@Test
	public void testCreateNewShip() {
		Craft craft;
		for (Orientation orient : Orientation.values()) {
			craft = CraftFactory.createCraft("ship.Carabela", orient);
			assertTrue (craft instanceof Carabela );
		}		
	}
	
	/* Se comprueba que la factoría es capaz de crear tipos de aviones desconocidos en tiempo de compilación */
	@Test
	public void testCreateNewAircraft() {
		Craft craft;
		for (Orientation orient : Orientation.values()) {
			craft = CraftFactory.createCraft("aircraft.Jumbo", orient);
			assertTrue (craft instanceof Jumbo );
		}		
	}

	
	/* Se comprueba que no se crean Craft sin prefijo ship/aircraft */
	@Test
	public void testCreateCraftNoOk1() {
		Craft craft;
		Orientation orient = Orientation.EAST;
		for (String name : badNames) {
			try {
				craft = CraftFactory.createCraft(name, orient);
				assertNull (craft );
			} catch(NoClassDefFoundError error) {
			  // en algunos casos, puede que Class.forName lance este error y no la de ClassNotFoundException
			  // lo capturamos aquí y damos por bueno el test si es el caso.			
			}
		}
	}

	
	/* Se comprueba que createCraft devuelve null si el Craft es desconocido en tiempo de ejecución */
	@Test
	public void testCreateCraftWrong1() {
		try {
			assertNull( CraftFactory.createCraft("ship.BATTLESHIP", Orientation.EAST));
		} catch(NoClassDefFoundError error) {
			  // en algunos casos, puede que Class.forName lance este error y no la de ClassNotFoundException
			  // lo capturamos aquí y damos por bueno el test si es el caso.			
		}
		try {			
			assertNull (CraftFactory.createCraft("aircraft.Bomberd", Orientation.EAST));		 
		} catch(NoClassDefFoundError error) {
			  // en algunos casos, puede que Class.forName lance este error y no la de ClassNotFoundException
			  // lo capturamos aquí y damos por bueno el test si es el caso.			
		}
	}

}


