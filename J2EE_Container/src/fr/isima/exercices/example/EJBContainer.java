/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 *  Conteneur d'EJBs
 */
package fr.isima.exercices.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/** Gestionnaire d'EJBs
 * 
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 */
public class EJBContainer {
	public EJBContainer() {}
	
	public static <T> T get(Class<T> obj) {
		return null;
	}
	
	public static void inject(Object obj) {
		for (Field f : obj.getClass().getDeclaredFields()) {
			System.out.println("Field: "+f.getName());
			for (Annotation annotation : f.getAnnotations()) {
				System.out.println("Annotation: "+annotation);
				if (annotation.toString().equals("@fr.isima.exercices.example.Inject()")) {
					Class<?> type = f.getType();
					System.out.println(type);
					if (type.isInterface()) {
						// Résolution de la classe adéquate
						//Set<Class<? extends type> > implementations = 
						//Class<?> good_class 
					} else {
					}
				}
			}
		}		
	}
}
