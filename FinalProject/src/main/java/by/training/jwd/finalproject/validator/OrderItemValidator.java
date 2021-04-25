package by.training.jwd.finalproject.validator;

/**
 * The {@code OrderItemValidator} class represents orderItem validator.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class OrderItemValidator {
	private static final String ID_REGEX = "^[1-9]\\d{0,9}$";

	/**
	 * Check id for valid.
	 *
	 * @param id the id
	 * @return true if id is valid
	 */
	public static boolean isIdValid(String id) {
		return isStringCorrect(id, ID_REGEX);
	}

	private static boolean isStringCorrect(String line, String regex) {
		boolean result = false;
		if (line != null) {
			result = line.matches(regex);
		}
		return result;
	}
}
