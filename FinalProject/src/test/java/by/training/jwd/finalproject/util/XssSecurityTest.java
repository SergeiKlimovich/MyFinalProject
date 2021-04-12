package by.training.jwd.finalproject.util;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class XssSecurityTest {
	@Test
	public void encryptTestPositive() throws NoSuchAlgorithmException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Word", "Word");
		map.put("<script>", "");
		map.put("<script>word</script>", "word");

		map.forEach((password, expected) -> {

			Assert.assertEquals(XssSecurity.secure(password), expected);

		});
	}

	@Test
	public void encryptTestNegative() throws NoSuchAlgorithmException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Word", "");
		map.put("<script>", " ");
		map.put("<script>hello</script>", "<script>word");

		map.forEach((password, expected) -> {

			Assert.assertNotEquals(XssSecurity.secure(password), expected);

		});
	}
}
