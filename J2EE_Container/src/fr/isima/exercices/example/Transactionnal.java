/**
 * 
 */
package fr.isima.exercices.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author pipissavy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactionnal {
	static public enum Values {
		REQUIRE,
		REQUIRE_NEW
	}
	
	Values value();

}
