<%@include file="blocks/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="container">
		<p class="display-4">Admin List</p>


		<div class="row p-3 text-center">
			<div class="searchbox col-md-3 text-md-left">
				<span>Show</span> <select id="dataLimit"
					onchange="showAdminData()">
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
				<a href="/view/adminRegistration.jsp" class="btn btn-warning btns">Add
					Admin</a>
			</div>
			<div class="col-md-3 mt-1">
				<button class="btn btn-danger btns">Delete Selected</button>
			</div>
			<div class="col-md-3 mt-1">
				<span>Search:&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text"
					class="searchbox btns">
			</div>
		</div>



		<table class="table mytable table-hover table-sm table-responsive-md">
			<caption class="text-center">
				<div class="bar">
					<a href="#" class="btn">�</a>
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

					<a href="#" class="btn">�</a>
				</div>

			</caption>
			<thead class="thead-light">
				<tr>
					<th scope="col"><div class="p-2">
							<input type="checkbox" />
						</div></th>
					<th scope="col">#</th>
					<th scope="col">Name</th>
					<th scope="col">ID</th>
					<th scope="col">Mob</th>
					<th scope="col">Email</th>
					<th scope="col">Extra</th>
				</tr>
			</thead>
			<tbody>
				<%
					int serialNumber = 0;
				%>
				
				<c:forEach var="admin" items="${admin}">
				
					<tr class="clickable-row" data-href="/admin/show/adminProfile/${admin.adminId}">
						<td><div class="p-2">
								<input type="checkbox" class="cb"/>
							</div></td>
						<td><%=++serialNumber%></td>
						<td>${admin.name}</td>
						<td>${admin.adminId}</td>
						<td>${admin.mobile}</td>
						<td>${admin.email}</td>
						
						<td><form action="/admin/show/deleteadmin/${admin.adminId}">
								<button class="btn btn-danger glyphicon glyphicon-remove"></button>
							</form></td>
					</tr>
					
				</c:forEach>
                

			</tbody>

		</table>



	</div>
</div>
<%@include file="blocks/footer.jsp"%>