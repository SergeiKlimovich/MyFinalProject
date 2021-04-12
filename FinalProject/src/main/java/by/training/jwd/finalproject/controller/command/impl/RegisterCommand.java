package by.training.jwd.finalproject.controller.command.impl;

import java.util.HashMap;
import java.util.Map;

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
 * The {@code RegisterCommand} class represents register command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class RegisterCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger(RegisterCommand.class);
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String email = request.getParameter(RequestParameter.EMAIL);
		String password = request.getParameter(RequestParameter.PASSWORD);
		String passwordRepeat = request.getParameter(RequestParameter.PASSWORD_REPEAT);
		String name = request.getParameter(RequestParameter.NAME);
		String surname = request.getParameter(RequestParameter.SURNAME);
		String patronymic = request.getParameter(RequestParameter.PATRONYMIC);
		Map<String, String> registrationParameters = new HashMap<>();
		registrationParameters.put(RequestParameter.EMAIL, email);
		registrationParameters.put(RequestParameter.NAME, name);
		registrationParameters.put(RequestParameter.SURNAME, surname);
		registrationParameters.put(RequestParameter.PATRONYMIC, patronymic);
		registrationParameters.put(RequestParameter.PASSWORD, password);
		registrationParameters.put(RequestParameter.PASSWORD_REPEAT, passwordRepeat);
		try {
			if (userService.addUser(registrationParameters)) {
				User user = userService.findUserByEmail(email).get();
				userService.sendLetter(user, request.getRequestURL().toString());
				request.setAttribute(RequestParameter.USER_CONFIRM_REGISTRATION_LETTER, true);
				page = PagePath.MESSAGE;
			} else {
				request.setAttribute(RequestParameter.REGISTRATION_PARAMETERS, registrationParameters);
				page = PagePath.REGISTRATION;
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while register user", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
