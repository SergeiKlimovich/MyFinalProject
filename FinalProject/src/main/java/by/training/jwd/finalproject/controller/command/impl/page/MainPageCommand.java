package by.training.jwd.finalproject.controller.command.impl.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.entity.User;

/**
 * The {@code MainPageCommand} class represents browse main page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class MainPageCommand implements ActionCommand {
	private static final String ENG_LOCALE = "en";

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(RequestParameter.CURRENT_LOCALE, ENG_LOCALE);
		if (session.isNew()) {

			session.setAttribute(RequestParameter.ROLE, User.Role.GUEST.toString());
		}
		return PagePath.MAIN;
	}
}