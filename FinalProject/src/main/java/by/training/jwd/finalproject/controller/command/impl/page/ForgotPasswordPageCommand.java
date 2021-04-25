package by.training.jwd.finalproject.controller.command.impl.page;

import javax.servlet.http.HttpServletRequest;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.command.ActionCommand;

/**
 * The {@code ForgotPasswordPageCommand} class represents browse forgot password
 * page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ForgotPasswordPageCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		return PagePath.FORGOT_PASSWORD;
	}

}
