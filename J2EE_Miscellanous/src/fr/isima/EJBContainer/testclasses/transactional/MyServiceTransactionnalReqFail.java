/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.Transactionnal;
import fr.isima.EJBContainer.Transactionnal.Values;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalReqFail implements ITransactionalService {

	@Transactionnal(Values.REQUIRE)
	@Override
	public void m() throws Exception {
		throw new Exception();
	}

}
