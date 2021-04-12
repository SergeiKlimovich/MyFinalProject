package by.training.jwd.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.training.jwd.finalproject.dao.DAOProvider;
import by.training.jwd.finalproject.dao.OrderItemDAO;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.entity.OrderItem;
import by.training.jwd.finalproject.service.OrderItemService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.validator.OrderItemValidator;

/**
 * The {@code OrderItemServiceImpl} class represents orderItem service
 * implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class OrderItemServiceImpl implements OrderItemService {
	DAOProvider provider = DAOProvider.getInstance();
	OrderItemDAO orderItemDAO = provider.getOrderItemDAO();

	@Override
	public List<OrderItem> findOrderItemsByOrderId(String id) throws ServiceException {
		List<OrderItem> orderItemList = new ArrayList<>();
		try {
			if (OrderItemValidator.isIdValid(id)) {
				int orderId = Integer.parseInt(id);
				orderItemList = orderItemDAO.findOrderItemsByOrderId(orderId);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return orderItemList;
	}

}
