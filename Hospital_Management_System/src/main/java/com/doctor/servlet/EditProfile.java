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
@WebServlet("/edit_profile")
public class EditProfile extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String fullname = req.getParameter("fullname");
			String dob = req.getParameter("dob");
			String qualification = req.getParameter("qualification");
			String specialist = req.getParameter("specialist");
			String email = req.getParameter("email");
			
			String mobile_no = req.getParameter("mobile_no");
			
			int id = Integer.parseInt(req.getParameter("id"));
			Doctor doc = new Doctor(id,fullname,dob,qualification,specialist,email,mobile_no,"");
			DoctorDao dao = new DoctorDao(DBConnect.getConn());
			
			
			HttpSession session = req.getSession();
			if(dao.editDoctorProfile(doc)) {
				Doctor updateDoctor = dao.getDoctorById(id);
				session.setAttribute("SuccessMessageD", "Doctor updated Successfully");
				session.setAttribute("doctorObj", updateDoctor);
				resp.sendRedirect("doctor/edit_profile.jsp");
			}
			else {
				session.setAttribute("FailureMessageD", "Something wrong on server");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	
	
}
