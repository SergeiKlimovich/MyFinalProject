package by.training.jwd.finalproject.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.dao.DAOProvider;
import by.training.jwd.finalproject.dao.UserDAO;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.entity.User;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.UserService;
import by.training.jwd.finalproject.util.Cipher;
import by.training.jwd.finalproject.util.MailSender;
import by.training.jwd.finalproject.util.SendMailException;
import by.training.jwd.finalproject.validator.UserValidator;

/**
 * The {@code UserServiceImpl} class represents user service implementation.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
	private static final String EMPTY_VALUE = "";

	DAOProvider provider = DAOProvider.getInstance();
	UserDAO userDAO = provider.getUserDAO();

	@Override
	public boolean changePassword(String email, String password, String passwordRepeat) throws ServiceException {
		boolean result = false;
		try {
			if (UserValidator.isEmailValid(email) && UserValidator.isPasswordValid(password)
					&& password.equals(passwordRepeat)) {
				Cipher cipher = new Cipher();
				String encryptedPassword = cipher.encrypt(password);
				result = userDAO.changePassword(email, encryptedPassword);
			}
		} catch (DAOException | NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public Optional<User> findUserById(String id) throws ServiceException {
		Optional<User> optionalUser = Optional.empty();
		try {
			if (UserValidator.isIdValid(id)) {
				int userId = Integer.parseInt(id);
				optionalUser = userDAO.findById(userId);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return optionalUser;
	}

	@Override
	public Optional<User> findUserByEmail(String email) throws ServiceException {
		Optional<User> optionalUser;
		try {
			optionalUser = userDAO.findByEmail(email);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return optionalUser;
	}

	public Optional<User> findUserByAccessCode(String code, List<User> userList) throws ServiceException {
		Optional<User> optionalUser = Optional.empty();
		Cipher cipher = new Cipher();
		try {
			for (User user : userList) {
				if (cipher.encrypt(user.getEmail()).equals(code)) {
					optionalUser = Optional.of(user);
					break;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		}
		return optionalUser;
	}

	public List<User> findUsersBySearchQuery(String searchQuery) throws ServiceException {
		try {
			return userDAO.findBySearchQuery(searchQuery);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean removeUser(String email) throws ServiceException {
		boolean isRemoved = false;
		try {
			if (UserValidator.isEmailValid(email)) {
				isRemoved = userDAO.remove(email);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return isRemoved;
	}

	@Override
	public boolean blockUser(String email) throws ServiceException {
		boolean isBlocked = false;
		try {
			if (UserValidator.isEmailValid(email)) {
				isBlocked = userDAO.block(email);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return isBlocked;
	}

	@Override
	public boolean unblockUser(String email) throws ServiceException {
		boolean isUnblocked = false;
		try {
			if (UserValidator.isEmailValid(email)) {
				isUnblocked = userDAO.unblock(email);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return isUnblocked;
	}

	public boolean fillUpBalance(String userId, String moneyAmount) throws ServiceException {
		boolean result = false;
		try {
			if (UserValidator.isIdValid(userId) && UserValidator.isPriceValid(moneyAmount)) {
				double amount = Double.parseDouble(moneyAmount);
				int userIdParsed = Integer.parseInt(userId);
				Optional<User> optionalUser = userDAO.findById(userIdParsed);
				if (optionalUser.isPresent()) {
					User user = optionalUser.get();
					double newBalance = user.getBalance() + amount;
					user.setBalance(newBalance);
					userDAO.update(user);
					result = true;
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;

	}

	public void sendLetter(User user, String url) throws ServiceException {
		try {
			Cipher cipher = new Cipher();
			String code = cipher.encrypt(user.getEmail());
			MailSender sender = new MailSender(user.getEmail(), url, code);
			sender.send();

		} catch (SendMailException | NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean activateUser(String email) throws ServiceException {
		boolean result = false;
		try {
			if (UserValidator.isEmailValid(email)) {
				Optional<User> optionalUser = userDAO.findByEmail(email);
				if (optionalUser.isPresent()) {
					User user = optionalUser.get();
					user.setStatus(User.Status.ENABLE);
					if (userDAO.update(user)) {
						result = true;
					}
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public List<User> findAllUsers() throws ServiceException {
		try {
			return userDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> findAllUsersNotAdmin() throws ServiceException {
		try {
			return userDAO.findAllNotAdmin();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isUserExists(String email, String password) throws ServiceException {
		boolean result = false;
		try {
			if (UserValidator.isEmailValid(email) && UserValidator.isPasswordValid(password)) {
				Cipher cipher = new Cipher();
				String encryptedPassword = cipher.encrypt(password);
				Optional<User> optionalUser = userDAO.findByEmailAndPassword(email, encryptedPassword);
				result = optionalUser.isPresent();
			}
		} catch (DAOException | NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public boolean addUser(Map<String, String> parameters) throws ServiceException {
		boolean result = false;
		String email = parameters.get(RequestParameter.EMAIL);
		String password = parameters.get(RequestParameter.PASSWORD);
		String name = parameters.get(RequestParameter.NAME);
		String surname = parameters.get(RequestParameter.SURNAME);
		String patronymic = parameters.get(RequestParameter.PATRONYMIC);
		if (UserValidator.isRegistrationParametersCorrect(parameters)) {
			if (isEmailFree(email)) {
				try {
					String encryptedPassword = Cipher.encrypt(password);
					User user = new User();
					user.setEmail(email);
					user.setName(name);
					user.setSurname(surname);
					user.setPatronymic(patronymic);
					user.setStatus(User.Status.NOT_CONFIRMED);
					user.setRole(User.Role.USER);
					user.setBalance(0);
					result = userDAO.add(user, encryptedPassword);
				} catch (DAOException | NoSuchAlgorithmException e) {
					throw new ServiceException("Error while adding user", e);
				}
			} else {
				parameters.put(RequestParameter.EMAIL, EMPTY_VALUE);
			}
		}
		return result;
	}

	private boolean isEmailFree(String email) throws ServiceException {
		boolean result = false;
		try {
			if (UserValidator.isEmailValid(email)) {
				Optional<User> optionalUser = userDAO.findByEmail(email);

				result = (!optionalUser.isPresent());
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}
}
