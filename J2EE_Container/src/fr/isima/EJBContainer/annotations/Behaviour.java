/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 */
package fr.isima.EJBContainer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.isima.EJBContainer.interceptors.IInterceptor;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface Behaviour {

	Class<? extends IInterceptor> interceptor();
	
}
