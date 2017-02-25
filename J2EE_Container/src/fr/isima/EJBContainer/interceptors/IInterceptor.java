/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 */
package fr.isima.EJBContainer.interceptors;

import java.lang.reflect.Method;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public interface IInterceptor {
	public void before(Object obj, Method method, Object ... params);
	public void afterNoError(Object obj, Method method, Object ... params);
	public void afterError(Object obj, Method method, Object ... params) throws Exception;
}
