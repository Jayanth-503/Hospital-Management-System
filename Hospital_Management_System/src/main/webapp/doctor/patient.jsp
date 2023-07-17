<%@page import="com.entity.Doctor"%>
<%@page import="com.entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.Dao.DoctorDao"%>
<%@page import="com.Dao.AppointmentDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
<%@include file="../components/allcss.jsp"%>
</head>
<body>
	<c:if test="${empty doctorObj }">
		<c:redirect url="../Doctor_login.jsp"></c:redirect>
	</c:if>
	
	<%@include file="navbar.jsp" %>
	
	<div class="col-md-12">
		<div class="card paint-card">
			<div class="card-body">
				<p class="fs-3 text-center">Patient Details</p>
				<c:if test="${not empty FailurMessage}">
					<p class="fs-3 text-center text-danger">${FailureMessage}</p>
					<c:remove var="FailureMessage" scope="session" />
				</c:if>
				<c:if test="${not empty SuccessMessage}">
					<div class="fs-3 text-center text-success" role="alert">${SuccessMessage}</div>
					<c:remove var="SuccessMessage" scope="session" />
					
				</c:if>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Full Name</th>
							<th scope="col">Gender</th>
							<th scope="col">Age</th>
							<th scope="col">Appointment Date</th>
							<th scope="col">Email</th>
							<th scope="col">Mob No</th>
							<th scope="col">Diseases</th>
							<th scope="col">Doctor Name</th>
							<th scope="col">Address</th>
							<th scope="col">Status</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<%
						Doctor doc = (Doctor)session.getAttribute("doctorObj");
						AppointmentDao dao = new AppointmentDao(DBConnect.getConn());
						DoctorDao dao2 = new DoctorDao(DBConnect.getConn());
						List<Appointment> list = dao.getAllAppointmentByDoctorLogin(doc.getId());
						for (Appointment ap : list) {
							Doctor d = dao2.getDoctorById(ap.getDoctorId());
						%>
						<tr>
							<th><%=ap.getFullName()%></th>
							<td><%=ap.getGender()%></td>
		 					<td><%=ap.getAge()%></td>
							<td><%=ap.getAppointmentDate()%></td>
							<td><%=ap.getEmail()%></td>
							<td><%=ap.getMobileNo()%></td>
							<td><%=ap.getDiseases()%></td>
							<td><%=d.getFullName()%></td>
							<td><%=ap.getAddress()%></td>
							<td><%=ap.getStatus()%></td>
							
							<td>
							<%
							if("pending".equals(ap.getStatus())){
								%>
								<a href="comment.jsp?id=<%=ap.getId()%>" class="btn btn-success btn-sm ">Comment</a>
							<%}
							else{
								%>
								<a href="#" class="btn btn-success btn-sm disabled">Comment</a>
							<%}
							%>
							
							</td>
							
										 
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