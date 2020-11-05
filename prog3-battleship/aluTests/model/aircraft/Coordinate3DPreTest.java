package model.aircraft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.Coordinate;
import model.ship.Coordinate2D;

public class Coordinate3DPreTest{
	
    List<int[]> vcoordinates = new ArrayList<int[]>();
    int []vcoor= {0,0,-70,-2,20,75}; //Para crear coordenadas
    final int DIM = vcoor.length;
    List<Coordinate> lcoor;
    
	@Before
	public void setUp() throws Exception {
		lcoor = new ArrayList<Coordinate>();
		//Se crean las Coordinate (0,0,-70),(0,-70, -2), (-70,-2,20),(-2,20,75);
				for (int i=0; i<DIM-2; i++) {
					lcoor.add(new Coordinate3D(vcoor[i],vcoor[i+1],vcoor[i+2]));
				}
		
	}
    /* Adapta los test de Coordinate2D (CoordinateP1Test y CoordinateP2Test) para Coordinate3D */
	
	//TODO
	@Test
	public void testHashCode() {
		fail ("Realiza el test");
	}

	//TODO
	/* Se comprueba que el Constructor funciona bien. Para ello se analiza que 
	 * las componentes '0', '1' y '2' de cada Coordinate3D creada en el setUp() son las 
	 * correctas.
	 */
	@Test
	public void testCoordinateConstructor() {
		fail ("Realiza el test");
	}

	//TODO
	/* Se comprueba que el constructor de copia crea una nueva Coordinate3D con
	 * los mismos valores que las componentes respectivas del Coordinate3D copiado.
	 * Y eso se hace para cada Coordinate3D creada en setUp();
	 */
	@Test
	public void testCoordinateConstructorCopy() {
		fail ("Realiza el test");
	}

	//TODO
	/* Se comprueba que el método get(int) para cada componente de una Coordinate 
	 * funciona correctamente.
	 * Se modifican los valores de las componentes de la Coordinate anterior con 
	 * el método set(int, int) y se comprueba con get(int) que los valores de sus 
	 * componentes han cambiado a dichos valores.
	 * OBSERVACION: No podrás usar el método set de Coordinate, ANALIZA POR QUÉ NO PUEDES.
	 * Al final del fichero encontrarás una clase auxiliar con la que podrás modificar los
	 * valores.
	 */
	@Test
	public void testGetSet() {
		fail ("Realiza el test");
	}

	//TODO
	/* Se suman las Coordinate creadas en el setUp() y se comprueba, conforme 
	 * se van sumando, que los valores de sus componentes van tomando los 
	 * valores correctos y que el Coordinate3D que devuelve no es el mismo que
	 * el Coordinate3D que invoca al método.
	 */
	@Test  //{0,0,-70,-2,20, 75}
	public void testAdd() {
		fail ("Realiza el test");
	}

	//TODO
	/* Se van restando las Coordinate3D creadas en el setUp() y se comprueba, 
	 * conforme se van restando, que los valores de sus componentes van tomando 
	 * los valores correctos y que el Coordinate3D que devuelve no es el mismo que
	 * el Coordinate3D que invoca al método.
	 */
	@Test
	public void testSubtract() {
		fail ("Realiza el test");
	}
	
	//TODO
	/* Se comprueba, para el método toString(), que las Coordinate creadas en el setUp() 
	 * tienen el formato correcto.
	 */
	@Test
	public void testToString() {
		fail ("Realiza el test");
		
	}
	
	//TODO
	/* Creamos copias de las Coordinates3D creadas en el setUp() y comprobamos que:
	 * 1 - La copia y el original no son la misma.
	 * 2 - La copia tiene los mismos valores en las componentes respectivas que el objeto copiado.
	 */
	@Test
	public void testCopy() {
		fail ("Realiza el test");
	}

	//TODO
	/* Se crea una Coordinate3D y a partir de ella se obtienen las Coordinate3D adyacentes 
	 * que se guardan en un Set<Coordinate>. Para cada una de las posiciones adyacentes
	 * a la Coordinate3D inicial se crea una Coordinate3D, y se va comprobando están
	 * contenidas en el Set.
	 * 
	 */
	@Test
	public void testAdjacentCoordinates() {
		fail ("Realiza el test");
	}
	
	//TODO
	/* Se toma una Coordinate3D y se comprueba todas las posibles condiciones bajo 
	 * las cuales nuestra función equals() devuelve true o false
	 */
	@Test
	public void testEqualsObject() {
		fail ("Realiza el test");
	}
	
/********************************************/
	//FUNCIÓN DE APOYO
		String removeSpaces (String str) {
			String exp[]=str.split(" ");
			String nstr=new String("");
			for (String s: exp) {
				if (! s.equals(" ") ) nstr+=s; 
			}
			return nstr;
		}

	//CLASE AUXILIAR PARA USAR set DE Coordinate
	class Coordinate3DAux extends Coordinate3D {
		protected Coordinate3DAux(Coordinate3D c) {
			super(c);
			// TODO Auto-generated constructor stub
		}
		
		public void set(int component, int value) {
			super.set(component,value);
		}
		
		public int get(int component) {
			return super.get(component);
		}		
	}
}
