/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.isima.EJBContainer.EJBContainer;
import fr.isima.EJBContainer.Inject;
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
	}
	
	@Test
	public void test() {
		assertTrue(service instanceof MyPreferredService);
	}

}
