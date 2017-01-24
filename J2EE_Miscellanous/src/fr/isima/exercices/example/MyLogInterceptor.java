package fr.isima.exercices.example;

import java.lang.reflect.Method;
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
