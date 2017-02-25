/**
 * 
 */
package fr.isima.exercices.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.isima.EJBContainer.EJBContainer;
import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.testclasses.prefered.IPreferredService;
import fr.isima.EJBContainer.testclasses.prefered.MyPreferredService;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class PreferedTest {
	
	@Inject
	private IPreferredService service;
	
	@Before
	public void before() throws Exception {
		EJBContainer.inject(this);
		service.m();
	}
	
	@Test
	public void test() {
		assertTrue(EJBContainer.getRealInstanceOfAService(service) instanceof MyPreferredService);
	}

}
