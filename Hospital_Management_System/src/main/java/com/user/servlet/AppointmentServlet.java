package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.AppointmentDao;
import com.db.DBConnect;
import com.entity.Appointment;
@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("userid"));
		String fullname = req.getParameter("fullname");
		String gender = req.getParameter("gender");
		String age = req.getParameter("age");
		
		String appointment_date = req.getParameter("appointment_date");
		String email = req.getParameter("email");
		String mobile_no = req.getParameter("mobile_no");
		
		String diseases = req.getParameter("diseases");
		String address = req.getParameter("address");
		int doctor_id = Integer.parseInt(req.getParameter("doctor"));
		
		Appointment app = new Appointment(userId,fullname,gender,age,appointment_date,email,mobile_no,diseases,doctor_id,address,"pending");
		AppointmentDao dao = new AppointmentDao(DBConnect.getConn());
		HttpSession session = req.getSession();
		if(dao.addAppointment(app)) {
			session.setAttribute("SuccessMessage", "Appointment Successfull");
			resp.sendRedirect("user_appointment.jsp");
		}
		else {
			session.setAttribute("FailureMessage", "Something, wrong in server");
			resp.sendRedirect("user_appointment.jsp");
		}
		
		
		
	}
	
}
