package by.training.jwd.finalproject.dao;

import java.util.List;

import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.entity.Basket;

/**
 * The {@code BasketDAO} interface used to access database basket data.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 * 
 */
public interface BasketDAO {
	/**
	 * Add basket.
	 *
	 * @param basket the basket
	 * @return true if adding success
	 * @throws DAOException if occurred problem in database
	 */
	boolean add(Basket basket) throws DAOException;

	/**
	 * Remove basket.
	 *
	 * @param basket the basket
	 * @return true if removing success
	 * @throws DAOException if occurred problem in database
	 */
	boolean remove(Basket basket) throws DAOException;

	/**
	 * Find basket by user id.
	 *
	 * @param userId the user index
	 * @return the list of basket
	 * @throws DAOException if occurred problem in database
	 */
	List<Basket> findBasketByUserId(Integer userId) throws DAOException;
}