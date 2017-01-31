/**
 * 
 */
package fr.isima.exercices.example;

import fr.isima.exercices.example.Transactionnal.Values;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalReqFail implements IService {

	@Transactionnal(Values.REQUIRE)
	@Override
	public void m() throws Exception {
		throw new Exception();
	}

}
