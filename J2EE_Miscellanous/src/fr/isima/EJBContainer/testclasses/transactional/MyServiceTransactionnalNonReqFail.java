package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.Transactionnal;
import fr.isima.EJBContainer.Transactionnal.Values;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 *
 */
public class MyServiceTransactionnalNonReqFail implements ITransactionalService {

	@Transactionnal(Values.REQUIRE_NEW)
	@Override
	public void m() throws Exception {
		throw new Exception();
	}
	
}