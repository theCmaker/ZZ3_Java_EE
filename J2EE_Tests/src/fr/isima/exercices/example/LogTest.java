/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.isima.EJBContainer.EJBContainer;
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
	
	@Before
	public void before() throws Exception {
		EJBContainer.inject(this);
		service.m();
	}
	
	@Test
	public void test() {
		assertTrue(myLogger.contains("before m"));
		assertTrue(myLogger.contains("after m"));
	}

}
