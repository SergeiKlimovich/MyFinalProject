package by.training.jwd.finalproject.controller.command;

import java.util.Set;
import java.util.EnumSet;
/**
 * The {@code RoleAccess} enum represents role access.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public enum RoleAccess {
	
	/**
     * Guest role access.
     */
	GUEST(EnumSet.of(CommandName.FIND_PRODUCTS_COMMAND,
			 CommandName.ACCOUNT_ACCESS_COMMAND,
			 CommandName.LOGIN_COMMAND,
			 CommandName.REGISTER_COMMAND,
			 CommandName.SWITCH_LANGUAGE_COMMAND,
			 CommandName.FORGOT_PASSWORD_COMMAND,
			 CommandName.CATALOG_PAGE,
			 CommandName.LOGIN_PAGE,
			 CommandName.FORGOT_PASSWORD_PAGE,
			 CommandName.MAIN_PAGE,
			 CommandName.PRODUCT_PAGE,
			 CommandName.CONTACT_PAGE,
			 CommandName.CHANGE_PASSWORD_COMMAND,
			 CommandName.REGISTRATION_PAGE)),

	/**
     * User role access.
     */
	 USER(EnumSet.of(CommandName.ACCOUNT_ACCESS_COMMAND,
	    		CommandName.ADD_PRODUCT_TO_BASKET_COMMAND,
	    		CommandName.CHANGE_PASSWORD_COMMAND,
	    		CommandName.CREATE_ORDER_COMMAND,
	    		CommandName.DELETE_PRODUCT_FROM_BASKET_COMMAND,
	    		CommandName.FILL_UP_BALANCE_COMMAND,
	    		CommandName.FIND_PRODUCTS_COMMAND,
	    		CommandName.SWITCH_LANGUAGE_COMMAND,
	    		CommandName.LOGOUT_COMMAND,
	    		CommandName.BASKET_PAGE,
	    		CommandName.CONTACT_PAGE,
	    		CommandName.CATALOG_PAGE,
	    		CommandName.CHANGE_PASSWORD_PAGE,
	    		CommandName.FILL_UP_BALANCE_PAGE,
	            CommandName.MAIN_PAGE,
	            CommandName.ORDER_PAGE,
	            CommandName.PERSONAL_ACCOUNT_PAGE,
	            CommandName.UNDO_ORDER_COMMAND,
	            CommandName.ORDER_HISTORY_PAGE,
	            CommandName.PRODUCT_PAGE)),

	     /**
	     * Admin role access.
	     */
	    ADMIN(EnumSet.of(CommandName.ACCOUNT_ACCESS_COMMAND,
	    		CommandName.ADD_PRODUCT_COMMAND,
	    		CommandName.BLOCK_USER_COMMAND,
	    		CommandName.FIND_ORDERS_COMMAND,
	    		CommandName.CHANGE_PASSWORD_COMMAND,
	    		CommandName.DELETE_USER_COMMAND,
	    		CommandName.FIND_PRODUCTS_COMMAND,
	    		CommandName.FIND_USERS_COMMAND,
	    		CommandName.SWITCH_LANGUAGE_COMMAND,
	    		CommandName.UNBLOCK_USER_COMMAND,
	    		CommandName.LOGOUT_COMMAND,
	    		CommandName.EDIT_PRODUCT_COMMAND,
	    		CommandName.PRODUCT_ACTIONS_PAGE,
	    		CommandName.ADD_PRODUCT_PAGE,
	    		CommandName.ADMIN_PAGE,
	    		CommandName.CATALOG_PAGE,
	    		CommandName.CONTACT_PAGE,
	    		CommandName.CHANGE_PASSWORD_PAGE,
	    		CommandName.MAIN_PAGE,
	    		CommandName.ORDER_PAGE,
	    		CommandName.ADMIN_ORDERS_PAGE,
	    		CommandName.PRODUCE_ORDER_COMMAND,
	    		CommandName.REJECT_ORDER_COMMAND,
	    		CommandName.ADMIN_USERS_PAGE,
	    		CommandName.PERSONAL_ACCOUNT_PAGE,
	    		CommandName.ORDER_HISTORY_PAGE,
	    		CommandName.PRODUCT_PAGE));

	    private Set<CommandName> accessCommands;

	    RoleAccess(Set<CommandName> accessCommands) {
	        this.accessCommands = accessCommands;
	    }

	  
	    public Set<CommandName> getAccessCommands() {
	        return this.accessCommands;
	    }
	}