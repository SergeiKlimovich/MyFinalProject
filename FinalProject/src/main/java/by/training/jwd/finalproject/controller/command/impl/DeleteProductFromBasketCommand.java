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
 * The {@code DeleteProductFromBasketCommand} class represents delete product
 * from basket command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class DeleteProductFromBasketCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(DeleteProductFromBasketCommand.class);
	private final BasketService basketService = ServiceProvider.getInstance().getBasketService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(RequestParameter.USER_ID);
		String productId = request.getParameter(RequestParameter.PRODUCT_ID);
		try {
			if (basketService.removeBasket(userId, productId)) {
				request.setAttribute(RequestParameter.REMOVE_PRODUCT_FROM_BASKET_SUCCESS, true);
			} else {
				request.setAttribute(RequestParameter.REMOVE_PRODUCT_FROM_BASKET_ERROR, true);
			}
			page = PagePath.MESSAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while removing product", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
