package by.training.jwd.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.training.jwd.finalproject.dao.ColumnName;
import by.training.jwd.finalproject.dao.UserDAO;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.dao.pool.ConnectionPool;
import by.training.jwd.finalproject.dao.pool.ConnectionPoolException;
import by.training.jwd.finalproject.entity.User;

/**
 * The {@code UserDAOImpl} class represents userDAO implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class UserDAOImpl implements UserDAO {
	
	
	private static final String FIND_BY_ID = "SELECT user_id, email, name, surname, patronymic, role, status, balance "
			+ "FROM users WHERE user_id = ?";

	private static final String DELETE_USER_BY_EMAIL = "DELETE FROM users where email = ?";

	private static final String UPDATE_USER = "UPDATE users SET email = ?, name = ?, surname = ?, patronymic = ?, role = ?, "
			+ "status = ?, balance = ? WHERE user_id = ?";

	private static final String ADD_USER = "INSERT INTO users (email, password, name, surname, patronymic, role, status, balance) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String FIND_USER_BY_EMAIL = "SELECT user_id, email, password, name, surname, patronymic, role, status, balance "
			+ "FROM users WHERE email = ?";

	private static final String CHANGE_USER_PASSWORD = "UPDATE users SET password = ? WHERE email = ?";

	private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT user_id, email, name, surname, patronymic, role, status, balance "
			+ "FROM users WHERE email = ? AND password = ?";

	private static final String FIND_ALL_USERS = "SELECT user_id, email, name, surname, patronymic, role, status, balance "
			+ "FROM users";
	
	private static final String FIND_ALL_USERS_NOT_ADMIN = "SELECT user_id, email, name, surname, patronymic, role, status, balance "
			+ "FROM users WHERE role != \"ADMIN\"";

	private static final String BLOCK_USER = "UPDATE users SET status = \"BLOCKED\" WHERE email = ?";

	private static final String UNBLOCK_USER = "UPDATE users SET status = \"ENABLE\" WHERE email = ?";

	private static final String FIND_USERS_BY_SEARCH_QUERY = "SELECT user_id, email, name, surname, patronymic, role, status, balance "
			+ "FROM users 	WHERE concat(user_id, email, name, surname, patronymic, role, status, balance) LIKE ?";

	private static final String PERCENT = "%";

	@Override
	public boolean remove(String email) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_EMAIL)) {
			statement.setString(1, email);
			return statement.executeUpdate() > 0;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public boolean add(User user, String encryptedPassword) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
			statement.setString(1, user.getEmail());
			statement.setString(2, encryptedPassword);
			statement.setString(3, user.getName());
			statement.setString(4, user.getSurname());
			statement.setString(5, user.getPatronymic());
			statement.setString(6, user.getRole().toString());
			statement.setNString(7, user.getStatus().toString());
			statement.setDouble(8, user.getBalance());
			return statement.executeUpdate() > 0;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public boolean update(User user) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getName());
			statement.setString(3, user.getSurname());
			statement.setString(4, user.getPatronymic());
			statement.setString(5, String.valueOf(user.getRole()));
			statement.setString(6, String.valueOf(user.getStatus()));
			statement.setDouble(7, user.getBalance());
			statement.setInt(8, user.getUserId());

			return statement.executeUpdate() > 0;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Optional<User> findById(Integer userId) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			Optional<User> userOptional = Optional.empty();
			if (resultSet.next()) {
				User user = makeUserByResultSet(resultSet);
				userOptional = Optional.of(user);
			}
			return userOptional;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);

		}

	}

	@Override
	public List<User> findBySearchQuery(String searchQuery) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_SEARCH_QUERY)) {
			statement.setString(1, PERCENT + searchQuery + PERCENT);
			ResultSet resultSet = statement.executeQuery();
			List<User> userList = new ArrayList<>();
			while (resultSet.next()) {
				User user = makeUserByResultSet(resultSet);
				userList.add(user);
			}
			return userList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Optional<User> findByEmail(String email) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			Optional<User> userOptional = Optional.empty();
			if (resultSet.next()) {
				User user = makeUserByResultSet(resultSet);
				userOptional = Optional.of(user);
			}
			return userOptional;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public boolean changePassword(String email, String password) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(CHANGE_USER_PASSWORD)) {
			statement.setString(1, password);
			statement.setString(2, email);
			return statement.executeUpdate() > 0;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			Optional<User> userOptional = Optional.empty();
			if (resultSet.next()) {
				User user = makeUserByResultSet(resultSet);
				userOptional = Optional.of(user);
			}
			return userOptional;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public List<User> findAllNotAdmin() throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_NOT_ADMIN)) {
			ResultSet resultSet = statement.executeQuery();
			List<User> userList = new ArrayList<>();
			while (resultSet.next()) {
				User user = makeUserByResultSet(resultSet);
				userList.add(user);
			}
			return userList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}
	
	
	
	
	@Override
	public List<User> findAll() throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS)) {
			ResultSet resultSet = statement.executeQuery();
			List<User> userList = new ArrayList<>();
			while (resultSet.next()) {
				User user = makeUserByResultSet(resultSet);
				userList.add(user);
			}
			return userList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean block(String email) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(BLOCK_USER)) {
			statement.setString(1, email);
			return statement.executeUpdate() > 0;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean unblock(String email) throws DAOException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UNBLOCK_USER)) {
			statement.setString(1, email);
			return statement.executeUpdate() > 0;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	private User makeUserByResultSet(ResultSet resultSet) throws SQLException {
		Integer userId = Integer.parseInt(resultSet.getString(ColumnName.USER_ID));
		String email = resultSet.getString(ColumnName.USER_EMAIL);
		String name = resultSet.getString(ColumnName.USER_NAME);
		String surname = resultSet.getString(ColumnName.USER_SURNAME);
		String patronymic = resultSet.getString(ColumnName.USER_PATRONYMIC);
		Double balance = resultSet.getDouble(ColumnName.USER_BALANCE);
		User.Role role = User.Role.valueOf(resultSet.getString(ColumnName.USER_ROLE));
		User.Status status = User.Status.valueOf(resultSet.getString(ColumnName.USER_STATUS));

		return new User(userId, name, surname, patronymic, email, role, status, balance);
	}
}
