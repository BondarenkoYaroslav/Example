package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDao;
import dbUtilities.DbConnection;
import dto.UserData;
import utilities.ReaderProperties;

public class DefaultUserDao implements UserDao {

	private static final String PATH_SQL_QUERIES = "resources/properties/dbQueries.properties";
	private static DefaultUserDao instance;

	private DefaultUserDao() {
	}

	public static synchronized DefaultUserDao getDefaultUserDao() {
		if (instance == null) {
			instance = new DefaultUserDao();
		}
		return instance;
	}

	@Override
	public UserData getUserById(int idUser) {

		UserData userData = null;

		try (Connection conn = DbConnection.getConnectionPool();
				PreparedStatement prSt = conn
						.prepareStatement(ReaderProperties.readFile(PATH_SQL_QUERIES, "getUserById"))) {
			
			prSt.setInt(idUser, 1);
			ResultSet rs = prSt.executeQuery();
			
			while (rs.next()) {
				
				userData = new UserData();
				userData.setUserId(rs.getInt("user_id"));
				userData.setUserName(rs.getString("user_name"));
				userData.setUserSurname(rs.getString("user_surname"));
				userData.setUserMail(rs.getString("user_mail"));
				userData.setUserPhone(rs.getString("user_phone"));
				userData.setUserBirthday(rs.getDate("user_birthday"));
				userData.setUserPassword(rs.getString("user_password"));
				userData.setUserRole(rs.getString("user_role"));
			}
			
			return userData;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userData;
	}

	@Override
	public UserData getUserByMail(String mail) {
		// TODO Auto-generated method stub
		return null;
	}

}
