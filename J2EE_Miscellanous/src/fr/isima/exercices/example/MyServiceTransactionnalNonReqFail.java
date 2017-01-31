package fr.isima.exercices.example;

import fr.isima.exercices.example.Transactionnal.Values;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 *
 */
public class MyServiceTransactionnalNonReqFail implements IService {

	@Transactionnal(Values.REQUIRE_NEW)
	@Override
	public void m() throws Exception {
		throw new Exception();
	}
	
}