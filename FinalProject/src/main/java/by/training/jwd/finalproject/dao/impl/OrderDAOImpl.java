package by.training.jwd.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.training.jwd.finalproject.dao.ColumnName;
import by.training.jwd.finalproject.dao.OrderDAO;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.dao.pool.ConnectionPool;
import by.training.jwd.finalproject.dao.pool.ConnectionPoolException;
import by.training.jwd.finalproject.entity.Order;
import by.training.jwd.finalproject.entity.User;

/**
 * The {@code OrderDAOImpl} class represents orderDAO implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class OrderDAOImpl implements OrderDAO {
	
	private static final String REMOVE_ORDER = "DELETE FROM orders WHERE order_id = ?";

	private static final String FIND_BY_ID = "SELECT order_id, creation_date, closing_date, order_status, user_id FROM orders "
			+ "INNER JOIN users ON user_id = user_id_fk WHERE order_id = ?";

	private static final String ADD_ORDER = "INSERT INTO orders (creation_date, closing_date, order_status, user_id_fk) VALUES (?, ?, ?, ?)";

	private static final String PRODUCE_ORDER = "UPDATE orders SET order_status = \"PRODUCED\", closing_date = ? WHERE order_id = ?";

	private static final String REJECT_ORDER = "UPDATE orders SET order_status = \"DENIED\", closing_date = ? WHERE order_id = ?";

	private static final String FIND_BY_USER_ID = "SELECT order_id, creation_date, closing_date, order_status, user_id FROM orders "
			+ "INNER JOIN users ON user_id = user_id_fk WHERE user_id = ?";

	private static final String FIND_ALL = "SELECT order_id, creation_date, closing_date, order_status, user_id FROM orders "
			+ "INNER JOIN users ON user_id = user_id_fk";

	private static final String FIND_PRODUCTS_BY_SEARCH_QUERY = "SELECT order_id, creation_date, closing_date, order_status, user_id FROM orders "
			+ "INNER JOIN users ON user_id = user_id_fk WHERE concat(order_id, creation_date, closing_date, order_status, user_id) LIKE ?";

	private static final String PERCENT = "%";

	@Override
	public boolean remove(Integer orderId, Connection connection) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(REMOVE_ORDER)) {
			statement.setInt(1, orderId);
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {

			throw new DAOException(e);
		}

	}

	@Override
	public boolean add(Order order, Connection connection) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
			Date creationDate = Date.valueOf(order.getCreationDate());
			Date closingDate = Date.valueOf(order.getClosingDate());
			statement.setLong(1, creationDate.getTime());
			statement.setLong(2, closingDate.getTime());
			statement.setString(3, order.getStatus().toString());
			statement.setInt(4, order.getUser().getUserId());
			boolean result = statement.executeUpdate() > 0;
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				order.setOrderId(resultSet.getInt(1));
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean produce(Integer orderId, LocalDate date) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(PRODUCE_ORDER)) {
			Date closingDate = Date.valueOf(date);
			statement.setLong(1, closingDate.getTime());
			statement.setInt(2, orderId);
			return statement.executeUpdate() > 0;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Optional<Order> findById(Integer orderId) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setInt(1, orderId);
			ResultSet resultSet = statement.executeQuery();
			Optional<Order> orderOptional = Optional.empty();
			if (resultSet.next()) {
				Order order = makeOrderByResultSet(resultSet);
				orderOptional = Optional.of(order);
			}
			return orderOptional;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<Order> findOrdersByUserId(Integer userId) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			List<Order> orderList = new ArrayList<>();
			while (resultSet.next()) {
				Order order = makeOrderByResultSet(resultSet);
				orderList.add(order);
			}
			return orderList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	public List<Order> findAll() throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
			ResultSet resultSet = statement.executeQuery();
			List<Order> orderList = new ArrayList<>();
			while (resultSet.next()) {
				Order order = makeOrderByResultSet(resultSet);
				orderList.add(order);
			}
			return orderList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	public boolean reject(Integer orderId, LocalDate date) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(REJECT_ORDER)) {
			Date closingDate = Date.valueOf(date);
			statement.setLong(1, closingDate.getTime());
			statement.setInt(2, orderId);
			return statement.executeUpdate() > 0;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<Order> findBySearchQuery(String searchQuery) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_PRODUCTS_BY_SEARCH_QUERY)) {
			statement.setString(1, PERCENT + searchQuery + PERCENT);
			ResultSet resultSet = statement.executeQuery();
			List<Order> orderList = new ArrayList<>();
			while (resultSet.next()) {
				Order order = makeOrderByResultSet(resultSet);
				orderList.add(order);
			}
			return orderList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	private Order makeOrderByResultSet(ResultSet resultSet) throws SQLException {
		Integer orderId = Integer.parseInt(resultSet.getString(ColumnName.ORDER_ID));
		Long creationDateLong = resultSet.getLong(ColumnName.ORDER_CREATION_DATE);
		Date creationDate = new Date(creationDateLong);
		Long closingDateLong = resultSet.getLong(ColumnName.ORDER_CLOSING_DATE);
		Date closingDate = new Date(closingDateLong);

		Order.Status status = Order.Status.valueOf(resultSet.getString(ColumnName.ORDER_STATUS));
		Integer userId = Integer.parseInt(resultSet.getString(ColumnName.USER_ID));
		User user = new User();
		user.setUserId(userId);
		return new Order(orderId, creationDate.toLocalDate(), closingDate.toLocalDate(), status, user);
	}

}
