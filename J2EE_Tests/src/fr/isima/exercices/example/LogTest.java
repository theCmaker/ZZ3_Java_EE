/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.interceptors.log.IMyLogger;
import fr.isima.EJBContainer.interceptors.log.MyLogger;
import fr.isima.EJBContainer.testclasses.log.IMyLogService;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class LogTest {	
	@Inject
	IMyLogger myLogger;
	
	@Inject
	IMyLogService service;
	
	@Test
	public void test() {
		service.m();
		assertTrue(myLogger.contains("before getDelegate"));
		assertTrue(myLogger.contains("after getDelegate"));
	}

}
