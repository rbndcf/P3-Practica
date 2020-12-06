package model.io;

import java.io.*;
import java.util.Objects;
import model.*;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para controlar los Jugadores que funcionen por fichero, para poner sus barcos y los disparos
 */
public class PlayerFile implements IPlayer{
	/**
	 * @param br BufferedReader desde el que se leera el fichero
	 */
	private BufferedReader br;
	/**
	 * @param name nombre del jugador
	 */
	private String name;
	
	/**
	 * @param name nombre del jugador
	 * @param reader BufferedReader que se utilizará
	 * Constructor por parámetros
	 */
	public PlayerFile(String name, BufferedReader reader) {
		Objects.requireNonNull(reader);
		
		br = reader;
		this.name = name;
	}
	
	/**
	 * @return name
	 * Devuelve el nombre del jugador
	 */
	public String getName() {
		return name + " (PlayerFile)";
	}
	
	/**
	 * @param b Board donde se van a colocar los barcos
	 * @throws BattleshipIOException cuando ocurre una excepcion I/O
	 * @throws InvalidCoordinateException cuando la coordenada no pertenece al tablero
	 * @throws OccupiedCoordinateException cuando la coordenada ya está ocupada
	 * @throws NextToAnotherCraftException cuando la coordenada se encuentra al lado de otro craft
	 * Lee el BufferedReader buscando todos los comandos put para intentar colocar los barcos hasta que salga endput o exit. En caso de que lea
	 * algun comando diferente de put antes de el endput o exit, lanzará una excepcion
	 */
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException{
		String line;
		
		Objects.requireNonNull(b);
		
		try {
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\s+");
				Craft craft;
				int[] coords;
				
				if(line.equals("endput") || line.equals("exit"))
					break;
				else if(!tokens[0].equals("put"))
					throw new BattleshipIOException("Comando diferente de put, endput o exit");
				
				if(tokens.length == 5 || tokens.length == 6) {
					coords = new int[tokens.length - 3];
					
					for(int i = 3 ; i < tokens.length ; i++) {
						try { coords[i - 3] = Integer.parseInt(tokens[i]); }
						catch(NumberFormatException e) {
							throw new BattleshipIOException("Algún parámetro tras la orientación no es un numero");
						}
					}
					
					switch(tokens[2]) {
						case "NORTH":	craft = CraftFactory.createCraft(tokens[1], Orientation.NORTH);
										break;
						case "SOUTH": 	craft = CraftFactory.createCraft(tokens[1], Orientation.SOUTH);
										break;
						case "EAST":	craft = CraftFactory.createCraft(tokens[1], Orientation.EAST);
										break;
						case "WEST": 	craft = CraftFactory.createCraft(tokens[1], Orientation.WEST);
										break;
						default:		throw new BattleshipIOException("Orientación no declarada en el enum Orientation");
					}
					
					b.addCraft(craft, CoordinateFactory.createCoordinate(coords));
				}
				
				else throw new BattleshipIOException("Cantidad de parámetros en put incorrecta");
			}
		}

		catch(IOException e) {
			throw new BattleshipIOException("IOException al leer una línea de br");
		}
	}
	
	/**
	 * @param b Board en el que se va a disparar
	 * @throws BattleshipIOException cuando hay un problema con el fichero
	 * @throws InvalidCoordinateException cuando la coordenada no pertenece al tablero
	 * @throws CoordinateAlreadyHitException cuando la coordenada ya ha sido alcanzada anteriormente
	 * @return coordenada a la que se dispara
	 * Lee el fichero buscando todos los comandos shoot para disparar al tablero y devolver esa coordenada, en caso de que no dispare a ninguna coordenada
	 * devolverá null, además de lanzar una excepcion cuando ocurra algun problema con el fichero o con alguna coordenada
	 */
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException{
		String line;
		
		Objects.requireNonNull(b);
		
		try {
			if((line = br.readLine()) != null) {
				String[] tokens = line.split("\\s+");
				
				if(line.equals("exit"))
					return null;
				else if(!tokens[0].equals("shoot"))
					throw new BattleshipIOException("Comando diferente a shoot o exit");

				if(tokens.length == 4 || tokens.length == 3) {
					int[] coords = new int[tokens.length - 1];
					
					for(int i = 1 ; i < tokens.length ; i++) {
						try { coords[i-1] = Integer.parseInt(tokens[i]); }
						catch(NumberFormatException e) {
							throw new BattleshipIOException("Algún parámetro tras el comando shoot no es un número");
						}
					}
					
					b.hit(CoordinateFactory.createCoordinate(coords));
					return CoordinateFactory.createCoordinate(coords);
				}
				else
					throw new BattleshipIOException("Cantidad de parámetros en shoot incorrecta");
			}
			else return null;
		}
		catch(IOException e) {
			throw new BattleshipIOException("IOException al leer una línea de br");
		}
	}
}