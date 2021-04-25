package by.training.jwd.finalproject.entity;

import java.io.Serializable;

/**
 * The {@code User} class represents User entity.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 */

public class User implements Serializable {

	private static final long serialVersionUID = -7842997693805908088L;

	private Integer userId;
	private String name;
	private String surname;
	private String patronymic;
	private String email;
	private Role role;
	private Status status;
	private double balance;

	/**
	 * Instantiates a new User.
	 */
	public User() {
	}

	/**
	 * Instantiates a new User.
	 *
	 * @param userId     a user index
	 * @param name       a name
	 * @param surname    a surname
	 * @param patronymic a patronymic
	 * @param email      a email
	 * @param role       a role
	 * @param status     a status
	 * @param balance    a balance
	 */
	public User(Integer userId, String name, String surname, String patronymic, String email, Role role, Status status,
			double balance) {
		super();
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.email = email;
		this.role = role;
		this.status = status;
		this.balance = balance;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((patronymic == null) ? 0 : patronymic.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (patronymic == null) {
			if (other.patronymic != null)
				return false;
		} else if (!patronymic.equals(other.patronymic))
			return false;
		if (role != other.role)
			return false;
		if (status != other.status)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[userId=" + userId + ", name=" + name + ", surname=" + surname + ", patronymic="
				+ patronymic + ", email=" + email + ", role=" + role + ", status=" + status + ", balance=" + balance
				+ "]";
	}

	public enum Role {
		GUEST, USER, ADMIN
	}

	public enum Status {
		ENABLE, BLOCKED, NOT_CONFIRMED;
	}

}
