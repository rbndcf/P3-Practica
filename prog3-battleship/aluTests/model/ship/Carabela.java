package model.ship;

import model.Orientation;

public class Carabela extends Ship {

	public Carabela(Orientation orientation) {
		super(orientation, 'C', "Carabela");
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
			    0, 0, 0, 1, 0,	
			    0, 1, 1, 1, 1,	
			    0, 0, 0, 1, 0,
			    0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
			    0, 0, 1, 0, 0,	
			    0, 1, 1, 1, 1,	
			    0, 0, 1, 0, 0,
			    0, 0, 0, 0, 0}};
	}

	@Override
	public int getValue() {
		return 10;
	}
	
}
