package by.training.jwd.finalproject.entity;

import java.io.Serializable;

/**
 * The {@code OrderItem} class represents OrderItem entity.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 */
public class OrderItem implements Serializable {

	private static final long serialVersionUID = -4357328449842944691L;

	private Integer orderItemId;
	private Product product;
	private Order order;

	/**
	 * Instantiates a new OrderItem.
	 */
	public OrderItem() {

	}

	/**
	 * Instantiates a new OrderItem.
	 *
	 * @param orderItemId a orderItem index
	 * @param product     a product
	 * @param order       a order
	 * 
	 */
	public OrderItem(Integer orderItemId, Product product, Order order) {
		super();
		this.orderItemId = orderItemId;
		this.product = product;
		this.order = order;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((orderItemId == null) ? 0 : orderItemId.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (orderItemId == null) {
			if (other.orderItemId != null)
				return false;
		} else if (!orderItemId.equals(other.orderItemId))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[orderItemId=" + orderItemId + ", product=" + product + ", order=" + order + "]";
	}

}
