/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 */
package fr.isima.EJBContainer.interceptors;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public @interface Behaviour {

	Class<? extends IInterceptor> interceptor();
	
}
