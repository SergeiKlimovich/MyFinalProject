package by.training.jwd.finalproject.controller.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code MyRequestListener} class handles the events of creating and
 * destroying a request to the servlet
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class MyRequestListener implements ServletRequestListener {
	private static final Logger logger = LogManager.getLogger(MyRequestListener.class);

	private static int reqCount;

	@Override
	public void requestInitialized(ServletRequestEvent e) {

		ServletRequest req = e.getServletRequest();

		String name = ((HttpServletRequest) req).getRequestURI();

		logger.log(Level.INFO, "Request for " + name + "; Count=" + ++reqCount);

	}

	@Override
	public void requestDestroyed(ServletRequestEvent e) {

		logger.log(Level.INFO, "Request has been destroyed");

	}

}
