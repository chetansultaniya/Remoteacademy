<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="card-header text-center text-white header">
	<a class="btn btn-dark glyphicon glyphicon-menu-left float-left" href="/teacher/show/teachers">
		<span class="backbtn">Back</span>
	</a>
	<h3>Edit Teacher</h3>
</div>
<div class="container mt-2" id="dataBody">
	<form action="/teacher/show/save" method="post" enctype="multipart/form-data">
		<!-- ------------------Basic--------------------------- -->
		<div class="card mt-2">
			<div class="card-header bg-dark text-white">
				<h4>Basic Detail</h4>
			</div>
			<div class="card-body form-group">
				<div class="form-row">
					<div class="col-md-6 row">
						<div class="col-md-12 text-center">
							<img src="" class="rounded" width="350px" height="355px" alt="No Image" data-id="${teacher.teacherId}" id="teacherImage">
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
							<input type="text" class="form-control" name="teacherId" readonly value="${teacher.teacherId}" id="teacherId">
						</div>
						<div class="form-group col-md-12">
							<label>Name</label>
							<input type="text" class="form-control" name="name" value="${teacher.name}" id="name">
						</div>
						<div class="form-group col-md-12">
							<label>Mobile</label>
							<input type="text" class="form-control" name="mobile" value="${teacher.mobile}" id="mobile">
						</div>
						<div class="form-group col-md-12">
							<label>Email</label>
							<input type="email" class="form-control" name="email" value="${teacher.email}" id="email">
							<input type="hidden" class="form-control" name="password" value="${teacher.password}" id="password">
						</div>
						<div class="form-group col-md-6">
							<label>Date Of Birth</label>
							<input type="date" class="form-control " name="dob" value="${teacher.dob}" id="dob">
						</div>
						<div class="form-group col-md-6">
							<label>Gender</label>
							<select class="form-control" name="gender" id="gender">
								<option value="" disabled>Gender</option>
								<option value="${teacher.gender}" selected>${teacher.gender}</option>
								<c:choose>
									<c:when test="${teacher.gender.length()==4}">
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
							<label for="inputEmail4">Address</label>
							<textarea class="form-control" name="address" id="address" rows="3">${teacher.address}</textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">City</label>
							<input type="text" class="form-control" name="city" value="${teacher.city}" id="inputPassword4">
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">State</label>
							<input type="text" class="form-control" name="state" value="${teacher.state}" id="inputPassword4">
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">Country</label>
							<input type="text" class="form-control" name="country" value="${teacher.country}" id="inputPassword4">
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">Pincode</label>
							<input type="text" class="form-control" name="pincode" value="${teacher.pincode}" id="inputPassword4">
						</div>
					</div>
				</div>
			</div>
			<!-- ------------------Academic--------------------------- -->
			<div class="card">
				<div class="card-header bg-dark text-white">
					<h4>Adcademic & Experience Detail</h4>
				</div>
				<div class="card-body form-group">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputPassword4">Occupation</label>
							<input type="text" class="form-control" name="occupation" value="${teacher.occupation}" id="inputPassword4">
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">Qualification</label>
							<input type="text" class="form-control" name="qualification" value="${teacher.qualification}" id="inputPassword4">
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">Experience</label>
							<input type="text" class="form-control" name="experience" value="${teacher.experience}" id="inputPassword4">
						</div>
					</div>
				</div>
				<div class="row justify-content-center">
					<a href="/teacher/show/teachers" class="btn btn-danger col-1 text-white">Cancel</a>
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
			teacherId = $("#teacherImage").data('id');
			if (typeof teacherId === 'undefined') {
				return;
			}

			$.ajax({
				url : baseURL + '/image/get',
				type : 'POST',
				data : {
					'entity' : 'teacher',
					'id' : teacherId
				},
				success : function(response) {
					console.log(response);
					if(response.length==0)
					{
						$("#teacherImage").attr('src',
								baseURL+"/dist/icons/person.jpg");
					}
					else
					{
					$("#teacherImage").attr('src',
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