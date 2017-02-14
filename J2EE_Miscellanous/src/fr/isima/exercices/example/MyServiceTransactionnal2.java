package fr.isima.exercices.example;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class MyServiceTransactionnal2 implements ITransactionalService {
	
	@Override
	@Transactionnal(Transactionnal.Values.REQUIRE)
	public void m() {}

}
