package fr.isima.EJBContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.Vector;

import fr.isima.EJBContainer.exceptions.InjectionException;
import fr.isima.EJBContainer.interceptors.Behaviour;
import fr.isima.EJBContainer.interceptors.IInterceptor;

public class MyInvocationHandler implements InvocationHandler {
	// The only way to have the instance use in the handler is to save it in a attribute
	private Object instanceCourante;
	
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
	
	private static Object executeMethodsWithInterceptors(
		Vector<? extends IInterceptor> listInterceptors,
		Object instanceCourante, Method currentUsedMethod, Object[] args ) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Object result = null;
		
		// before
		for(IInterceptor interceptor: listInterceptors){
			interceptor.before(instanceCourante, currentUsedMethod, args);
		}
		
		result = currentUsedMethod.invoke(instanceCourante, args);
		
		// after
		for(IInterceptor interceptor: listInterceptors){
			interceptor.after(instanceCourante, currentUsedMethod, args);
		}
		
		return result;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException, InjectionException {
		Class<?> interfaceToInject = method.getDeclaringClass(); // Get object cur interface
		Class<?> classToBeInstanciated = EJBClassFinder.findClassToBeInstanciated(interfaceToInject);
		Vector<? extends IInterceptor> listInterceptors = (Vector<? extends IInterceptor>) getInterceptorInstances(classToBeInstanciated, method);

		instanceCourante = EJBInstanceFactory.getInstanceOfClass(classToBeInstanciated);

		return executeMethodsWithInterceptors(listInterceptors, instanceCourante, method, args);
	}
	
	public Object getIntanceCourante(){
		return instanceCourante;
	}
}
