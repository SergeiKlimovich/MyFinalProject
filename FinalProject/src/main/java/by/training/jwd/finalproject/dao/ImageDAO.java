package by.training.jwd.finalproject.dao;

import java.sql.Connection;

import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.entity.Image;

/**
 * The {@code ImageDAO} interface used to access database image data.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 * 
 */
public interface ImageDAO {
	/**
	 * Add image.
	 *
	 * @param image the image
	 * @return true if adding success
	 * @throws DAOException if occurred problem in database
	 */
	boolean add(Image image, Connection connection) throws DAOException;
}
