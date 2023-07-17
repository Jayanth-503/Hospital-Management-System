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
import com.entity.Doctor;

@WebServlet("/doctor_login")
public class DoctorLogin extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		DoctorDao dao = new DoctorDao(DBConnect.getConn());
		Doctor doctor = dao.login(email, password);
		
		if(doctor!=null) {
			session.setAttribute("doctorObj",doctor);
			resp.sendRedirect("doctor/index.jsp");
		}
		else {
			session.setAttribute("FailureMessage", "Invalid Credentials");
			resp.sendRedirect("Doctor_login.jsp");
		}
	}
	
}
