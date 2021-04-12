package by.training.jwd.finalproject.controller.command;

import by.training.jwd.finalproject.controller.command.impl.*;
import by.training.jwd.finalproject.controller.command.impl.page.*;
/**
 * The {@code CommandName} enum represents command name.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */


public enum CommandName {

	    LOGIN_COMMAND(new LoginCommand()),
	    REGISTER_COMMAND(new RegisterCommand()),
	    LOGOUT_COMMAND(new LogoutCommand()),
	    ACCOUNT_ACCESS_COMMAND(new AccountAccessCommand()),
	    FIND_USERS_COMMAND(new FindUsersCommand()),
	    DELETE_USER_COMMAND(new DeleteUserCommand()),
	    BLOCK_USER_COMMAND(new BlockUserCommand()),
	    UNBLOCK_USER_COMMAND(new UnblockUserCommand()),
	    ADD_PRODUCT_COMMAND(new AddProductCommand()),
	    FORGOT_PASSWORD_COMMAND(new ForgotPasswordCommand()),
    CHANGE_PASSWORD_COMMAND(new ChangePasswordCommand()),
	    ADD_PRODUCT_TO_BASKET_COMMAND(new AddProductToBasketCommand()),
	    DELETE_PRODUCT_FROM_BASKET_COMMAND(new DeleteProductFromBasketCommand()),
	    FILL_UP_BALANCE_COMMAND(new FillUpBalanceCommand()),
	    FIND_PRODUCTS_COMMAND(new FindProductsCommand()),
	    CREATE_ORDER_COMMAND(new CreateOrderCommand()),
	    PRODUCE_ORDER_COMMAND(new ProduceOrderCommand()),
	    REJECT_ORDER_COMMAND(new RejectOrderCommand()),
	    EDIT_PRODUCT_COMMAND(new EditProductCommand()),
	    UNDO_ORDER_COMMAND(new UndoOrderCommand()),
	    FIND_ORDERS_COMMAND(new FindOrdersCommand()),

	    SWITCH_LANGUAGE_COMMAND(new SwitchLanguageCommand()),

	    EMPTY_COMMAND(new EmptyCommand()),
	    CONTACT_PAGE(new ContactPageCommand()),
	    MAIN_PAGE(new MainPageCommand()),
	    ADMIN_USERS_PAGE(new AdminUsersPageCommand()),
	    ADMIN_ORDERS_PAGE(new AdminOrdersPageCommand()),
	    PRODUCT_PAGE(new ProductPageCommand()),
	    LOGIN_PAGE(new LoginPageCommand()),
	    PERSONAL_ACCOUNT_PAGE(new PersonalAccountPageCommand()),
	    ADMIN_PAGE(new AdminPageCommand()),
	    REGISTRATION_PAGE(new RegistrationPageCommand()),
	    CATALOG_PAGE(new CatalogPageCommand()),
	    FORGOT_PASSWORD_PAGE(new ForgotPasswordPageCommand()),
	    CHANGE_PASSWORD_PAGE(new ChangePasswordPageCommand()),
	    BASKET_PAGE(new BasketPageCommand()),
    FILL_UP_BALANCE_PAGE(new FillUpBalancePageCommand()),
	    ORDER_PAGE(new OrderPageCommand()),
	    PRODUCT_ACTIONS_PAGE(new ProductActionsPageCommand()),
	    ORDER_HISTORY_PAGE(new OrderHistoryPageCommand()),
	    ADD_PRODUCT_PAGE(new ProductActionsPageCommand());
;
	private final ActionCommand command;

	CommandName(ActionCommand command) {
		this.command = command;
	}

	public ActionCommand getCommand() {
		return command;
	}
}
