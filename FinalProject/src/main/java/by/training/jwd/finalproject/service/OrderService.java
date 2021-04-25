package by.training.jwd.finalproject.service;

import java.util.List;
import java.util.Optional;

import by.training.jwd.finalproject.entity.Basket;
import by.training.jwd.finalproject.entity.Order;

/**
 * The {@code OrderService} represents an interface of a service providing
 * order-related actions.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public interface OrderService {
	/**
	 * Add order.
	 *
	 * @param userId     the user index
	 * @param basketList the basket list
	 * @return true if adding success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean addOrder(String userId, List<Basket> basketList) throws ServiceException;

	/**
	 * Remove order.
	 *
	 * @param orderId the order index
	 * @return true if removing success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean removeOrder(String orderId) throws ServiceException;

	/**
	 * Find orders by user id.
	 *
	 * @param userId the user index
	 * @return the list of orders
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	List<Order> findOrdersByUserId(String userId) throws ServiceException;

	/**
	 * Find orders by search query.
	 *
	 * @param searchQuery the search query
	 * @return the list of orders
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	List<Order> findOrdersBySearchQuery(String searchQuery) throws ServiceException;

	/**
	 * Find all orders.
	 *
	 * @return the list of orders
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	List<Order> findAllOrders() throws ServiceException;

	/**
	 * Produce order.
	 *
	 * @param orderId the order index
	 * @return true if producing success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean produceOrder(String orderId) throws ServiceException;

	/**
	 * Reject order.
	 *
	 * @param orderId the order index
	 * @return true if rejecting success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean rejectOrder(String orderId) throws ServiceException;

	/**
	 * Find order by id.
	 *
	 * @param orderId the order index
	 * @return the optional of order
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	Optional<Order> findOrderById(String orderId) throws ServiceException;
}
