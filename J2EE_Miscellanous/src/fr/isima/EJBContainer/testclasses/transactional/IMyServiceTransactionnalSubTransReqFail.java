package fr.isima.EJBContainer.testclasses.transactional;

import fr.isima.EJBContainer.interceptors.transaction.annotations.Transactionnal;
import fr.isima.EJBContainer.interceptors.transaction.annotations.Transactionnal.Values;

public interface IMyServiceTransactionnalSubTransReqFail {
	@Transactionnal(Values.REQUIRE)
	public void m() throws Exception ;
}
