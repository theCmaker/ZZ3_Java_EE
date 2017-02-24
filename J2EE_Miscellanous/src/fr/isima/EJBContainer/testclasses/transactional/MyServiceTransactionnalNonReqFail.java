package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.annotations.Transactionnal;
import fr.isima.EJBContainer.annotations.Transactionnal.Values;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 *
 */
public class MyServiceTransactionnalNonReqFail implements IMyServiceTransactionnalNonReqFail {

	@Transactionnal(Values.REQUIRE_NEW)
	@Override
	public void m() throws Exception {
		throw new Exception();
	}
	
}