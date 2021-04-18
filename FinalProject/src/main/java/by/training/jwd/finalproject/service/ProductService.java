package by.training.jwd.finalproject.service;

import java.util.List;
import java.util.Optional;

import by.training.jwd.finalproject.entity.Product;

/**
 * The {@code ProductService} represents an interface of a service providing
 * product-related actions.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public interface ProductService {
	/**
	 * Add product.
	 *
	 * @param title           the title
	 * @param price           the price
	 * @param facture         the facture
	 * @param applicationArea the applicationArea
	 * @param imageName       the image name
	 * @return true if adding success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean addProduct(String title, String price, String facture, String applicationArea, String imageName)
			throws ServiceException;

	/**
	 * Update product.
	 *
	 * @param productId       the product index
	 * @param title           the title
	 * @param price           the price
	 * @param facture         the facture
	 * @param applicationArea the applicationArea
	 * @return true if updating success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean updateProduct(String productId, String title, String price, String facture, String applicationArea)
			throws ServiceException;

	/**
	 * Find product by id.
	 *
	 * @param productId the product index
	 * @return the optional of product
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	Optional<Product> findProductById(String productId) throws ServiceException;

	/**
	 * Find products by search query.
	 *
	 * @param searchQuery the search query
	 * @return the list of products
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	List<Product> findBySearchQuery(String searchQuery) throws ServiceException;

	/**
	 * Find all products.
	 *
	 * @return the list of products
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	List<Product> findAllProducts() throws ServiceException;
}