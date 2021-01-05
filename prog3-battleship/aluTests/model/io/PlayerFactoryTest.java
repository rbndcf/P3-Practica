package model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.exceptions.io.BattleshipIOException;

public class PlayerFactoryTest {

	final String DIRFILES = "test/files/";
	
	/* En los tests siguientes se crea un PlayerFile a partir de un fichero y se comprueba que createPlayer ha creado
	 * un PlayerFile.
	 */
	@Test
	public void testCreatePlayerFile1() throws BattleshipIOException {
		IPlayer ip=PlayerFactory.createPlayer("Saul",DIRFILES+"testCreatePlayerFile1.in");
		assertEquals("PlayerFile",ip.getClass().getSimpleName());
	}
	
	
	@Test
	public void testCreatePlayerFile2() throws BattleshipIOException {
		
			IPlayer ip=PlayerFactory.createPlayer("Sandra","12345/54321");
			assertEquals("PlayerFile",ip.getClass().getSimpleName());
	}
	
	@Test
	public void testCreatePlayerFile3() throws BattleshipIOException {
		
			IPlayer ip=PlayerFactory.createPlayer("Marco","IJL/OSTZ");
			assertEquals("PlayerFile",ip.getClass().getSimpleName());
	}

	/* En los tests siguientes se crea un PlayerRandom y se comprueba que createPlayer ha creado
	 * un PlayerRandom.
	 */
	@Test
	public void testCreatePlayerRandom1() throws BattleshipIOException {
		
		IPlayer ip=PlayerFactory.createPlayer("Jeremy", "67584902");
		assertEquals("PlayerRandom",ip.getClass().getSimpleName());
	}
	
	@Test
	public void testCreatePlayerRandom2() throws BattleshipIOException {
		
		IPlayer ip=PlayerFactory.createPlayer("Leonor","-567890");
		assertEquals("PlayerRandom",ip.getClass().getSimpleName());
	}
	
	/* Tests de comprobación de excepciones */
	@Test(expected=BattleshipIOException.class)
	public void testCreatePlayerFileNotExist() throws BattleshipIOException {
		try {
			PlayerFactory.createPlayer("Laura",DIRFILES+"/noexistefichero.in");
		} catch (BattleshipIOException e) {
		      assertTrue((e.getMessage().length())>1);
		      PlayerFactory.createPlayer("Laura","test\\files\\noexistefichero.in");
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreatePlayerNullPointerException() throws BattleshipIOException {
		     PlayerFactory.createPlayer("Laura",null);
	}
		
	/* Tests con situaciones en las que no se crea jugador */
	@Test
	public void testCreatePlayerNull1() throws BattleshipIOException {
		
			IPlayer ip=PlayerFactory.createPlayer("Julio"," ");
			assertNull(ip);
	}
	
	@Test
	public void testCreatePlayerNull2() throws BattleshipIOException {
		
			IPlayer ip=PlayerFactory.createPlayer("Julio","222file123");
			assertNull(ip);
	}
	
}
