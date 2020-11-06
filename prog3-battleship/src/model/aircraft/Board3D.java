package model.aircraft;

import model.*;

public class Board3D extends Board{
	public Board3D(int size) {
		super(size);
	}
	
	@Override
	public boolean checkCoordinate(Coordinate c) {
		if(c instanceof Coordinate3D)
			if(c.get(0) < 0 || c.get(0) >= this.getSize() || c.get(1) < 0 || c.get(1) >= this.getSize() || c.get(2) < 0 || c.get(2) >= this.getSize())
				return false;
			else
				return true;
		else
			throw new IllegalArgumentException();
	}
	
	@Override
	public String show(boolean unveil) {
		StringBuilder sb = new StringBuilder();

		for(int y = 0 ; y < this.getSize() ; y++) {
			for(int z = 0 ; z < this.getSize() ; z++) {
				for(int x = 0 ; x < this.getSize() ; x++) {
					Coordinate c = new Coordinate3D(x, y, z);
					
					if(unveil) 
						if(this.getCraft(c) != null)
							if(this.getCraft(c).isHit(c))
								sb.append(HIT_SYMBOL);
							else
								sb.append(this.getCraft(c).getSymbol());
						else
							sb.append(WATER_SYMBOL);
					else if(this.isSeen(c))
						if(this.getCraft(c) != null)
							if(this.getCraft(c).isShotDown())
								sb.append(this.getCraft(c).getSymbol());
							else if(this.getCraft(c).isHit(c))
								sb.append(HIT_SYMBOL);
							else
								sb.append(this.getCraft(c).getSymbol());
						else
							sb.append(WATER_SYMBOL);
					else
						sb.append(NOTSEEN_SYMBOL);
				}
				if(z != this.getSize()-1)
					sb.append(Board_SEPARATOR);
			}
			if(y != this.getSize()-1)
				sb.append("\n");
		}
			
		return sb.toString();
	}
}