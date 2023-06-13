
package com.notebook.servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notebook.bean.User;
import com.notebook.dao.UserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get request parameters for username/email and password
		String usernameEmail = request.getParameter("usernameemail");
		String pwd = UserDAO.encodePassword(request.getParameter("password"));
		
		User user = UserDAO.logIn(usernameEmail, pwd);
		
		try {
			if( (user.getUserName().equals(usernameEmail) || user.getEmail().equals(usernameEmail)) 
					&& user.getPassword().equals(pwd)) {
				int timeToExpire = 30*60; // 30 min to expire

				Cookie userIdCookie = new Cookie("userid", Integer.toString(user.getId()));
				userIdCookie.setMaxAge(timeToExpire);
				response.addCookie(userIdCookie);

				Cookie userCookie = new Cookie("username", user.getFirstName());
				userCookie.setMaxAge(timeToExpire);
				response.addCookie(userCookie);
				response.sendRedirect("dashboard.jsp");
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				PrintWriter out = response.getWriter();
				out.println("<font size=5 color=red>Usuario/Email ou senha estao incorretos.</font>");
				rd.include(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font size=5 color=red>Nao foi possivel efetuar login.</font>");
			rd.include(request, response);
		}
	}

}
