package fr.isima.EJBContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import fr.isima.EJBContainer.annotations.Behaviour;
import fr.isima.EJBContainer.exceptions.InjectionException;
import fr.isima.EJBContainer.interceptors.IInterceptor;

public class EJBInterceptorsHandler {
	private static void saveBehavioursClassesOfAnnotation(Vector<Class<? extends IInterceptor>> listInterceptors, Annotation annotation){
		for(Behaviour behaviour : annotation.annotationType().getAnnotationsByType(Behaviour.class)) {
			listInterceptors.add(behaviour.interceptor());
		}
	}
	
	private static Vector<Object> getInterceptorInstances(Class<?> classToBeInstanciated, Method currentUsedMethod ) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InjectionException{
		Vector<Class<? extends IInterceptor>> listClassesInterceptors = new Vector<Class<? extends IInterceptor>>();
		Vector<Object> listInterceptors = new Vector<Object>();
		
		for (Annotation annotation : classToBeInstanciated.getAnnotations()) {
			saveBehavioursClassesOfAnnotation(listClassesInterceptors,annotation);
		}
		for (Annotation annotation : currentUsedMethod.getAnnotations()) {
			saveBehavioursClassesOfAnnotation(listClassesInterceptors,annotation);
		}
		for (Class<? extends IInterceptor> classInterceptor : listClassesInterceptors){
			listInterceptors.add(EJBInstanceFactory.getInstanceOfClass(classInterceptor));
		}
		return listInterceptors;
	}
	
	@SuppressWarnings("unchecked")
	public static Object executeMethodsWithInterceptors(
		Class<?> classToBeInstanciated,
		Object instanceCourante, Method currentUsedMethod, Object[] args ) throws Exception {
		
		Object result = null;
		Vector<? extends IInterceptor> listInterceptors = 
				(Vector<? extends IInterceptor>) getInterceptorInstances(classToBeInstanciated, currentUsedMethod);
		
		try {
			// before
			for(IInterceptor interceptor: listInterceptors){
				interceptor.before(instanceCourante, currentUsedMethod, args);
			}
			
			result = currentUsedMethod.invoke(instanceCourante, args);
			
			// after
			for(IInterceptor interceptor: listInterceptors){
				interceptor.afterNoError(instanceCourante, currentUsedMethod, args);
			}
		} catch(Exception exception){
			
			// after errors
			for(IInterceptor interceptor: listInterceptors){
				interceptor.afterError(instanceCourante, currentUsedMethod, args);
			}
		}
		return result;
	}
}
