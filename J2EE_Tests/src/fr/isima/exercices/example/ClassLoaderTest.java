/**
 * 
 */
package fr.isima.exercices.example;

import org.junit.Test;


/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class ClassLoaderTest {
	
	public class Useless implements IService {
		public void m() {}
	}
	
	@Test(expected = ClassNotFoundException.class)
	public void testClassLoader() {
		EJBContainer.inject(new Useless());
	}
}
