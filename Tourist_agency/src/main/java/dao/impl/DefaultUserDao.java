package dao.impl;

import java.sql.Connection;
import java.sql.Date;
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
	public boolean loginVerification(String login) {

		String requestSelectionMailOrPhone = null;

		if (login.contains("@")) {
			requestSelectionMailOrPhone = "mailVerification";
		} else {
			requestSelectionMailOrPhone = "phoneVerification";
		}

		try (Connection conn = DbConnection.getConnectionPool();
				PreparedStatement prSt = conn
						.prepareStatement(ReaderProperties.readFile(PATH_SQL_QUERIES, requestSelectionMailOrPhone))) {

			prSt.setString(1, login);
			ResultSet rs = prSt.executeQuery();
			
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserData getUser(int idUser) {

		UserData userData = null;

		try (Connection conn = DbConnection.getConnectionPool();
				PreparedStatement prSt = conn
						.prepareStatement(ReaderProperties.readFile(PATH_SQL_QUERIES, "getUserById"))) {

			prSt.setInt(1, idUser);
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
	public UserData getUser(String login) {

		UserData userData = null;

		String requestSelectionMailOrPhone = null;

		if (login.contains("@")) {
			requestSelectionMailOrPhone = "getUserByMail";
		} else {
			requestSelectionMailOrPhone = "getUserByPhone";
		}

		try (Connection conn = DbConnection.getConnectionPool();
				PreparedStatement prSt = conn
						.prepareStatement(ReaderProperties.readFile(PATH_SQL_QUERIES, requestSelectionMailOrPhone))) {

			prSt.setString(1, login);
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
	public boolean setUser(String userName, String userSurname, String userMail, String userPhone, Date userBirthday,
			String userPassword) {

		try (Connection conn = DbConnection.getConnectionPool();
				PreparedStatement prSt = conn
						.prepareStatement(ReaderProperties.readFile(PATH_SQL_QUERIES, "setUser"))) {

			prSt.setString(1, userName);
			prSt.setString(2, userSurname);
			prSt.setString(3, userMail);
			prSt.setString(4, userPhone);
			prSt.setDate(5, userBirthday);
			prSt.setString(6, userPassword);
			prSt.setString(7, "user"); // default for all new users
			prSt.executeUpdate();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(int idUser, String userName, String userSurname, String userMail, String userPhone,
			Date userBirthday, String userPassword) {

		try (Connection conn = DbConnection.getConnectionPool();
				PreparedStatement prSt = conn
						.prepareStatement(ReaderProperties.readFile(PATH_SQL_QUERIES, "updateUser"))) {

			prSt.setString(1, userName);
			prSt.setString(2, userSurname);
			prSt.setString(3, userMail);
			prSt.setString(4, userPhone);
			prSt.setDate(5, userBirthday);
			prSt.setString(6, userPassword);
			prSt.setInt(7, idUser);
			prSt.executeUpdate();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteUser(int idUser) {

		try (Connection conn = DbConnection.getConnectionPool();
				PreparedStatement prSt = conn
						.prepareStatement(ReaderProperties.readFile(PATH_SQL_QUERIES, "deleteUserById"))) {

			prSt.setInt(1, idUser);
			prSt.executeUpdate();

			return true;

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean deleteUser(String mailUser) {

		try (Connection conn = DbConnection.getConnectionPool();
				PreparedStatement prSt = conn
						.prepareStatement(ReaderProperties.readFile(PATH_SQL_QUERIES, "deleteUserByMail"))) {

			prSt.setString(1, mailUser);
			prSt.executeUpdate();

			return true;

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
}
