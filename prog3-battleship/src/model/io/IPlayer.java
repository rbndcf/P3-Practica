package model.io;

import model.*;

public interface IPlayer {
	public String getName();
	public void putCrafts(Board b);
	public Coordinate nextShoot(Board b);
}
