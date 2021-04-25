package by.training.jwd.finalproject.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code ActionCommand} interface represents command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public interface ActionCommand {
	/**
	 * Execute command.
	 *
	 * @param request the request
	 * @return the String containing page path
	 */
	String execute(HttpServletRequest request);
}