package by.training.jwd.finalproject.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.controller.command.CommandProvider;
import by.training.jwd.finalproject.dao.pool.ConnectionPool;

/**
 * The {@code MainController} class represents main controller.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class MainController extends HttpServlet {

	private static final long serialVersionUID = -3174778254539599013L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
		Optional<ActionCommand> commandOptional = CommandProvider.defineCommand(commandName);
		ActionCommand command = commandOptional.orElseThrow(IllegalArgumentException::new);
		String page = command.execute(request);
		HttpSession session = request.getSession();
		session.setAttribute(RequestParameter.CURRENT_PAGE, page);
		RequestAttributeHandler requestAttributeHandler = new RequestAttributeHandler();
		requestAttributeHandler.setAttributes(request);
		session.setAttribute(RequestParameter.REQUEST_ATTRIBUTE_HANDLER, requestAttributeHandler);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	@Override
	public void destroy() {
		super.destroy();
		ConnectionPool.getInstance().destroyPool();
	}
}