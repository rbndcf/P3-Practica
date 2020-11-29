package model.io;

import model.*;
import model.exceptions.*;
import model.exceptions.io.*;

public interface IPlayer {
	public String getName();
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException;
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException;
}