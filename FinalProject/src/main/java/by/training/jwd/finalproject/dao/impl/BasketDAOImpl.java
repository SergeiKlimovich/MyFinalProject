package by.training.jwd.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.training.jwd.finalproject.dao.BasketDAO;
import by.training.jwd.finalproject.dao.ColumnName;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.dao.pool.ConnectionPool;
import by.training.jwd.finalproject.dao.pool.ConnectionPoolException;

import by.training.jwd.finalproject.entity.Basket;
import by.training.jwd.finalproject.entity.Image;
import by.training.jwd.finalproject.entity.Product;
import by.training.jwd.finalproject.entity.User;

/**
 * The {@code BasketDAOImpl} class represents basketDAO implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class BasketDAOImpl implements BasketDAO {
	
	private static final String ADD_PRODUCT_TO_BASKET = "INSERT INTO baskets (user_id_fk, product_id_fk) VALUES (?, ?)";
	private static final String FIND_BY_USER_ID = "SELECT basket_id, user_id, product_id, title, price,"
			+ " facture, application_area, image_id, image_name FROM baskets INNER JOIN products ON product_id_fk = product_id "
			+ "INNER JOIN images ON image_id = image_id_fk INNER JOIN users ON user_id_fk = user_id where user_id = ?";
	private static final String REMOVE_PRODUCT_FROM_BASKET = "DELETE FROM baskets WHERE user_id_fk = ? AND product_id_fk = ? LIMIT 1";

	@Override
	public boolean add(Basket basket) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_TO_BASKET)) {
			statement.setInt(1, basket.getUser().getUserId());
			statement.setInt(2, basket.getProduct().getProductId());
			boolean result = statement.executeUpdate() > 0;
			return result;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean remove(Basket basket) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(REMOVE_PRODUCT_FROM_BASKET)) {
			statement.setInt(1, basket.getUser().getUserId());
			statement.setInt(2, basket.getProduct().getProductId());
			boolean result = statement.executeUpdate() > 0;
			return result;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<Basket> findBasketByUserId(Integer userId) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			List<Basket> basketList = new ArrayList<>();
			while (resultSet.next()) {
				Basket basket = makeBasketByResultSet(resultSet);
				basketList.add(basket);
			}
			return basketList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	private Basket makeBasketByResultSet(ResultSet resultSet) throws SQLException {
		Integer basketId = resultSet.getInt(ColumnName.BASKET_ID);
		Integer userId = resultSet.getInt(ColumnName.USER_ID);
		Integer productId = resultSet.getInt(ColumnName.PRODUCT_ID);
		String title = resultSet.getString(ColumnName.PRODUCT_TITLE);
		double price = resultSet.getDouble(ColumnName.PRODUCT_PRICE);
		String facture = resultSet.getString(ColumnName.PRODUCT_FACTURE);
		String applicationArea = resultSet.getString(ColumnName.PRODUCT_APPLICATION_AREA);
		Integer imageId = Integer.parseInt(resultSet.getString(ColumnName.IMAGE_ID));
		String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
		User user = new User();
		user.setUserId(userId);
		Image image = new Image(imageId, imageName);
		Product product = new Product(productId, title, price, facture, applicationArea, image);
		return new Basket(basketId, user, product);
	}
}
