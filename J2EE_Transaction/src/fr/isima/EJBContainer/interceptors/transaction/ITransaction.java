/**
 * 
 */
package fr.isima.EJBContainer.interceptors.transaction;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public interface ITransaction {
	void begin();
	void commit();
	void rollback();
}
