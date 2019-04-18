<div class="card-header text-center header text-white">
	<a class="btn btn-dark glyphicon glyphicon-menu-left float-left" href="/subject_joiners/show/subject_joiners">
		<span class="backbtn">Back</span>
	</a>
	<h3>Join Subject</h3>
</div>
<div class="container  text-center w-25 error" id="error">${error}</div>
<div class="container  text-center w-25 success" id="success">${success}</div>
<div class="container con border rounded w-50">

	<div class="display-4 content text-center">
		<img src="${baseURL}/dist/icons/student_1.png">
	</div>


	<form action="/subject_joiners/show/save" method="post">
		<div class="form-group row">
			<div class="form-group col">
				<h1>Subject Detail</h1>

				<div class="form-group">
					<select class="form-control" name="teacherEmail" id="teacherEmail">
						<option value="" disabled selected>Select Teacher</option>
					</select>
				</div>

				<div class="form-group">
					<select class="form-control" name="subjectId" id="subjectId">
						<option value="" disabled selected>Select Subject</option>
					</select>
				</div>

				<div class="form-group">
					<input type="text" class="form-control" name="studentEmail" id="studentEmail" placeholder="Enter Student Email">
				</div>


				<div class="p-2 row justify-content-center">
					<a href="/subject_joiners/show/subject_joiners" class="btn btn-danger col-2 text-white">Cancel</a>
					<button type="submit" class="btn btn-primary ml-4 col-2">Join Now</button>
				</div>
			</div>
		</div>
	</form>
</div>
<script>
	$(document).ready(
			function() {
				
				$.ajax({
					type : "POST",
					url : "${baseURL}/teacher/show/getAllTeachers",
					dataType : "json",
					success : function(result) {
						for (var i = 0; i < result.length; i++) {
							$("#teacherEmail").append("<option>" + result[i] + "</option>");
						}
					},
					error : function(xhr, status, error) {
						alert(error);
					}
				});
				
				$("#teacherEmail").change(function(){
					var baseURL="${baseURL}";
					var email=$("#teacherEmail").val();
					$.ajax({
						type : "POST",
						headers:{
							Accept : "application/json; charset=utf-8",
							"Content-Type":"application/json; charset=utf-8"
						},
						url : baseURL+"/subject/show/getAllSubjects/"+email,
						/*dataType: 'json',*/
						success : function(data) {
							var arr=data.subject;
							 $("#subjectId").empty().append('<option selected="selected" disabled value="Select Subject">Select Subject</option>');
							for(var i=0;i<arr.length;i++){
								 $("#subjectId").append("<option value="+arr[i].id+">" + arr[i].name + "</option>");
							}
						},
						error : function(xhr, status, error) {
							alert(error);
						}
					});
				});
		
			});
</script>