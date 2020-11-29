package model;

import model.aircraft.*;
import model.ship.*;

public class CraftFactory {
	public static Craft createCraft(String type, Orientation orientation) {
		switch(type) {
			case "Bomber": 		return new Bomber(orientation);
			case "Fighter":		return new Fighter(orientation);
			case "Transport":	return new Transport(orientation);
			case "Battleship":	return new Battleship(orientation);
			case "Carrier":		return new Carrier(orientation);
			case "Cruiser": 	return new Cruiser(orientation);
			case "Destroyer":	return new Destroyer(orientation);
			default: 			return null;
		}
	}
}
