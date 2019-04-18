<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="container con">

<form action="" method="get">
	<div class="row  justify-content-center">
		<button formaction="/student/show/students" class="col-lg-3   text-center  dd form-group text-decoration-none"><img
			src="${baseURL}/dist/icons/student_1.png" class=" rounded-circle">
		<p>Student</p>
		</button> 
		<button formaction="/teacher/show/teachers"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/tutor_1.png" class=" img-circle">
		<p>Teacher</p>
		</button> 
		<button formaction="/admin/show/admins"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/response_1.png" class=" img-circle mt-1">
		<p>Admin</p>
		</button>
		 
		 <button formaction="/subject/show/subjects"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/student_1.png" class=" img-circle">
		<p>Subject</p>
		</button> 
		<button formaction="/subject_joiners/show/subject_joiners"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Subject Joiners</p>
		</button>
		<button formaction="/admin/show/admins"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Subject Queries</p>
		</button> 
		<button formaction="/admin/show/admins"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Subject Query Responses</p>
		</button> 
		<button formaction="/admin/show/admins"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Subject Rating</p>
		</button>
		 
		 <button formaction="/admin/show/admins"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/student_1.png" class=" img-circle">
		<p>Batch</p>
		</button>
		 <button formaction="/batch/show/batches"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Batch Joiners</p></button>
		<button formaction="/admin/show/admins"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Batch Queries</p></button>
		<button formaction="/admin/show/admins"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Batch Query Responses</p>
		</button>
		<button formaction="/admin/show/admins"
			class="col-lg-3   text-center  dd text-decoration-none"><img
			src="${baseURL}/dist/icons/city_1.png" class=" img-circle">
		<p>Batch Rating</p>
		</button> 
		


	</div>
</form>

</div>