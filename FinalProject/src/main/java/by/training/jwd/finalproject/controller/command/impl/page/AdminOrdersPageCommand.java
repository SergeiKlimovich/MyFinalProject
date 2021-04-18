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
 * The {@code AdminOrdersPageCommand} class represents browse admin orders page
 * command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class AdminOrdersPageCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(AdminOrdersPageCommand.class);
	 private final OrderService orderService = ServiceProvider.getInstance().getOrderService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		try {
			List<Order> orderList = orderService.findAllOrders();
			request.setAttribute(RequestParameter.ORDERS, orderList);
			page = PagePath.ADMIN_ORDERS_PAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while finding orders", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
