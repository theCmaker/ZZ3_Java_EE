/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.annotations.Transactionnal;
import fr.isima.EJBContainer.annotations.Transactionnal.Values;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalReqFail implements IMyServiceTransactionnalReqFail {

	@Transactionnal(Values.REQUIRE)
	@Override
	public void m() throws Exception {
		throw new Exception();
	}

}
