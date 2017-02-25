/**
 * 
 */
package fr.isima.EJBContainer.interceptors.transaction.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.isima.EJBContainer.annotations.Behaviour;
import fr.isima.EJBContainer.interceptors.transaction.TransactionInterceptor;

/**
 * @author pipissavy
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Behaviour(interceptor=TransactionInterceptor.class)
public @interface Transactionnal {
	static public enum Values {
		REQUIRE,
		NOT_REQUIRE
	}
	
	Values value();

}
