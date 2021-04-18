package by.training.jwd.finalproject.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.service.OrderService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;

/**
 * The {@code RejectOrderCommand} class represents reject order command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class RejectOrderCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(RejectOrderCommand.class);
	private final OrderService orderService = ServiceProvider.getInstance().getOrderService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String orderId = request.getParameter(RequestParameter.ORDER_ID);
		try {
			if (orderService.rejectOrder(orderId)) {
				request.setAttribute(RequestParameter.ORDER_REJECT_SUCCESS_MESSAGE, true);
			} else {
				request.setAttribute(RequestParameter.ORDER_REJECT_ERROR_MESSAGE, true);
			}
			page = PagePath.MESSAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while reject order", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
