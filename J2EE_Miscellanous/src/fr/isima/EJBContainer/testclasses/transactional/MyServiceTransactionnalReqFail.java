/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalReqFail implements IMyServiceTransactionnalReqFail {

	@Override
	public void m() throws Exception {
		throw new Exception();
	}

}
