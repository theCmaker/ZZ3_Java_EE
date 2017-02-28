/**
 * 
 */
package fr.isima.EJBContainer.testclasses.singleton;

import fr.isima.EJBContainer.annotations.Inject;
import fr.isima.EJBContainer.annotations.Singleton;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
@Singleton
public class MySelfServiceSingleton implements ISelfServiceSingleton {

	@Inject 
	ISelfServiceSingleton service;
	
	@Override
	public ISelfServiceSingleton getService() {
		return service;
	}
	@Override
	public void m() throws Exception {
	}

}
