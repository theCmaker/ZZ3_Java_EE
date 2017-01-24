/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class InjectionTest {

	@Inject
	private IService service; // Fonctionnalités
	
	@Test
	public void testType() {
		IService service = EJBContainer.get(IService.class);
		assertNotNull(service);
		assertTrue(service instanceof MyServiceInjection);	
	}
	
	@Test
	public void testSimple() {
		// Résoud les injections dans l'instance
		EJBContainer.inject(this);
		assertTrue(service instanceof MyServiceInjection);		
	}
	
	@Test
	public void testCascade() {
		assertTrue(service instanceof MyServiceInjection 
				&& ((MyServiceInjection) service).service instanceof IService);
		// + Test injection de soi-même sur soi-même
	}

}
