package com.notebook.dao;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.util.codec.binary.Base64;

import com.notebook.bean.User;


public class UserDAO {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
	}
	
	public static String encodePassword(String valor) {
        return new Base64().encodeToString(valor.getBytes());
    }

    public static String decodePassword(String valorCriptografado) {
        return new String(new Base64().decode(valorCriptografado));
    }

	
	public static User logIn(String usernameEmail, String password) {
		User user = new User();
		
		try {
			Connection conn = ConnectionDAO.getConnection();
			String sql = "";
			
			boolean isEmail = validate(usernameEmail);
			if (isEmail) {
				sql = "SELECT * FROM users WHERE email = ?";
			} else {
				sql = "SELECT * FROM users WHERE username = ?";
			}
			System.out.println(sql);
			System.out.println(usernameEmail);
			System.out.println(decodePassword(password));
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, usernameEmail);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				System.out.println(decodePassword(rs.getString("password")));
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
					System.out.println("Senha fornecida: "+password + " - " + decodePassword(password));
					System.out.println("Senha salva: "+ rs.getString("password") + " - " + decodePassword(rs.getString("password")));
					return user;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Returning User");
		return user;
	}
	
	public static int createUser(User u) {
		int status = 0;
		
		try {
			Connection conn = ConnectionDAO.getConnection();
			PreparedStatement pstm = conn.prepareStatement("INSERT INTO users "
					+ "(firstName, lastName, userName, email, password, createdAt) "
					+ "VALUES (?,?,?,?,?,?)");
			
			pstm.setString(1, u.getFirstName());
			pstm.setString(2, u.getLastName());
			pstm.setString(3, u.getUserName());
			pstm.setString(4, u.getEmail());
			pstm.setString(5, u.getPassword());
			pstm.setDate(6, new Date(u.getCreatedAt().getTime()));
			
			status = pstm.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
}
