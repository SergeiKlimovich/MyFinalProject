package by.training.jwd.finalproject.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The {@code Cipher} class represents cipher.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class Cipher {
	private static final String CIPHER_FORMAT = "%02x";

	/**
	 * Encrypt string.
	 *
	 * @param string the string
	 * @return the encrypted string
	 */
	public static String encrypt(String string) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
		byte[] data = messageDigest.digest(string.getBytes());
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			stringBuilder.append(String.format(CIPHER_FORMAT, data[i]));
		}
		return stringBuilder.toString();
	}
}
