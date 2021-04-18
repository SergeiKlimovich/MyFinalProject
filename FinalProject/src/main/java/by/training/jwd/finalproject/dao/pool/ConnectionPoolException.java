package by.training.jwd.finalproject.dao.pool;

/**
 * The {@code ConnectionPoolException} class represents an exception that may be
 * thrown on connection pool
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = -6028788723707807740L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}
}