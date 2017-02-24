/**
 * 
 */
package fr.isima.EJBContainer.testclasses.prefered;

import fr.isima.EJBContainer.annotations.Prefered;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
@Prefered
public class MyPreferredService implements IPreferredService { 
	@Override
	public void m() {}
}