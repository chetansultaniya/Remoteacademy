<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="container ">
		<p class="display-4">Students List</p>
		<div class="row p-3 text-center">
			<div class="searchbox col-md-3 text-md-left">
				<span>Show</span>
				<select id="dataLimit" onchange="showStudentData()">
					<option value="10">Select</option>
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="24">24</option>
					<option value="30">30</option>
					<option value="50">50</option>
					<option value="60">60</option>
					<option value="100">100</option>
				</select>
				<span>entries</span>
			</div>
			<div class="col-md-3 mt-1">
				<a href="/view/studentRegistration.jsp" class="btn btn-warning btns">Add Student</a>
			</div>
			<div class="col-md-3 mt-1">
				<button class="btn btn-danger btns" data-toggle="modal" data-target="#confirmationModel">Delete Selected</button>
			</div>
			<div class="col-md-3 mt-1">
				<span>Search:&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<input type="text" class="searchbox btns">
			</div>
		</div>
		<form id="students">
			<table class="table  mytable table-hover table-sm table-responsive-md">
				<caption class="text-center">
					<div class="bar">
						<a href="#" class="btn">«</a>
						<c:forEach var="j" begin="1" end="${totalPage}">
							<c:choose>
								<c:when test="${param.pageNo==j}">
									<a href="?data=${param.data}&pageNo=${j}" class="btn btn-primary">${j}</a>
								</c:when>
								<c:otherwise>
									<a href="?data=${param.data}&pageNo=${j}" class="btn">${j}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<a href="#" class="btn">»</a>
					</div>
				</caption>
				<thead class="tableHead">
					<tr>
						<th scope="col">
							<div class="p-2">
								<input type="checkbox" class="parentCheckBox" id="parent" onclick="multiSelector()" />
							</div>
						</th>
						<th scope="col">#</th>
						<th scope="col">Image</th>
						<th scope="col">Name</th>
						<th scope="col">ID</th>
						<th scope="col">Mob</th>
						<th scope="col">Email</th>
						<th scope="col">DOB</th>
						<th scope="col">Gender</th>
						<th scope="col">Address</th>
						<th scope="col">City</th>
						<th scope="col">State</th>
						<th scope="col">Country</th>
						<th scope="col">PinCode</th>
						<th scope="col">Course</th>
						<th scope="col">Class</th>
						<th scope="col">Institute</th>
						
					</tr>
				</thead>
				<tbody>
					<%
						int serialNumber = 0;
					%>
					<c:set var="count" value="1" scope="page" />
					<c:forEach var="student" items="${studentList}">
						<tr class="clickable-row" id="${student.studentId}" data-href="/student/show/studentProfile/${student.studentId}">
							<td class="special-td">
								<div class="p-2">
									<input type="checkbox" value="${student.studentId}" class="childCheckBox" id="child${student.studentId}" />
								</div>
							</td>
							<td><%=++serialNumber%></td>
							<td>
								<img src="" class="rounded" width="100px" height="100px" alt="No Image" data-id="${student.studentId}" id="studentImg${count}">
							</td>
							<td>
								<strong>${student.name}</strong>
							</td>
							<td>
								<strong>${student.studentId}</strong>
							</td>
							<td>${student.mobile}</td>
							<td>${student.email}</td>
							<td>${student.dob}</td>
							<td>${student.gender}</td>
							<td>${student.address}</td>
							<td>${student.city}</td>
							<td>${student.state}</td>
							<td>${student.country}</td>
							<td>${student.pincode}</td>
							<td>${student.course}</td>
							<td>${student.studentClass}</td>
							<td>${student.institute}</td>
							
						</tr>
						<c:set var="count" value="${count + 1}" scope="page" />
					</c:forEach>
				</tbody>
			</table>
		</form>
		<!-- Modal -->
		<div class="modal fade" id="confirmationModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="confirmationModelTitle">Remote Academy</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<span>Do You Want to Delete These Students</span>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deleteMultipleStudent()">Delete</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			/***************************Student Delete Confirmation Box******************************/
			function studentDeleteConfirm(n) {

				document.getElementById("students").action = "deletestudent/"
						+ n;
				document.getElementById("students").method = "post";
				document.getElementById("students").submit();
			}

			/**********************Multiple Student Delete*********************/
			var studentIdCollection;
			function deleteMultipleStudent() {
				studentIdCollection = new Set();
				var x = document.getElementsByClassName("childCheckBox");

				for (var i = 0; i < x.length; i++) {
					if (x[i].checked) {
						studentIdCollection.add(x[i].value);
					} else {

					}
				}
				if (studentIdCollection.size == 0) {
				} else {
					var studentId = Array.from(studentIdCollection);
					studentDeleteConfirm(studentId);
				}

			}

			/***********************Pagination Data*******************/
			function showStudentData() {
				var data = document.getElementById('dataLimit').value;
				document.location.href = "students?data=" + data;

			}

			
			$(document).ready(function() {
				var baseURL = "${baseURL}";
				var imgCount = 1;
				var listSize = "${studentList.size()}";
				function getImage() {
					studentId = $("#studentImg" + imgCount).data('id');
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
									if (response.length == 0) {
									$("#studentImg"+ imgCount).attr('src',baseURL+ "/dist/icons/person.jpg");
									} 
									else {
									$("#studentImg"+ imgCount).attr('src',"data:image/jpg;base64,"+ response);
									}
									imgCount++;
									if (imgCount <= listSize) {
									getImage();
									}
									},
									error : function(xhr, status,
									error) {
									alert(error);
									}
								});
							}
								getImage();
					});
		</script>
	</div>
</div>