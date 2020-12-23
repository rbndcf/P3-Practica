package mains;

import model.Board;
import model.Game;
import model.aircraft.Board3D;
import model.exceptions.io.BattleshipIOException;
import model.exceptions.score.EmptyRankingException;
import model.io.IPlayer;
import model.io.IVisualiser;
import model.io.PlayerFactory;
import model.io.VisualiserFactory;
import model.score.CraftScore;
import model.score.HitScore;
import model.score.Ranking;

public class MainP5 {

	public static void main(String[] args) {
		
		Ranking<HitScore> hitRanking = new Ranking<>();
		Ranking<CraftScore> craftRanking = new Ranking<>();
		
		try {
			IPlayer player1 = PlayerFactory.createPlayer("John","files/playerfile-john.txt");
			IPlayer player2 = PlayerFactory.createPlayer("Mary","files/playerfile-mary.txt");
			Board b1 = new Board3D(6);
			Board b2 = new Board3D(6);
			
			Game game = new Game(b1, b2, player1, player2);

			IVisualiser visualiser = VisualiserFactory.createVisualiser("Console", game);
			
			game.playGame(visualiser);
			System.out.println(game.getScoreInfo());
			
			hitRanking.addScore(game.getHitScorePlayer1());
			hitRanking.addScore(game.getHitScorePlayer2());
			craftRanking.addScore(game.getCraftScorePlayer1());
			craftRanking.addScore(game.getCraftScorePlayer2());
			
			System.out.println("According to HitScore the winner is: " + hitRanking.getWinner());
			System.out.println("According to CraftScore the winner is: " + craftRanking.getWinner());
		} catch (BattleshipIOException | EmptyRankingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}