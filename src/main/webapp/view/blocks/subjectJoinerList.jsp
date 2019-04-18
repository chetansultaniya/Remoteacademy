<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="container ">
		<p class="display-4">Subject Joiners List</p>
		<div class="row p-3 text-center">
			<div class="searchbox col-md-3 text-md-left">
				<span>Show</span>
				<select id="dataLimit" onchange="showSubjectJoinersData()">
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
				<thead class="tableHead">
					<tr>
						<th scope="col">
							<div class="p-2">
								<input type="checkbox" class="parentCheckBox" id="parent" onclick="multiSelector()" />
							</div>
						</th>
						<th scope="col">#</th>
						<th scope="col">Subject Name</th>
						<th scope="col">Teacher Email</th>
						<th scope="col">Joined Student</th>
					</tr>
				</thead>
				<tbody>
					<%
						int serialNumber = 0;
					%>
					<c:forEach var="items" items="${subjectJoinersList}">
						<tr class="clickable-row" id="${items.subjectId.subjectId}"
							data-href="/subject_joiners/show/subjectJoinersProfile/${items.subjectId.subjectId}">
							<td class="special-td">
								<div class="p-2">
									<input type="checkbox" value="${items.subjectId.subjectId}" class="childCheckBox" id="child${items.subjectId.subjectId}" />
								</div>
							</td>
							<td><%=++serialNumber%></td>
							<input type="hidden" id="subjectId${items.subjectId}">
							<td>
								<strong>${items.subjectId.subjectName}</strong>
							</td>
							<td>
								<strong>${items.subjectId.teacherId.email}</strong>
							</td>
							<td>
								<h5>
									<span class="badge badge-dark">${items.counter}</span>
								</h5>
							</td>
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
						<h5 class="modal-title" id="confirmationModelTitle">Remote Academy</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<span>Do You Want to Delete These Subject Joiners</span>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deleteMultipleSubjectJoiners()">Delete</button>
					</div>
				</div>
			</div>
		</div>
		<script>
		
		/***************************Subject Joiners Delete Confirmation Box******************************/
		function DeleteConfirm(n) {
			document.getElementById("subjectJoiners").action = "deletesubjectjoiners/"+ n;
				document.getElementById("subjectJoiners").method = "post";
				document.getElementById("subjectJoiners").submit();
			}

			/**********************Multiple Subject Joiners Delete*********************/
			var subjectJoinersIdCollection;
			function deleteMultipleSubjectJoiners() {
				subjectJoinersIdCollection = new Set();
				var x = document.getElementsByClassName("childCheckBox");

				for (var i = 0; i < x.length; i++) {
					if (x[i].checked) {
						subjectJoinersIdCollection.add(x[i].value);
					} else {

					}
				}
				if (subjectJoinersIdCollection.size == 0) {
				} else {
					var Id = Array.from(subjectJoinersIdCollection);
					DeleteConfirm(Id);
				}

			}

			/***********************Pagination Data*******************/
			function showSubjectJoinersData() {
				var data = document.getElementById('dataLimit').value;
				document.location.href = "subject_joiners?data=" + data;

			}
		</script>