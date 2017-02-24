/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.isima.EJBContainer.EJBContainer;
import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.testclasses.injection.IServiceInjection;
import fr.isima.EJBContainer.testclasses.injection.MyServiceInjection;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class InjectionTest {

	@Inject
	private IServiceInjection service; // Fonctionnalités
	
	@Before
	public void before() throws Exception {
		EJBContainer.inject(this);
	}
	
	@Test
	public void testType() throws Exception {
		IServiceInjection service = EJBContainer.get(IServiceInjection.class);
		assertNotNull(service);
		assertTrue(service instanceof MyServiceInjection);	
	}
	
	@Test
	public void testSimple() throws Exception {
		// Résoud les injections dans l'instance
		assertTrue(service instanceof MyServiceInjection);		
	}
	
	@Test
	public void testCascade() {
		assertNotNull(((MyServiceInjection) service).getService());
		assertTrue(
				(service instanceof MyServiceInjection) 
				&& ((MyServiceInjection) service).getService() instanceof MyServiceInjection);
		
		// + Test injection de soi-même sur soi-même
	}

}
