/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.Inject;
import fr.isima.EJBContainer.Transactionnal;
import fr.isima.EJBContainer.Transactionnal.Values;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 * 
 */

public class MyServiceTransactionnal1 implements ITransactionalService {

	@Inject
	MyServiceTransactionnal2 s2;

	@Transactionnal(Transactionnal.Values.REQUIRE)
	@Override
	public void m() {
		s2.m();
	}

}
