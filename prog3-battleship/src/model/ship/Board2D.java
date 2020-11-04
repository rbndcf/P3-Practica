package model.ship;

import model.*;

public class Board2D extends Board{
	public Board2D(int size) {
		super(size);
	}
	
	@Override
	public boolean checkCoordinate(Coordinate c) {
		if(!(c instanceof Coordinate2D))
			throw new IllegalArgumentException();
		
		if(c.get(0) < 0 || c.get(0) >= this.getSize() || c.get(1) < 0 || c.get(1) >= this.getSize()) return false;
		else return true;
	}
	
	@Override
	public String show(boolean unveil) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < this.getSize() ; i++) {
			for(int j = 0 ; j < this.getSize() ; j++) {
				Coordinate c = CoordinateFactory.createCoordinate(j, i);
				
				if(unveil) {
					if(this.getCraft(c) != null) {
						if(this.getCraft(c).isHit(c))
							sb.append(HIT_SYMBOL);
							
						else
							sb.append(this.getCraft(c).getSymbol());
					}
					
					else
						sb.append(WATER_SYMBOL);
				}
				
				else {
					if(this.isSeen(c)) {
						if(this.getCraft(c) != null && this.getCraft(c).isShotDown())
							sb.append(this.getCraft(c).getSymbol());
						
						else if(this.getCraft(c) != null)
							sb.append(HIT_SYMBOL);
						
						else
							sb.append(WATER_SYMBOL);
					}
					
					else
						sb.append(NOTSEEN_SYMBOL);
				}
			}
			
			if(i < (this.getSize() - 1))
				sb.append("\n");
		}
		
		return sb.toString();
	}
}