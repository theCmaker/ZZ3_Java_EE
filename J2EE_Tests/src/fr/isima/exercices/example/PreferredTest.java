/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class PreferredTest {
	
	@Inject
	private IPreferredService service;

	@Test
	public void test() {
		assertTrue(service instanceof MyPreferredService);
	}

}
