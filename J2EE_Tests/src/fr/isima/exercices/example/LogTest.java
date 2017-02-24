/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.isima.EJBContainer.Inject;
import fr.isima.EJBContainer.testclasses.log.MyLogService;
import fr.isima.EJBContainer.testclasses.log.MyLogger;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class LogTest {	
	@Inject
	MyLogger myLogger;
	
	@Inject
	MyLogService service;
	
	@Test
	public void test() {
		service.m();
		assertTrue(myLogger.contains("before getDelegate"));
		assertTrue(myLogger.contains("after getDelegate"));
	}

}
