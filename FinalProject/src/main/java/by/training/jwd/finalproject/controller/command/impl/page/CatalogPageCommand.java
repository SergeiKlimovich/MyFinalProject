package by.training.jwd.finalproject.controller.command.impl.page;

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

/**
 * The {@code CatalogPageCommand} class represents browse catalog page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class CatalogPageCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(CatalogPageCommand.class);
	private final ProductService productService = ServiceProvider.getInstance().getProductService();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		try {
			List<Product> productList = productService.findAllProducts();
			request.setAttribute(RequestParameter.PRODUCTS, productList);
			page = PagePath.CATALOG;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while finding all products", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}