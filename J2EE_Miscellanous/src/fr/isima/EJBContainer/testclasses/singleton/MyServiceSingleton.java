/**
 * 
 */
package fr.isima.EJBContainer.testclasses.singleton;

import fr.isima.EJBContainer.Singleton;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
@Singleton
public class MyServiceSingleton implements IServiceSingleton {

	@Override
	public void m() {}

}
