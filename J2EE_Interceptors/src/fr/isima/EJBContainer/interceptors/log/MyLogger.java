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
public class MyLogger implements IMyLogger {
	private List<String> log = new ArrayList<String>();
	
	@Override
	public boolean contains(String s) {
		return log.contains(s);
	}
	
	@Override
	public void log(String s) {
		log.add(s);
	}
}
