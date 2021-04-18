package by.training.jwd.finalproject.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.entity.User;
import by.training.jwd.finalproject.entity.User.Role;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;
import by.training.jwd.finalproject.service.UserService;

/**
 * The {@code ChangePasswordCommand} class represents change password command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ChangePasswordCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		String email;
		if (session.getAttribute(RequestParameter.EMAIL) == null) {
			email = ((User) session.getAttribute(RequestParameter.USER)).getEmail();
		} else {
			email = session.getAttribute(RequestParameter.EMAIL).toString();
		}
		String password = request.getParameter(RequestParameter.PASSWORD);
		String passwordRepeat = request.getParameter(RequestParameter.PASSWORD_REPEAT);
		try {
			if (userService.changePassword(email, password, passwordRepeat)) {
				request.setAttribute(RequestParameter.USER_SUCCESS_CHANGE_PASSWORD, true);
				session.removeAttribute(RequestParameter.EMAIL);
				if (session.getAttribute(RequestParameter.USER) != null) {
					session.removeAttribute(RequestParameter.USER);
					session.setAttribute(RequestParameter.ROLE, Role.GUEST.toString());
				}
				page = PagePath.LOGIN;
			} else {
				request.setAttribute(RequestParameter.USER_ERROR_CHANGE_PASSWORD, true);
				page = PagePath.MESSAGE;
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while changing password", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
