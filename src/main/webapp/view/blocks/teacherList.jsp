<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="container">
	
		<p class="display-4">Teacher List</p>


		<div class="row p-3 text-center">
			<div class="searchbox col-md-3 text-md-left">
				<span>Show</span> <select id="dataLimit"
					onchange="showTeacherData()">
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
				<a href="/view/teacherRegistration.jsp" class="btn btn-warning btns">Add
					Teacher</a>
			</div>
			<div class="col-md-3 mt-1">
				<button class="btn btn-danger btns" onclick="deleteMultipleTeacher()">Delete Selected</button>
			</div>
			<div class="col-md-3 mt-1">
				<span>Search:&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text"
					class="searchbox btns">
			</div>
		</div>


<form id="teachers">
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
					<th scope="col">Occupation</th>
					<th scope="col">Qualification</th>					
					<th scope="col">Experience</th>					
					<th scope="col">Extra1</th>
				</tr>
			</thead>
			<tbody>
				<%
					int serialNumber = 0;
				%>
				
				<c:forEach var="teacher" items="${teacher}">
				
					<tr class="clickable-row" data-href="/teacher/show/teacherProfile/${teacher.teacherId}">
						<td class="special-td"><div class="p-2">
								<input type="checkbox" value="${teacher.teacherId}" class="childCheckBox" id="child${teacher.teacherId}"/>
							</div></td>
						<td><%=++serialNumber%></td>
						<td><strong>${teacher.name}</strong></td>
						<td><strong>${teacher.teacherId}</strong></td>
						<td>${teacher.mobile}</td>
						<td>${teacher.email}</td>
						<td>${teacher.dob}</td>
						<td>${teacher.gender}</td>
						<td>${teacher.address}</td>
						<td>${teacher.city}</td>
						<td>${teacher.state}</td>
						<td>${teacher.country}</td>
						<td>${teacher.pincode}</td>
						<td>${teacher.occupation}</td>
						<td>${teacher.qualification}</td>
						<td>${teacher.experience}</td>
						
						<td class="special-td">
							<button  type="button" class="btn btn-danger glyphicon glyphicon-remove" value="${teacher.teacherId}" onclick="teacherDeleteConfirm(this.value)"></button>
						</td>
					</tr>
					
				</c:forEach>
                

			</tbody>

		</table>
</form>
<script>

/***************************Teacher Delete Confirmation Box******************************/
function teacherDeleteConfirm(n)
{
	x=confirm("You want to delete this Teacher Entries");
	if(x)
	{
	document.getElementById("teachers").action="deleteteacher/"+n;
	document.getElementById("teachers").method="post";
	document.getElementById("teachers").submit();
	}
	else{}
}

var teacherIdCollection;
function deleteMultipleTeacher()
{
	teacherIdCollection=new Set();
	var x=document.getElementsByClassName("childCheckBox");
	
	for(var i=0;i<x.length;i++)
	{
		if(x[i].checked)
		{
			teacherIdCollection.add(x[i].value);
		}
		else
		{
			
		}
	}
	if(teacherIdCollection.size==0){}
	else
	{
		var teacherId = Array.from(teacherIdCollection);
		teacherDeleteConfirm(teacherId);
	}
	
}

/***********************Pagination Data*******************/
function showTeacherData()
{
	var data=document.getElementById('dataLimit').value;
    document.location.href = "teachers?data="+data; 

}

</script>

	</div>
</div>