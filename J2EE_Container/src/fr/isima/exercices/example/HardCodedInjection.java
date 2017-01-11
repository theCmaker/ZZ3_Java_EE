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
	
	@Inject
	private IService service1, service2;
	
	@Inject
	private IOtherService oService1;
	
	@Before
	public void before() {
		// Creation des injections
		EJBContainer.inject(this);
	}

	@Test
	public void testType() {
		// Assertions
		// Test type
		IService service = EJBContainer.get(IService.class);
		assertNotNull(service);
		assertTrue(service instanceof MyService);	
	}
	
	@Test
	public void testInjection() {
		// Résoud les injections dans l'instance
		EJBContainer.inject(this);
		assertTrue(service instanceof MyService);		
	}
	
	@Test
	public void testSingleton1() {
		//1ere injection
		EJBContainer.inject(this);
		
		//Sauvegarde
		IService serviceTemp = service;
		
		//2eme injection
		EJBContainer.inject(this);
		
		assertSame(service,serviceTemp);
	}

	@Test
	public void testSingleton2() {
		assertSame(service1,service2);
	}
	
	interface IUseless {}
	
	@Test(expected = ClassNotFoundException.class)
	public void testClassLoader() {
		EJBContainer.inject(IUseless.class);
	}
	
	@Test 
	public void testPreferred() {
		assertTrue(oService1 instanceof MyPreferredService);
	}
	
	@Test 
	public void testCascade() {
		assertTrue(oService1 instanceof MyPreferredService 
				&& oService1.getDelegate() instanceof IService);
	}
	
	@Test
	public void testLog() {
		
	}
	
	@Test
	public void testTransactionnal() {
		
	}
}
