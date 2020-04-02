package dao;

import java.sql.Date;

import dto.UserData;

public interface UserDao {

	boolean loginVerification(String login);
	
	UserData getUser(int idUser);

	UserData getUser(String login);

	boolean setUser(String userName, String userSurname, String userMail, String userPhone, Date userBirthday,
			String userPassword);

	boolean updateUser(int idUser, String userName, String userSurname, String userMail, String userPhone,
			Date userBirthday, String userPassword);

	boolean deleteUser(int idUser);

	boolean deleteUser(String mailUser);

}
