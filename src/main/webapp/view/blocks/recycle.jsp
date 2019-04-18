<input type="hidden" id="path" value="${contextDetail}">
<div class="container">
	<p class="display-4">${contextDetail}Trash</p>
	<div class="row p-3 text-center">
		<div class="searchbox col-md-3 text-md-left">
			<span>Show</span>
			<select id="dataLimit" onchange="showPageData()">
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
			<button class="btn btn-dark btns" id="delete" data-toggle="modal" data-target="#confirmationModel">Delete Selected</button>
		</div>
		<div class="col-md-3 mt-1">
			<button class="btn btn-danger btns" id="restore" data-toggle="modal" data-target="#confirmationModel">Restore Selected</button>
		</div>
		<div class="col-md-3 mt-1">
			<span>Search:&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<input type="text" class="searchbox btns">
		</div>
	</div>
	<form id="records">
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
			<thead class="tableHeadRecycle">
				<tr>
					<th scope="col">
						<div class="p-2">
							<input type="checkbox" class="parentCheckBox" id="parent" onclick="multiSelector()" />
						</div>
					</th>
					<th scop="col">#</th>
					<c:if test="${(contextDetail=='Student')||(contextDetail=='Teacher')}">
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
						<!--For Student -->
						<c:if test="${contextDetail=='Student'}">
							<th scope="col">Course</th>
							<th scope="col">Class</th>
							<th scope="col">Institute</th>
						</c:if>
						<!--For Teacher -->
						<c:if test="${contextDetail=='Teacher'}">
							<th scope="col">Occupation</th>
							<th scope="col">Qualification</th>
							<th scope="col">Experience</th>
						</c:if>
					</c:if>
					<!--For Subject -->
					<c:if test="${contextDetail=='Subject'}">
						<th scope="col">Subject Name</th>
						<th scope="col">Subject ID</th>
						<th scope="col">Teacher Id</th>
						<th scope="col">Teacher Name</th>
						<th scope="col">Duration</th>
						<th scope="col">Timing</th>
						<th scope="col">Description</th>
						<th scope="col">Tution Fee</th>
					</c:if>
					<!--For Subject Joiners -->
					<c:if test="${(contextDetail=='Subject Joiners')||(contextDetail=='Joined Students')}">
						<th scope="col">Subject Name</th>
						<th scope="col">Teacher Email</th>
						<th scope="col">Joined Student</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<%
					int serialNumber = 0;
				%>
				<c:forEach var="items" items="${records}">
					<tr>
						<td class="special-td">
							<div class="p-2">
								<c:if test="${contextDetail=='Student'}">
									<input type="checkbox" value="${items.studentId }" class="childCheckBox" id="child${items.studentId }" />
								</c:if>
								<c:if test="${contextDetail=='Teacher'}">
									<input type="checkbox" value="${items.teacherId }" class="childCheckBox" id="child${items.teacherId }" />
								</c:if>
								<c:if test="${contextDetail=='Subject'}">
									<input type="checkbox" value="${items.subjectId }" class="childCheckBox" id="child${items.subjectId }" />
								</c:if>
								<c:if test="${contextDetail=='Subject Joiners'}">
									<input type="checkbox" value="${items.subjectId.subjectId}" class="childCheckBox" id="child${items.subjectId.subjectId}" />
								</c:if>
							</div>
						</td>
						<td><%=++serialNumber%></td>
						<c:if test="${contextDetail=='Student'}">
							<td>
								<strong>${items.name}</strong>
							</td>
							<td>
								<strong>${items.studentId}</strong>
							</td>
						</c:if>
						<c:if test="${contextDetail=='Teacher'}">
							<td>
								<strong>${items.name}</strong>
							</td>
							<td>
								<strong>${items.teacherId}</strong>
							</td>
						</c:if>
						<c:if test="${contextDetail=='Subject'}">
							<td>
								<strong>${items.subjectName}</strong>
							</td>
							<td>
								<strong>${items.subjectId}</strong>
							</td>
						</c:if>
						<c:if test="${(contextDetail=='Student')||(contextDetail=='Teacher')}">
							<td>${items.mobile}</td>
							<td>${items.email}</td>
							<td>${items.dob}</td>
							<td>${items.gender}</td>
							<td>${items.address}</td>
							<td>${items.city}</td>
							<td>${items.state}</td>
							<td>${items.country}</td>
							<td>${items.pincode}</td>
							<c:if test="${contextDetail=='Student'}">
								<td>${items.course}</td>
								<td>${items.studentClass}</td>
								<td>${items.institute}</td>
							</c:if>
							<c:if test="${contextDetail=='Teacher'}">
								<td>${items.occupation}</td>
								<td>${items.qualification}</td>
								<td>${items.experience}</td>
							</c:if>
						</c:if>
						<c:if test="${contextDetail=='Subject' }">
							<td>${items.teacherId.teacherId}</td>
							<td>${items.teacherId.name}</td>
							<td>${items.duration}</td>
							<td>${items.timing}</td>
							<td>${items.description}</td>
							<td>${items.tutionFee}</td>
						</c:if>
						<c:if test="${contextDetail=='Subject Joiners' }">
							<input type="hidden" id="subjectId${items.subjectId.subjectId}">
							<td>
								<strong>${items.subjectId.subjectName}</strong>
							</td>
							<td>
								<strong>${items.subjectId.teacherId.email}</strong>
							</td>
							<td>
								<h5>
									<span class="badge badge-success">${items.counter}</span>
								</h5>
							</td>
						</c:if>
						<c:if test="${contextDetail=='Joined Students' }">
							<input type="hidden" id="subjectJoinerId${items.subjectJoinerId}">
							<td>
								<strong>${items.subjectId.subjectName}</strong>
							</td>
							<td>
								<strong>${items.subjectId.teacherId.email}</strong>
							</td>
							<td>
							<strong>${item.studentId.studentEmail}</strong>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<!-- Modal -->
	<div class="modal fade" id="confirmationModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title id="confirmationModelTitle">Remote Academy</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<span id="instruction">You Want to Restore These Records</span>
				</div>
				<div class="modal-footer">
					<button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					<button type="button" id="operation" class="btn btn-primary" data-dismiss="modal" value="restore"
						onclick="restoreOrDeleteMultipleData(this.value)">Restore</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	/***************************Records Restore******************************/
	function recordRestoreConfirm(n) {
		var path = document.getElementById('path').value;
		if (path == "Student") {
			document.getElementById("records").action = "restorestudents/" + n;
		} else if (path == 'Teacher') {
			document.getElementById("records").action = "restoreteachers/" + n;
		} else if (path == 'Subject') {
			document.getElementById("records").action = "restoresubjects/" + n;
		} else if (path == 'Subject Joiners') {
			document.getElementById("records").action = "restoresubjectjoiners/"
					+ n;
		}
		document.getElementById("records").method = "post";
		document.getElementById("records").submit();
	}

	/***************************Records Delete******************************/
	function recordDeleteConfirm(n) {
		var path = document.getElementById('path').value;
		if (path == "Student") {
			document.getElementById("records").action = "harddeletestudents/"
					+ n;
		} else if (path == 'Teacher') {
			document.getElementById("records").action = "harddeleteteachers/"
					+ n;
		} else if (path == 'Subject') {
			document.getElementById("records").action = "harddeletesubjects/"
					+ n;
		} else if (path == 'Subject Joiners') {
			document.getElementById("records").action = "harddeletesubjectjoiners/"
					+ n;
		}

		document.getElementById("records").method = "post";
		document.getElementById("records").submit();
	}
	/**********************Select Multiple Records*********************/
	var idCollection;
	function restoreOrDeleteMultipleData(n) {
		idCollection = new Set();
		var x = document.getElementsByClassName("childCheckBox");

		for (var i = 0; i < x.length; i++) {
			if (x[i].checked) {

				idCollection.add(x[i].value);
			} else {

			}
		}

		if (idCollection.size == 0) {
		} else {
			var id = Array.from(idCollection);
			if (n == "restore") {
				recordRestoreConfirm(id);
			} else {
				recordDeleteConfirm(id);
			}

		}

	}

	/***********************Pagination Data*******************/
	function showPageData(n) {
		var data = document.getElementById('dataLimit').value;
		var path = document.getElementById('path').value;
		document.location.href = "?data=" + data;
	}

	$(document)
			.ready(

					function() {
						$('.toast').toast('show');
						$(".header").css('background', '#d10228');
						$("#delete")
								.click(
										function() {
											$("#instruction")
													.html(
															"Do you want to delete these entries?<br>Remember that these records will not be stored after the delete.");
											$("#operation").text("Delete");
											$("#operation").val("delete");
										});

						$("#restore")
								.click(
										function() {
											$("#instruction")
													.html(
															"Do You want to Restore These Records");
											$("#operation").text("Restore");
											$("#operation").val("restore");
										});

					});
</script>