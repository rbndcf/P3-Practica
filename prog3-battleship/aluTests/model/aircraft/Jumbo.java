package model.aircraft;

import model.Orientation;

public class Jumbo extends Aircraft {

	public Jumbo(Orientation orientation) {
		super(orientation, 'J', "Jumbo");
		 shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	1, 0, 1, 0, 1,
		    	0, 0, 1, 0, 0},
		      { 0, 1, 0, 0, 0,
			    0, 0, 1, 0, 0,	
			    1, 1, 1, 1, 0,	
			    0, 0, 1, 0, 0,
			    0, 1, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
			    1, 0, 1, 0, 1,	
			    0, 1, 1, 1, 0,	
			    0, 0, 1, 0, 0,
			    0, 0, 0, 0, 0},
			  { 0, 0, 0, 1, 0,
				0, 0, 1, 0, 0,	
			    0, 1, 1, 1, 1,	
			    0, 0, 1, 0, 0,
			    0, 0, 0, 1, 0}}; 
	}

	@Override
	public int getValue() {
		return 20;
	}
	
}

