package fr.isima.EJBContainer.interceptors.transaction;

import java.lang.reflect.Method;

import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.interceptors.IInterceptor;
import fr.isima.EJBContainer.interceptors.transaction.annotations.Transactionnal;
import fr.isima.EJBContainer.interceptors.transaction.exceptions.TransactionStoppedException;

public class TransactionInterceptor implements IInterceptor {

	@Inject
	ITransaction transactionner;
	
	@Override
	public void before(Object obj, Method method, Object... params) {
		transactionner.begin();
	}

	@Override
	public void afterNoError(Object obj, Method method, Object... params) {
		transactionner.commit();
	}

	@Override
	public void afterError(Object obj, Method method, Object... params) throws TransactionStoppedException {
		transactionner.rollback();
		if(isTransactionRequire(obj, method)){
			throw new TransactionStoppedException();
		}
	}
	
	private static boolean isTransactionRequire(Object obj, Method method){
		boolean transactionRequire = true; // default value, true
		Transactionnal[] annotationsTransactionnal = method.getAnnotationsByType(Transactionnal.class);
		Transactionnal ourAnnotation = null;
		if(annotationsTransactionnal.length == 1){
			ourAnnotation = annotationsTransactionnal[0];
			transactionRequire = (ourAnnotation.value() == Transactionnal.Values.REQUIRE);
		}
		return transactionRequire;
	}

}
