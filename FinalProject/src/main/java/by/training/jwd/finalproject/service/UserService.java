package by.training.jwd.finalproject.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.training.jwd.finalproject.entity.User;

/**
 * The {@code UserService} represents an interface of a service providing
 * user-related actions.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public interface UserService {
	/**
	 * Check is user exists.
	 *
	 * @param email    the email
	 * @param password the password
	 * @return true if existing success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean isUserExists(String email, String password) throws ServiceException;

	/**
	 * Fill up user balance.
	 *
	 * @param userId      the user index
	 * @param moneyAmount the amount of money
	 * @return true if fillUping success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean fillUpBalance(String userId, String moneyAmount) throws ServiceException;

	/**
	 * Send letter to user mail.
	 *
	 * @param user the user
	 * @param url  the url
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	void sendLetter(User user, String url) throws ServiceException;

	/**
	 * Activate user.
	 *
	 * @param email the email
	 * @return true if activating success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean activateUser(String email) throws ServiceException;

	/**
	 * Find all users.
	 *
	 * @return the list of users
	 * @throws ServiceException the service exception on an underlying level
	 */
	List<User> findAllUsers() throws ServiceException;

	/**
	 * Find all users which not admin.
	 *
	 * @return the list of users
	 * @throws ServiceException the service exception on an underlying level
	 */

	List<User> findAllUsersNotAdmin() throws ServiceException;

	/**
	 * Find user by search query.
	 *
	 * @param searchQuery the search query
	 * @return the list of users
	 * @throws ServiceException the service exception on an underlying level
	 */
	List<User> findUsersBySearchQuery(String searchQuery) throws ServiceException;

	/**
	 * Change user password.
	 *
	 * @param email          the email
	 * @param password       the password
	 * @param passwordRepeat the password repeat
	 * @return true if changing success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean changePassword(String email, String password, String passwordRepeat) throws ServiceException;

	/**
	 * Find user by id.
	 *
	 * @param userId the user index
	 * @return the optional of user
	 * @throws ServiceException the service exception on an underlying level
	 */
	Optional<User> findUserById(String userId) throws ServiceException;

	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the optional of user
	 * @throws ServiceException the service exception on an underlying level
	 */
	Optional<User> findUserByEmail(String email) throws ServiceException;

	/**
	 * Find user by access code.
	 *
	 * @param code     the code
	 * @param userList the user list
	 * @return the optional of user
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	Optional<User> findUserByAccessCode(String code, List<User> userList) throws ServiceException;

	/**
	 * Remove user.
	 *
	 * @param email the email
	 * @return true if removing success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean removeUser(String email) throws ServiceException;

	/**
	 * Block user.
	 *
	 * @param email the email
	 * @return true if blocking success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean blockUser(String email) throws ServiceException;

	/**
	 * Unblock user.
	 *
	 * @param email the email
	 * @return true if unblocking success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean unblockUser(String email) throws ServiceException;

	/**
	 * Add user.
	 *
	 * @param parameters the parameters
	 * @return true if adding success
	 * @throws ServiceException if occurred problem on an underlying level
	 */
	boolean addUser(Map<String, String> parameters) throws ServiceException;
}
