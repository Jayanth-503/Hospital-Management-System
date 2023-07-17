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
@WebServlet("/user_change_password")
public class changePassword extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int userId = Integer.parseInt(req.getParameter("userId"));
		String oldPwd = req.getParameter("oldPassword");
		String newPwd = req.getParameter("newPassword");
		
		UserDao dao = new UserDao(DBConnect.getConn());
		HttpSession session = req.getSession();
		
		if(dao.checkOldPassword(userId, oldPwd)) {
			
			if(dao.changePassword(userId, newPwd)) {
				session.setAttribute("SuccessMessage", "Password Changed Successfully");
				resp.sendRedirect("change_password.jsp");
			}
			else {
				session.setAttribute("FailureMessage","Something, wrong on server");
				resp.sendRedirect("change_password.jsp");
			}
			
		}
		else {
			session.setAttribute("FailureMessage","Old Password incorrect");
			resp.sendRedirect("change_password.jsp");
		}
		
	}
	
}
