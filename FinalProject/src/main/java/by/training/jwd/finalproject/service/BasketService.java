package by.training.jwd.finalproject.service;

import java.util.List;

import by.training.jwd.finalproject.entity.Basket;

/**
 * The {@code BasketService} represents an interface of a service providing
 * basket-related actions.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public interface BasketService {
	/**
	 * Add basket.
	 *
	 * @param userId    the user index
	 * @param productId the product index
	 * @return true if adding success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean addBasket(String userId, String productId) throws ServiceException;

	/**
	 * Calculate total price of basket.
	 *
	 * @param basketList the basket list
	 * @return the total price
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	double calculateTotalPrice(List<Basket> basketList) throws ServiceException;

	/**
	 * Remove basket.
	 *
	 * @param userId    the user index
	 * @param productId the product index
	 * @return true if removing success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean removeBasket(String userId, String productId) throws ServiceException;

	/**
	 * Find baskets by user index.
	 *
	 * @param userId the user index
	 * @return the list of baskets
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	List<Basket> findBasketByUserId(String userId) throws ServiceException;
}
