package by.training.jwd.finalproject.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.training.jwd.finalproject.controller.RequestParameter;
import by.training.jwd.finalproject.dao.UserDAO;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.dao.impl.UserDAOImpl;
import by.training.jwd.finalproject.entity.User;
import by.training.jwd.finalproject.service.ServiceException;
import by.training.jwd.finalproject.service.UserService;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
	private UserDAO userDAO;
	private UserService userService;

	@Before
	public void setUp() {
		userDAO = mock(UserDAOImpl.class);
		userService = new UserServiceImpl();
	}

	@After
	public void tearDown() {
		userDAO = null;
		userService = null;
	}

	@Test
	public void addUserPositiveTest() throws DAOException, ServiceException {

		when(userDAO.findByEmail(any(String.class))).thenReturn(Optional.empty());
		when(userDAO.add(any(User.class), any(String.class))).thenReturn(true);
		Map<String, String> actual = new HashMap<>();
		actual.put(RequestParameter.EMAIL, "cikeyi4704@684hh.com");
		actual.put(RequestParameter.NAME, "Name");
		actual.put(RequestParameter.SURNAME, "Surname");
		actual.put(RequestParameter.PATRONYMIC, "Patronymic");
		actual.put(RequestParameter.PASSWORD, "12345a");
		actual.put(RequestParameter.PASSWORD_REPEAT, "12345a");
		Map<String, String> expected = new HashMap<>();
		expected.put(RequestParameter.EMAIL, "cikeyi4704@684hh.com");
		expected.put(RequestParameter.NAME, "Name");
		expected.put(RequestParameter.SURNAME, "Surname");
		expected.put(RequestParameter.PATRONYMIC, "Patronymic");
		expected.put(RequestParameter.PASSWORD, "12345a");
		expected.put(RequestParameter.PASSWORD_REPEAT, "12345a");
		userService.addUser(actual);
		Assert.assertEquals(actual, expected);

	}

	@Test
	public void changePasswordPositiveTest() throws DAOException, ServiceException {

		when(userDAO.changePassword(any(String.class), any(String.class))).thenReturn(true);
		String email = "cikeyi4704@684hh.com";
		String password = "sergei1984";
		String passwordRepeat = "sergei1984";
		boolean actual = userService.changePassword(email, password, passwordRepeat);
		Assert.assertTrue(actual);

	}

	@Test
	public void removePositiveTest() throws DAOException, ServiceException {

		when(userDAO.remove(any(String.class))).thenReturn(true);
		boolean actual = userService.removeUser("cikeyi4704@684hh.com");
		Assert.assertTrue(actual);

	}

	@Test
	public void blockPositiveTest() throws DAOException, ServiceException {

		when(userDAO.block(any(String.class))).thenReturn(true);
		boolean actual = userService.blockUser("sopaha7288@684hh.com");
		Assert.assertTrue(actual);

	}

	@Test
	public void unblockPositiveTest() throws DAOException, ServiceException {

		when(userDAO.unblock(any(String.class))).thenReturn(true);
		boolean actual = userService.unblockUser("sopaha7288@684hh.com");
		Assert.assertTrue(actual);

	}

	@Test
	public void fillUpBalancePositiveTest() throws DAOException, ServiceException {

		when(userDAO.findById(any(Integer.class))).thenReturn(Optional.of(new User()));
		when(userDAO.update(any(User.class))).thenReturn(true);
		boolean actual = userService.fillUpBalance("1", "100");
		Assert.assertTrue(actual);

	}

	@Test
	public void activateUserPositiveTest() throws DAOException, ServiceException {

		when(userDAO.findByEmail(any(String.class))).thenReturn(Optional.of(new User()));
		when(userDAO.update(any(User.class))).thenReturn(true);
		boolean actual = userService.activateUser("140184sk@gmail.com");
		Assert.assertTrue(actual);

	}

	@Test
	public void isUserExistsPositiveTest() throws DAOException, ServiceException {

		when(userDAO.findByEmailAndPassword(any(String.class), any(String.class))).thenReturn(Optional.of(new User()));
		boolean actual = userService.isUserExists("sibox49526@art2427.com", "sergei1984");
		Assert.assertTrue(actual);

	}

}
