package by.training.jwd.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.training.jwd.finalproject.dao.BasketDAO;
import by.training.jwd.finalproject.dao.DAOProvider;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.entity.Basket;
import by.training.jwd.finalproject.entity.Product;
import by.training.jwd.finalproject.entity.User;
import by.training.jwd.finalproject.service.BasketService;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.validator.BasketValidator;
import by.training.jwd.finalproject.validator.UserValidator;

/**
 * The {@code BasketServiceImpl} class represents basket service implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class BasketServiceImpl implements BasketService {
	DAOProvider provider = DAOProvider.getInstance();
	BasketDAO basketDAO = provider.getBasketDAO();

	@Override
	public boolean addBasket(String userId, String productId) throws ServiceException {
		boolean result = false;
		try {
			if (UserValidator.isIdValid(userId) && BasketValidator.isIdValid(productId)) {
				Integer productIdParsed = Integer.parseInt(productId);
				Integer userIdParsed = Integer.parseInt(userId);
				User user = new User();
				user.setUserId(userIdParsed);
				Product product = new Product();
				product.setProductId(productIdParsed);
				Basket basket = new Basket();
				basket.setUser(user);
				basket.setProduct(product);
				result = basketDAO.add(basket);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public double calculateTotalPrice(List<Basket> basketList) {
		double totalPrice = 0;
		for (Basket basket : basketList) {
			totalPrice += basket.getProduct().getPrice();
		}
		double scale = Math.pow(10, 2);
		return Math.ceil(totalPrice * scale) / scale;
	}

	@Override
	public boolean removeBasket(String userId, String productId) throws ServiceException {
		boolean result = false;
		try {
			if (UserValidator.isIdValid(userId) && BasketValidator.isIdValid(productId)) {
				Integer productIdValue = Integer.parseInt(productId);
				Integer userIdParsed = Integer.parseInt(userId);
				Product product = new Product();
				product.setProductId(productIdValue);
				User user = new User();
				user.setUserId(userIdParsed);
				Basket basket = new Basket();
				basket.setUser(user);
				basket.setProduct(product);
				result = basketDAO.remove(basket);
			}
		} catch (DAOException e) {
			throw new ServiceException("Error while removing basket", e);
		}
		return result;
	}

	@Override
	public List<Basket> findBasketByUserId(String id) throws ServiceException {
		List<Basket> basketList = new ArrayList<>();
		try {
			if (BasketValidator.isIdValid(id)) {
				int userId = Integer.parseInt(id);
				basketList = basketDAO.findBasketByUserId(userId);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return basketList;
	}
}
