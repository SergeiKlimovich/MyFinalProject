package by.training.jwd.finalproject.controller.command.impl.page;

import javax.servlet.http.HttpServletRequest;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.command.ActionCommand;

/**
 * The {@code ChangePasswordPageCommand} class represents browse change password
 * page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ChangePasswordPageCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		return PagePath.CHANGE_PASSWORD;
	}

}
