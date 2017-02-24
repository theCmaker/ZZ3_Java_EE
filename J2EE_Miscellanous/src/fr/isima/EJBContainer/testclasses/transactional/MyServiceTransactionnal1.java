/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.annotations.Transactionnal;
import fr.isima.EJBContainer.annotations.Transactionnal.Values;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 * 
 */

public class MyServiceTransactionnal1 implements IMyServiceTransactionnal1 {

	@Inject
	IMyServiceTransactionnal2 s2;

	@Transactionnal(Transactionnal.Values.REQUIRE)
	@Override
	public void m() {
		s2.m();
	}

}
