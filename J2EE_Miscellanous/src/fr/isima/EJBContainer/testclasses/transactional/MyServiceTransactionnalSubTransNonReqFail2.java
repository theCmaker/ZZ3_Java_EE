/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalSubTransNonReqFail2 implements IMyServiceTransactionnalSubTransNonReqFail2 {

	/* (non-Javadoc)
	 * @see fr.isima.exercices.example.IService#m()
	 */
	@Override
	public void m() throws Exception {
		throw new Exception();
	}

}
