/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class InjectionTest {

	@Inject
	private IService service; // Fonctionnalités
	
	@Before
	public void before() throws Exception {
		EJBContainer.inject(this);
	}
	
	@Test
	public void testType() throws InstantiationException, IllegalAccessException {
		IService service = EJBContainer.get(IService.class);
		assertNotNull(service);
		assertTrue(service instanceof MyServiceInjection);	
	}
	
	@Test
	public void testSimple() throws Exception {
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
