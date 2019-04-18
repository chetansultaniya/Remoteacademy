<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="card-header text-center text-white header">
	<a class="btn btn-dark glyphicon glyphicon-menu-left float-left" href="/student/show/students">
		<span class="backbtn">Back</span>
	</a>
	<h3>Edit Student</h3>
</div>
<div class="container mt-2" id="dataBody">
	<form action="/student/show/save" method="post" enctype="multipart/form-data">
		<!-- ------------------Basic--------------------------- -->
		<div class="card mt-2">
			<div class="card-header bg-dark text-white">
				<h4>Basic Detail</h4>
			</div>
			<div class="card-body form-group">
				<div class="row">
					<div class="col-md-6 row">
						<div class="col-md-12 text-center">
							<img src="" class="rounded" width="350px" height="355px" alt="No Image" data-id="${student.studentId}" id="studentImage">
						</div>
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<input type="file" class="custom-file-input" name="image" id="image">
							<label class="custom-file-label">Change Image</label>
						</div>
					</div>
					<div class="col-md-6 form-row">
						<div class="form-group col-md-12">
							<label>Id</label>
							<input type="text" class="form-control" name="studentId" readonly value="${student.studentId}" id="studentId">
						</div>
						<div class="form-group col-md-12">
							<label>Name</label>
							<input type="text" class="form-control" name="name" value="${student.name}" id="name">
						</div>
						<div class="form-group col-md-12">
							<label>Mobile</label>
							<input type="text" class="form-control" name="mobile" value="${student.mobile}" id="mobile">
						</div>
						<div class="form-group col-md-12">
							<label>Email</label>
							<input type="email" class="form-control" name="email" value="${student.email}" id="email">
							<input type="hidden" class="form-control" name="password" value="${student.password}" id="password">
						</div>
						<div class="form-group col-md-6">
							<label>Date Of Birth</label>
							<input type="date" class="form-control " name="dob" value="${student.dob}" id="dob">
						</div>
						<div class="form-group col-md-6">
							<label>Gender</label>
							<select class="form-control" name="gender" id="gender">
								<option value="" disabled>Gender</option>
								<option value="${student.gender}" selected>${student.gender}</option>
								<c:choose>
									<c:when test="${student.gender.length()==4}">
										<option value="Female">Female</option>
									</c:when>
									<c:otherwise>
										<option value="Male">Male</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</div>
			<!-- ------------------Address--------------------------- -->
			<div class="card">
				<div class="card-header bg-dark text-white">
					<h4>Address Detail</h4>
				</div>
				<div class="card-body form-group">
					<div class="form-row">
						<div class="form-group col-md-12">
							<label>Address</label>
							<textarea class="form-control" name="address" id="address" rows="3">${student.address}</textarea>
						</div>
						<div class="form-group col-md-6">
							<label>City</label>
							<input type="text" class="form-control" name="city" value="${student.city}" id="city">
						</div>
						<div class="form-group col-md-6">
							<label>State</label>
							<input type="text" class="form-control" name="state" value="${student.state}" id="state">
						</div>
						<div class="form-group col-md-6">
							<label>Country</label>
							<input type="text" class="form-control" name="country" value="${student.country}" id="country">
						</div>
						<div class="form-group col-md-6">
							<label>Pincode</label>
							<input type="text" class="form-control" name="pincode" value="${student.pincode}" id="pincode">
						</div>
					</div>
				</div>
			</div>
			<!-- ------------------Academic--------------------------- -->
			<div class="card">
				<div class="card-header bg-dark text-white">
					<h4>Adcademic Detail</h4>
				</div>
				<div class="card-body form-group">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Course</label>
							<input type="text" class="form-control" name="course" value="${student.course}" id="course">
						</div>
						<div class="form-group col-md-6">
							<label>Class</label>
							<input type="text" class="form-control" name="studentClass" value="${student.studentClass}" id="studentClass">
						</div>
						<div class="form-group col-md-6">
							<label>Institute</label>
							<input type="text" class="form-control" name="institute" value="${student.institute}" id="institute">
						</div>
					</div>
				</div>
				<div class="row justify-content-center">
					<a href="/student/show/students" class="btn btn-danger col-1 text-white">Cancel</a>
					<button type="submit" class="btn btn-primary ml-4 col-1">Submit</button>
				</div>
			</div>
		</div>
	</form>
</div>
<script>
	$(document).ready(function() {
		var baseURL = "${baseURL}";
		function getImage() {
			studentId = $("#studentImage").data('id');
			if (typeof studentId === 'undefined') {
				return;
			}

			$.ajax({
				url : baseURL + '/image/get',
				type : 'POST',
				data : {
					'entity' : 'student',
					'id' : studentId
				},
				success : function(response) {
					console.log(response);
					if(response.length==0)
					{

						$("#studentImage").attr('src',
								baseURL+"/dist/icons/person.jpg");
					}
					else
					{
					
					$("#studentImage").attr('src',
							"data:image/jpg;base64," + response);
					
					}
				},
				error : function(xhr, status, error) {
					alert(error);
				}
			});
		}
		getImage();
	});
</script>