package by.training.jwd.finalproject.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The {@code Order} class represents Order entity.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 8715929578563323215L;

	private Integer orderId;
	private LocalDate creationDate;
	private LocalDate closingDate;
	private Status status;
	private User user;

	/**
	 * Instantiates a new Order.
	 */
	public Order() {

	}

	/**
	 * Instantiates a new Order.
	 *
	 * @param orderId      a order index
	 * @param creationDate a creationDate
	 * @param closingDate  a closingDate
	 * @param status       a status
	 * @param user         a user
	 */
	public Order(Integer orderId, LocalDate creationDate, LocalDate closingDate, Status status, User user) {
		super();
		this.orderId = orderId;
		this.creationDate = creationDate;
		this.closingDate = closingDate;
		this.status = status;
		this.user = user;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((closingDate == null) ? 0 : closingDate.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Order other = (Order) obj;
		if (closingDate == null) {
			if (other.closingDate != null)
				return false;
		} else if (!closingDate.equals(other.closingDate))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (status != other.status)
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
		return getClass().getName() + "[orderId=" + orderId + ", creationDate=" + creationDate + ", closingDate="
				+ closingDate + ", status=" + status + ", user=" + user + "]";
	}

	public enum Status {
		UNDER_CONSIDERATION, DENIED, PRODUCED;
	}
}
