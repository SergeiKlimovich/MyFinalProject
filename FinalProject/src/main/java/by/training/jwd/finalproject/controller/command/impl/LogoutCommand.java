package by.training.jwd.finalproject.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.entity.User;

/**
 * The {@code LogoutCommand} class represents logout command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class LogoutCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(RequestParameter.USER);
			session.removeAttribute(RequestParameter.USER_ID);
			session.setAttribute(RequestParameter.ROLE, User.Role.GUEST.toString());
		}
		
		return PagePath.LOGIN;
	}
}
