package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.UserDao;
import com.db.DBConnect;
import com.entity.User;

@WebServlet("/user_register")
public class UserRegister extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String fullName = req.getParameter("fullname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			User user = new User(fullName,email,password);
			UserDao userdao = new UserDao(DBConnect.getConn());
			
			HttpSession session = req.getSession();
			boolean status = userdao.register(user);
			if(status == true) {
				session.setAttribute("SuccessMessage","Registered Successfully, please go to user login page");
				resp.sendRedirect("signup.jsp");
				
			}
			else {
				session.setAttribute("FailureMessage","Server Error");
				resp.sendRedirect("signup.jsp");
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	
}
