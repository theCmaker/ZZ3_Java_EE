/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.annotations.Inject;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalSubTransNonReqFail implements IMyServiceTransactionnalSubTransNonReqFail{
	
	@Inject
	IMyServiceTransactionnalSubTransNonReqFail2 s2;

	@Override
	public void m() throws Exception {
		s2.m();
	}

}
