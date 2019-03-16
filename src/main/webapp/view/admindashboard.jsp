<%@include file="blocks/header.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="container con">

<form action="" method="get">
	<div class="row  justify-content-center">
		<button
			  formaction="/student/show/students" class="col-lg-3   text-center  dd form-group text-decoration-none"><img
			src="${baseURL}/dist/icons/student_1.png" class=" rounded-circle">
		<p>Student</p></button> 
		<button 
		formaction="/teacher/show/teachers"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/tutor_1.png" class=" img-circle">
		<p>Teacher</p></button> 
		<button 
		formaction="/admin/show/admins"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/response_1.png" class=" img-circle mt-1">
		<p>Admin</p></button> <a href="#"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/student_1.png" class=" img-circle">
		<p>Batch</p></a> <a href="#"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Batch Joiners</p></a> <a href="#"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Batch Queries</p></a> <a href="#"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Batch Query Responses</p></a> <a href="#"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Batch Rating</p></a> <a href="#"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/student_1.png" class=" img-circle">
		<p>Subject</p></a> <a href="#"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Subject Joiners</p></a> <a href="#"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Subject Queries</p></a> <a href="#"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Subject Query Responses</p></a> <a href="#"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Subject Rating</p></a>


	</div>
</form>

</div>
<%@include file="blocks/footer.jsp"%>