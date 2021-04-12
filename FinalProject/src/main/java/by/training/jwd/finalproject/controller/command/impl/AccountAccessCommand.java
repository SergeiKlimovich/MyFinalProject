package by.training.jwd.finalproject.controller.command.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.entity.User;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;
import by.training.jwd.finalproject.service.UserService;

/**
 * The {@code AccountAccessCommand} class represents account access command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class AccountAccessCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(AccountAccessCommand.class);
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String code = request.getParameter(RequestParameter.ACCESS_CODE);
		try {
			List<User> userList = userService.findAllUsers();
			Optional<User> optionalUser = userService.findUserByAccessCode(code, userList);
			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				if (user.getStatus() == User.Status.NOT_CONFIRMED) {
					userService.activateUser(user.getEmail());
					request.setAttribute(RequestParameter.USER_SUCCESS_CONFIRM_REGISTRATION_LETTER, true);
					page = PagePath.MESSAGE;
				} else {
					request.getSession().setAttribute(RequestParameter.EMAIL, user.getEmail());
					page = PagePath.CHANGE_PASSWORD;
				}
			} else {
				request.setAttribute(RequestParameter.USER_CODE_NOT_FOUND, true);
				page = PagePath.MESSAGE;
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while activating account", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
