package by.training.jwd.finalproject.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

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
 * The {@code DeleteUserCommand} class represents delete user command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class DeleteUserCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(DeleteUserCommand.class);
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String email = request.getParameter(RequestParameter.EMAIL);
		try {
			if (userService.removeUser(email)) {
				request.setAttribute(RequestParameter.USER_DELETE_SUCCESS_MESSAGE, true);
			} else {
				request.setAttribute(RequestParameter.USER_DELETE_ERROR_MESSAGE, true);
			}
			page = PagePath.MESSAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while deleting users", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
