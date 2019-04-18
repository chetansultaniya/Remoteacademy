<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="container border text-center w-25 success">${success}</div>
	<div class="container ">
		<p class="display-4">Subject List</p>
		<div class="row p-3 text-center">
			<div class="searchbox col-md-3 text-md-left">
				<span>Show</span>
				<select id="dataLimit" onchange="showSubjectData()">
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
				<a href="/view/subjectRegistration.jsp" class="btn btn-warning btns">Add Subject</a>
			</div>
			<div class="col-md-3 mt-1">
				<button class="btn btn-danger btns" data-toggle="modal" data-target="#confirmationModel">Delete Selected</button>
			</div>
			<div class="col-md-3 mt-1">
				<span>Search:&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<input type="text" class="searchbox btns">
			</div>
		</div>
		<form id="subjects">
			<table class="table  mytable table-hover table-responsive-md">
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
						<th scope="col">Teacher Name</th>
						<th scope="col">Duration</th>
						<th scope="col">Timing</th>
						<th scope="col">Description</th>
						<th scope="col">Tution Fee</th>
						<th scope="col">Other</th>
					</tr>
				</thead>
				<tbody>
				
					<%
						int serialNumber = 0;
					%>
					<c:forEach var="subject" items="${subjectList}">
						<tr class="clickable-row" data-href="/subject/show/subjectProfile/${subject.subjectId}">
							<td class="special-td">
								<div class="p-2">
									<input type="checkbox" value="${subject.subjectId}" class="childCheckBox" id="child${subject.subjectId}" />
								</div>
							</td>
							<td><%=++serialNumber%></td>
							<td>
								<strong>${subject.subjectName}</strong>
							</td>
							<td>
								<strong>${subject.teacherId.name}</strong>
							</td>
							<td>${subject.duration}</td>
							<td>${subject.timing}</td>
							<td class="description">${subject.description}</td>
							<td>${subject.tutionFee}</td>
							<td>
								<button id="btn${subject.subjectId }" class="btn btn-primary" formaction="/subject_joiners/show/subjectJoinersProfile/${subject.subjectId}">Joiners</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</div>
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
				<span>Do You Want to Delete These Subjects</span>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deleteMultipleSubject()">Delete</button>
			</div>
		</div>
	</div>
</div>
<script>
	function subjectDeleteConfirm(n) {

		document.getElementById("subjects").action = "deletesubject/" + n;
		document.getElementById("subjects").method = "post";
		document.getElementById("subjects").submit();
	}

	var subjectIdCollection;
	function deleteMultipleSubject() {
		subjectIdCollection = new Set();
		var x = document.getElementsByClassName("childCheckBox");

		for (var i = 0; i < x.length; i++) {
			if (x[i].checked) {
				subjectIdCollection.add(x[i].value);
			} else {

			}
		}
		if (subjectIdCollection.size == 0) {
		} else {
			var subjectId = Array.from(subjectIdCollection);
			subjectDeleteConfirm(subjectId);
		}

	}

	/***********************Pagination Data*******************/
	function showSubjectData() {
		var data = document.getElementById('dataLimit').value;
		document.location.href = "subjects?data=" + data;

	}
</script>