package model.ship;

import model.*;

public class Battleship extends Ship{
	public Battleship(Orientation o) {
		super(o, 'O', "Battleship");
		
		shape = new int[][] {
	      	  { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
			   	0, 0, 1, 0, 0,
			   	0, 0, 1, 0, 0},
		   	  { 0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0,	
		    	0, 1, 1, 1, 1,	
		    	0, 0, 0, 0, 0,
			   	0, 0, 0, 0, 0},
		   	  { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		   	  { 0, 0, 0, 0, 0,
			   	0, 0, 0, 0, 0,	
			   	0, 1, 1, 1, 1,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0}
		};
	}
}