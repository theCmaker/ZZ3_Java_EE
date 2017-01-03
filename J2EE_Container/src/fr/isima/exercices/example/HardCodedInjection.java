/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 *  TESTS
 */
package fr.isima.exercices.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/** Tests d'injection de dépendances
 * 
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 */
public class HardCodedInjection {
	@Inject
	private IService service; // Fonctionnalités
	
	@Before
	public void before() {
		// Creation des injections
		// 
	}

	@Test
	public void testType() {
		// Assertions
		// Test type
		IService service = EJBContainer.get(IService.class);
		assertTrue(service instanceof MyService);	
	}
	
	@Test
	public void testInjection() {
		// Résoud les injections dans l'instance
		EJBContainer.inject(this);
		assertTrue(service instanceof MyService);		
	}
}
