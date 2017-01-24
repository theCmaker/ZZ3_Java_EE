/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 *  Implémentation d'un service
 */
package fr.isima.exercices.example;

/** Service simple
 * 
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 */
public class MyServiceInjection implements IService {
	@Inject
	IService service;
	
	@Override
	public void m() {}
}


/**
 * @RequestScope -> le même objet est injecté pour toute la durée du traitement d'une requête
 * @SessionScope -> objet de session pour l'utilisateur plutôt que singleton
 * @Alternative
 * @Preferred 
 */