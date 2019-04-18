<div class="card-header text-center header text-white"><a class="btn btn-dark glyphicon glyphicon-menu-left float-left" href="/student/show/students"><span class="backbtn">Back</span></a>
<h3>Student Registration</h3></div>
<div class="container con border rounded w-50" >
    <div class="display-4 content text-center"><img src="${baseURL}/dist/icons/student_1.png"></div>
   <form class="p-2" action="/student/show/save" method="post" id="form" enctype="multipart/form-data" modelAttribute="student">
    <div class="form-group row">
    <div class="form-group col tab"><h1>Student Profile</h1>
    <div class="form-group col-md-12 mt-3">
        <input type="hidden" class="form-control" name="studentId" id="studentId" value="">
    </div>
    <div class="form-group col-md-12">
        <input class="form-control" name="name" id="name" placeholder="Enter Name">
    </div>
        
    <div class="form-group col-md-12">
        <input class="form-control" name="mobile" id="mobile" placeholder="Mobile No.">
    </div>
        
    <div class="form-group col-md-12">
        <input class="form-control" name="email" id="email" placeholder="Email" type="email">
    </div>
    
     <div class="form-group col-md-12">
        <input class="form-control" name="password" id="password" placeholder="Password" type="password">
    </div>
    <div class="form-group col-md-12">
        <input class="form-control" name="dob" id="dob" placeholder="Date of Birth" type="date">
    </div>
  
    <div class="form-group col-md-12">
    <select class="form-control" name="gender" id="gender">
     <option value="" disabled selected>Gender</option>
    <option value="Male">Male</option>
    <option value="Female">Female</option>
    </select>
    </div>
   
    </div>
    
   
   
   
   
   
   <div class="form-group col tab"><h1>Address Detail</h1>
    <div class="form-group col-md-12 mt-3">
        <textarea class="form-control" name="address" id="address" rows="3" placeholder="e.g. 1421/24 Nanda Nagar"></textarea>
    </div>
   <div class="form-group col-md-12 mt-3">
        <input class="form-control" name="city" id="city" placeholder="City">
    </div>
    
     <div class="form-group col-md-12 mt-3">
        <input class="form-control" name="state" id="state" placeholder="State">
    </div>
    
     <div class="form-group col-md-12 mt-3">
        <input class="form-control" name="country" id="country" placeholder="Country">
    </div>
  
     <div class="form-group col-md-12 mt-3">
        <input class="form-control" name="pincode" id="pinCode" placeholder="Pin Code">
    </div>
  
    </div>
   
   <div class="form-group col tab"><h1>Academic Detail</h1>
    <div class="form-group col-md-12 mt-3">
        <input class="form-control" name="course" id="course" placeholder="e.g. PCM,PCB,BE,MCA">
    </div>
    <div class="form-group col-md-12">
        <input class="form-control" name="studentClass"  id="studentClass" placeholder="e.g. 12th,1st yr">
    </div>
    <div class="form-group col-md-12">
        <input class="form-control" name="institute" id="institute" placeholder="e.g Sagar Institute of Reaserch & Technology Indore">
    </div>
 
   
   
    </div>
    
    
    
    
    
    
    
    <div class="form-group col tab"><h1>Image</h1>
    
     <div class="form-group col-md-12">
         
    <input type="file" class="custom-file-input" name="image" id="image">
    <label class="custom-file-label">Choose file</label>
  
    </div>
   
   
    </div>
   <div class="form-group col-lg-12">
        <button type="button" id="btnNext" class="form-control btn btn-primary btns float-md-right" onclick="nextPrev(1)">Next</button>
        <button type="button" id="btnPrevious" class="form-control btn btn-primary btns float-md-right mr-2" onclick="nextPrev(-1)">Previous</button>
    </div>
    
   </div>
     <div class="text-center col">
    <span class="step"></span>
    <span class="step"></span>
    <span class="step"></span>
    <span class="step"></span>
  </div>
    
    
   </form>
    
</div>
<script type="text/Javascript">
showTab(0);</script>