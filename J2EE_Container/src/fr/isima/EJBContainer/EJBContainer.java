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
	
	@SuppressWarnings("unchecked")
	public static <T> T get(Class type) throws  TooManyPreferedClassException,NoPreferedClassException,NoImplementationFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<T> classToBeInstanciated = null;
		reflections = new Reflections(type, new FieldAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner());
		Set<Class<?>> subClasses = reflections.getSubTypesOf(type);
		// Either subClasses contains several classes
		if (subClasses.size() > 1) {
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
		// Find the good implementation
		// Create or get the right instance
		T instance  = null;
		if (classToBeInstanciated != null) {
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
	
	public static void inject(Object obj) throws Exception {
		// For each field
		for (Field field : obj.getClass().getDeclaredFields()) {
			// If the field is @Inject
			if (field.isAnnotationPresent(Inject.class)) {
				// Inject into the target obj
				try {
					field.setAccessible(true);
					field.set(obj, get(field.getType()));
					// Cascade injection
					if (field.get(obj).getClass() != obj.getClass()) {
						//TODO: Monsieur c'est pas normal
						inject(field.get(obj));
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
