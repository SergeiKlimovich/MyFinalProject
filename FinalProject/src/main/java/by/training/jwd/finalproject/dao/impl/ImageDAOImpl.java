package by.training.jwd.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.training.jwd.finalproject.dao.ImageDAO;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.entity.Image;

/**
 * The {@code ImageDAOImpl} class represents imageDAO implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ImageDAOImpl implements ImageDAO {

	private static final String ADD_IMAGE = "INSERT INTO images (image_name) VALUES (?)";

	@Override
	public boolean add(Image image, Connection connection) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(ADD_IMAGE, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, image.getName());
			boolean result = statement.executeUpdate() > 0;
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				image.setImageId(resultSet.getInt(1));
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
