/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class PreferredTest {
	
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
