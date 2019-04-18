<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="container ">
		<p class="display-4">Joined Student List</p>
		<div class="row p-3 text-center">
			<div class="searchbox col-md-3 text-md-left">
				<span>Show</span>
				<select id="dataLimit" onchange="showJoinedStudentData()">
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
				<a href="/view/joinSubject.jsp" class="btn btn-warning btns">Join Subject</a>
			</div>
			<div class="col-md-3 mt-1">
				<button class="btn btn-danger btns" data-toggle="modal" data-target="#confirmationModel">Delete Selected</button>
			</div>
			<div class="col-md-3 mt-1">
				<span>Search:&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<input type="text" class="searchbox btns">
			</div>
		</div>
		<form id="subjectJoiners">
			<table class="table mytable table-hover table-sm table-responsive-md">
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
				<c:choose>
				<c:when test="${data==0}">
				<div class="card-header bg-dark text-white">
					<span class="h2">Nobody Joined This Subject</span>
				</div>
				</c:when>
				<c:otherwise>
				<div class="card-header bg-dark text-white">
					<input type="hidden" name="subjectId" value="${subjectJoinersData.get(0).subjectId }">
					<span class="h2"> ${subjectJoinersData.get(0).subjectId.subjectName} </span>
					<span> ${subjectJoinersData.get(0).subjectId.teacherId.email} </span>
					<span class="badge badge-light float-right mt-3"> ${subjectJoinersData.get(0).subjectId.timing} </span>
				</div>
				<thead class="tableHead">
					<tr>
						<th scope="col">
							<div class="p-2">
								<input type="checkbox" class="parentCheckBox" id="parent" onclick="multiSelector()" />
							</div>
						</th>
						<th scope="col">#</th>
						<th scope="col">Student Name</th>
						<th scope="col">Student Email</th>
						<th scope="col">Joined On</th>
					</tr>
				</thead>
				<tbody>
				
					<%
						int serialNumber = 0;
					%>
					<c:forEach var="items" items="${subjectJoinersData}">
						<tr id="${items.subjectId.subjectId}">
							<td class="special-td">
								<div class="p-2">
									<input type="checkbox" value="${items.subjectJoinerId}" class="childCheckBox" id="child${items.subjectJoinerId}" />
								</div>
							</td>
							<td><%=++serialNumber%></td>
							<input type="hidden" id="subjectId${items.studentId.studentId}">
							<td>
								<strong>${items.studentId.name}</strong>
							</td>
							<td>
								<strong>${items.studentId.email}</strong>
							</td>
							<td>
								<strong>${items.createdOn}</strong>
							</td>
						</tr>
					</c:forEach>
					</c:otherwise>
				</c:choose>
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
						<span>Do You Want to Remove these student joined with Subject Name</span>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deleteMultipleJoinedStudents()">Delete</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			/***********************Pagination Data*******************/
			function showJoinedStudentData() {
				var data = document.getElementById('dataLimit').value;
				document.location.href = "?data=" + data;

			}
		</script>