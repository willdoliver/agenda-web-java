package com.notebook.interfaces;

import com.notebook.bean.User;

public interface UserInterface {
	boolean validateEmail(String emailStr);
	String encodePassword(String passwordDecoded);
	String decodePassword(String passwordEnconded);
	User logIn(String usernameEmail, String password);
	int createUser(User user);
}
