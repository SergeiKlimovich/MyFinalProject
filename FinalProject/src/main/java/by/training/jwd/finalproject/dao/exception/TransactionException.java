package by.training.jwd.finalproject.dao.exception;
/**
 * The {@code TransactionException} class represents an exception that may be thrown
 * with transaction operations.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class TransactionException extends Exception{

	private static final long serialVersionUID = -6083900069978463786L;
	
	public TransactionException() {
		super();
	}

	public TransactionException(String message) {
		super(message);
	}

	public TransactionException(String message, Exception e) {
		super(message, e);
	}

	public TransactionException(Exception e) {
		super(e);
	}
	

}
