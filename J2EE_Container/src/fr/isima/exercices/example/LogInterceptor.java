package fr.isima.exercices.example;

import java.lang.reflect.Method;

public class LogInterceptor implements IInterceptor {
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
