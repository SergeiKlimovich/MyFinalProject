package by.training.jwd.finalproject.controller.command.impl.page;

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

/**
 * The {@code AdminUsersPageCommand} class represents browse admin users page
 * command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class AdminUsersPageCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(AdminUsersPageCommand.class);
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		try {
			List<User> userList = userService.findAllUsersNotAdmin();
			request.setAttribute(RequestParameter.USERS, userList);
			page = PagePath.ADMIN_USERS_PAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while finding all users", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
