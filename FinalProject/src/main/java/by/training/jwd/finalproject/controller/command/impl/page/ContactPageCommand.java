package by.training.jwd.finalproject.controller.command.impl.page;

import javax.servlet.http.HttpServletRequest;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.command.ActionCommand;

/**
 * The {@code ContactPageCommand} class represents browse contact page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ContactPageCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return PagePath.CONTACT;
	}

}
