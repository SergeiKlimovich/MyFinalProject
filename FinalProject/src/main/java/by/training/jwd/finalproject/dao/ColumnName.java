package by.training.jwd.finalproject.dao;

/**
 * The {@code ColumnName} class represents column name.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ColumnName {
	/**
	 * Constants for user table
	 */
	public static final String USER_ID = "user_id";
	public static final String USER_EMAIL = "email";
	public static final String USER_ROLE = "role";
	public static final String USER_STATUS = "status";
	public static final String USER_NAME = "name";
	public static final String USER_SURNAME = "surname";
	public static final String USER_PATRONYMIC = "patronymic";
	public static final String USER_BALANCE = "balance";
	/**
	 * Constants for product table
	 */
	public static final String PRODUCT_ID = "product_id";
	public static final String PRODUCT_TITLE = "title";
	public static final String PRODUCT_PRICE = "price";
	public static final String PRODUCT_FACTURE = "facture";
	public static final String PRODUCT_APPLICATION_AREA = "application_area";
	/**
	 * Constants for order table
	 */
	public static final String ORDER_ID = "order_id";
	public static final String ORDER_CREATION_DATE = "creation_date";
	public static final String ORDER_CLOSING_DATE = "closing_date";
	public static final String ORDER_STATUS = "order_status";
	/**
	 * Constants for image table
	 */
	public static final String IMAGE_ID = "image_id";
	public static final String IMAGE_NAME = "image_name";
	/**
	 * Constants for basket table
	 */
	public static final String BASKET_ID = "basket_id";
	/**
	 * Constants for order item table
	 */
	public static final String ORDER_ITEM_ID = "orderitem_id";

	private ColumnName() {
	}
}
