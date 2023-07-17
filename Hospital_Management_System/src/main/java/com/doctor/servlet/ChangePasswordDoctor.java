package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.DoctorDao;
import com.db.DBConnect;
@WebServlet("/doctor_password_change")
public class ChangePasswordDoctor extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("id"));
		String oldPwd = req.getParameter("oldPassword");
		String newPwd = req.getParameter("newPassword");
		
		DoctorDao dao = new DoctorDao(DBConnect.getConn());
		HttpSession session = req.getSession();
		
		if(dao.checkOldPassword(userId, oldPwd)) {
			
			if(dao.changePassword(userId, newPwd)) {
				session.setAttribute("SuccessMessage", "Password Changed Successfully");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}
			else {
				session.setAttribute("FailureMessage","Something, wrong on server");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}
			
		}
		else {
			session.setAttribute("FailureMessage","Old Password incorrect");
			resp.sendRedirect("doctor/edit_profile.jsp");
		}
	
	}
}
