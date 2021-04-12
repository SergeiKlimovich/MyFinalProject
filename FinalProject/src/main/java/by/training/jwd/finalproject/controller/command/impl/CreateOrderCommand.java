package by.training.jwd.finalproject.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.entity.Basket;
import by.training.jwd.finalproject.service.BasketService;
import by.training.jwd.finalproject.service.OrderService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;

/**
 * The {@code CreateOrderCommand} class represents create order command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class CreateOrderCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(CreateOrderCommand.class);
	private final OrderService orderService = ServiceProvider.getInstance().getOrderService();
	private final BasketService basketService = ServiceProvider.getInstance().getBasketService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(RequestParameter.USER_ID);
		try {
			List<Basket> basketList = basketService.findBasketByUserId(userId);
			if (orderService.addOrder(userId, basketList)) {
				request.setAttribute(RequestParameter.ORDER_CREATE_SUCCESS_MESSAGE, true);
			} else {
				request.setAttribute(RequestParameter.ORDER_CREATE_ERROR_MESSAGE, true);
			}
			page = PagePath.MESSAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while creating order", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
