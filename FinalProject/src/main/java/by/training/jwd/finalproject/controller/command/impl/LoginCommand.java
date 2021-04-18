package by.training.jwd.finalproject.controller.command.impl;

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
 * The {@code LoginCommand} class represents login command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class LoginCommand implements ActionCommand {

	private final UserService userService = ServiceProvider.getInstance().getUserService();

	private static final Logger logger = LogManager.getLogger(LoginCommand.class);

	public String execute(HttpServletRequest request) {
		String page;
		String email = request.getParameter(RequestParameter.EMAIL);
		String password = request.getParameter(RequestParameter.PASSWORD);
		try {
			if (userService.isUserExists(email, password)) {
				Optional<User> optionalUser = userService.findUserByEmail(email);
				User user = optionalUser.get();
				switch (user.getStatus()) {
				case ENABLE: {
					request.getSession().setAttribute(RequestParameter.USER, user);
					request.getSession().setAttribute(RequestParameter.USER_ID, user.getUserId().toString());
					request.getSession().setAttribute(RequestParameter.ROLE, user.getRole().toString());
					page = PagePath.MAIN;
					break;
				}
				case BLOCKED: {
					request.setAttribute(RequestParameter.USER_LOGIN_BLOCKED, true);
					page = PagePath.MESSAGE;
					break;
				}
				case NOT_CONFIRMED: {
					request.setAttribute(RequestParameter.USER_CONFIRM_REGISTRATION_LETTER, true);
					page = PagePath.MESSAGE;
					break;
				}
				default:
					page = PagePath.MAIN;
				}
			} else {
				request.setAttribute(RequestParameter.REGISTRATION_PARAMETERS, true);
				page = PagePath.LOGIN;
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while login user", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}