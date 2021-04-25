package by.training.jwd.finalproject.util;

/**
 * The {@code SendMailException} class represents an exception that may be
 * thrown with mail sending.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class SendMailException extends Exception {

	private static final long serialVersionUID = 6517909879637572809L;

	public SendMailException() {
		super();
	}

	public SendMailException(String message) {
		super(message);
	}

	public SendMailException(String message, Exception e) {
		super(message, e);
	}

	public SendMailException(Exception e) {
		super(e);
	}

}
