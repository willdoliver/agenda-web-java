package com.notebook.controller;

import com.notebook.bean.User;
import com.notebook.dao.UserDAO;
import com.notebook.interfaces.UserInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.util.codec.binary.Base64;


public class UserController implements UserInterface {

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	@Override
	public boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
	}
	
	public String encodePassword(String valor) {
        return new Base64().encodeToString(valor.getBytes());
    }

    public String decodePassword(String valorCriptografado) {
        return new String(new Base64().decode(valorCriptografado));
    }
    
    public User logIn(String usernameEmail, String password) {
		UserController userController = new UserController();
		User user = new User();
		
		try {
			boolean isEmail = userController.validateEmail(usernameEmail);
			password = userController.encodePassword(password);
			user = UserDAO.getUser(usernameEmail, password, isEmail);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Returning User");
		return user;
	}
    
    public int createUser(User u) {
		int status = 0;
		int userFound = 0;

		try {
			userFound = UserDAO.checkUserAlreadyExists(u);

			if (userFound == 0) {
				status = UserDAO.createNewUser(u);
			}
			// ELSE Option to reset password

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

}
