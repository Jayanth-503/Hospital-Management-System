<%@page import="com.entity.Doctor"%>
<%@page import="com.Dao.DoctorDao"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.Dao.AppointmentDao"%>
<%@page import="java.util.*"%>
<%@page import="com.entity.Appointment" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file = "../components/allcss.jsp" %>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class = "col-md-12">
	<div class = "card paint-card">
		<div class="card-body">
			<p class="fs-3 text-center">Patient Details</p>
			<table class="table">
				<thead>
				<tr>
					<th scope="col">Full Name</th>
					<th scope="col">Gender</th>
					<th scope="col">Age</th>
					<th scope="col">Appointment</th>
					<th scope="col">Email</th>
					<th scope="col">Mobile No</th>
					<th scope="col">Diseases</th>
					<th scope="col">Doctor Name</th>
					<th scope="col">Address</th>
					<th scope="col">Status</th>
				</tr>
				</thead>
				<tbody>
					<%
						AppointmentDao dao = new AppointmentDao(DBConnect.getConn());
					DoctorDao dao2 =new DoctorDao(DBConnect.getConn()); 
					List<Appointment> res = dao.getAllAppointment();
					for(Appointment app:res){
						Doctor doc = dao2.getDoctorById(app.getDoctorId());
						%>
							<tr>
								<th><%=app.getFullName() %></th>
								<td><%=app.getGender() %></td>
								<td><%=app.getAge() %></td>
								<td><%=app.getAppointmentDate() %></td>
								<td><%=app.getEmail() %></td>
								<td><%=app.getMobileNo() %></td>
								<td><%=app.getDiseases() %></td>
								<td><%=doc.getFullName() %></td>
								<td><%=app.getAddress() %></td>
								<td><%=app.getStatus() %></td>
							</tr>
						<%
					}
					%>
					
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>