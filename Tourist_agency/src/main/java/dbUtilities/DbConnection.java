package dbUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import utilities.ReaderProperties;

public class DbConnection {

	private static final String PATH_DB_CONFIGS = "resources/properties/dbConfiguration.properties";
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
//			DataSource dataSource = (DataSource) initialContext.lookup("java:global/env/jdbc/touristagency");
			DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/touristagency");
//			DataSource dataSource = (DataSource) initialContext.lookup("jdbc/touristagency");
			return dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			
/*			
 * В идеале здесь должна быть строка
 * 	"throw new RuntimeException(e);"
 *  и она здесь будет!
 */
			throw new RuntimeException(e);
//			return getConnection();
		}
	}
}

//   <resource-ref>
//   <description>DB Connection</description>
//   <res-ref-name>jdbc/touristagency</res-ref-name>
//   <res-type>javax.sql.DataSource</res-type>
//   <res-auth>Container</res-auth>
//	 </resource-ref>
