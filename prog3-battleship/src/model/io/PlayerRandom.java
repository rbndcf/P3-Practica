package model.io;

import java.util.*;

import model.*;
import model.ship.*;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;

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
		Craft[] crafts;
		
		if(b instanceof Board2D) {
			crafts = new Craft[4];

			crafts[0] = CraftFactory.createCraft("Battleship", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			crafts[1] = CraftFactory.createCraft("Carrier", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			crafts[2] = CraftFactory.createCraft("Cruiser", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			crafts[3] = CraftFactory.createCraft("Destroyer", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
		}
		
		else {
			crafts = new Craft[7];

			crafts[0] = CraftFactory.createCraft("Battleship", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			crafts[1] = CraftFactory.createCraft("Carrier", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			crafts[2] = CraftFactory.createCraft("Cruiser", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			crafts[3] = CraftFactory.createCraft("Destroyer", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			
			crafts[4] = CraftFactory.createCraft("Bomber", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			crafts[5] = CraftFactory.createCraft("Fighter", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			crafts[6] = CraftFactory.createCraft("Transport", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
		}
		
		for(int i = 0 ; i < crafts.length ; i++) {
			int z = 0;
			
			while(z < 100) {
				try {
					b.addCraft(crafts[i], genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
					break;
				}
				catch(Exception e) {
					z++;
				}
			}
		}
		
		/*Craft battleship = CraftFactory.createCraft("Battleship", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
		Craft carrier = CraftFactory.createCraft("Carrier", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
		Craft cruiser = CraftFactory.createCraft("Cruiser", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
		Craft destroyer = CraftFactory.createCraft("Destroyer", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			
		if(b instanceof Board2D) {
			int i = 0;
			while(i < 100) {
				try {
					b.addCraft(battleship, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
					b.addCraft(carrier, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
					b.addCraft(cruiser, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
					b.addCraft(destroyer, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
				}
				catch(Exception e) {
					i++;
				}
			}
		}
		else {
			Craft bomber = CraftFactory.createCraft("Bomber", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			Craft fighter = CraftFactory.createCraft("Fighter", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			Craft transport = CraftFactory.createCraft("Transport", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			
			int i = 0;
			while(i < 100) {
				try {
					b.addCraft(battleship, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
					b.addCraft(carrier, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
					b.addCraft(cruiser, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
					b.addCraft(destroyer, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));

					b.addCraft(bomber, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
					b.addCraft(fighter, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
					b.addCraft(transport, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
				}
				catch(Exception e) {
					i++;
				}
			}
		}*/
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
}
