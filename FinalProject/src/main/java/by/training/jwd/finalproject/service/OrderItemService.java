package by.training.jwd.finalproject.service;

import java.util.List;

import by.training.jwd.finalproject.entity.OrderItem;

/**
 * The {@code OrderItemService} represents an interface of a service providing
 * orderItem-related actions.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public interface OrderItemService {
	/**
	 * Find order items by order id.
	 *
	 * @param orderId the order index
	 * @return the list of order items
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	List<OrderItem> findOrderItemsByOrderId(String orderId) throws ServiceException;
}
