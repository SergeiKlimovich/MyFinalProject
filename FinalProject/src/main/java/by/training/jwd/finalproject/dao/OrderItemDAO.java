package by.training.jwd.finalproject.dao;

import java.sql.Connection;
import java.util.List;

import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.entity.Order;
import by.training.jwd.finalproject.entity.OrderItem;
import by.training.jwd.finalproject.entity.Product;

/**
 * The {@code OrderItemDAO} interface used to access database order item data.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 * 
 */
public interface OrderItemDAO {
	/**
	 * Add order item.
	 *
	 * @param order      the order
	 * @param product    the product
	 * @param connection the connection
	 * @return true if adding success
	 * @throws DAOException if occurred problem in database
	 */
	boolean add(Order order, Product product, Connection connection) throws DAOException;

	/**
	 * Remove order item.
	 *
	 * @param order      the order
	 * @param product    the product
	 * @param connection the connection
	 * @return true if removing success
	 * @throws DAOException if occurred problem in database
	 */
	boolean remove(Order order, Product product, Connection connection) throws DAOException;

	/**
	 * Remove all order items.
	 *
	 * @param orderId    the order index
	 * @param connection the connection
	 * @return true if removing all success
	 * @throws DAOException if occurred problem in database
	 */
	boolean removeAll(Integer orderId, Connection connection) throws DAOException;

	/**
	 * Find order items by order id.
	 *
	 * @param orderId the order index
	 * @return the list of order items
	 * @throws DAOException if occurred problem in database
	 */
	List<OrderItem> findOrderItemsByOrderId(Integer orderId) throws DAOException;
}
