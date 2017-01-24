/**
 * 
 */
package fr.isima.exercices.example;


import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class SingletonTest {
	@Inject
	private IServiceSingleton service;

	@Inject
	private IServiceSingleton service1, service2;

	@Test
	public void testSingletonManuel() {		
		//Sauvegarde
		IService serviceTemp = service;
		
		//2eme injection
		EJBContainer.inject(this);
		
		assertSame(service,serviceTemp);
	}

	@Test
	public void testSingletonAuto() {
		assertSame(service1,service2);
	}

}
