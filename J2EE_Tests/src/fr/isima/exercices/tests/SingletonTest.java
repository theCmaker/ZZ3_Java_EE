/**
 * 
 */
package fr.isima.exercices.tests;


import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import fr.isima.EJBContainer.EJBContainer;
import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.testclasses.singleton.IServiceSingleton;
import fr.isima.EJBContainer.testclasses.singleton.MyServiceSingleton;

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
		service.m();
		service1.m();
		service2.m();
	}

	@Test
	public void testSingletonManuel() throws Exception {		
		//Sauvegarde
		MyServiceSingleton serviceTemp = (MyServiceSingleton) EJBContainer.getRealInstanceOfAService(service);
		
		//2eme injection
		EJBContainer.inject(this);
		service.m();
		
		assertSame(EJBContainer.getRealInstanceOfAService(service),serviceTemp);
	}

	@Test
	public void testSingletonAuto() throws Exception {
		service1.m();
		MyServiceSingleton realInstance1 = (MyServiceSingleton) EJBContainer.getRealInstanceOfAService(service1);
		
		service2.m();
		MyServiceSingleton realInstance2 = (MyServiceSingleton) EJBContainer.getRealInstanceOfAService(service1);
		assertSame(realInstance1,realInstance2);
	}

}
