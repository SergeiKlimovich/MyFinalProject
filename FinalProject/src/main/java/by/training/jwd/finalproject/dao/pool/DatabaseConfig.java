package by.training.jwd.finalproject.dao.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code DatabaseConfig} class represents database config.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class DatabaseConfig {
	private static final Logger logger = LogManager.getLogger(DatabaseConfig.class);
	private static final String FILE_NAME = "property/db.properties";
	private static final String DATABASE_DRIVER = "db.driver";
	private static final String DATABASE_URL = "db.url";
	private static final String DATABASE_USERNAME = "db.username";
	private static final String DATABASE_PASSWORD = "db.password";
	private final String driverName;
	private final String url;
	private final String username;
	private final String password;

	/**
	 * Instantiates a new Database config.
	 */
	DatabaseConfig() {
		Properties properties = new Properties();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
			properties.load(inputStream);
		} catch (IOException e) {
			logger.log(Level.FATAL, "Error with database properties file", e);
			throw new RuntimeException("Error with database properties file", e);
		}
		driverName = properties.getProperty(DATABASE_DRIVER);
		url = properties.getProperty(DATABASE_URL);
		username = properties.getProperty(DATABASE_USERNAME);
		password = properties.getProperty(DATABASE_PASSWORD);
	}

	public String getDriverName() {
		return driverName;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
