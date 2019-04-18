<c:choose>
	<c:when test="${adminDetail ==null}">
		<c:redirect url="${baseURL }/admin/login" />
	</c:when>
</c:choose>
<div class=" p-3 header d-flex justify-content-between">
	<form action="" method="">
		<c:choose>
			<c:when test="${contextDetail =='Student'}">
				<button formaction="/student/show/students" formmethod="get" class="btn btn-dark ">Back</button>
			</c:when>
			<c:when test="${contextDetail =='Teacher'}">
				<button formaction="/teacher/show/teachers" formmethod="get" class="btn btn-dark ">Back</button>
			</c:when>
			<c:when test="${contextDetail =='Subject'}">
				<button formaction="/subject/show/subjects" formmethod="get" class="btn btn-dark ">Back</button>
			</c:when>
			<c:when test="${(contextDetail =='Subject Joiners')||(contextDetail =='Joined Students')}">
				<button formaction="/subject_joiners/show/subject_joiners" formmethod="get" class="btn btn-dark ">Back</button>
			</c:when>
			<c:otherwise>
				<button formaction="/admin/show/showdashboard" formmethod="post" class="btn btn-dark ">Back</button>
			</c:otherwise>
		</c:choose>
	</form>
	<h5 class="text-white mr-4">Admin : ${adminDetail}</h5>
	<form id="logout">
		<c:if test="${context !=null}">
			<c:choose>
				<c:when test="${context=='joined_students'}">
					<button formaction="${baseURL }/subject_joiners/recycle/joined_students" formmethod="get"
						class="btn glyphicon glyphicon-trash adminHeaderTool"></button>
				</c:when>
				<c:otherwise>
					<button formaction="${baseURL }/${context}/recycle/${context}" formmethod="get" class="btn glyphicon glyphicon-trash adminHeaderTool"></button>
				</c:otherwise>
			</c:choose>
		</c:if>
		<button type="button" data-toggle="modal" data-target=".bd-example-modal-sm" class="btn glyphicon glyphicon-off adminHeaderTool"></button>
	</form>
	<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title id="confirmationModelTitle">Remote Academy</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<span>
						<strong>Do you want to logout</strong>
					</span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-success" data-dismiss="modal" onclick="logoutConfirmation()">Logout</button>
				</div>
			</div>
		</div>
	</div>
</div>
