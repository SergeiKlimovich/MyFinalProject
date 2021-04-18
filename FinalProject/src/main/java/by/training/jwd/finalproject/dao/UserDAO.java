package by.training.jwd.finalproject.dao;

import java.util.List;
import java.util.Optional;

import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.entity.User;

/**
 * The {@code UserDAO} interface used to access database user data.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 * 
 */
public interface UserDAO {
	/**
	 * Remove user.
	 *
	 * @param email the email
	 * @return true if removing success
	 * @throws DAOException if occurred problem in database
	 */
	boolean remove(String email) throws DAOException;

	/**
	 * Add user.
	 *
	 * @param user              the user
	 * @param encryptedPassword the encrypted password
	 * @return true if adding success
	 * @throws DAOException if occurred problem in database
	 */
	boolean add(User user, String encryptedPassword) throws DAOException;

	/**
	 * Update user.
	 *
	 * @param user the user
	 * @return true if updating success
	 * @throws DAOException if occurred problem in database
	 */
	boolean update(User user) throws DAOException;

	/**
	 * Find user by id.
	 *
	 * @param userId the user index
	 * @return the optional of user
	 * @throws DAOException if occurred problem in database
	 */
	Optional<User> findById(Integer userId) throws DAOException;

	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the optional of user
	 * @throws DAOException if occurred problem in database
	 */
	Optional<User> findByEmail(String email) throws DAOException;

	/**
	 * Change password.
	 *
	 * @param email    the email
	 * @param password the password
	 * @return true if changing success
	 * @throws DAOException if occurred problem in database
	 */
	boolean changePassword(String email, String password) throws DAOException;

	/**
	 * Find user by email and password.
	 *
	 * @param email    the email
	 * @param password the password
	 * @return the optional of user
	 * @throws DAOException if occurred problem in database
	 */
	Optional<User> findByEmailAndPassword(String email, String password) throws DAOException;

	/**
	 * Find all users.
	 *
	 * @return the list users
	 * @throws DAOException if occurred problem in database
	 */
	public List<User> findAll() throws DAOException;

	/**
	 * Find all users which not admin.
	 *
	 * @return the list users
	 * @throws DAOException if occurred problem in database
	 */

	public List<User> findAllNotAdmin() throws DAOException;

	/**
	 * Block user.
	 *
	 * @param email the email
	 * @return true if blocking success
	 * @throws DAOException if occurred problem in database
	 */

	boolean block(String email) throws DAOException;

	/**
	 * Unblock user.
	 *
	 * @param email the email
	 * @return true if unblocking success
	 * @throws DAOException if occurred problem in database
	 */
	boolean unblock(String email) throws DAOException;

	/**
	 * Find by search query.
	 *
	 * @param searchQuery the search query
	 * @return the list of users
	 * @throws DAOException if occurred problem in database
	 */
	List<User> findBySearchQuery(String searchQuery) throws DAOException;
}
