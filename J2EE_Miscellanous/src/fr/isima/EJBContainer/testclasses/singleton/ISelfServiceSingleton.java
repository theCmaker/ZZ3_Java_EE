/**
 * 
 */
package fr.isima.EJBContainer.testclasses.singleton;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public interface ISelfServiceSingleton {

	void m() throws Exception;	
	ISelfServiceSingleton getService();
}
