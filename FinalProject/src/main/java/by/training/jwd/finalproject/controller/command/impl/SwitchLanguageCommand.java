package by.training.jwd.finalproject.controller.command.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.jwd.finalproject.controller.RequestAttributeHandler;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;

/**
 * The {@code SwitchLanguageCommand} class represents switch language command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class SwitchLanguageCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String locale = request.getParameter(RequestParameter.NEW_LOCALE);
		HttpSession session = request.getSession();
		session.setAttribute(RequestParameter.CURRENT_LOCALE, locale);
		RequestAttributeHandler requestAttributeHandler = (RequestAttributeHandler) session
				.getAttribute(RequestParameter.REQUEST_ATTRIBUTE_HANDLER);
		Map<String, Object> attributes = requestAttributeHandler.getAttributes();
		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			request.setAttribute(entry.getKey(), entry.getValue());
		}
		return (String) session.getAttribute(RequestParameter.CURRENT_PAGE);
	}
}
