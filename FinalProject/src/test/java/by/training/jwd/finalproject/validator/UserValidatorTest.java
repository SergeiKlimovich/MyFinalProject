package by.training.jwd.finalproject.validator;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class UserValidatorTest {
	@Test
	public void isPasswordValidTestPositive() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();

		map.put("qwert1", Boolean.TRUE);
		map.put("123456789qwerty", Boolean.TRUE);
		map.put("12345y", Boolean.TRUE);

		map.forEach((password, expected) -> Assert.assertEquals(expected, UserValidator.isPasswordValid(password)));

	}

	@Test
	public void isPasswordValidTestNegative() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();

		map.put("-.,!#$&%^*1a", Boolean.FALSE);
		map.put("0", Boolean.FALSE);
		map.put("-1234567890qwerty1", Boolean.FALSE);
		map.put("   ", Boolean.FALSE);

		map.forEach((password, expected) -> Assert.assertEquals(expected, UserValidator.isPasswordValid(password)));
	}

	@Test
	public void isEmailValidTestPositive() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();

		map.put("qwer1@mail.ru", Boolean.TRUE);
		map.put("1@gmail.com", Boolean.TRUE);
		map.put("egor.letov@tut.by", Boolean.TRUE);

		map.forEach((email, expected) -> Assert.assertEquals(expected, UserValidator.isEmailValid(email)));

	}

	@Test
	public void isEmailValidTestNegative() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();

		map.put(",.@gmail.com", Boolean.FALSE);
		map.put("0", Boolean.FALSE);
		map.put("123@mailru", Boolean.FALSE);
		map.put("123mail.ru", Boolean.FALSE);

		map.forEach((email, expected) -> Assert.assertEquals(expected, UserValidator.isEmailValid(email)));

	}
}
