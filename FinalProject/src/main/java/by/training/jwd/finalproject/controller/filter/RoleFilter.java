package by.training.jwd.finalproject.controller.filter;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.controller.command.CommandName;
import by.training.jwd.finalproject.controller.command.CommandProvider;
import by.training.jwd.finalproject.controller.command.RoleAccess;
import by.training.jwd.finalproject.controller.command.impl.EmptyCommand;
import by.training.jwd.finalproject.entity.User;

/**
 * The {@code RoleFilter} class represents role filter.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
@WebFilter(urlPatterns = { "/*" })
public class RoleFilter implements Filter {
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void init(FilterConfig config) {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpRequest.getSession();
		String commandName = httpRequest.getParameter(RequestParameter.COMMAND_NAME);
		Optional<ActionCommand> commandOptional = CommandProvider.defineCommand(commandName);
		if (commandOptional.isPresent()) {
			ActionCommand command = commandOptional.get();
			if (command.getClass() != EmptyCommand.class) {
				String roleName = (String) httpSession.getAttribute(RequestParameter.ROLE);
				User.Role role = roleName != null ? User.Role.valueOf(roleName.toUpperCase()) : User.Role.GUEST;
				Set<CommandName> commands = null;
				switch (role) {
				case USER:
					commands = RoleAccess.USER.getAccessCommands();
					break;
				case ADMIN:
					commands = RoleAccess.ADMIN.getAccessCommands();
					break;
				case GUEST:
					commands = RoleAccess.GUEST.getAccessCommands();
					break;
				default:
					break;
				}
				if (commands != null && !commands.contains(CommandName.valueOf(commandName.toUpperCase()))) {
					LOGGER.log(Level.ERROR, "Role " + role + " has no access to command " + commandName);
					request.setAttribute(RequestParameter.ACCESS_ERROR_MESSAGE, true);
					request.getRequestDispatcher(PagePath.MESSAGE).forward(request, response);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
