/**
 * 
 */
package fr.isima.exercices.example;

/**
 * @author pipissavy
 *
 */
public @interface Transactionnal {
	static public enum Values {
		REQUIRE,
		REQUIRE_NEW
	}
	
	Values value();

}
