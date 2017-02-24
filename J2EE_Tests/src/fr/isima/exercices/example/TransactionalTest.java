/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.isima.EJBContainer.EJBContainer;
import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.testclasses.transactional.IMyServiceTransactionnal1;
import fr.isima.EJBContainer.testclasses.transactional.IMyServiceTransactionnalNonReqFail;
import fr.isima.EJBContainer.testclasses.transactional.IMyServiceTransactionnalReqFail;
import fr.isima.EJBContainer.testclasses.transactional.IMyServiceTransactionnalSubTransNonReqFail;
import fr.isima.EJBContainer.testclasses.transactional.IMyServiceTransactionnalSubTransReqFail;
import fr.isima.EJBContainer.testclasses.transactional.MyServiceTransactionnal1;
import fr.isima.EJBContainer.testclasses.transactional.MyServiceTransactionnalNonReqFail;
import fr.isima.EJBContainer.testclasses.transactional.MyServiceTransactionnalReqFail;
import fr.isima.EJBContainer.testclasses.transactional.MyServiceTransactionnalSubTransNonReqFail;
import fr.isima.EJBContainer.testclasses.transactional.MyServiceTransactionnalSubTransReqFail;
import fr.isima.EJBContainer.testclasses.transactional.MyTransactionMock;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class TransactionalTest {
	
	@Inject 
	IMyServiceTransactionnal1 s1;
	
	@Inject 
	IMyServiceTransactionnalNonReqFail s2;
	
	@Inject 
	IMyServiceTransactionnalReqFail s3;
	
	@Inject 
	IMyServiceTransactionnalSubTransNonReqFail s4;
	
	@Inject 
	IMyServiceTransactionnalSubTransReqFail s5;
	
	@Before
	public void before() throws Exception {
		MyTransactionMock.reset();
		EJBContainer.inject(this);
	}
	
	@Test
	// Success
	public void testDoubleSuccess() {
		s1.m();
		assertEquals(MyTransactionMock.getBeginCounter(),2);
		assertEquals(MyTransactionMock.getCommitCounter(),2);
		assertEquals(MyTransactionMock.getRollbackCounter(),0);
	}
	
	/**
	 * Transaction ratee mais non require
	 * @throws Exception 
	 */
	@Test
	public void testFailureNonRequire() throws Exception {
		s2.m();
		assertEquals(MyTransactionMock.getBeginCounter(),1);
		assertEquals(MyTransactionMock.getCommitCounter(),0);
		assertEquals(MyTransactionMock.getRollbackCounter(),1);
	}
	
	@Test(expected = Exception.class)
	public void testFailureRequire() throws Exception {
		try {
			s3.m();
		} catch (Exception e) {
			throw e;
		} finally {
			assertEquals(MyTransactionMock.getBeginCounter(),1);
			assertEquals(MyTransactionMock.getCommitCounter(),0);
			assertEquals(MyTransactionMock.getRollbackCounter(),1);
		}
	}
	
	/**
	 * Appel de sous transaction non required
	 * @throws Exception 
	 */
	@Test
	public void testSubTransactNonRequire() throws Exception {
		s4.m();
		assertEquals(MyTransactionMock.getBeginCounter(),2);
		assertEquals(MyTransactionMock.getCommitCounter(),1);
		assertEquals(MyTransactionMock.getRollbackCounter(),1);
	}
	

	/**
	 * Double fail
	 * @throws Exception 
	 */
	@Test(expected = Exception.class)
	public void testSubTransactRequire() throws Exception {
		try {
			s5.m();
		} catch (Exception e) {
			throw e;
		} finally {
			assertEquals(MyTransactionMock.getBeginCounter(),2);
			assertEquals(MyTransactionMock.getCommitCounter(),0);
			assertEquals(MyTransactionMock.getRollbackCounter(),2);
		}
	}
	
	
	
}
