package by.training.jwd.finalproject.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;
import by.training.jwd.finalproject.service.UserService;

/**
 * The {@code FillUpBalanceCommand} class represents fill up balance command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class FillUpBalanceCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(FillUpBalanceCommand.class);
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(RequestParameter.USER_ID);
		String moneyAmount = request.getParameter(RequestParameter.MONEY_AMOUNT);
		try {
			if (userService.fillUpBalance(userId, moneyAmount)) {
				request.setAttribute(RequestParameter.FILL_UP_BALANCE_SUCCESS, true);
				page = PagePath.MESSAGE;
			} else {
				request.setAttribute(RequestParameter.INCORRECT_MONEY_AMOUNT, true);
				page = PagePath.FILL_UP_BALANCE;
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while filling up balance", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
