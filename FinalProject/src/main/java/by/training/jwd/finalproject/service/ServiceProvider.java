package by.training.jwd.finalproject.service;

import by.training.jwd.finalproject.service.impl.BasketServiceImpl;
import by.training.jwd.finalproject.service.impl.OrderItemServiceImpl;
import by.training.jwd.finalproject.service.impl.OrderServiceImpl;
import by.training.jwd.finalproject.service.impl.ProductServiceImpl;
import by.training.jwd.finalproject.service.impl.UserServiceImpl;

/**
 * The {@code ServiceProvider} represents a singleton.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private ServiceProvider() {

	}

	private final UserService userService = new UserServiceImpl();
	private final ProductService productService = new ProductServiceImpl();
	private final OrderService orderService = new OrderServiceImpl();
	private final OrderItemService orderItemService = new OrderItemServiceImpl();
	private final BasketService basketService = new BasketServiceImpl();

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public OrderItemService getOrderItemService() {
		return orderItemService;
	}

	public BasketService getBasketService() {
		return basketService;
	}

}
