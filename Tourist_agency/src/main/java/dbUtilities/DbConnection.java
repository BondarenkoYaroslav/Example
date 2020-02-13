package dbUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import utilities.ReaderProperties;

public class DbConnection {

	private static final String PATH_DB_CONFIGS = "properties/dbConfiguration.properties";
	private static final String URL = ReaderProperties.readFile(PATH_DB_CONFIGS, "db.host");
	private static final String CONNECTIONS_OPTION = ReaderProperties.readFile(PATH_DB_CONFIGS, "db.parametrs");
	private static final String JDBC_DRIVER = ReaderProperties.readFile(PATH_DB_CONFIGS, "db.jdbc");
	private static final String LOGIN = ReaderProperties.readFile(PATH_DB_CONFIGS, "db.login");
	private static final String PASSWORD = ReaderProperties.readFile(PATH_DB_CONFIGS, "db.password");

	public static Connection getConnection() {

		try {
			Class.forName(JDBC_DRIVER);
			return DriverManager.getConnection(URL + CONNECTIONS_OPTION, LOGIN, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnectionPool() {
		try {
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource) initialContext.lookup("jdbc:comp/env/jdbc/touristagency");
			return dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
