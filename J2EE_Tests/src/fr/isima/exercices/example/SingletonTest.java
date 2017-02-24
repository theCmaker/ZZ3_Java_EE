/**
 * 
 */
package fr.isima.exercices.example;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import fr.isima.EJBContainer.EJBContainer;
import fr.isima.EJBContainer.Inject;
import fr.isima.EJBContainer.testclasses.singleton.IServiceSingleton;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class SingletonTest {
	@Inject
	private IServiceSingleton service;

	@Inject
	private IServiceSingleton service1, service2;
	
	@Before
	public void before() throws Exception {
		EJBContainer.inject(this);
	}

	@Test
	public void testSingletonManuel() throws Exception {		
		//Sauvegarde
		IServiceSingleton serviceTemp = service;
		
		//2eme injection
		EJBContainer.inject(this);
		
		assertSame(service,serviceTemp);
	}

	@Test
	public void testSingletonAuto() {
		assertNotNull(service1);
		assertSame(service1,service2);
	}

}
