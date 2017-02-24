package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.annotations.Transactionnal;
import fr.isima.EJBContainer.annotations.Transactionnal.Values;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class MyServiceTransactionnal2 implements ITransactionalService {
	
	@Override
	@Transactionnal(Transactionnal.Values.REQUIRE)
	public void m() {}

}
