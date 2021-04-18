package by.training.jwd.finalproject.controller.command.impl.page;

import javax.servlet.http.HttpServletRequest;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.command.ActionCommand;

/**
 * The {@code FillUpBalancePageCommand} class represents browse fill up balance
 * page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class FillUpBalancePageCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		return PagePath.FILL_UP_BALANCE;
	}

}
