/**
 * 
 */
package fr.isima.exercices.example;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 * 
 */

public class MyServiceTransactionnal1 implements IService {

	@Inject
	MyServiceTransactionnal2 s2;

	@Transactionnal(Transactionnal.Values.REQUIRE)
	@Override
	public void m() {
		s2.m();
	}

}
