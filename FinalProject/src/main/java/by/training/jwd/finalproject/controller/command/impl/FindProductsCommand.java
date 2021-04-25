package by.training.jwd.finalproject.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.controller.PagePath;
import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.controller.command.ActionCommand;
import by.training.jwd.finalproject.entity.Product;
import by.training.jwd.finalproject.service.ProductService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.ServiceProvider;
import by.training.jwd.finalproject.util.XssSecurity;

/**
 * The {@code FindProductsCommand} class represents find products command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class FindProductsCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(FindProductsCommand.class);
	private final ProductService productService = ServiceProvider.getInstance().getProductService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String searchQuery = request.getParameter(RequestParameter.SEARCH_PRODUCTS_QUERY);
		try {
			String searchQuerySecured = XssSecurity.secure(searchQuery);
			List<Product> productList = productService.findBySearchQuery(searchQuerySecured);
			request.setAttribute(RequestParameter.PRODUCTS, productList);
			page = PagePath.CATALOG;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while finding users", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
