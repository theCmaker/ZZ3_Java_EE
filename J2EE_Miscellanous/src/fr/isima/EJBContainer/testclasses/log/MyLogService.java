/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 */
package fr.isima.EJBContainer.testclasses.log;

import fr.isima.EJBContainer.interceptors.log.Log;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class MyLogService implements IMyLogService {
	
	@Log
	public void m() {}

}
