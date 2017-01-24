/**
 * 
 */
package fr.isima.exercices.example;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
@Preferred
public class MyTransactionMock implements ITransaction {
	private static Integer counter = 0;
	
	/* Begins a transaction
	 * @see fr.isima.exercices.example.ITransaction#begin()
	 */
	@Override
	public void begin() {
		counter++;
	}

	/* Validates a transaction
	 * @see fr.isima.exercices.example.ITransaction#commit()
	 */
	@Override
	public void commit() {}

	/* Reverts changes done during transaction
	 * @see fr.isima.exercices.example.ITransaction#rollback()
	 */
	@Override
	public void rollback() {
		counter--;
	}
	
	public static int getCounter() {
		return counter;
	}
	
	public static void reset() {
		counter = 0;
	}

}
