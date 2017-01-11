/**
 * 
 */
package fr.isima.exercices.example;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
@Preferred
public class MyPreferredService implements IOtherService {
	@Inject 
	private IService serviceDelegate; 
	
	@Override
	public IService getDelegate() {	
		return serviceDelegate;
	}
	
}
