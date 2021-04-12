package by.training.jwd.finalproject.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.service.ProductService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;

/**
 * The {@code AddProductCommand} class represents add product command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class AddProductCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(AddProductCommand.class);
	private final ProductService productService = ServiceProvider.getInstance().getProductService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String title = request.getParameter(RequestParameter.TITLE);
		String price = request.getParameter(RequestParameter.PRICE);
		String facture = request.getParameter(RequestParameter.FACTURE);
		String applicationArea = request.getParameter(RequestParameter.APPLICATION_AREA);
		String photoName = (String) request.getAttribute(RequestParameter.PHOTO_NAME);
		try {
			if (productService.addProduct(title, price, facture, applicationArea, photoName)) {
				request.setAttribute(RequestParameter.ADD_PRODUCT_SUCCESS, true);
			} else {
				request.setAttribute(RequestParameter.ADD_PRODUCT_ERROR, true);
			}
			page = PagePath.MESSAGE;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while adding product", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}