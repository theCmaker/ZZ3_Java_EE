/**
 * 
 */
package fr.isima.EJBContainer.interceptors.transaction;

import fr.isima.EJBContainer.annotations.Prefered;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
@Prefered
public class MyTransactionMock implements ITransaction {
	private static Integer commitCounter = 0;
	private static Integer beginCounter = 0;
	private static Integer rollbackCounter = 0;
	
	/* Begins a transaction
	 * @see fr.isima.exercices.example.ITransaction#begin()
	 */
	@Override
	public void begin() {
		beginCounter++;
	}

	/* Validates a transaction
	 * @see fr.isima.exercices.example.ITransaction#commit()
	 */
	@Override
	public void commit() {
		commitCounter++;
	}

	/* Reverts changes done during transaction
	 * @see fr.isima.exercices.example.ITransaction#rollback()
	 */
	@Override
	public void rollback() {
		rollbackCounter++;
	}
	
	public static int getBeginCounter() {
		return beginCounter;
	}
	
	public static int getCommitCounter() {
		return commitCounter;
	}
	
	public static int getRollbackCounter() {
		return rollbackCounter;
	}
	
	public static void reset() {
		beginCounter = 0;
		commitCounter = 0;
		rollbackCounter = 0;
	}

}
