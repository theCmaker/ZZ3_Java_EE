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
import fr.isima.EJBContainer.exceptions.serviceIsNotAnInterfaceException;

/** Gestionnaire d'EJBs
 * 
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 */
public class EJBContainer {

	public static <T> Object getInstanceFromProxy(Class<T> interfaceToInject) throws serviceIsNotAnInterfaceException
	{
		Object instance = null;
		if (interfaceToInject.isInterface()){
			instance = Proxy.newProxyInstance(
					interfaceToInject.getClassLoader(),
					new Class<?>[] {interfaceToInject},
					new MyInvocationHandler());	
		} else {
			throw new serviceIsNotAnInterfaceException();
		}
		return instance;
	}
	
	public static void handleInjectedField(Object obj, Field field) throws IllegalArgumentException, IllegalAccessException, serviceIsNotAnInterfaceException
	{
		// If the field is @Inject
		if (field.isAnnotationPresent(Inject.class)) {
			// Inject into the target obj
			field.setAccessible(true);
			field.set(obj, getInstanceFromProxy(field.getType()));
		}
	
	}

	public static void inject(Object obj) throws IllegalArgumentException, IllegalAccessException, serviceIsNotAnInterfaceException
	{
		// For each field
		for (Field field : obj.getClass().getDeclaredFields()) {
			handleInjectedField(obj, field);
		}
	}
	
	public static Object getRealInstanceOfAService(Object service){
		return ((MyInvocationHandler)Proxy.getInvocationHandler(service)).getIntanceCourante();
	}
}
