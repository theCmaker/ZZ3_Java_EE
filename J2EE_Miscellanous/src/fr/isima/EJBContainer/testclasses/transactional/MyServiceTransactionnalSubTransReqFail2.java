/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

/**
 * @author pipissavy
 *
 */
public class MyServiceTransactionnalSubTransReqFail2 implements IMyServiceTransactionnalSubTransReqFail2 {

	/* (non-Javadoc)
	 * @see fr.isima.exercices.example.IService#m()
	 */
	@Override
	public void m() throws Exception {
		throw new Exception();
	}

}
