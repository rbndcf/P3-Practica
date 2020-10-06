package mains;

import model.Board;
import model.Coordinate;
import model.Orientation;
import model.Ship;

public class MainP2 {

	public static void main(String[] args) {
		
		Coordinate c1 = new Coordinate(7,5);
		Coordinate c2 = new Coordinate(-6,3);
		
		Coordinate c3 = c1.add(c2);
		
		System.out.println("c2.x="+c2.get(0)+", c2.y="+c2.get(1));
		System.out.println("c3="+c3.toString());
		
		System.out.println("c1.equals(c3)="+c1.equals(c3));
		
		
		Board b = new Board(10);
		Ship portaaviones = new Ship(Orientation.EAST,'P',"Dijkstra");
		Ship submarino = new Ship(Orientation.NORTH,'s',"Boole");
		Ship destructor = new Ship(Orientation.EAST,'d',"Knuth");
		
		b.addShip(portaaviones,new Coordinate(0,0));
		b.addShip(submarino,new Coordinate(5,5));
		b.addShip(destructor,new Coordinate(2,3));
		
		System.out.println(b);
		System.out.println(portaaviones);
	}
}