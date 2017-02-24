/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import fr.isima.EJBContainer.EJBContainer;
import fr.isima.EJBContainer.MyInvocationHandler;
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
		// One method has to be called to create service instance
		service.m();
	}
	
	@Test
	public void testType() throws Exception {
		IServiceInjection service = (IServiceInjection) EJBContainer.getInstanceFromProxy(IServiceInjection.class);
		service.m();
		assertNotNull(service);
		assertTrue(EJBContainer.getRealInstanceOfAService(service) instanceof MyServiceInjection);	
	}
	
	@Test
	public void testSimple() throws Exception {
		assertTrue(EJBContainer.getRealInstanceOfAService(service) instanceof MyServiceInjection);		
	}
	
	@Test
	public void testCascade() throws Exception {
		IServiceInjection inService = service.getService();
		inService.m();
		
		assertNotNull(service.getService());
		assertTrue(EJBContainer.getRealInstanceOfAService(inService) instanceof MyServiceInjection);
		
	}

}
