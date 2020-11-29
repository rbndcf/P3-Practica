package model.io;

import java.io.*;
import java.util.Objects;
import model.*;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.*;

public class PlayerFile implements IPlayer{
	private BufferedReader br;
	private String name;
	
	public PlayerFile(String name, BufferedReader reader) {
		Objects.requireNonNull(reader);
		
		br = new BufferedReader(reader);
		this.name = name;
	}
	
	public String getName() {
		return name + " (PlayerFile)";
	}
	
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException{
		String line;
		
		Objects.requireNonNull(b);
		
		try {
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\s+");
				Craft craft;
				int[] coords;
				
				if(line == "endput" || line == "exit")
					break;
				else if(tokens[0] != "put")
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
	
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException{
		String line;
		
		Objects.requireNonNull(b);
		
		try {
			if((line = br.readLine()) != null) {
				line = br.readLine();
				
				String[] tokens = line.split("\\s+");
				
				if(line == "exit")
					return null;
				else if(tokens[0] != "shoot")
					throw new BattleshipIOException("Comando diferente a shoot o exit");

				if(tokens.length == 4 && tokens.length == 3) {
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