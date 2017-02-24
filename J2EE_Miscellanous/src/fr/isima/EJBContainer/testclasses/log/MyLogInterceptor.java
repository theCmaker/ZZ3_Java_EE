package fr.isima.EJBContainer.testclasses.log;

import java.lang.reflect.Method;

import fr.isima.EJBContainer.Inject;
import fr.isima.EJBContainer.iterceptors.IInterceptor;
/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class MyLogInterceptor implements IInterceptor {
	@Inject 
	private MyLogger log;
	
	@Override
	public void before(Object obj, Method method, Object ... params) {
		log.log("before " + method.getName());
	}
	
	@Override
	public void after(Object obj, Method method, Object ... params) {
		log.log("after " + method.getName());
	}
}
