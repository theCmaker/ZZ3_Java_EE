/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 *  Conteneur d'EJBs
 */
package fr.isima.EJBContainer;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.exceptions.ServiceIsNotAnInterfaceException;

/** Gestionnaire d'EJBs
 * 
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 */
public class EJBContainer {

	public static <T> Object getInstanceFromProxy(Class<T> interfaceToInject) throws ServiceIsNotAnInterfaceException
	{
		Object instance = null;
		if (interfaceToInject.isInterface()){
			instance = Proxy.newProxyInstance(
					interfaceToInject.getClassLoader(),
					new Class<?>[] {interfaceToInject},
					new EJBInvocationHandler());	
		} else {
			throw new ServiceIsNotAnInterfaceException();
		}
		return instance;
	}
	
	public static void handleInjectedField(Object obj, Field field) throws IllegalArgumentException, IllegalAccessException, ServiceIsNotAnInterfaceException
	{
		// If the field is @Inject
		if (field.isAnnotationPresent(Inject.class)) {
			// Inject into the target obj
			field.setAccessible(true);
			field.set(obj, getInstanceFromProxy(field.getType()));
		}
	
	}

	public static void inject(Object obj) throws IllegalArgumentException, IllegalAccessException, ServiceIsNotAnInterfaceException
	{
		// For each field
		for (Field field : obj.getClass().getDeclaredFields()) {
			handleInjectedField(obj, field);
		}
	}
	
	public static Object getRealInstanceOfAService(Object service){
		return ((EJBInvocationHandler)Proxy.getInvocationHandler(service)).getIntanceCourante();
	}
}
