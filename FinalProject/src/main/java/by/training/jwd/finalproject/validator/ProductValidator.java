package by.training.jwd.finalproject.validator;

/**
 * The {@code ProductValidator} class represents product validator.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ProductValidator {
	private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
	private static final String TITLE_REGEX = "^\\p{L}[^<>]{2,25}$";
	private static final String PRICE_REGEX = "^[0-9]\\d{0,4}(\\.\\d{0,2})?$";
	private static final String FACTURE_REGEX = "^\\p{L}[^<>]{1,45}$";
	private static final String APPLICATION_AREA_REGEX = "^\\p{L}[^<>]{1,120}$";

	/**
	 * Check id for valid.
	 *
	 * @param id the id
	 * @return true if id is valid
	 */
	public static boolean isIdValid(String id) {
		return isStringCorrect(id, ID_REGEX);
	}

	/**
	 * Check title for valid.
	 *
	 * @param title the title
	 * @return true if title is valid
	 */
	public static boolean isTitleValid(String title) {
		return isStringCorrect(title, TITLE_REGEX) && !title.trim().isEmpty();
	}

	/**
	 * Check price for valid.
	 *
	 * @param price the price
	 * @return true if price is valid
	 */
	public static boolean isPriceValid(String price) {
		return isStringCorrect(price, PRICE_REGEX);
	}

	/**
	 * Check facture for valid.
	 *
	 * @param facture the facture
	 * @return true if facture is valid
	 */
	public static boolean isFactureValid(String facture) {
		return isStringCorrect(facture, FACTURE_REGEX) && !facture.trim().isEmpty();
	}

	/**
	 * Check applicationArea for valid.
	 *
	 * @param applicationArea the applicationArea
	 * @return true if applicationArea is valid
	 */
	public static boolean isApplicationAreaValid(String applicationArea) {
		return isStringCorrect(applicationArea, APPLICATION_AREA_REGEX) && !applicationArea.trim().isEmpty();
	}

	private static boolean isStringCorrect(String line, String regex) {
		boolean result = false;
		if (line != null) {
			result = line.matches(regex);
		}
		return result;
	}
}
