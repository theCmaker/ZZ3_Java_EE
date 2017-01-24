/**
 * 
 */
package fr.isima.exercices.example;

import static org.junit.Assert.assertEquals;

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
	
	
	@Test
	// Success
	public void testDoubleSuccess() {
		MyTransactionMock.reset();
		s1.m();
		assertEquals(MyTransactionMock.getCounter(),2);
	}
	
	@Test(expected = Exception.class)
	public void testFailureNonRequire() {
		MyTransactionMock.reset();
		s2.m();
		assertEquals(MyTransactionMock.getCounter(),0);
	}
	
	@Test(expected = Exception.class)
	public void testFailureRequire() {
		MyTransactionMock.reset();
		s3.m();
		assertEquals(MyTransactionMock.getCounter(),0);
	}
	
	@Test
	public void testSubTransactNonRequire() {
		MyTransactionMock.reset();
		s4.m();
		assertEquals(MyTransactionMock.getCounter(),1);
	}
	

	@Test(expected = Exception.class)
	public void testSubTransactRequire() {
		MyTransactionMock.reset();
		s5.m();
		assertEquals(MyTransactionMock.getCounter(),0);
	}
	
	
	
}
