package by.training.jwd.finalproject.dao;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.entity.Order;

/**
 * The {@code OrderDAO} interface used to access database order data.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 * 
 */
public interface OrderDAO {

	/**
	 * Add order.
	 *
	 * @param order      the order
	 * @param connection the connection
	 * @return true if adding success
	 * @throws DAOException if occurred problem in database
	 */

	boolean add(Order order, Connection connection) throws DAOException;

	/**
	 * Remove order.
	 *
	 * @param orderId    the order index
	 * @param connection the connection
	 * @return true if removing success
	 * @throws DAOException if occurred problem in database
	 */

	boolean remove(Integer orderId, Connection connection) throws DAOException;

	/**
	 * Produce order.
	 *
	 * @param orderId the order index
	 * @param date    the local date
	 * @return true if producing success
	 * @throws DAOException if occurred problem in database
	 */

	boolean produce(Integer orderId, LocalDate date) throws DAOException;

	/**
	 * Reject order.
	 *
	 * @param orderId the order index
	 * @param date    the local date
	 * @return true if rejecting success
	 * @throws DAOException if occurred problem in database
	 */

	boolean reject(Integer orderId, LocalDate date) throws DAOException;

	/**
	 * Find orders by user id.
	 *
	 * @param userId the user id
	 * @return the list of orders
	 * @throws DAOException if occurred problem in database
	 */

	List<Order> findOrdersByUserId(Integer userId) throws DAOException;

	/**
	 * Find all orders.
	 *
	 * @return the list of orders
	 * @throws DAOException if occurred problem in database
	 */

	List<Order> findAll() throws DAOException;

	/**
	 * Find order by id.
	 *
	 * @return the optional of order
	 * @throws DAOException if occurred problem in database
	 */

	Optional<Order> findById(Integer id) throws DAOException;

	/**
	 * Find orders by search query.
	 *
	 * @param searchQuery the search query
	 * @return the list of orders
	 * @throws DAOException if occurred problem in database
	 */

	List<Order> findBySearchQuery(String searchQuery) throws DAOException;
}
