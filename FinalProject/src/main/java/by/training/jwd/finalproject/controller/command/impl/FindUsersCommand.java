package by.training.jwd.finalproject.controller.command.impl;

import java.util.List;

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
import by.training.jwd.finalproject.util.XssSecurity;

/**
 * The {@code FindUsersCommand} class represents find users command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class FindUsersCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(FindUsersCommand.class);
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String searchQuery = request.getParameter(RequestParameter.SEARCH_USERS_QUERY);
		try {
			String searchQuerySecured = XssSecurity.secure(searchQuery);
			List<User> userList = userService.findUsersBySearchQuery(searchQuerySecured);
			request.setAttribute(RequestParameter.USERS, userList);
			page = PagePath.ADMIN_USERS_PAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while finding users", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}