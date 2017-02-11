/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 *  Conteneur d'EJBs
 */
package fr.isima.exercices.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import static org.reflections.ReflectionUtils.*;
import org.reflections.scanners.FieldAnnotationsScanner;

/** Gestionnaire d'EJBs
 * 
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 */
public class EJBContainer {
	private static boolean hasRun = false;

	public EJBContainer() throws Exception {	
	}
	
	public static void init() throws Exception {
		if (!hasRun) {
			objectMap = new HashMap<Class<?>,Class<?>>();
			reflections = new Reflections("fr.isima.exercices.example", new FieldAnnotationsScanner());
			Set<Class<?>> classesToBeInjected = 
					reflections.getTypesAnnotatedWith(fr.isima.exercices.example.Inject.class);
			for (Class<?> theClass : classesToBeInjected) {
				if (theClass.isInterface()) {
					// Find the correct implementation
					Set<?> implementations = reflections.getSubTypesOf(theClass);
					Iterator<Class<?>> iterator = ((Set<Class<?>>)implementations).iterator();
	
					if (implementations.size() > 1) {
						//Many implementations
						while (iterator.hasNext()) {
							Class<?> object = iterator.next();
							if (object.isAnnotationPresent(fr.isima.exercices.example.Preferred.class)) {
								if (!objectMap.containsKey(theClass)) {							
									objectMap.put(theClass, object);
								} else {
									throw new Exception("Preferred annotation set twice for " + theClass.toString());
								}
							}
						}
						
						// No preferred implementation ?
						if (!objectMap.containsKey(theClass)) {	
							throw new Exception("No preferred annotation for " + theClass.toString());
						}
						
					} else if (implementations.size() == 1){
						// Only one implementation
						objectMap.put(theClass, (Class<?>) iterator.next());
					} else {
						// No implementation
						throw new Exception("No implementation for " + theClass.toString());
					}
				} else {
					// 
					objectMap.put(theClass, theClass);
				}
			}
			hasRun = true;
		}
	}
	
	private static Reflections reflections;
	private static Map<Class<?>,Class<?>> objectMap;
	// Maps Interface -> Implementation
	
	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> obj) throws InstantiationException, IllegalAccessException {
		// Find the good implementation
		// Create or get the right instance
		T instance  = null;
		if (EJBContainer.objectMap.containsKey(obj)) {
			instance = ((T) EJBContainer.objectMap.get(obj).newInstance());
		}
		return instance;
	}
	
	public static void inject(Object obj) throws Exception {
		EJBContainer.init();
		Reflections reflections = new Reflections(obj.getClass().toString(), new FieldAnnotationsScanner());
		Set<Field> injectFields = reflections.getFieldsAnnotatedWith(fr.isima.exercices.example.Inject.class);
		
		System.out.println("In inject");
		// For each field
		for (Field field : injectFields) {
			// Inject into the target obj
			try {
				field.set(obj, get(field.getType()));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Cascade injection
			//inject(field.get(obj));
		}
	}
}
