package model.io;

public class VisualiserFactory {
	public IVisualiser createVisualiser(String n, Game g) {
		if(n == "Console")
			return new VisualiserConsole(g);
		else if(n == "GIF")
			return new VisualiserGIF(g);
		else
			return null;
	}
}
