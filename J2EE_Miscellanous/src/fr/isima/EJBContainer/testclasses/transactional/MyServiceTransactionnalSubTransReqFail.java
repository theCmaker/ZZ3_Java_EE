/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.annotations.Inject;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalSubTransReqFail implements IMyServiceTransactionnalSubTransReqFail {

	@Inject
	IMyServiceTransactionnalSubTransReqFail2 s2;

	@Override
	public void m() throws Exception {
		s2.m();
	}

}
