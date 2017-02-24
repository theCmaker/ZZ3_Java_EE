/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.Inject;
import fr.isima.EJBContainer.Transactionnal;
import fr.isima.EJBContainer.Transactionnal.Values;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalSubTransReqFail implements ITransactionalService {

	@Inject
	MyServiceTransactionnalSubTransReqFail2 s2;

	@Transactionnal(Values.REQUIRE)
	@Override
	public void m() throws Exception {
		s2.m();
	}

}
