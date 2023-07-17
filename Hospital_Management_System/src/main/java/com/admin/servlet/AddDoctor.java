package com.admin.servlet;

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

@WebServlet("/add_doctor")
public class AddDoctor extends HttpServlet{
	


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			String fullname = req.getParameter("fullname");
			String dob = req.getParameter("dob");
			String qualification = req.getParameter("qualification");
			String specialist = req.getParameter("specialist");
			String email = req.getParameter("email");
			
			String mobile_no = req.getParameter("mobile_no");
			String password = req.getParameter("password");
			Doctor doc = new Doctor(fullname,dob,qualification,specialist,email,mobile_no,password);
			DoctorDao dao = new DoctorDao(DBConnect.getConn());
			
			
			HttpSession session = req.getSession();
			if(dao.registerDoctor(doc)) {
				session.setAttribute("SuccessMessage", "Doctor added Successfully");
				resp.sendRedirect("admin/doctor.jsp");
			}
			else {
				session.setAttribute("FailureMessage", "Something wrong on server");
				resp.sendRedirect("admin/doctor.jsp");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
	}
}
