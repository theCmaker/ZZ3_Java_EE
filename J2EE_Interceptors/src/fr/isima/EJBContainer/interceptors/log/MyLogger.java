/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 */
package fr.isima.EJBContainer.interceptors.log;

import java.util.ArrayList;
import java.util.List;

import fr.isima.EJBContainer.annotations.Singleton;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
@Singleton
public class MyLogger {
	private List<String> log = new ArrayList<String>();
	
	public boolean contains(String s) {
		return log.contains(s);
	}
	
	public void log(String s) {
		log.add(s);
	}
}
