package by.training.jwd.finalproject.dao;

import by.training.jwd.finalproject.dao.impl.ImageDAOImpl;
import by.training.jwd.finalproject.dao.impl.OrderDAOImpl;
import by.training.jwd.finalproject.dao.impl.OrderItemDAOImpl;
import by.training.jwd.finalproject.dao.impl.ProductDAOImpl;
import by.training.jwd.finalproject.dao.impl.BasketDAOImpl;
import by.training.jwd.finalproject.dao.impl.UserDAOImpl;
/**
 * The {@code DAOProvider} represents a singleton.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();

	private final UserDAO userDAO = new UserDAOImpl();
	private final OrderDAO orderDAO = new OrderDAOImpl();
	private final ProductDAO productDAO = new ProductDAOImpl();
	private final BasketDAO basketDAO = new BasketDAOImpl();
	private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
	private final ImageDAO imageDAO = new ImageDAOImpl();

	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public BasketDAO getBasketDAO() {
		return basketDAO;
	}

	public OrderItemDAO getOrderItemDAO() {
		return orderItemDAO;
	}

	public ImageDAO getImageDAO() {
		return imageDAO;
	}

}
