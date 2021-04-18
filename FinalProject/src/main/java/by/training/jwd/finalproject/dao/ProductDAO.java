package by.training.jwd.finalproject.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.entity.Product;

/**
 * The {@code ProductDAO} interface used to access database product data.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 * 
 */
public interface ProductDAO {
	/**
	 * Add product.
	 *
	 * @param product    the product
	 * @param connection the connection
	 * @return true if adding success
	 * @throws DAOException if occurred problem in database
	 */
	boolean add(Product product, Connection connection) throws DAOException;

	/**
	 * Update product.
	 *
	 * @param product the product
	 * @return true if updating success
	 * @throws DAOException if occurred problem in database
	 */
	boolean update(Product product) throws DAOException;

	/**
	 * Find product by id.
	 *
	 * @param productId the product index
	 * @return the optional of product
	 * @throws DAOException if occurred problem in database
	 */
	Optional<Product> findById(Integer productId) throws DAOException;

	/**
	 * Find all products.
	 *
	 * @return the list of products
	 * @throws DAOException if occurred problem in database
	 */
	List<Product> findAll() throws DAOException;

	/**
	 * Find products by search query.
	 *
	 * @param searchQuery the search query
	 * @return the list of products
	 * @throws DAOException if occurred problem in database
	 */
	List<Product> findBySearchQuery(String searchQuery) throws DAOException;

}
