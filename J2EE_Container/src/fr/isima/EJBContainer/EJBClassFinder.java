package fr.isima.EJBContainer;

import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import fr.isima.EJBContainer.annotations.Prefered;
import fr.isima.EJBContainer.exceptions.NoImplementationFoundException;
import fr.isima.EJBContainer.exceptions.NoPreferedClassException;
import fr.isima.EJBContainer.exceptions.TooManyPreferedClassException;

public class EJBClassFinder {
	private static Reflections reflections;
	
	private static <T> Class<?> browseImplementations(Set<Class<? extends T>> implementations) throws TooManyPreferedClassException{
		Class<? extends T> classToBeInstanciated = null;
		// Find the preferred implementation
		Iterator<Class<? extends T>> itr = implementations.iterator();
		while (itr.hasNext()) {
			Class<? extends T> cl = (Class<? extends T>) itr.next();
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
	
	public static <T> Class<? extends T> findClassToBeInstanciated(Class<T> interfaceToInject) throws TooManyPreferedClassException, NoPreferedClassException, NoImplementationFoundException{
		reflections = new Reflections(interfaceToInject, new FieldAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner());
		Set<Class<? extends T>> implementations = reflections.getSubTypesOf(interfaceToInject);
		
		// Find the good implementation
		Class<? extends T> classToBeInstanciated = null;
		if (implementations.size() > 1) {
			classToBeInstanciated = (Class<? extends T>) browseImplementations(implementations);
			
			if (classToBeInstanciated == null) {
				throw new NoPreferedClassException();				
			}
		}
		// Or there is only one Class
		else if (implementations.size() == 1) {
			// Get the implementation
			classToBeInstanciated = (Class<? extends T>) implementations.toArray()[0];			
		}
		// Or there is no implementation
		else {
			// Error
			throw new NoImplementationFoundException();
		}
		return classToBeInstanciated;
	}
}
