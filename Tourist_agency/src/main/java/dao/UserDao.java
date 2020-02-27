package dao;

import dto.UserData;

public interface UserDao {

	UserData getUserById(int idUser);
	
	UserData getUserByMail(String mail);
	
}
