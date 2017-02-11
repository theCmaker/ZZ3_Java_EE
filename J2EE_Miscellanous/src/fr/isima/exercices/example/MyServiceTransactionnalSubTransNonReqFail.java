/**
 * 
 */
package fr.isima.exercices.example;

import fr.isima.exercices.example.Transactionnal.Values;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalSubTransNonReqFail implements IService{
	
	@Inject
	MyServiceTransactionnalSubTransNonReqFail2 s2;

	@Transactionnal(Values.REQUIRE)
	@Override
	public void m() throws Exception {
		s2.m();
	}

}
