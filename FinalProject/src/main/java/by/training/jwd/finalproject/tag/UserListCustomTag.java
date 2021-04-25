package by.training.jwd.finalproject.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.entity.User;

/**
 * The {@code UserListCustomTag} class represents user list custom tag for
 * admin.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class UserListCustomTag extends TagSupport {
	private static final long serialVersionUID = -3327088103976507549L;
	private static final String DELETE_COMMAND = "delete_user_command";
	private static final String BLOCK_COMMAND = "block_user_command";
	private static final String UNBLOCK_COMMAND = "unblock_user_command";
	private static final String ORDER_HISTORY_PAGE = "order_history_page";
	private static final Logger LOGGER = LogManager.getLogger(UserListCustomTag.class);

	@Override
	public int doStartTag() throws JspException {

		ServletRequest servletRequest = pageContext.getRequest();
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) servletRequest.getAttribute(RequestParameter.USERS);
		int index = 0;
		while (index < userList.size()) {
			User user = userList.get(index);
			String commandChoice = user.getStatus() == User.Status.NOT_CONFIRMED ? DELETE_COMMAND
					: user.getStatus() == User.Status.ENABLE ? BLOCK_COMMAND : UNBLOCK_COMMAND;

			try {
				pageContext.getOut().write("<tr><td style=\"vertical-align: middle\">" + user.getName() + "</td>"
						+ "<td style=\"vertical-align: middle\">" + user.getSurname() + "</td>"
						+ "<td style=\"vertical-align: middle\">" + user.getPatronymic() + "</td>"
						+ "<td style=\"vertical-align: middle\">" + user.getRole() + "</td>"
						+ "<td style=\"vertical-align: middle\">" + user.getStatus() + "</td>"
						+ "<td style=\"vertical-align: middle\">" + user.getEmail() + "</td>"
						+ "<td style=\"vertical-align: middle\"><form method=\"post\" action=\"controller\">\n"
						+ "<input type=\"hidden\" name =\"commandName\" value=\"" + commandChoice + "\">\n"
						+ "<button class=\"btn btn-secondary nav-link\" name=\"email\"\n" + "value=\"" + user.getEmail()
						+ "\"><i class=\"fa fa-lock\" aria-hidden=\"true\"></i></button>\n" + "</form></td>"
						+ "<td style=\"vertical-align: middle\"><form method=\"post\" action=\"controller\">\n"
						+ "<input type=\"hidden\" name =\"commandName\" value=\"" + ORDER_HISTORY_PAGE + "\">\n"
						+ "<button class=\"btn btn-secondary nav-link\" name=\"userId\"\n" + "value=\""
						+ user.getUserId() + "\"><i class=\"fa fa-folder\" aria-hidden=\"true\"></i></button>\n"
						+ "</form></td></tr>");
			} catch (IOException e) {
				LOGGER.log(Level.ERROR, "Error while write out");
			}
			index++;
		}
		return SKIP_BODY;
	}
}