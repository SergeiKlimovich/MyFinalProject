package by.training.jwd.finalproject.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code ConnectionPool} class represents connection pool.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class ConnectionPool {
	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
	private static final int POOL_SIZE = 8;
	private static ConnectionPool connectionPool = new ConnectionPool();

	private BlockingQueue<ProxyConnection> freeConnections;
	private BlockingQueue<ProxyConnection> proxyConnections;

	/**
	 * Instance connection pool.
	 */
	public static ConnectionPool getInstance() {
		return connectionPool;
	}

	private ConnectionPool() {
		try {
			DatabaseConfig databaseConfig = new DatabaseConfig();
			Class.forName(databaseConfig.getDriverName());
			freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
			proxyConnections = new LinkedBlockingDeque<>(POOL_SIZE);
			for (int i = 0; i < POOL_SIZE; i++) {
				freeConnections.offer(new ProxyConnection(DriverManager.getConnection(databaseConfig.getUrl(),
						databaseConfig.getUsername(), databaseConfig.getPassword())));
			}
			logger.log(Level.INFO, "Connection pool has been filled");
		} catch (ClassNotFoundException | SQLException e) {
			logger.log(Level.FATAL, "Error during connection pool creating");
		}
	}

	/**
	 * Gets connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() throws ConnectionPoolException {
		ProxyConnection connection;
		try {
			connection = freeConnections.take();
			proxyConnections.offer(connection);
			logger.log(Level.DEBUG, "Connection has been given");
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "Pool can't provide connection", e);
			throw new ConnectionPoolException(e);
		}
		return connection;
	}

	/**
	 * Release connection.
	 *
	 * @param connection the connection
	 */
	public void releaseConnection(Connection connection) {
		if (connection.getClass() == ProxyConnection.class) {
			if (proxyConnections.remove(connection)) {
				freeConnections.offer((ProxyConnection) connection);
			}
			logger.log(Level.DEBUG, "Connection has been released");
		} else {
			logger.log(Level.WARN, "Invalid connection to release");
		}
	}

	/**
	 * Destroy pool.
	 */
	public void destroyPool() {
		try {
			for (int i = 0; i < POOL_SIZE; i++) {
				freeConnections.take().reallyClose();
			}
			logger.log(Level.INFO, "Connection pool has been destroyed");

		} catch (SQLException e) {
			logger.log(Level.ERROR, "Connection was not deleted", e);
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "Thread was interrupted", e);
		}
	}

}