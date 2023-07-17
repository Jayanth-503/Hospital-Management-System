<%-- <%@page import="com.entity.Doctor"%>
<%@page import="com.Dao.DoctorDao"%> --%> 
 <%@page import="com.entity.Doctor"%>
<%@page import="com.Dao.DoctorDao"%>
<%@page import="com.entity.Specialist"%>
<%@page import="java.util.List"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.Dao.SpecialistDao"%> 
<%@page import="com.db.DBConnect"%>
<%@page import="com.Dao.SpecialistDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="../components/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container-fluid p-3">
		<div class="row">
			
			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Doctor Details</p>
						<c:if test="${not empty FailureMessage}">
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
									<th scope="col">Full Name </th>
									<th scope="col">DOB</th>
									<th scope="col">Qualification</th>
									<th scope="col">Specialist</th>
									<th scope="col">Email</th>
									<th scope="col">Mobile No</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>
								<%
									DoctorDao dao2 = new DoctorDao(DBConnect.getConn());
								List<Doctor> res = dao2.getAllDoctors();
								for(Doctor doc:res){
									%>
									<tr>
										<td> <%=doc.getFullName() %></td>
										<td><%=doc.getDob() %></td>
										<td><%=doc.getQualification() %></td>
										
										<td><%=doc.getSpecialist() %></td>
										<td><%=doc.getEmail() %></td>
										<td><%=doc.getMobile_no() %></td>
										
										<td>
										<a href="editDoctor.jsp?id=<%=doc.getId() %>" class="btn btn-sm btn-primary">Edit</a>
										<a href="../delete_doctor?id=<%=doc.getId() %>" class="btn btn-sm btn-danger">Delete</a>
										</td>	
								<%}
								
								%>
						
						
							</tbody>
						</table>
					</div>
				</div>
			</div>


			
		</div>
	</div>
</body>
</html>