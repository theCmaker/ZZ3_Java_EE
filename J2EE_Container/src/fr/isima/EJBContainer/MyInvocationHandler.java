package fr.isima.EJBContainer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	// The only way to have the instance use in the handler is to save it in a attribute
	private Object instanceCourante;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> interfaceToInject = method.getDeclaringClass(); // Get object cur interface
		Class<?> classToBeInstanciated = EJBClassFinder.findClassToBeInstanciated(interfaceToInject);
		instanceCourante = EJBInstanceFactory.getInstanceOfClass(classToBeInstanciated);
		Object resultatMethod = method.invoke(instanceCourante, args);
		
		
		return resultatMethod;
	}
	
	public Object getIntanceCourante(){
		return instanceCourante;
	}
}
