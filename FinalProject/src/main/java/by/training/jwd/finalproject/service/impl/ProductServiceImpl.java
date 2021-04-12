package by.training.jwd.finalproject.service.impl;

import java.util.List;
import java.util.Optional;

import by.training.jwd.finalproject.dao.DAOProvider;
import by.training.jwd.finalproject.dao.ProductDAO;
import by.training.jwd.finalproject.dao.Transaction;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.dao.exception.TransactionException;
import by.training.jwd.finalproject.entity.Image;
import by.training.jwd.finalproject.entity.Product;
import by.training.jwd.finalproject.service.ProductService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.validator.ProductValidator;

/**
 * The {@code ProductServiceImpl} class represents product service
 * implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ProductServiceImpl implements ProductService {
	DAOProvider provider = DAOProvider.getInstance();
	ProductDAO productDAO = provider.getProductDAO();
	private final Transaction transaction = Transaction.getInstance();

	@Override
	public boolean addProduct(String title, String price, String facture, String applicationArea, String imageName)
			throws ServiceException {
		boolean result = false;
		try {
			if (ProductValidator.isTitleValid(title) && ProductValidator.isPriceValid(price)
					&& ProductValidator.isFactureValid(facture)
					&& ProductValidator.isApplicationAreaValid(applicationArea)) {
				Product product = new Product();
				product.setTitle(title);
				product.setPrice(Double.parseDouble(price));
				product.setFacture(facture);
				product.setApplicationArea(applicationArea);
				Image image = new Image();
				image.setName(imageName);
				product.setImage(image);
				result = transaction.addProductAndImage(product);
			}
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public boolean updateProduct(String productId, String title, String price, String facture, String applicationArea)
			throws ServiceException {
		boolean result = false;
		try {
			if (ProductValidator.isIdValid(productId) && ProductValidator.isTitleValid(title)
					&& ProductValidator.isPriceValid(price) && ProductValidator.isFactureValid(facture)
					&& ProductValidator.isApplicationAreaValid(applicationArea)) {
				int productIdParsed = Integer.parseInt(productId);
				Optional<Product> optionalProduct = productDAO.findById(productIdParsed);
				if (optionalProduct.isPresent()) {
					Product product = optionalProduct.get();
					product.setTitle(title);
					product.setPrice(Double.parseDouble(price));
					product.setFacture(facture);
					product.setApplicationArea(applicationArea);

					result = productDAO.update(product);
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public Optional<Product> findProductById(String id) throws ServiceException {
		Optional<Product> optionalProduct = Optional.empty();
		try {
			if (ProductValidator.isIdValid(id)) {
				int productId = Integer.parseInt(id);
				optionalProduct = productDAO.findById(productId);
			}
			return optionalProduct;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> findBySearchQuery(String searchQuery) throws ServiceException {
		try {
			return productDAO.findBySearchQuery(searchQuery);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> findAllProducts() throws ServiceException {
		try {
			return productDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
