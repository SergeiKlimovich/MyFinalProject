package by.training.jwd.finalproject.controller.command.impl.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.entity.Order;
import by.training.jwd.finalproject.service.OrderService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;

/**
 * The {@code OrderHistoryPageCommand} class represents browse order history
 * page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class OrderHistoryPageCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(OrderHistoryPageCommand.class);
	private final OrderService orderService = ServiceProvider.getInstance().getOrderService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String userId = request.getParameter(RequestParameter.USER_ID);
		try {
			List<Order> orderList = orderService.findOrdersByUserId(userId);
			request.setAttribute(RequestParameter.ORDERS, orderList);
			page = PagePath.ORDER_HISTORY;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while finding orders", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
