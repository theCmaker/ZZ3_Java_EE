/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class TransactionalTest {
	
	@Inject 
	MyServiceTransactionnal1 s1;
	
	@Inject 
	MyServiceTransactionnalNonReqFail s2;
	
	@Inject 
	MyServiceTransactionnalReqFail s3;
	
	@Inject 
	MyServiceTransactionnalSubTransNonReqFail s4;
	
	@Inject 
	MyServiceTransactionnalSubTransReqFail s5;
	
	@Before
	private void before() {
		MyTransactionMock.reset();
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
