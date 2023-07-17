<%@page import="com.db.DBConnect"%>
<%@page import="com.entity.Specialist"%>
<%@page import="java.util.List"%>
<%@page import="com.Dao.SpecialistDao"%>
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
	<%@include file="navbar.jsp"%>
	
	<div class="container p-4">
		<div class="row">
			<div class="col-md-4 ">
				<div class="card paint-card">
					<p class="text-center fs-3">Change Password</p>
					<c:if test="${not empty SuccessMessage }">
						<p class="text-center text-success fs-3">${SuccessMessage}</p>
						<c:remove var="SuccessMessage" scope="session" />
					</c:if>

					<c:if test="${not empty FailureMessage}">
						<p class="text-center text-danger fs-5">${FailureMessage}</p>
						<c:remove var="FailureMessage" scope="session" />
					</c:if>
					<div class="card-body">
						<form action="../doctor_password_change" method="post">
							<div class="mb-3">
								<label>Enter New Password</label> <input type="text"
									name="newPassword" class="form-control" required>
							</div>

							<div class="mb-3">
								<label>Enter Old Password</label> <input type="text"
									name="oldPassword" class="form-control" required>
							</div>
							<input type="hidden" value="${doctorObj.id }" name="id">
							<button class="btn btn-info col-md-12">Change
								Password</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-5 offset-md-2">
				<div class="card paint-card">
				<p class="text-center fs-3">Edit Profile</p>
				<c:if test="${not empty SuccessMessageD }">
						<p class="text-center text-success fs-3">${SuccessMessageD}</p>
						<c:remove var="SuccessMessageD" scope="session" />
					</c:if>

					<c:if test="${not empty FailureMessageD}">
						<p class="text-center text-danger fs-5">${FailureMessageD}</p>
						<c:remove var="FailureMessageD" scope="session" />
					</c:if>
					<div class="card-body">
						<form action="../edit_profile" method="post">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input type="text"
									required name="fullname" class="form-control" value="${doctorObj.fullName }">
							</div>

							<div class="mb-3">
								<label class="form-label">DOB</label> <input type="date"
									required name="dob" class="form-control" value="${doctorObj.dob }">
							</div>

							<div class="mb-3">
								<label class="form-label">Qualification</label> <input required
									name="qualification" type="text" class="form-control" value="${doctorObj.qualification }">
							</div>
							<div class="mb-3">
								<label class="form-label" >Specialist</label> <select name="specialist"
									required class="form-control"  >
									<option>${doctorObj.specialist }</option>
									
									<% SpecialistDao dao = new SpecialistDao(DBConnect.getConn());
									List<Specialist> specs = dao.getAllSpecialist();
									for(Specialist s:specs){
										%>
										<option><%=s.getSpecialistName() %></option>
										
									<%}
									%>
									


								</select>
							</div>

							<div class="mb-3">
								<label class="form-label">Email</label> <input type="text" readonly
									name="email" class="form-control" value="${doctorObj.email }">
							</div>

							<div class="mb-3">
								<label class="form-label">Mob No</label> <input type="text"
									required name="mobile_no" class="form-control" value="${doctorObj.mobile_no }">
							</div>

							<input type="hidden" name="id" value="${doctorObj.id }">
							

							<button type="submit" class="btn btn-primary">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	

</body>
</html>