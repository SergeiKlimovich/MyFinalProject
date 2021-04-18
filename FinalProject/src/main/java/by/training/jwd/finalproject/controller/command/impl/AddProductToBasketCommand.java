package by.training.jwd.finalproject.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.service.BasketService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;

/**
 * The {@code AddProductToBasketCommand} class represents add product to basket
 * command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class AddProductToBasketCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(AddProductToBasketCommand.class);
	private final BasketService basketService = ServiceProvider.getInstance().getBasketService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(RequestParameter.USER_ID);
		String productId = request.getParameter(RequestParameter.PRODUCT_ID);
		try {
			if (basketService.addBasket(userId, productId)) {
				request.setAttribute(RequestParameter.ADD_PRODUCT_TO_BASKET_SUCCESS, true);
			} else {
				request.setAttribute(RequestParameter.ADD_PRODUCT_TO_BASKET_ERROR, true);
			}
			page = PagePath.MESSAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while adding product", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
