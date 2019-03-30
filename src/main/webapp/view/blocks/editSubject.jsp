<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="card-header text-center text-white header"><a class="btn btn-dark glyphicon glyphicon-menu-left float-left" href="/subject/show/subjects"><span class="backbtn">Back</span></a>
<h3>Edit Subject</h3></div>

<div class="container mt-2" id="dataBody">

<form action="/subject/show/save" method="post">
  
<div class="card mt-2">
  <div class="card-header bg-dark text-white"><h4>Subject Detail</h4></div>
 
  <div class="card-body form-group">
      <div class="form-row">
    <div class="form-group col-md-6">
      <label>Subject Id</label>
      <input type="text" class="form-control" name="subjectId" readonly value="${subject.subjectId}" id="inputEmail4">
    </div>
     <div class="form-group col-md-6">
      <label>Subject Name</label>
      <input type="text" class="form-control" name="subjectName" value="${subject.subjectName}" id="inputPassword4">
    </div>
    <div class="form-group col-md-6">
      <label for="inputEmail4">Teacher Id</label>
      <input type="text" class="form-control" name="teacherId" readonly value="${subject.teacherId.teacherId}" id="inputEmail4">
    </div>
    <div class="form-group col-md-6">
      <label>Teacher Name</label>
      <input type="text" class="form-control" name="teacherName" readonly value="${subject.teacherId.name}" id="inputPassword4" >
    </div>
    
   
   
     <div class="form-group col-md-6">
      <label>Timing</label>
      <input  type="text" class="form-control" name="timing" value="${subject.timing}" id="inputPassword4" >
    </div>
    <div class="form-group col-md-6">
      <label>Duration</label>
      <input type="text" class="form-control" name="subjectDuration" value="${subject.duration}" id="inputPassword4" >
    </div>
   
     <div class="form-group col-md-6">
      <label>Tution Fee</label>
      <input type="text" class="form-control" name="tutionFee" value="${subject.tutionFee}" id="inputPassword4" >
    </div>
    
     <div class="form-group col-md-6">
      <label>Description</label>
      <input type="text" class="form-control" name="subjectDescription" value="${subject.description}" id="inputPassword4" >
    </div>
    
  </div>
  </div>
  
  <div class="row justify-content-center">
   <a href="/subject/show/subjects" class="btn btn-danger col-1 text-white" >Cancel</a>
  <button type="submit" class="btn btn-primary ml-4 col-1">Submit</button>
</div>
</div>
  </form>
</div>



</div>