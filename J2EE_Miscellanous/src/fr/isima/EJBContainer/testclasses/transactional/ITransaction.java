/**
 * 
 */
package fr.isima.EJBContainer.testclasses.transactional;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public interface ITransaction {
	void begin();
	void commit();
	void rollback();
}
