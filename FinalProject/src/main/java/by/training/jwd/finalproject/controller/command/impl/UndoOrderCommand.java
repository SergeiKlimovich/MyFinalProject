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
 * The {@code UndoOrderCommand} class represents undo order command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class UndoOrderCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(UndoOrderCommand.class);
	private final OrderService orderService = ServiceProvider.getInstance().getOrderService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String orderId = request.getParameter(RequestParameter.ORDER_ID);
		try {
			if (orderService.removeOrder(orderId)) {
				request.setAttribute(RequestParameter.ORDER_UNDO_SUCCESS_MESSAGE, true);
			} else {
				request.setAttribute(RequestParameter.ORDER_UNDO_ERROR_MESSAGE, true);
			}
			page = PagePath.MESSAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while order undo", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
