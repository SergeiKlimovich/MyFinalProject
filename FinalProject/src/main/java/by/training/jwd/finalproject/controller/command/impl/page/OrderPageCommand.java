package by.training.jwd.finalproject.controller.command.impl.page;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.entity.Order;
import by.training.jwd.finalproject.entity.OrderItem;
import by.training.jwd.finalproject.service.OrderItemService;
import by.training.jwd.finalproject.service.OrderService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;

/**
 * The {@code OrderPageCommand} class represents browse order page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class OrderPageCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(OrderPageCommand.class);
	private final OrderService orderService = ServiceProvider.getInstance().getOrderService();
	private final OrderItemService orderItemService = ServiceProvider.getInstance().getOrderItemService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String orderId = request.getParameter(RequestParameter.ORDER_ID);
		try {
			Optional<Order> orderOptional = orderService.findOrderById(orderId);
			if (orderOptional.isPresent()) {
				List<OrderItem> orderItemList = orderItemService.findOrderItemsByOrderId(orderId);
				request.setAttribute(RequestParameter.ORDER_ITEMS, orderItemList);
				request.setAttribute(RequestParameter.ORDER, orderOptional.get());
			}
			page = PagePath.ORDER;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while finding order", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}