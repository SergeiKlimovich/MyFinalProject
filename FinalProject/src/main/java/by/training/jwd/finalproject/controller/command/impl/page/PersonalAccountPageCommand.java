package by.training.jwd.finalproject.controller.command.impl.page;

import javax.servlet.http.HttpServletRequest;

import by.training.jwd.finalproject.controller.PagePath;

import by.training.jwd.finalproject.controller.command.ActionCommand;

/**
 * The {@code PersonalAccountPageCommand} class represents browse personal
 * account page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class PersonalAccountPageCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return PagePath.PERSONAL_ACCOUNT;

	}
}
