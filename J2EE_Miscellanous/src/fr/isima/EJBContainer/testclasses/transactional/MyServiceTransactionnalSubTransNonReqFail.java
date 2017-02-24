/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.annotations.Transactionnal;
import fr.isima.EJBContainer.annotations.Transactionnal.Values;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalSubTransNonReqFail implements IMyServiceTransactionnalSubTransNonReqFail{
	
	@Inject
	IMyServiceTransactionnalSubTransNonReqFail2 s2;

	@Transactionnal(Values.REQUIRE)
	@Override
	public void m() throws Exception {
		s2.m();
	}

}
