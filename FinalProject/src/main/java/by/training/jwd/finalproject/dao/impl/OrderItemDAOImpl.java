package by.training.jwd.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.training.jwd.finalproject.dao.ColumnName;
import by.training.jwd.finalproject.dao.OrderItemDAO;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.dao.pool.ConnectionPool;
import by.training.jwd.finalproject.dao.pool.ConnectionPoolException;
import by.training.jwd.finalproject.entity.Image;
import by.training.jwd.finalproject.entity.Order;
import by.training.jwd.finalproject.entity.OrderItem;
import by.training.jwd.finalproject.entity.Product;

/**
 * The {@code OrderItemDAOImpl} class represents orderItemDAO implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class OrderItemDAOImpl implements OrderItemDAO {

	private static final String ADD_ORDER_ITEM = "INSERT INTO orderitems (product_id_fk, order_id_fk) VALUES (?, ?)";
	private static final String REMOVE_ORDER_ITEM = "DELETE FROM orderitems WHERE product_id_fk = ?, order_id_fk = ?";
	private static final String REMOVE_ALL_ORDER_ITEMS = "DELETE FROM orderitems WHERE order_id_fk = ?";
	private static final String FIND_ORDER_ITEM_BY_ORDER_ID = "SELECT orderitem_id, product_id, title, price, facture, application_area, "
			+ "image_id, image_name, order_id from orderitems INNER JOIN products ON product_id_fk = product_id "
			+ "INNER JOIN images ON image_id_fk = image_id INNER JOIN orders ON order_id_fk = order_id WHERE order_id = ?";

	@Override
	public boolean add(Order order, Product product, Connection connection) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(ADD_ORDER_ITEM)) {
			statement.setInt(1, product.getProductId());
			statement.setInt(2, order.getOrderId());
			boolean result = statement.executeUpdate() > 0;
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean remove(Order order, Product product, Connection connection) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(REMOVE_ORDER_ITEM)) {
			statement.setInt(1, product.getProductId());
			statement.setInt(2, order.getOrderId());
			boolean result = statement.executeUpdate() > 0;
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean removeAll(Integer orderId, Connection connection) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(REMOVE_ALL_ORDER_ITEMS)) {
			statement.setInt(1, orderId);
			boolean result = statement.executeUpdate() > 0;
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<OrderItem> findOrderItemsByOrderId(Integer orderId) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ORDER_ITEM_BY_ORDER_ID)) {
			statement.setInt(1, orderId);
			ResultSet resultSet = statement.executeQuery();
			List<OrderItem> orderItemList = new ArrayList<>();
			while (resultSet.next()) {
				OrderItem orderItem = makeOrderItemByResultSet(resultSet);
				orderItemList.add(orderItem);
			}
			return orderItemList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	private OrderItem makeOrderItemByResultSet(ResultSet resultSet) throws SQLException {
		Integer orderItemId = Integer.parseInt(resultSet.getString(ColumnName.ORDER_ITEM_ID));
		Integer productId = Integer.parseInt(resultSet.getString(ColumnName.PRODUCT_ID));
		String title = resultSet.getString(ColumnName.PRODUCT_TITLE);
		Double price = resultSet.getDouble(ColumnName.PRODUCT_PRICE);
		String facture = resultSet.getString(ColumnName.PRODUCT_FACTURE);
		String applicationArea = resultSet.getString(ColumnName.PRODUCT_APPLICATION_AREA);
		Integer imageId = Integer.parseInt(resultSet.getString(ColumnName.IMAGE_ID));
		String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
		Integer orderId = Integer.parseInt(resultSet.getString(ColumnName.ORDER_ID));
		Order order = new Order();
		order.setOrderId(orderId);
		Image image = new Image(imageId, imageName);
		Product product = new Product(productId, title, price, facture, applicationArea, image);
		return new OrderItem(orderItemId, product, order);
	}

}
