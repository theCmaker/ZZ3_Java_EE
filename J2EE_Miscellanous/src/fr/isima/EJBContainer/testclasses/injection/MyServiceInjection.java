/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 *  Implémentation d'un service
 */
package fr.isima.EJBContainer.testclasses.injection;

import fr.isima.EJBContainer.annotations.Inject;

/** Service simple
 * 
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 */
public class MyServiceInjection implements IServiceInjection {
	@Inject
	IServiceInjection service;
	
	@Override
	public void m() {}
	
	public IServiceInjection getService(){
		return this.service;
	}
}