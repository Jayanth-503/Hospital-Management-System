<%@page import="com.db.DBConnect"%>
<%@page import="com.Dao.DoctorDao"%>
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
<%@include file = "navbar.jsp" %>

<c:if test="${ empty adminObj }">
		<c:redirect url="../Admin_login.jsp"></c:redirect>
	</c:if>

	<div class="container p-5">
		<p class="text-center fs-3">Admin Dashboard</p>
		<c:if test="${not empty FailurMessage}">
			<p class="fs-3 text-center text-danger">${FailureMessage}</p>
			<c:remove var="FailureMessage" scope="session" />
		</c:if>
		<c:if test="${not empty SuccessMessage}">
			<div class="fs-3 text-center text-success" role="alert">${SuccessMessage}</div>
			<c:remove var="SuccessMessage" scope="session" />
			
		</c:if>
		<% 
		DoctorDao dao = new DoctorDao(DBConnect.getConn());
		%>
		<div class="row">
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-info">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-4 text-center">
							Doctor <br>
							<%=dao.countDoctor() %>
						</p>
					</div>
				</div>
			</div>

			

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-info">
						<i class="fas fa-user-circle fa-3x"></i><br>
						<p class="fs-4 text-center">
							 User <br>
							 <%=dao.countUsers() %>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-info">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">
							 Total Appointment <br>
							 <%=dao.countAppointment() %>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-4 mt-2">

				<div class="card paint-card " data-bs-toggle="modal"
					data-bs-target="#exampleModal">
					<div class="card-body text-center text-info">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">
							Specialist <br>
							<%=dao.countSpecialist() %>
						</p>
					</div>
				</div>

			</div>

		</div>
	</div>
	
	<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Add Specialist</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="../add_specialist" method="post">
        
        <div class="from-group">
        <label>Enter Specialist Name</label>
        <input type="text" name="specName" class="form-control">
        </div>
        <button type="submit" class="btn btn-info">Add</button>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

      </div>
    </div>
  </div>
</div>

</body>
</html>