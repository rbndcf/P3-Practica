package model.score;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.CellStatus;
import model.CraftFactory;
import model.Orientation;
import model.exceptions.BattleshipException;
import model.exceptions.score.EmptyRankingException;
import model.io.IPlayer;
import model.io.PlayerFactory;

public class RankingTest {
	
	final String SRANKING1 = "Craft ranking = [Julia (PlayerFile): 0]\n" + 
							 "Hit ranking = [Julia (PlayerFile): 0]";
	
	final String SRANKING2 = "Craft ranking = [Julia (PlayerFile): 60, Raul (PlayerRandom): 24]\n" + 
		     				 "Hit ranking = [Raul (PlayerRandom): 3, Julia (PlayerFile): 0]\n";
	
	final String SRANKING3 = "Craft ranking = [Julia (PlayerFile): 30, Raul (PlayerRandom): 16]\n" + 
							 "Hit ranking = [Raul (PlayerRandom): 40, Julia (PlayerFile): 30]";
	
	final String SRANKING4 = "Craft ranking = [Simon (PlayerFile): 1995, Raul (PlayerRandom): 310, Laura (PlayerRandom): 280, Julia (PlayerFile): 263]\n" + 
							 "Hit ranking = [Laura (PlayerRandom): 120, Simon (PlayerFile): 100, Julia (PlayerFile): 46, Raul (PlayerRandom): 45]";

	final String SRANKING5 = "Craft ranking = [Julia (PlayerFile): 0, Laura (PlayerRandom): 0, Raul (PlayerRandom): 0, Simon (PlayerFile): 0]\n" + 
							 "Hit ranking = [Julia (PlayerFile): 0, Laura (PlayerRandom): 0, Raul (PlayerRandom): 0, Simon (PlayerFile): 0]";
	final String DIRFILES = "aluTests/files/";
	Ranking<CraftScore> craftRanking;
	Ranking<HitScore> hitRanking;
	HitScore hitScore;
	CraftScore craftScore;
	IPlayer playerJulia, playerRaul;
	IPlayer playerLaura, playerSimon;
	
	
	@Before
	public void setUp() throws Exception {
		craftRanking = new Ranking<CraftScore>();
		hitRanking = new Ranking<HitScore>();
		playerJulia= PlayerFactory.createPlayer("Julia", DIRFILES+"ShipsFile1.in");
		playerRaul= PlayerFactory.createPlayer("Raul", "21");
		playerLaura = PlayerFactory.createPlayer("Laura", "34");
		playerSimon = PlayerFactory.createPlayer("Simon", DIRFILES+"ShipsFile1.in");
	}

	 /* Se comprueba que el constructor crea los set y que están vacíos */
	@Test
	public void testRanking() {
		assertNotNull(craftRanking.getSortedRanking());
		assertNotNull(hitRanking.getSortedRanking());
		assertTrue(craftRanking.getSortedRanking().isEmpty());
		assertTrue(hitRanking.getSortedRanking().isEmpty());
	}
	

	/*
	 * Se crea para Julia los 2 tipos de Scores y se añaden a los distintos Ranking. Se 
	 * comprueba que la salida de los ranking coincide con SRANKING1.
	 * Julia realiza disparos al agua y destruye 10 Battleship. 
	 * Realiza lo mismo ahora para Raul, disparando sobre 4 veces sobre un Craft y hundiendo
	 * 4 Carrier y se añade a los distintos Ranking anteriores.
	 * Comprueba que la salida  coincide con SRANKING3
	 * 
	 */
	@Test
	public void testAddScore() throws BattleshipException {
		
		
		//Iniciamos marcadores para Julia
		initScores(playerJulia);
		
		//Los añadimos al ranking
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		compareRankings(SRANKING1, rankingsToString());
		
		//Realizamos disparos al agua. Los marcadores de Julia deben seguir iguales a 0
		//Hunde 10 Battleship
		for (int i=0; i<10; i++) {
			hitScore.score(CellStatus.WATER);
			craftScore.score(CraftFactory.createCraft("ship.Battleship", Orientation.NORTH));
		}
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		
		//Iniciamos marcadores para Raul
		initScores(playerRaul);
		//Raul realiza 4 disparos sobre un Craft de Julia. Y destruye 4 carriers
		for (int i=0; i<3; i++) {
			hitScore.score(CellStatus.HIT);
			craftScore.score(CraftFactory.createCraft("ship.Carrier", Orientation.EAST));
		}
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);	
		compareRankings(SRANKING2, rankingsToString());
	}

	
	/* Para Julia:
	 * Se crean los 2 tipos de Scores y se añaden a los distintos Ranking.  
	 * Se comprueba que la salida de los ranking coincide con SRANKING1.
	 * Julia realiza varios disparos, algunos fueron disparos al agua, 30 sobre
	 * Crafts, destruyendo 5 Battleship. 
	 * 
	 * Para Raul:
	 * Se realiza lo mismo ahora para Raul, disparando  40 veces sobre  Crafts de Julia
	 * y hundiendo solo 2 Carrier y se añaden a los distintos Ranking anteriores.
	 * Se omprueba que la salida obtenida con la función auxiliar rankingsToString() coincide 
	 * con SRANKING2
	 * 
	 */
	@Test
	public void testAddScoreAndGetWinner1() throws BattleshipException {
		
		
		//Para Julia
		initScores(playerJulia);
		
		//Los añadimos al ranking
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);

		compareRankings(SRANKING1, rankingsToString());
		
		//Realizamos disparos al agua. Los marcadores de Julia deben seguir iguales a 0
		//Hunde 10 Battleship
		for (int i=0; i<30; i++) {
			hitScore.score(CellStatus.HIT);
			hitScore.score(CellStatus.WATER);
			if (i<5) craftScore.score(CraftFactory.createCraft("ship.Battleship", Orientation.NORTH));
		}
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		compareScores("Julia(PlayerFile):30",hitRanking.getWinner().toString());
		compareScores("Julia(PlayerFile):30",craftRanking.getWinner().toString());
		
		initScores(playerRaul);
		
		
		//Raul realiza 40 disparos sobre Crafts de Julia. Y destruye 2 carriers
		for (int i=0; i<40; i++) {
			hitScore.score(CellStatus.HIT);
			if (i<2) craftScore.score(CraftFactory.createCraft("ship.Carrier", Orientation.EAST));
		}
		//Los añadimos al ranking
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);	
		compareScores("Raul(PlayerRandom):40",hitRanking.getWinner().toString());
		compareScores("Julia(PlayerFile):30",craftRanking.getWinner().toString());
		compareRankings(SRANKING3, rankingsToString());
	}

	
	/* Prueba de Score y Ranking para 4 jugadores
	 * 
	 */
	@Test
	public void testAddScoreAndGetWinner2() throws BattleshipException {
		
		//Play Julia
		initScores(playerJulia);
			
		//Julia realiza 46 disparos a distintos Crafts y destruye 46 Destroyer, 10 Cruiser y 5 Bomber
		for (int i=0; i<46; i++) {
			hitScore.score(CellStatus.HIT);
			craftScore.score(CraftFactory.createCraft("ship.Destroyer", Orientation.NORTH));
			if (i<10) craftScore.score(CraftFactory.createCraft("ship.Cruiser", Orientation.WEST));
			if (i<5) craftScore.score(CraftFactory.createCraft("aircraft.Bomber", Orientation.EAST));
		}
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		compareScores("Julia(PlayerFile):263",craftRanking.getWinner().toString());	
		compareScores("Julia(PlayerFile):46",hitRanking.getWinner().toString());
		
		//Play Raul
		initScores(playerRaul);
		//Raul realiza 45 disparos a distintos Crafts y destruye 10 Carrier, 30 Battleship y 5 Fighter
		for (int i=0; i<45; i++) {
			hitScore.score(CellStatus.HIT);
			if (i<30) craftScore.score(CraftFactory.createCraft("ship.Battleship", Orientation.NORTH));
			if (i<10) craftScore.score(CraftFactory.createCraft("ship.Carrier", Orientation.WEST));
			if (i<5) craftScore.score(CraftFactory.createCraft("aircraft.Fighter", Orientation.EAST));
		}
		
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		compareScores("Raul(PlayerRandom):310",craftRanking.getWinner().toString());	
		compareScores("Julia(PlayerFile):46",hitRanking.getWinner().toString());
		
		//Play Laura
		initScores(playerLaura);
		//Laura realiza 120 disparos a distintos Crafts y destruye 30 Battleship, 10 Cruiser y 5 Fighter
		for (int i=0; i<120; i++) {
			hitScore.score(CellStatus.HIT);
			if (i<30) craftScore.score(CraftFactory.createCraft("ship.Battleship", Orientation.EAST));
			if (i<10) craftScore.score(CraftFactory.createCraft("ship.Cruiser", Orientation.WEST));
			if (i<5) craftScore.score(CraftFactory.createCraft("aircraft.Fighter", Orientation.SOUTH));
		}
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		compareScores("Raul(PlayerRandom):310",craftRanking.getWinner().toString());	
		compareScores("Laura(PlayerRandom):120",hitRanking.getWinner().toString());
		
		//Play Simon
		initScores(playerSimon);
		//Simon realiza 100 disparos a distintos Crafts y destruye 40 Bomber, 15 Destroyer y 75 Transport
		for (int i=0; i<100; i++) {
			hitScore.score(CellStatus.DESTROYED);
			if (i<40) craftScore.score(CraftFactory.createCraft("aircraft.Bomber", Orientation.NORTH));
			if (i<15) craftScore.score(CraftFactory.createCraft("ship.Destroyer", Orientation.WEST));
			if (i<75) craftScore.score(CraftFactory.createCraft("aircraft.Transport", Orientation.EAST));
		}
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		compareScores("Simon(PlayerFile):1995",craftRanking.getWinner().toString());	
		compareScores("Laura(PlayerRandom):120",hitRanking.getWinner().toString());
		compareRankings(SRANKING4, rankingsToString());
	}
	
	
	/* Prueba de Ranking para 4 jugadores con los mismos scores.
	 * Debe ordenar por nombre.
	 * 
	 */
	@Test
	public void testRankingSortedByName() throws BattleshipException {
		
		//Play Julia
		initScores(playerJulia);
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		//Play Raul
		initScores(playerRaul);
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		//Play Laura
		initScores(playerLaura);
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		//Play Simon
		initScores(playerSimon);
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		compareRankings(SRANKING5, rankingsToString());
	}

	/* Prueba del método getWinner */
	@Test
	public void testGetWinner() throws EmptyRankingException {
		initScores(playerJulia);
		for (int i=0; i<100; i++) {
			hitScore.score(CellStatus.DESTROYED);
			craftScore.score(CraftFactory.createCraft("aircraft.Fighter", Orientation.SOUTH));
		}
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		
		initScores(playerRaul);
		for (int i=0; i<75; i++) {
			hitScore.score(CellStatus.HIT);
			hitScore.score(CellStatus.WATER);
			craftScore.score(CraftFactory.createCraft("aircraft.Transport", Orientation.WEST));
			craftScore.score(CraftFactory.createCraft("aircraft.Bomber", Orientation.NORTH));
		}
		craftRanking.addScore(craftScore);
		hitRanking.addScore(hitScore);
		compareScores("Raul(PlayerRandom):2475",craftRanking.getWinner().toString());	
		compareScores("Julia(PlayerFile):100",hitRanking.getWinner().toString());
	}
	
	/* Comprobación de la excepción EmptyRankingException con getWinner()
	 * cuando no hay valores en los rankings.
	 */
	@Test(expected=EmptyRankingException.class)
	public void testGetWinnerException() throws EmptyRankingException {
		try {
			hitRanking.getWinner();
			fail("Error, no se lanzó EmptyRankingException");
		} catch (EmptyRankingException e) {
			craftRanking.getWinner();	
		}
	}
	
	

	/*************************
	 * FUNCIONES AUXILIARES
	 *************************/
	
	/* Construye la salida como String, con los distintos Ranking */
	private String rankingsToString () {
		StringBuilder ps = new StringBuilder();
		ps.append("Craft ranking = "+craftRanking.getSortedRanking()+"\n");
		ps.append("Hit ranking = "+ hitRanking.getSortedRanking()+"\n");
		return ps.toString();
	}
	
	String removeSpaces (String str) {
		String exp[]=str.split(" ");
		String nstr=new String("");
		for (String s: exp) {
			if (! s.equals(" ") ) nstr+=s; 
		}
		return nstr;
	}

	/* Para las salidas Score.toString() compara los valores impresos
	 * de los Scores hasta una precisión de 0.01
	 * 
	 */
	private void compareScores(String expected, String result ) {
		String ex[]= expected.split(":");
		String re[]= result.split(":");
		if (ex.length!=re.length) fail("Lineas distintas");
		if (ex.length==2) {
			if (removeSpaces(ex[0]).equals(removeSpaces(re[0]))) {
				double ed = Double.parseDouble(ex[1]);
				double rd = Double.parseDouble(re[1]);
		
				assertEquals(ex[0],ed,rd,0.01);
			}
			else fail("Nombres jugadores distintos: esperado=<"+ex[0].trim()+"> obtenido=<"+re[0].trim()+">");
		}
		else
			assertEquals(removeSpaces(expected),removeSpaces(result));		
	}	
	
	/* Se usa para comparar los valores impresos de los rankings con una precisión 
	 * de 0.01
	 */
	private void compareRankings(String expected, String result) {
		String exp[]=expected.split("\n");
		String res[]=result.split("\n");
		boolean iguales = true;
		for (int i=0; i<exp.length && iguales; i++) {
			
			String exp1[]=exp[i].split(", ");
			String res1[]=res[i].split(", ");
			if (exp1.length!=res1.length) 
				fail("Error: cantidad de jugadores distintos en el ranking "+res[i]);
			for (int k=0; k<exp1.length; k++) { //Para cada Score
				if (k==exp1.length-1) //Es el último Score
					compareScores(exp1[k].substring(0, exp1[k].indexOf(']')).trim(),res1[k].substring(0, res1[k].indexOf(']')).trim());
				else 
					compareScores(exp1[k].trim(),res1[k].trim());
				
			}
		
		}
	}
	

	/*Inicia los Score.
	*/
	private void initScores (IPlayer player) {
			hitScore = new HitScore(player);
			craftScore = new CraftScore(player);
	}
		
	
}
