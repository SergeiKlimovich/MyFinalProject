package by.training.jwd.finalproject.controller.command.impl.page;

import java.util.Optional;

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
 * The {@code ProductPageCommand} class represents browse product page command.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ProductPageCommand implements ActionCommand {
	private static final Logger logger = LogManager.getLogger(ProductPageCommand.class);
	private final ProductService productService = ServiceProvider.getInstance().getProductService();

	@Override
	public String execute(HttpServletRequest request) {
		String id = request.getParameter(RequestParameter.PRODUCT_ID);
		String page;
		try {
			Optional<Product> optionalProduct = productService.findProductById(id);
			if (optionalProduct.isPresent()) {
				request.setAttribute(RequestParameter.PRODUCT, optionalProduct.get());
				page = PagePath.PRODUCT;
			} else {
				request.setAttribute(RequestParameter.PRODUCT_FIND_ERROR, true);
				page = PagePath.MESSAGE;
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error while finding all users", e);
			request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
			page = PagePath.ERROR_500;
		}
		return page;
	}
}
