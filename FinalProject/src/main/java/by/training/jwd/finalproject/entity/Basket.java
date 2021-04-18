package by.training.jwd.finalproject.entity;

import java.io.Serializable;

/**
 * The {@code Basket} class represents Basket entity.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 */
public class Basket implements Serializable {

	private static final long serialVersionUID = -4449751090369972488L;
	private Integer basketId;
	private User user;
	private Product product;

	/**
	 * Instantiates a new Basket.
	 */
	public Basket() {

	}

	/**
	 * Instantiates a new Basket.
	 *
	 * @param basketId a basket index
	 * @param user     a user
	 * @param product  a product
	 * 
	 */
	public Basket(Integer basketId, User user, Product product) {
		super();
		this.basketId = basketId;
		this.user = user;
		this.product = product;
	}

	public Integer getBasketId() {
		return basketId;
	}

	public void setBasketId(Integer basketId) {
		this.basketId = basketId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basketId == null) ? 0 : basketId.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Basket other = (Basket) obj;
		if (basketId == null) {
			if (other.basketId != null)
				return false;
		} else if (!basketId.equals(other.basketId))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[basketId=" + basketId + ", user=" + user + ", product=" + product + "]";
	}

}
