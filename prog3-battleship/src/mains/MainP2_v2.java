/*package mains;

import model.Board;
import model.Coordinate;
import model.Orientation;
import model.Craft;

public class MainP2_v2 {
	public static void main(String[] args) {
				
		Board bplayer1 = new Board(10);
		Craft s1p1 = new Craft(Orientation.EAST,'P',"Dijkstra");
		Craft s2p1 = new Craft(Orientation.NORTH,'s',"Boole");
		Craft s3p1 = new Craft(Orientation.EAST,'d',"Knuth");
		bplayer1.addCraft(s1p1,new Coordinate(0,0));
		bplayer1.addCraft(s2p1,new Coordinate(5,-1));
		bplayer1.addCraft(s3p1,new Coordinate(2,3));
		
		System.out.println("(1) Player 1:" + bplayer1);
		System.out.println(bplayer1.show(true));
		
		Board bplayer2 = new Board(10);
		Craft s1p2 = new Craft(Orientation.SOUTH,'X',"X-wing");
		Craft s2p2 = new Craft(Orientation.WEST,'M',"Millenium Falcon");
		Craft s3p2 = new Craft(Orientation.NORTH,'C',"Corellian cruiser");
		bplayer2.addCraft(s1p2,new Coordinate(0,0));
		bplayer2.addCraft(s2p2,new Coordinate(5,5));
		bplayer2.addCraft(s3p2,new Coordinate(1,1));

		System.out.println("(1) Player 2:"+bplayer2);
		System.out.println(bplayer2.show(true));
		
		bplayer2.addCraft(s3p2,new Coordinate(0,2));
		System.out.println("(2) Player 2:"+bplayer2);
		System.out.println(bplayer2.show(true));
		
		bplayer2.addCraft(s3p2,new Coordinate(9,0));
		System.out.println("(3) Player 2:"+bplayer2);
		System.out.println(bplayer2.show(true));
		
		bplayer2.hit(new Coordinate(0,0));
		bplayer1.hit(new Coordinate(1,1));
		bplayer2.hit(new Coordinate(2,2));
		bplayer2.hit(new Coordinate(3,3));
		bplayer1.hit(new Coordinate(5,5));
		bplayer2.hit(new Coordinate(2,3));
		bplayer2.hit(new Coordinate(2,1));

		System.out.println("-------------------");
		System.out.println("Player 1:" + bplayer1);
		System.out.println(bplayer1.show(true));
		System.out.println(bplayer1.show(false));
		
		System.out.println("-------------------");
		System.out.println("Player 2:" + bplayer2);
		System.out.println(bplayer2.show(true));
		System.out.println(bplayer2.show(false));
		
		System.out.println("-------------------");
		System.out.println(s3p1);
		System.out.println(s1p2);
	}
}*/