/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 *  Ajout d'annotation de log
 */
package fr.isima.EJBContainer.interceptors.log.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.isima.EJBContainer.annotations.Behaviour;
import fr.isima.EJBContainer.interceptors.log.LogInterceptor;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Behaviour(interceptor=LogInterceptor.class)
public @interface Log {
	
}
