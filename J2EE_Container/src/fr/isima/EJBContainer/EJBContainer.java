/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 *  Conteneur d'EJBs
 */
package fr.isima.EJBContainer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.annotations.Prefered;
import fr.isima.EJBContainer.annotations.Singleton;
import fr.isima.EJBContainer.exceptions.InjectionException;
import fr.isima.EJBContainer.exceptions.NoImplementationFoundException;
import fr.isima.EJBContainer.exceptions.NoPreferedClassException;
import fr.isima.EJBContainer.exceptions.TooManyPreferedClassException;

/** Gestionnaire d'EJBs
 * 
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 */
public class EJBContainer {

	public EJBContainer() throws Exception {	
	}
	
	public static void init(Object obj) throws Exception {
	}
	
	private static Reflections reflections;
	private static Map<Class<?>,Object> singletonMap = new HashMap<Class<?>,Object>();
	// Maps Interface -> Implementation
	
	private static <T> Class<T> browseSubClasses(Set<Class<?>> subClasses) throws TooManyPreferedClassException{
		Class<T> classToBeInstanciated = null;
		// Find the preferred implementation
		Iterator<Class<?>> itr = subClasses.iterator();
		while (itr.hasNext()) {
			Class<T> cl = (Class<T>) itr.next();
			if (cl.isAnnotationPresent(Prefered.class)) {
				if (classToBeInstanciated == null)  {
					classToBeInstanciated = cl;
				} else {
					throw new TooManyPreferedClassException();
				}
			}
		}
		return classToBeInstanciated;
	}
	
	private static <T> Class<T> findClassToBeInstanciated(Set<Class<?>> subClasses) throws TooManyPreferedClassException, NoPreferedClassException, NoImplementationFoundException{
		// Find the good implementation
		Class<T> classToBeInstanciated = null;
		if (subClasses.size() > 1) {
			classToBeInstanciated = browseSubClasses(subClasses);
			
			if (classToBeInstanciated == null) {
				throw new NoPreferedClassException();				
			}
		}
		// Or there is only one Class
		else if (subClasses.size() == 1) {
			// Get the implementation
			classToBeInstanciated = (Class<T>) subClasses.toArray()[0];			
		}
		// Or there is no implementation
		else {
			// Error
			throw new NoImplementationFoundException();
		}
		return classToBeInstanciated;
	}
	
	public static <T> T getInstanceOfClass(Class<T> classToBeInstanciated) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		// Create or get the right instance
		T instance  = null;
		if (classToBeInstanciated != null) {
			// Handle singleton
			if (classToBeInstanciated.isAnnotationPresent(Singleton.class)) {
				if (!singletonMap.containsKey(classToBeInstanciated)) {
					singletonMap.put(classToBeInstanciated, classToBeInstanciated.getConstructor().newInstance());
				}
				instance = ((T) singletonMap.get(classToBeInstanciated));
			} else {
				instance = ((T) classToBeInstanciated.getConstructor().newInstance());
			}
		}
		return instance;
	}
	
	public static <T> T get(Class type) throws  
		InjectionException,
		InstantiationException, IllegalAccessException, IllegalArgumentException, 
		InvocationTargetException, NoSuchMethodException, SecurityException 
	{
		reflections = new Reflections(type, new FieldAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner());
		Set<Class<?>> subClasses = reflections.getSubTypesOf(type);
		Class<T> classToBeInstanciated = findClassToBeInstanciated(subClasses);
		return getInstanceOfClass(classToBeInstanciated);
	}
	
	public static void handleInjectedField(Object obj, Field field) throws 
		IllegalArgumentException, IllegalAccessException, InstantiationException, 
		InvocationTargetException, NoSuchMethodException, SecurityException, InjectionException
	{
		// If the field is @Inject
		if (field.isAnnotationPresent(Inject.class)) {
			// Inject into the target obj
			field.setAccessible(true);
			field.set(obj, get(field.getType()));
			// Cascade injection
			if (field.get(obj).getClass() != obj.getClass()) {
				inject(field.get(obj));
			}
			handleBehavior(field.get(obj).getClass());
		}
	
	}
	
	private static void handleBehavior(Class<? extends Object> classOfInjectedField) {
		
		
	}

	public static void inject(Object obj) throws 
		InjectionException,
		InstantiationException, IllegalAccessException, IllegalArgumentException, 
		InvocationTargetException, NoSuchMethodException, SecurityException 
	{
		// For each field
		for (Field field : obj.getClass().getDeclaredFields()) {
			handleInjectedField(obj, field);
		}
	}
}
