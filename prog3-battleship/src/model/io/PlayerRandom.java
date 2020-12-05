package model.io;

import java.util.*;

import model.*;
import model.ship.*;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.aircraft.*;

public class PlayerRandom implements IPlayer{
	private Random random;
	private String name;
	
	public PlayerRandom(String name, long seed) {
		this.name = name;
		random = new Random(seed);
	}
	
	public String getName() {
		return name + " (PlayerRandom)";
	}
	
	public void putCrafts(Board b) {
		Craft battleship = CraftFactory.createCraft("Battleship", Orientation.values()[genRandomInt(0, Orientation.values().length)]);
		this.setRandomPosition(battleship, b);
		
		Craft carrier = CraftFactory.createCraft("Carrier", Orientation.values()[genRandomInt(0, Orientation.values().length)]);
		this.setRandomPosition(carrier, b);

		Craft cruiser = CraftFactory.createCraft("Cruiser", Orientation.values()[genRandomInt(0, Orientation.values().length)]);
		this.setRandomPosition(cruiser, b);

		Craft destroyer = CraftFactory.createCraft("Destroyer", Orientation.values()[genRandomInt(0, Orientation.values().length)]);
		this.setRandomPosition(destroyer, b);
		
		if(b instanceof Board3D) {
			Craft bomber = CraftFactory.createCraft("Bomber", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			this.setRandomPosition(bomber, b);
			
			Craft fighter = CraftFactory.createCraft("Fighter", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			this.setRandomPosition(fighter, b);
			
			Craft transport = CraftFactory.createCraft("Transport", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			this.setRandomPosition(transport, b);
		}
	}
	
	public Coordinate nextShoot(Board b) throws InvalidCoordinateException, CoordinateAlreadyHitException {
		Coordinate c = genRandomCoordinate(b, 0);
		b.hit(c);
		return c;
	}
	
	private int genRandomInt(int min, int max) { 
	    return random.nextInt(max-min)+min;
	}
	
	private Coordinate genRandomCoordinate(Board b, int offset) {
		if(b instanceof Board2D)
			return CoordinateFactory.createCoordinate(genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()));
		else
			return CoordinateFactory.createCoordinate(genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()));
	}
	
	//Función auxiliar//
	private void setRandomPosition(Craft c, Board b) {
		int z = 0;
		
		while(z < 100) {
			try {
				b.addCraft(c, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
				break;
			}
			catch(Exception e) {
				z++;
			}
		}
	}
}
