package fr.isima.EJBContainer.interceptors.log;

import java.lang.reflect.Method;

import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.interceptors.IInterceptor;
/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class MyLogInterceptor implements IInterceptor {
	@Inject 
	private IMyLogger log;
	
	@Override
	public void before(Object obj, Method method, Object ... params) {
		log.log("before " + method.getName());
	}
	
	@Override
	public void after(Object obj, Method method, Object ... params) {
		log.log("after " + method.getName());
	}
}
