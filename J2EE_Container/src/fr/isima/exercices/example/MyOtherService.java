/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 */
package fr.isima.exercices.example;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
public class MyOtherService implements IOtherService {
	
	@Log
	public void doSomething() {
		
	}

	@Override @Log
	public IService getDelegate() {
		return null;
	}

}
