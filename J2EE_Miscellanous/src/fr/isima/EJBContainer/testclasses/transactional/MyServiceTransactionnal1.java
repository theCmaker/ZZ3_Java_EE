/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.annotations.Inject;
/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 * 
 */

public class MyServiceTransactionnal1 implements IMyServiceTransactionnal1 {

	@Inject
	IMyServiceTransactionnal2 s2;


	@Override
	public void m() {
		s2.m();
	}

}
