/**
 * 
 */
package fr.isima.exercices.example;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public interface ITransaction {
	void begin();
	void commit();
	void rollback();
}
