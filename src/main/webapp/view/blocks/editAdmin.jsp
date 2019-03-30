<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="card-header text-center text-white header"><a class="btn btn-dark glyphicon glyphicon-menu-left float-left" href="/admin/show/admins"><span class="backbtn">Back</span></a>
<h3>Edit Admin</h3></div>

<div class="container mt-2" id="dataBody">

<form action="/admin/show/save" method="post">
  
<div class="card mt-2">
  <div class="card-header bg-dark text-white"><h4>Basic Detail</h4></div>
 
  <div class="card-body form-group">
      <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Id</label>
      <input type="text" class="form-control" name="id" readonly value="${admin.adminId}" id="inputEmail4">
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Name</label>
      <input type="text" class="form-control" name="name" value="${admin.name}" id="inputPassword4" >
    </div>
     <div class="form-group col-md-6">
      <label for="inputPassword4">Mobile</label>
      <input type="text" class="form-control" name="mobile" value="${admin.mobile}" id="inputPassword4">
    </div>
     <div class="form-group col-md-6">
      <label for="inputPassword4">Email</label>
      <input type="email" class="form-control" name="email" value="${admin.email}" id="inputPassword4" >
    </div>

  </div>
  </div>
  
  <div class="row justify-content-center">
   <a href="/admin/show/admins" class="btn btn-danger col-1 text-white" >Cancel</a>
  <button type="submit" class="btn btn-primary ml-4 col-1">Submit</button>
</div>
</div>
  </form>
</div>



</div>