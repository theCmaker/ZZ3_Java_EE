package fr.isima.EJBContainer.testclasses.transactional;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 *
 */
public class MyServiceTransactionnalNonReqFail implements IMyServiceTransactionnalNonReqFail {

	@Override
	public void m() throws Exception {
		throw new Exception();
	}
	
}