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
@WebServlet("/update_doctor")
public class UpdateDoctor extends HttpServlet {

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
			int id = Integer.parseInt(req.getParameter("id"));
			Doctor doc = new Doctor(id,fullname,dob,qualification,specialist,email,mobile_no,password);
			DoctorDao dao = new DoctorDao(DBConnect.getConn());
			
			
			HttpSession session = req.getSession();
			if(dao.updateDoctor(doc)) {
				session.setAttribute("SuccessMessage", "Doctor updated Successfully");
				resp.sendRedirect("admin/view_doctor.jsp");
			}
			else {
				session.setAttribute("FailureMessage", "Something wrong on server");
				resp.sendRedirect("admin/view_doctor.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
