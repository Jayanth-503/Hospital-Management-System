package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.SpecialistDao;
import com.db.DBConnect;

@WebServlet("/add_specialist")
public class AddSpecialist extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String specName = req.getParameter("specName");
		SpecialistDao dao = new SpecialistDao(DBConnect.getConn());
		boolean isAdded = dao.addSpecialist(specName);
		HttpSession session = req.getSession();
		
		if(isAdded) {
			session.setAttribute("SuccessMessage","Specialist Added");
			resp.sendRedirect("admin/index.jsp");
		}
		else {
			session.setAttribute("FailureMessage", "Something wrong on server");
			resp.sendRedirect("admin/index.jsp");
		}
	}
	
}
