/**
 * 
 */
package fr.isima.exercices.example;

import fr.isima.exercices.example.Transactionnal.Values;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalSubTransReqFail implements IService {

	@Inject
	MyServiceTransactionnalSubTransReqFail2 s2;

	@Transactionnal(Values.REQUIRE)
	@Override
	public void m() {
		s2.m();
	}

}