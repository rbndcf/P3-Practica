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
					lcoor.add(new Coordinate3D(vcoor[i], vcoor[i+1], vcoor[i+2]));
				}
		
	}
    /* Adapta los test de Coordinate2D (CoordinateP1Test y CoordinateP2Test) para Coordinate3D */
	
	@Test
	public void testHashCode() {
		Coordinate c1 = lcoor.get(2);
		Coordinate c2 = c1.copy();
		/* Se comprueba que cuando dos Coordinate son iguales, el resultado 
		 * del hash ha de ser el mismo.
		 * Si los Coordinate son distintos el hash puede ser igual o no.
		 */
		assertEquals (c1,c2);
		assertEquals (c1.hashCode(), c2.hashCode());
	}

	/* Se comprueba que el Constructor funciona bien. Para ello se analiza que 
	 * las componentes '0', '1' y '2' de cada Coordinate3D creada en el setUp() son las 
	 * correctas.
	 */
	@Test
	public void testCoordinateConstructor() {
		int j=0;
		for (int i=0; i<DIM-2; i++) {			
			assertEquals("x", vcoor[i], lcoor.get(j).get(0));
			assertEquals("y", vcoor[i+1], lcoor.get(j).get(1));
			assertEquals("z", vcoor[i+2], lcoor.get(j).get(2));
			j++;
		}
	}

	/* Se comprueba que el constructor de copia crea una nueva Coordinate3D con
	 * los mismos valores que las componentes respectivas del Coordinate3D copiado.
	 * Y eso se hace para cada Coordinate3D creada en setUp();
	 */
	@Test
	public void testCoordinateConstructorCopy() {
		Coordinate ccopy;
		for (Coordinate caux: lcoor) {
			ccopy= caux.copy();
			assertEquals(caux.get(0),ccopy.get(0));
			assertEquals(caux.get(1),ccopy.get(1));	
		}
	}

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
		Coordinate3DAux c = new Coordinate3DAux((Coordinate3D)lcoor.get(2));
		assertEquals("x==-70", -70, c.get(0));
		assertEquals("x==-2", -2, c.get(1));
		assertEquals("x==20", 20, c.get(2));
		
		
		c.set(1, -11); c.set(0, 33); c.set(2, 25);
		assertEquals("x==33", 33, c.get(0));
		assertEquals("x==-11", -11, c.get(1));
		assertEquals("x==25", 25, c.get(2));
	}

	/* Se suman las Coordinate creadas en el setUp() y se comprueba, conforme 
	 * se van sumando, que los valores de sus componentes van tomando los 
	 * valores correctos y que el Coordinate3D que devuelve no es el mismo que
	 * el Coordinate3D que invoca al método.
	 */
	@Test  //{0,0,-70,-2,20, 75}
	public void testAdd() {
		Coordinate caux1 = lcoor.get(0);
		Coordinate caux2;
		
		int sumx = caux1.get(0);
		int sumy = caux1.get(1);
		for (int i=0; i<DIM-3; i++) {	
		   caux2 = caux1;
		   caux1 = caux1.add(lcoor.get(i+1));	  
		   sumx += (vcoor[i+1]);
		   sumy += (vcoor[i+2]);
		   
		   assertEquals(sumx,caux1.get(0)); 
		   assertEquals(sumy,caux1.get(1));
		   assertNotSame(caux2, caux1);
		}
	}

	/* Se van restando las Coordinate3D creadas en el setUp() y se comprueba, 
	 * conforme se van restando, que los valores de sus componentes van tomando 
	 * los valores correctos y que el Coordinate3D que devuelve no es el mismo que
	 * el Coordinate3D que invoca al método.
	 */
	@Test
	public void testSubtract() {
		Coordinate caux1 = lcoor.get(0);
		Coordinate caux2;
		int subx = caux1.get(0);
		int suby = caux1.get(1);
		for (int i=0; i<DIM-3; i++) {
		   caux2 = caux1;
		   caux1 = caux1.subtract(lcoor.get(i+1));
		   subx -= (vcoor[i+1]);
		   suby -= (vcoor[i+2]);
		   assertEquals(subx,caux1.get(0)); 
		   assertEquals(suby,caux1.get(1));
		   assertNotSame(caux2, caux1);
		}
	}
	
	/* Se comprueba, para el método toString(), que las Coordinate creadas en el setUp() 
	 * tienen el formato correcto.
	 */
	//Se crean las Coordinate (0, 0, -70), (0, -70, -2), (-70, -2, 20), (-2, 20, 75);
	@Test
	public void testToString() {
		assertEquals ("(0, 0, -70)", lcoor.get(0).toString());
		assertEquals ("(0, -70, -2)", lcoor.get(1).toString());
		assertEquals ("(-70, -2, 20)", lcoor.get(2).toString());
		assertEquals ("(-2, 20, 75)", lcoor.get(3).toString());
		
	}
	
	/* Creamos copias de las Coordinates3D creadas en el setUp() y comprobamos que:
	 * 1 - La copia y el original no son la misma.
	 * 2 - La copia tiene los mismos valores en las componentes respectivas que el objeto copiado.
	 */
	@Test
	public void testCopy() {
		Coordinate ccopy;
		
		for (Coordinate caux: lcoor) {
			ccopy= caux.copy();
			assertNotSame(caux, ccopy);
			assertEquals(caux.get(0),ccopy.get(0));
			assertEquals(caux.get(1),ccopy.get(1));	
		}
	}

	/* Se crea una Coordinate3D y a partir de ella se obtienen las Coordinate3D adyacentes 
	 * que se guardan en un Set<Coordinate>. Para cada una de las posiciones adyacentes
	 * a la Coordinate3D inicial se crea una Coordinate3D, y se va comprobando están
	 * contenidas en el Set.
	 * 
	 */
	@Test
	public void testAdjacentCoordinates() {
		Coordinate c = new Coordinate3D(-3, 5, 10);
		Set<Coordinate> setcoord = c.adjacentCoordinates();
		//Set<Coordinate> setcoord = new HashSet<Coordinate>();
		for (int k=-1; k<2; k++)
			for (int i=-1; i<2; i++) 
				for (int j=-1; j<2; j++) 
					if ((i==0)&&(j==0)&&(k==0))
						assertFalse(setcoord.contains(new Coordinate3D(c.get(0)+i, c.get(1)+j, c.get(2)+k)));
					else
						assertTrue(setcoord.contains(new Coordinate3D(c.get(0)+i, c.get(1)+j, c.get(2)+k)));
	}
	
	/* Se toma una Coordinate3D y se comprueba todas las posibles condiciones bajo 
	 * las cuales nuestra función equals() devuelve true o false
	 */
	//Se crean las Coordinate (0, 0, -70), (0, -70, -2), (-70, -2, 20), (-2, 20, 75);
	@Test
	public void testEqualsObject() {
		Object obj = new String("(0, 0, -70)");
		Coordinate c = lcoor.get(0);
		assertFalse(c.equals(null));
		assertFalse(c.equals(obj));
		assertFalse(c.equals(lcoor.get(1)));
		assertFalse(c.equals(new Coordinate3D(24, 0, 0)));
		assertTrue (c.equals(c));
		Coordinate d = new Coordinate3D(0, 0, -70);
		assertTrue((c.equals(d)));
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
			super.set(component, value);
		}
		
		public int get(int component) {
			return super.get(component);
		}		
	}
}