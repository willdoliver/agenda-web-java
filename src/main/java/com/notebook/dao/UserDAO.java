package com.notebook.dao;

import com.notebook.bean.User;
import com.notebook.controller.UserController;

import java.sql.*;


public class UserDAO {
	public static User getUser(String usernameEmail, String password, boolean isEmail) {
		UserController userController = new UserController();
		String sql = "";
		User user = new User();
		
		try {
			Connection conn = ConnectionDAO.getConnection();

			if (isEmail) {
				sql = "SELECT * FROM users WHERE email = ?";
			} else {
				sql = "SELECT * FROM users WHERE username = ?";
			}
			System.out.println(sql);
			System.out.println(usernameEmail);
			System.out.println(userController.decodePassword(password));
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, usernameEmail);
			
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				System.out.println(userController.decodePassword(rs.getString("password")));
				if (password.equals(rs.getString("password"))) {
					user.setId(rs.getInt("id"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setUserName(rs.getString("userName"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setCreatedAt(rs.getDate("createdAt"));
					user.setUpdatedAt(rs.getDate("updatedAt"));
				} else {
					System.out.println("Senha fornecida: "+password + " - " + userController.decodePassword(password));
					System.out.println("Senha salva: "+ rs.getString("password") + " - " + userController.decodePassword(rs.getString("password")));
					return user;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return user;
	}
	
	public static int createNewUser(User user) {
		int status = 0;
		
		try {
			Connection conn = ConnectionDAO.getConnection();
			PreparedStatement pstm = conn.prepareStatement("INSERT INTO users "
					+ "(firstName, lastName, userName, email, password, createdAt) "
					+ "VALUES (?,?,?,?,?,?)");
			
			pstm.setString(1, user.getFirstName());
			pstm.setString(2, user.getLastName());
			pstm.setString(3, user.getUserName());
			pstm.setString(4, user.getEmail());
			pstm.setString(5, user.getPassword());
			pstm.setDate(6, new Date(user.getCreatedAt().getTime()));
			
			status = pstm.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int checkUserAlreadyExists(User user) {
		int userFound = 0;

		try {
			Connection conn = ConnectionDAO.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT username, email FROM users WHERE username LIKE ? OR email LIKE ?");

			pstm.setString(1, user.getUserName());
			pstm.setString(2, user.getEmail());

			ResultSet rs = pstm.executeQuery();

			if(rs.next()) {
				if (rs.getString("username").equals(user.getUserName())) {
					System.out.println("Username already registered the system!");
					System.out.println(rs.getString("username"));
				}
				if (rs.getString("email").equals(user.getEmail())) {
					System.out.println("Email already registered the system!");
					System.out.println(rs.getString("email"));
				}

				userFound = 1;
			} else {
				System.out.println("New user/email detected");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return userFound;
	}

}
