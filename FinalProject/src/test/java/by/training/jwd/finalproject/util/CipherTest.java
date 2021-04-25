package by.training.jwd.finalproject.util;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class CipherTest {
	@Test
	public void encryptTestPositive() throws NoSuchAlgorithmException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sergei1984", "00e8b52c166a4c121a52c3f33e47010ae155af385a2600b9de5bfc8eaf7405d77cac796adb6c51bd732239"
				+ "bedc3dbea007257b966179c8a80736f8d0b38f0a1e");
		map.put("password", "b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df"
				+ "5f1326af5a2ea6d103fd07c95385ffab0cacbc86");
		map.put("newPassword", "6f63f637f1346149532158022899bdf424a19c3dc472e21c2068cd324d7263ed521fb1c1335afaad6bf3f"
				+ "d94a24c0371217086295255e7773eb8deb2c7a54e1a");

		map.forEach((password, expected) -> {
			try {
				Assert.assertEquals(Cipher.encrypt(password), expected);
			} catch (NoSuchAlgorithmException e) {
				Assert.assertNotEquals("No algorithm", e);

			}
		});
	}

	@Test
	public void encryptTestNegative() throws NoSuchAlgorithmException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sergei1984", "sergei1984");
		map.put("password", "password111");
		map.put("newPassword", "  ");

		map.forEach((password, expected) -> {
			try {
				Assert.assertNotEquals(Cipher.encrypt(password), expected);
			} catch (NoSuchAlgorithmException e) {
				Assert.assertNotEquals("No algorithm", e);

			}
		});
	}
}
