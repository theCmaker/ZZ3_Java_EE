/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.interceptors.log.MyLogger;
import fr.isima.EJBContainer.testclasses.log.MyLogService;

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
