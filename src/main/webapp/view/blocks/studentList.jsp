<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="container">

	<div class="container ">
		
		<p class="display-4">Students List</p>


		<div class="row p-3 text-center">
			<div class="searchbox col-md-3 text-md-left">
				<span>Show</span> <select id="dataLimit"
					onchange="showStudentData()">
					<option value="10">Select</option>
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="24">24</option>
					<option value="30">30</option>
					<option value="50">50</option>
					<option value="60">60</option>
					<option value="100">100</option>
				</select> <span>entries</span>
			</div>
			<div class="col-md-3 mt-1">
				<a href="/view/studentRegistration.jsp" class="btn btn-warning btns">Add
					Student</a>
			</div>
			<div class="col-md-3 mt-1">
				<button class="btn btn-danger btns" onclick="deleteMultipleStudent()">Delete Selected</button>
			</div>
			<div class="col-md-3 mt-1">
				<span>Search:&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text"
					class="searchbox btns">
			</div>
		</div>


<form id="students">
		<table class="table mytable table-hover table-sm table-responsive-md">
			<caption class="text-center">
				<div class="bar">
					<a href="#" class="btn">«</a>
					<c:forEach var="j" begin="1" end="${totalPage}">
						<c:choose>
							<c:when test="${param.pageNo==j}">
								<a href="?data=${param.data}&pageNo=${j}"
									class="btn btn-primary">${j}</a>
							</c:when>
							<c:otherwise>
								<a href="?data=${param.data}&pageNo=${j}" class="btn">${j}</a>
							</c:otherwise>
						</c:choose>

					</c:forEach>

					<a href="#" class="btn">»</a>
				</div>

			</caption>
			<thead class="thead-light">
				<tr>
					<th scope="col"><div class="p-2">
							<input type="checkbox" class="parentCheckBox" id="parent" onclick="multiSelector()"/>
						</div></th>
					<th scope="col">#</th>
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
					<th scope="col">Extra1</th>
				</tr>
			</thead>
			<tbody>
				<%
					int serialNumber = 0;
				%>
				
				<c:forEach var="student" items="${studentList}">
				
					<tr class="clickable-row" data-href="/student/show/studentProfile/${student.studentId}">
						<td class="special-td"><div class="p-2">
								<input type="checkbox" value="${student.studentId}" class="childCheckBox" id="child${student.studentId}"/>
							</div></td>
						<td><%=++serialNumber%></td>
						<td><strong>${student.name}</strong></td>
						<td><strong>${student.studentId}</strong></td>
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
						
						<td class="special-td">
							<button  type="button" class="btn btn-danger glyphicon glyphicon-remove" value="${student.studentId}" onclick="studentDeleteConfirm(this.value)"></button>
						</td>
					</tr>
					
				</c:forEach>
                

			</tbody>

		</table>
</form>


	</div>
</div>