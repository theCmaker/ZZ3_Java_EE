package fr.isima.EJBContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import fr.isima.EJBContainer.annotations.Singleton;
import fr.isima.EJBContainer.exceptions.InjectionException;

public class EJBInstanceFactory {
	private static Map<Class<?>,Object> singletonMap = new HashMap<Class<?>,Object>();
	// Maps Interface -> Implementation
		
	@SuppressWarnings("unchecked")
	public static <T> T getInstanceOfClass(Class<T> classToBeInstanciated) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InjectionException{
		// Create or get the right instance
		T instance  = null;
		if (classToBeInstanciated != null) {
			// Handle singleton
			if (classToBeInstanciated.isAnnotationPresent(Singleton.class)) {
				if (!singletonMap.containsKey(classToBeInstanciated)) {
					singletonMap.put(classToBeInstanciated, classToBeInstanciated.newInstance());
				}
				instance = ((T) singletonMap.get(classToBeInstanciated));
			} else {
				instance = classToBeInstanciated.newInstance();
			}
		}
		// Cascade
		EJBContainer.inject(instance);
		return instance;
	}
}
