package by.training.jwd.finalproject.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.command.ActionCommand;

/**
 * The {@code EmptyCommand} class represents empty command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class EmptyCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		return PagePath.ERROR_404;
	}

}
