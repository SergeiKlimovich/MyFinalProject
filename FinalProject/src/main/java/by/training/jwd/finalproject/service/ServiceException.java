package by.training.jwd.finalproject.service;

/**
 * The {@code ServiceException} class represents an exception that may be thrown
 * on the service layer
 *
 * @author Sergei Klimovich
 * @version 1.0
 */

public class ServiceException extends Exception {

	private static final long serialVersionUID = -3321500639618391934L;

	public ServiceException() {
		super();
	}

	public ServiceException(String massage) {
		super(massage);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);

	}
}
