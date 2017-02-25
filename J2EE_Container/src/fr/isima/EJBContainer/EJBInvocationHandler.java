package fr.isima.EJBContainer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.isima.EJBContainer.exceptions.InjectionException;

public class EJBInvocationHandler implements InvocationHandler {
	// The only way to have the instance use in the handler is to save it in a attribute
	private Object instanceCourante;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
		Class<?> interfaceToInject = method.getDeclaringClass(); // Get object cur interface
		Class<?> classToBeInstanciated = EJBClassFinder.findClassToBeInstanciated(interfaceToInject);
		instanceCourante = EJBInstanceFactory.getInstanceOfClass(classToBeInstanciated);

		return EJBInterceptorsHandler.executeMethodsWithInterceptors(classToBeInstanciated, instanceCourante, method, args);
	}
	
	public Object getIntanceCourante(){
		return instanceCourante;
	}
}
