package model.io;

import model.*;
import model.exceptions.*;
import model.exceptions.io.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Interfaz que utilizaremos para crear los diferentes Jugadores
 */
public interface IPlayer {
	/**
	 * @return name
	 */
	public String getName();
	/**
	 * @param b Board donde se van a colocar los barcos
	 * @throws BattleshipIOException cuando ocurre una excepcion I/O
	 * @throws InvalidCoordinateException cuando la coordenada no pertenece al tablero
	 * @throws OccupiedCoordinateException cuando la coordenada ya está ocupada
	 * @throws NextToAnotherCraftException cuando la coordenada se encuentra al lado de otro craft
	 */
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException;
	/**
	 * @param b Board en el que se va a disparar
	 * @throws BattleshipIOException cuando ocurre una excepcion I/O
	 * @throws InvalidCoordinateException cuando la coordenada no pertenece al tablero
	 * @throws CoordinateAlreadyHitException cuando la coordenada ya ha sido alcanzada anteriormente
	 * @return coordenada a la que se dispara
	 */
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException;
	
	/**
	 * @return estado del ultimo disparo
	 */
	public CellStatus getLastShotStatus();
}