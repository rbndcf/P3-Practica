package model.io;

import java.util.Objects;
import model.*;

public class VisualiserConsole implements IVisualiser{
	private Game game;
	
	public VisualiserConsole(Game g){
		Objects.requireNonNull(g);
		game = g;
	}
	
	public void show() {
		System.out.println(game.toString());
	}
	
	public void close() {}
}
