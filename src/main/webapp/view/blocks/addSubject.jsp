<div class="card-header text-center header text-white"><a class="btn btn-dark glyphicon glyphicon-menu-left float-left" href="/subject/show/subjects"><span class="backbtn">Back</span></a>
<h3>Subject Registration</h3>
</div>
<div class="container border text-center w-25 error">${error}</div> 
<div class="container border text-center w-25 success">${success}</div> 
<div class="container con border rounded w-50" >

<div class="display-4 content text-center"><img src="${baseURL}/dist/icons/student_1.png"></div>
  

<form action="/subject/show/save" method="post">
    <div class="form-group row">
    <div class="form-group col"><h1>Subject Detail</h1>
  <div class="form-group">
    <input type="hidden" class="form-control" name="subjectId" id="subjectId">
  </div>
  
  <div class="form-group">
    <input type="text" class="form-control" name="teacherEmail" id="teacherEmail" placeholder="Enter Teacher Email">
  </div>
  
  <div class="form-group">
    <input type="text" class="form-control" name="subjectName" id="subjectName" placeholder="Enter Subject Name">
  </div>
  
  <div class="form-group">
   <select class="form-control" name="subjectDuration" id="subjectDuration">
     <option value="" disabled selected>Duration</option>
    <option value="1 Hours">1 Hours</option>
    <option value="2 Hours">2 Hours</option>
    <option value="3 Hours">3 Hours</option>
    <option value="4 Hours">4 Hours</option>
    <option value="5 Hours">5 Hours</option>
    </select>
  </div>
  

  <div class="form-group">Timing</div>
  <div class="form-row form-group">
    <div class="input-group form-group col-md-6">
    <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">From</label>
    </div>
      <select class="form-control" name="subjectTimeFrom" id="subjectTimeFrom">
     <option value="" disabled selected>Duration</option>
    <option value="6 AM">6 AM</option>
    <option value="7 AM">7 AM</option>
    <option value="8 AM">8 AM</option>
    <option value="9 AM">9 AM</option>
    <option value="10 AM">10 AM</option>
    <option value="11 AM">11 AM</option>
    <option value="12 AM">12 AM</option>
    <option value="1 PM">1 PM</option>
    <option value="2 PM">2 PM</option>
    <option value="3 PM">3 PM</option>
    <option value="4 PM">4 PM</option>
    <option value="5 PM">5 PM</option>
    <option value="6 PM">6 PM</option>
    <option value="7 PM">7 PM</option>
    <option value="8 PM">8 PM</option>
    <option value="9 PM">9 PM</option>
    <option value="10 PM">10 PM</option>
    </select>
    </div>
    <div class="input-group form-group col-md-6">
    <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">To</label>
    </div>
      <select class="form-control" name="subjectTimeTo" id="subjectTimeTo">
     <option value="" disabled selected>Duration</option>
    <option value="6 AM">6 AM</option>
    <option value="7 AM">7 AM</option>
    <option value="8 AM">8 AM</option>
    <option value="9 AM">9 AM</option>
    <option value="10 AM">10 AM</option>
    <option value="11 AM">11 AM</option>
    <option value="12 AM">12 AM</option>
    <option value="1 PM">1 PM</option>
    <option value="2 PM">2 PM</option>
    <option value="3 PM">3 PM</option>
    <option value="4 PM">4 PM</option>
    <option value="5 PM">5 PM</option>
    <option value="6 PM">6 PM</option>
    <option value="7 PM">7 PM</option>
    <option value="8 PM">8 PM</option>
    <option value="9 PM">9 PM</option>
    <option value="10 PM">10 PM</option>
    </select>
    </div>
  </div>
  <div class="form-group">
    <input type="text" class="form-control" name="subjectDescription" id="subjectDescription" placeholder="Write Description">
  </div>
  <div class="form-group">
    <input type="text" class="form-control" name="tutionFee" id="tutionFee" placeholder="Enter Tution Fee in Rupee">
  </div>
    <div class="p-2 row justify-content-center">
  <a href="/subject/show/subjects" class="btn btn-danger col-2 text-white" >Cancel</a>
  <button type="submit" class="btn btn-primary ml-4 col-2">Submit</button>
</div>
</div>
</div>
</form>   
</div>