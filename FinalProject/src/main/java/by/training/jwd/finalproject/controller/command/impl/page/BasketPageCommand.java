package by.training.jwd.finalproject.controller.command.impl.page;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.entity.Basket;
import by.training.jwd.finalproject.entity.User;
import by.training.jwd.finalproject.service.BasketService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;
import by.training.jwd.finalproject.service.UserService;

/**
 * The {@code BasketPageCommand} class represents browse basket page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class BasketPageCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(BasketPageCommand.class);
	private final UserService userService = ServiceProvider.getInstance().getUserService();
	private final BasketService basketService = ServiceProvider.getInstance().getBasketService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(RequestParameter.USER_ID);
		try {
			List<Basket> basketList = basketService.findBasketByUserId(userId);
			double totalPrice = basketService.calculateTotalPrice(basketList);
			request.setAttribute(RequestParameter.TOTAL_PRICE, totalPrice);
			request.setAttribute(RequestParameter.BASKETS, basketList);
			Optional<User> optionalUser = userService.findUserById(userId);
			if (optionalUser.isPresent()) {
				double userBalance = optionalUser.get().getBalance();
				request.setAttribute(RequestParameter.BALANCE, userBalance);
			} else {
				request.setAttribute(RequestParameter.BALANCE_LOADING_ERROR, true);
			}
			page = PagePath.BASKET;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while finding user basket", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
