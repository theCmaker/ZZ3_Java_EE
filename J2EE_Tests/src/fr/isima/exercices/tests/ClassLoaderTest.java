/**
 * 
 */
package fr.isima.exercices.tests;

import org.junit.Test;

import fr.isima.EJBContainer.EJBClassFinder;
import fr.isima.EJBContainer.exceptions.NoImplementationFoundException;
import fr.isima.EJBContainer.exceptions.NoPreferedClassException;
import fr.isima.EJBContainer.exceptions.TooManyPreferedClassException;
import fr.isima.EJBContainer.testclasses.classloader.IMuchImplemented;
import fr.isima.EJBContainer.testclasses.classloader.IMuchImplementedPrefered;
import fr.isima.EJBContainer.testclasses.classloader.INotImplementedService;


/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class ClassLoaderTest {	
	@Test(expected = NoImplementationFoundException.class)
	public void testClassLoaderClassNotFound() throws Exception {
		EJBClassFinder.findClassToBeInstanciated(INotImplementedService.class);
	}
	
	@Test(expected = TooManyPreferedClassException.class)
	public void testClassLoaderClassTooMany() throws Exception {
		EJBClassFinder.findClassToBeInstanciated(IMuchImplementedPrefered.class);
	}
	
	@Test(expected = NoPreferedClassException.class)
	public void testClassLoaderNoPreferred() throws Exception {
		EJBClassFinder.findClassToBeInstanciated(IMuchImplemented.class);
	}
	
}
