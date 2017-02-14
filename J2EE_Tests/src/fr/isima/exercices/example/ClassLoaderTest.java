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
	@Test(expected = ClassNotFoundException.class)
	public void testClassLoaderClassNotFound() throws Exception {
		EJBContainer.get(INotImplementedService.class);
	}
	
	@Test(expected = TooManyPreferedClassException.class)
	public void testClassLoaderClassTooMany() throws Exception {
		EJBContainer.get(IMuchImplementedPrefered.class);
	}
	
	@Test(expected = NoPreferedClassException.class)
	public void testClassLoaderNoPreferred() throws Exception {
		EJBContainer.get(IMuchImplemented.class);
	}
	
}
