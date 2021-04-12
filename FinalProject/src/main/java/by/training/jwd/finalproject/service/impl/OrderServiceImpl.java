package by.training.jwd.finalproject.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.training.jwd.finalproject.dao.DAOProvider;
import by.training.jwd.finalproject.dao.OrderDAO;
import by.training.jwd.finalproject.dao.Transaction;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.dao.exception.TransactionException;
import by.training.jwd.finalproject.entity.Basket;
import by.training.jwd.finalproject.entity.Order;
import by.training.jwd.finalproject.entity.User;
import by.training.jwd.finalproject.service.OrderService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.validator.OrderValidator;
import by.training.jwd.finalproject.validator.UserValidator;

/**
 * The {@code OrderServiceImpl} class represents order service implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {
	DAOProvider provider = DAOProvider.getInstance();
	OrderDAO orderDAO = provider.getOrderDAO();
	private final Transaction transaction = Transaction.getInstance();

	@Override
	public boolean addOrder(String userId, List<Basket> basketList) throws ServiceException {
		boolean result = false;
		try {
			if (UserValidator.isIdValid(userId) && basketList != null) {
				int userIdParsed = Integer.parseInt(userId);
				Order order = new Order();
				LocalDate date = LocalDate.now();
				order.setCreationDate(date);
				order.setClosingDate(date);
				order.setStatus(Order.Status.UNDER_CONSIDERATION);
				User user = new User();
				user.setUserId(userIdParsed);
				order.setUser(user);
				result = transaction.addOrderAndOrderItems(order, basketList);
			}
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public boolean removeOrder(String orderId) throws ServiceException {
		boolean result = false;
		try {
			if (OrderValidator.isIdValid(orderId)) {
				int orderIdParsed = Integer.parseInt(orderId);
				result = transaction.removeOrderAndOrderItems(orderIdParsed);
			}
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public List<Order> findOrdersByUserId(String id) throws ServiceException {
		List<Order> orderList = new ArrayList<>();
		try {
			if (UserValidator.isIdValid(id)) {
				int userId = Integer.parseInt(id);
				orderList = orderDAO.findOrdersByUserId(userId);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return orderList;
	}

	@Override
	public List<Order> findOrdersBySearchQuery(String searchQuery) throws ServiceException {
		try {
			return orderDAO.findBySearchQuery(searchQuery);
		} catch (DAOException e) {
			throw new ServiceException("Error while finding orders by search query", e);
		}
	}

	@Override
	public List<Order> findAllOrders() throws ServiceException {
		try {
			return orderDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean produceOrder(String orderId) throws ServiceException {
		boolean isProduced = false;
		try {
			if (OrderValidator.isIdValid(orderId)) {
				LocalDate date = LocalDate.now();
				int orderIdParsed = Integer.parseInt(orderId);
				isProduced = orderDAO.produce(orderIdParsed, date);
			}
		} catch (DAOException e) {
			throw new ServiceException("Error while order produce", e);
		}
		return isProduced;
	}

	@Override
	public boolean rejectOrder(String orderId) throws ServiceException {
		boolean isRejected = false;
		try {
			if (OrderValidator.isIdValid(orderId)) {
				LocalDate date = LocalDate.now();
				int orderIdParsed = Integer.parseInt(orderId);
				isRejected = orderDAO.reject(orderIdParsed, date);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return isRejected;
	}

	@Override
	public Optional<Order> findOrderById(String id) throws ServiceException {
		Optional<Order> orderOptional = Optional.empty();
		try {
			if (OrderValidator.isIdValid(id)) {
				int orderId = Integer.parseInt(id);
				orderOptional = orderDAO.findById(orderId);
			}
			return orderOptional;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
