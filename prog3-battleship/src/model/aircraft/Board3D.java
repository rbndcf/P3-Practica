package model.aircraft;

import model.*;

/**
 * 
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos para crear Boards de 3 dimensiones, a parte de comprobar las coordenadas de dicho board
 * y hacer el show de cada coordenada, separando cada altura con el Board_SEPARATOR de la superclase
 */
public class Board3D extends Board{
	/**
	 * @param size del board
	 * Constructor por parámetros
	 */
	public Board3D(int size) {
		super(size);
	}
	
	/**
	 * @param c Coordenada a checkear
	 * @throws IllegalArgumentException cuando la coordenada c no es una coordenada 3D
	 * @return true si esta dentro, false si no
	 * Recibe una coordenada y devuelve true o false según esté dentro del board o no
	 */
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
	
	/**
	 * @param unveil
	 * @return estado de la coordenada
	 * Recibe si se quieren mostrar todas las casillas o no, en caso de que si se quieran ver se mostrarán todas las coordenadas del board, en caso
	 * de que no se quieran ver todas solo se mostraran las casillas que hayan sido vistas anteriormente. Cada altura del board se separa de las
	 * demás utilizando Board_SEPARATOR de la superclase
	 */
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