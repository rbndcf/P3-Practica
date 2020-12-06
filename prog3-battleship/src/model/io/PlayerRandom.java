package model.io;

import java.util.*;

import model.*;
import model.ship.*;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.aircraft.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos para controlar los jugadores aleatorios, tanto cuando tengan que poner los barcos con putCraft como los shoots
 */
public class PlayerRandom implements IPlayer{
	/**
	 * @param random Random para generar ints
	 */
	private Random random;
	/**
	 * @param name nombre del jugador
	 */
	private String name;
	
	/**
	 * @param name nombre del jugador
	 * @param seed para el random
	 * Constructor por parámetros
	 */
	public PlayerRandom(String name, long seed) {
		this.name = name;
		random = new Random(seed);
	}
	
	/**
	 * @return name del jugador + tipo de jugador
	 */
	public String getName() {
		return name + " (PlayerRandom)";
	}
	
	/**
	 * @param b Board donde se van a colocar los barcos
	 * Recibe un board y se colocan los barcos en el, además, si el board es 3d, se añaden también
	 * los aviones
	 */
	public void putCrafts(Board b) {
		Craft battleship = CraftFactory.createCraft("Battleship", Orientation.values()[genRandomInt(0, Orientation.values().length)]);
		this.setRandomPosition(battleship, b);
		
		Craft carrier = CraftFactory.createCraft("Carrier", Orientation.values()[genRandomInt(0, Orientation.values().length)]);
		this.setRandomPosition(carrier, b);

		Craft cruiser = CraftFactory.createCraft("Cruiser", Orientation.values()[genRandomInt(0, Orientation.values().length)]);
		this.setRandomPosition(cruiser, b);

		Craft destroyer = CraftFactory.createCraft("Destroyer", Orientation.values()[genRandomInt(0, Orientation.values().length)]);
		this.setRandomPosition(destroyer, b);
		
		if(b instanceof Board3D) {
			Craft bomber = CraftFactory.createCraft("Bomber", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			this.setRandomPosition(bomber, b);
			
			Craft fighter = CraftFactory.createCraft("Fighter", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			this.setRandomPosition(fighter, b);
			
			Craft transport = CraftFactory.createCraft("Transport", Orientation.values()[genRandomInt(0,Orientation.values().length)]);
			this.setRandomPosition(transport, b);
		}
	}
	
	/**
	 * @param b Board en el que se van a realizar los disparos
	 * @throws InvalidCoordinateException si la coordenada no está dentro del board
	 * @throws CoordinateAlreadyHitException si la coordenada ya fue alcanzada anteriormente
	 * @return c Coordenada a la que dispara
	 * Recibe un tablero, y genera una coordenada aleatoria con la que dispara a esa coordenada
	 */
	public Coordinate nextShoot(Board b) throws InvalidCoordinateException, CoordinateAlreadyHitException {
		Coordinate c = genRandomCoordinate(b, 0);
		b.hit(c);
		return c;
	}
	
	/**
	 * @param min numero minimo para el numero aleatorio
	 * @param max numero maximo para el numero aleatorio
	 * @return numero aleatorio
	 * Recibe un numero minimo y otro maximo, y devuelve un numero aleatorio comprendido entre esos dos numeros
	 */
	private int genRandomInt(int min, int max) { 
	    return random.nextInt(max-min)+min;
	}
	
	/**
	 * @param b Tablero del que queremos la coordenada
	 * @param offset
	 * @return Coordenada (2D o 3D) aleatoria
	 * Recibe un tablero del que se quiere una coordenada aleatoria, junto a un offset para poder colocar los barcos en el borde del tablero, y devuelve
	 * una coordenada aleatoria
	 */
	private Coordinate genRandomCoordinate(Board b, int offset) {
		if(b instanceof Board2D)
			return CoordinateFactory.createCoordinate(genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()));
		else
			return CoordinateFactory.createCoordinate(genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()));
	}
	
	// Función Auxiliar \\
	/**
	 * @param c Craft que se quiere posicionar
	 * @param b Tablero en el que se quiere posicionar
	 * Recibe un craft e intenta posicionarlo con coordenadas aleatorias en el board que recibe, si una coordenada no es valida genera otra, hasta un
	 * maximo de 100 coordenadas
	 */
	private void setRandomPosition(Craft c, Board b) {
		int z = 0;
		
		while(z < 100) {
			try {
				b.addCraft(c, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
				break;
			}
			catch(Exception e) {
				z++;
			}
		}
	}
}
