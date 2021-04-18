package by.training.jwd.finalproject.controller.command.impl.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
 * The {@code PersonalAccountPageCommand} class represents browse personal
 * account page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class PersonalAccountPageCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(PersonalAccountPageCommand.class);
	private final OrderService orderService = ServiceProvider.getInstance().getOrderService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(RequestParameter.USER_ID);
		try {
			List<Order> orderList = orderService.findOrdersByUserId(userId);
			request.setAttribute(RequestParameter.ORDERS, orderList);
			page = PagePath.PERSONAL_ACCOUNT;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while finding orders", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
