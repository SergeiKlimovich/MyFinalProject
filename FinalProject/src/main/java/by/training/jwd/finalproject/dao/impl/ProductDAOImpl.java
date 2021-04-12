package by.training.jwd.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.training.jwd.finalproject.dao.ColumnName;
import by.training.jwd.finalproject.dao.ProductDAO;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.dao.pool.ConnectionPool;
import by.training.jwd.finalproject.dao.pool.ConnectionPoolException;
import by.training.jwd.finalproject.entity.Image;
import by.training.jwd.finalproject.entity.Product;

/**
 * The {@code ProductDAOImpl} class represents productDAO implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ProductDAOImpl implements ProductDAO {
	
	private static final String ADD_PRODUCT = "INSERT INTO products (title, price, facture, application_area, image_id_fk) VALUES (?,?,?,?,?)";
	private static final String UPDATE_PRODUCT = "UPDATE products SET title = ?, price = ?, facture= ?, application_area= ? WHERE product_id = ?";
	private static final String FIND_BY_ID = "SELECT product_id, title, price, facture, application_area, image_id, image_name FROM products "
			+ "INNER JOIN images ON products.image_id_fk = images.image_id WHERE product_id = ?";
	private static final String FIND_ALL = "SELECT product_id, title, price, facture, application_area, image_id, image_name FROM products "
			+ "INNER JOIN images ON products.image_id_fk = images.image_id";
	private static final String FIND_PRODUCTS_BY_SEARCH_QUERY = "SELECT product_id, title, price, facture, application_area, image_id, image_name "
			+ "FROM products INNER JOIN images ON products.image_id_fk = images.image_id WHERE concat(title, price) LIKE ?";
	private static final String PERCENT = "%";

	@Override
	public boolean add(Product product, Connection connection) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
			statement.setString(1, product.getTitle());
			statement.setDouble(2, product.getPrice());
			statement.setString(3, product.getFacture());
			statement.setString(4, product.getApplicationArea());
			statement.setInt(5, product.getImage().getImageId());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean update(Product product) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
			statement.setString(1, product.getTitle());
			statement.setDouble(2, product.getPrice());
			statement.setString(3, product.getFacture());
			statement.setString(4, product.getApplicationArea());
			statement.setInt(5, product.getProductId());
			return statement.executeUpdate() > 0;

		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Optional<Product> findById(Integer productId) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setInt(1, productId);
			ResultSet resultSet = statement.executeQuery();
			Optional<Product> productOptional = Optional.empty();
			if (resultSet.next()) {
				Product product = makeProductByResultSet(resultSet);
				productOptional = Optional.of(product);
			}
			return productOptional;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<Product> findAll() throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
			ResultSet resultSet = statement.executeQuery();
			List<Product> productList = new ArrayList<>();
			while (resultSet.next()) {
				Product product = makeProductByResultSet(resultSet);
				productList.add(product);
			}
			return productList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<Product> findBySearchQuery(String searchQuery) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_PRODUCTS_BY_SEARCH_QUERY)) {
			statement.setString(1, PERCENT + searchQuery + PERCENT);
			ResultSet resultSet = statement.executeQuery();
			List<Product> productList = new ArrayList<>();
			while (resultSet.next()) {
				Product product = makeProductByResultSet(resultSet);
				productList.add(product);
			}
			return productList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	private Product makeProductByResultSet(ResultSet resultSet) throws SQLException {
		Integer productId = Integer.parseInt(resultSet.getString(ColumnName.PRODUCT_ID));
		String title = resultSet.getString(ColumnName.PRODUCT_TITLE);
		double price = resultSet.getDouble(ColumnName.PRODUCT_PRICE);
		String facture = resultSet.getString(ColumnName.PRODUCT_FACTURE);
		String applicationArea = resultSet.getString(ColumnName.PRODUCT_APPLICATION_AREA);
		Integer imageId = Integer.parseInt(resultSet.getString(ColumnName.IMAGE_ID));
		String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
		Image image = new Image(imageId, imageName);
		return new Product(productId, title, price, facture, applicationArea, image);
	}
}
