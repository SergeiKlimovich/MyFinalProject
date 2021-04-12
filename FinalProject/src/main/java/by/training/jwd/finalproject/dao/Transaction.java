package by.training.jwd.finalproject.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.dao.exception.TransactionException;
import by.training.jwd.finalproject.dao.pool.ConnectionPool;
import by.training.jwd.finalproject.dao.pool.ConnectionPoolException;

import by.training.jwd.finalproject.entity.Basket;
import by.training.jwd.finalproject.entity.Order;
import by.training.jwd.finalproject.entity.Product;

/**
 * The {@code Transaction} class represents transactions.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class Transaction {
	private static final Transaction instance = new Transaction();
	private static final Logger logger = LogManager.getLogger(Transaction.class);

	public static Transaction getInstance() {
		return instance;
	}

	/**
	 * Add order and order item transaction.
	 *
	 * @param order      the order
	 * @param basketList the user basketList
	 * @return true if adding success
	 * @throws TransactionException if occurred problem in database
	 */
	public boolean addOrderAndOrderItems(Order order, List<Basket> basketList) throws TransactionException {
		Connection connection = null;
		BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
		OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
		OrderItemDAO orderItemDAO = DAOProvider.getInstance().getOrderItemDAO();
		try {
			connection = ConnectionPool.getInstance().getConnection();
			connection.setAutoCommit(false);
			boolean result = orderDAO.add(order, connection);
			if (result) {
				for (Basket basket : basketList) {
					if (!(orderItemDAO.add(order, basket.getProduct(), connection) && basketDAO.remove(basket))) {
						result = false;
					}
				}
			}
			connection.commit();
			return result;
		} catch (ConnectionPoolException | SQLException | DAOException e) {
			rollback(connection);
			throw new TransactionException(e);
		} finally {
			close(connection);
		}
	}

	/**
	 * Remove order and order items transaction.
	 *
	 * @param orderId the order index
	 * @return true if removing success
	 * @throws TransactionException if occurred problem in database
	 */
	public boolean removeOrderAndOrderItems(Integer orderId) throws TransactionException {
		Connection connection = null;
		OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
		OrderItemDAO orderItemDAO = DAOProvider.getInstance().getOrderItemDAO();
		try {
			connection = ConnectionPool.getInstance().getConnection();
			connection.setAutoCommit(false);
			boolean result = orderItemDAO.removeAll(orderId, connection);
			if (result) {
				if (!orderDAO.remove(orderId, connection)) {
					result = false;
				}
			}
			connection.commit();
			return result;
		} catch (ConnectionPoolException | SQLException | DAOException e) {
			rollback(connection);
			throw new TransactionException(e);
		} finally {
			close(connection);
		}
	}

	/**
	 * Add product and image transaction.
	 *
	 * @param product the product
	 * @return true if adding success
	 * @throws TransactionException if occurred problem in database
	 */
	public boolean addProductAndImage(Product product) throws TransactionException {
		Connection connection = null;
		ImageDAO imageDAO = DAOProvider.getInstance().getImageDAO();
		ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
		try {
			connection = ConnectionPool.getInstance().getConnection();
			connection.setAutoCommit(false);
			boolean result = imageDAO.add(product.getImage(), connection);
			if (result) {
				result = productDAO.add(product, connection);
			}
			connection.commit();
			return result;
		} catch (ConnectionPoolException | SQLException | DAOException e) {
			rollback(connection);
			throw new TransactionException(e);
		} finally {
			close(connection);
		}
	}

	private void rollback(Connection connection) {
		try {
			if (connection != null) {
				connection.rollback();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Rollback error");
		}
	}

	private void close(Connection connection) {
		if (connection != null) {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Close connection error");
			}
		}
	}
}