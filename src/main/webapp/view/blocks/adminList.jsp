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
				<button class="btn btn-danger btns" data-toggle="modal" data-target="#confirmationModel">Delete Selected</button>
			</div>
			<div class="col-md-3 mt-1">
				<span>Search:&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text"
					class="searchbox btns">
			</div>
		</div>


<form id="admins">
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
			<thead class="tableHead">
				<tr>
					<th scope="col"><div class="p-2">
							<input type="checkbox" class="parentCheckBox" id="parent" onclick="multiSelector()"/>
						</div></th>
					<th scope="col">#</th>
					<th scope="col">Name</th>
					<th scope="col">ID</th>
					<th scope="col">Mob</th>
					<th scope="col">Email</th>
				</tr>
			</thead>
			<tbody>
				<%
					int serialNumber = 0;
				%>
				
				<c:forEach var="admin" items="${admin}">
				
					<tr class="clickable-row" data-href="/admin/show/adminProfile/${admin.adminId}">
						<td class="special-td"><div class="p-2">
								<input type="checkbox" value="${admin.adminId}" class="childCheckBox" id="child${admin.adminId}"/>
							</div></td>
						<td><%=++serialNumber%></td>
						<td><strong>${admin.name}</strong></td>
						<td><strong>${admin.adminId}</strong></td>
						<td>${admin.mobile}</td>
						<td>${admin.email}</td>
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
        You Want to Delete These Admin
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deleteMultipleAdmin()">Delete</button>
      </div>
    </div>
  </div>
</div>

<script>

/***************************Admin Delete Confirmation Box******************************/
function adminDeleteConfirm(n)
{
	
	document.getElementById("admins").action="deleteadmin/"+n;
	document.getElementById("admins").method="post";
	document.getElementById("admins").submit();
}



/**********************Multiple Admin Delete*********************/
var adminIdCollection;
function deleteMultipleAdmin()
{
	adminIdCollection=new Set();
	var x=document.getElementsByClassName("childCheckBox");
	
	for(var i=0;i<x.length;i++)
	{
		if(x[i].checked)
		{
			adminIdCollection.add(x[i].value);
		}
		else
		{
			
		}
	}
	if(adminIdCollection.size==0){}
	else
	{
		var adminId = Array.from(adminIdCollection);
		adminDeleteConfirm(adminId);
	}
	
}

/***********************Pagination Data*******************/
function showAdminData()
{
	var data=document.getElementById('dataLimit').value;
    document.location.href = "admins?data="+data; 

}

</script>

	</div>
</div>