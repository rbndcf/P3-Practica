package model.ship;

import model.Orientation;

/**
 * This class adds a marine mine. FUCK YOU, SHIP'S!
 */
public class Mine extends Ship {

	/**
	 * Instantiates a new mine.
	 *
	 * @param o the orientation
	 */
	public Mine(Orientation o) {
		super(o, '¤', "Mine");
		this.shape = new int[][] {
		      { 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0,	
			   	0, 0, 1, 0, 0,	
			   	0, 0, 0, 0, 0,
			   	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
			   	0, 0, 0, 0, 0,	
				0, 0, 1, 0, 0,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 0, 1, 0, 0,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 0, 1, 0, 0,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0}};
	}

	/**
	 * Gets the value.
	 *
	 * @return the value of the mine. That's 1.
	 */
	@Override
	public int getValue() {
		return 1;
	}

}
