<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="card-header text-center text-white header"><a class="btn btn-dark glyphicon glyphicon-menu-left float-left" href="/teacher/show/teachers"><span class="backbtn">Back</span></a><h3>Edit Teacher</h3></div>
<div class="container mt-2" id="dataBody">

<form action="/teacher/show/save" method="post">
    <!-- ------------------Basic--------------------------- -->
<div class="card mt-2">
  <div class="card-header bg-dark text-white"><h4>Basic Detail</h4></div>
  <div class="card-body form-group">
      <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Id</label>
      <input type="text" class="form-control" name="id" readonly value="${teacher.teacherId}" id="inputEmail4">
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Name</label>
      <input type="text" class="form-control" name="name" value="${teacher.name}" id="inputPassword4" >
    </div>
     <div class="form-group col-md-6">
      <label for="inputPassword4">Mobile</label>
      <input type="text" class="form-control" name="mobile" value="${teacher.mobile}" id="inputPassword4">
    </div>
     <div class="form-group col-md-6">
      <label for="inputPassword4">Email</label>
      <input type="email" class="form-control" name="email" value="${teacher.email}" id="inputPassword4" >
    </div>
     <div class="form-group col-md-6">
      <label for="inputPassword4">Date Of Birth</label>
      <input type="date" class="form-control " name="dob" value="${teacher.dob}" id="inputPassword4">
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Gender</label>
     <select class="form-control" name="gender" id="gender">
     <option value="" disabled>Gender</option>
    <option value="${teacher.gender}" selected>${teacher.gender}</option>
    <c:choose>
    <c:when test="${teacher.gender.length()==4}">
            <option value="Female">Female</option>
        </c:when>
        <c:otherwise>
        <option value="Male">Male</option>
        </c:otherwise>
    </c:choose>
    </select>
    </div>
  </div>
  </div>
  
  
  
  
    <!-- ------------------Address--------------------------- -->
  <div class="card" >
  <div class="card-header bg-dark text-white"><h4>Address Detail</h4></div>
  <div class="card-body form-group">
      <div class="form-row">
    <div class="form-group col-md-12">
      <label for="inputEmail4">Address</label>
     <textarea class="form-control" name="address" id="address" rows="3" >${teacher.address}</textarea>
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">City</label>
      <input type="text" class="form-control" name="city" value="${teacher.city}" id="inputPassword4" >
    </div>
     <div class="form-group col-md-6">
      <label for="inputPassword4">State</label>
      <input type="text" class="form-control" name="state" value="${teacher.state}" id="inputPassword4">
    </div>
     <div class="form-group col-md-6">
      <label for="inputPassword4">Country</label>
      <input type="text" class="form-control" name="country" value="${teacher.country}" id="inputPassword4" >
    </div>
     <div class="form-group col-md-6">
      <label for="inputPassword4">Pincode</label>
      <input type="text" class="form-control" name="pincode" value="${teacher.pincode}" id="inputPassword4" >
    </div>
  </div>
  </div>
</div>
  
  
  
  
  
  <!-- ------------------Academic--------------------------- -->
    <div class="card" >
  <div class="card-header bg-dark text-white"><h4>Adcademic & Experience Detail</h4></div>
  <div class="card-body form-group">
      <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputPassword4">Occupation</label>
      <input type="text" class="form-control" name="occupation" value="${teacher.occupation}" id="inputPassword4">
    </div>
     <div class="form-group col-md-6">
      <label for="inputPassword4">Qualification</label>
      <input type="text" class="form-control" name="qualification" value="${teacher.qualification}" id="inputPassword4" >
    </div>
     <div class="form-group col-md-6">
      <label for="inputPassword4">Experience</label>
      <input type="text" class="form-control" name="experience" value="${teacher.experience}" id="inputPassword4" >
    </div>   
  </div>
  </div>
  <div class="row justify-content-center">
   <a href="/teacher/show/teachers" class="btn btn-danger col-1 text-white" >Cancel</a>
  <button type="submit" class="btn btn-primary ml-4 col-1">Submit</button>
</div>
</div>

  
  
  
</div>


</form>
</div>
