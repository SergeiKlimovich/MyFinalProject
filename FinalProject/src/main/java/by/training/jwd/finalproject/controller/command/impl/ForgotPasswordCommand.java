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
 * The {@code ForgotPasswordCommand} class represents forgot password command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ForgotPasswordCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(ForgotPasswordCommand.class);
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String email = request.getParameter(RequestParameter.EMAIL);
		try {
			Optional<User> optionalUser = userService.findUserByEmail(email);
			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				userService.sendLetter(user, request.getRequestURL().toString());
				request.setAttribute(RequestParameter.USER_SUCCESS_CHANGE_PASSWORD_LETTER, true);
			} else {
				request.setAttribute(RequestParameter.USER_EMAIL_IS_NOT_FOUND, true);
			}
			page = PagePath.MESSAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error forgot password", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
