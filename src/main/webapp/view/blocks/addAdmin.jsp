<div class="card-header text-center header text-white"><a class="btn btn-dark glyphicon glyphicon-menu-left float-left" href="/admin/show/admins"><span class="backbtn">Back</span></a>
<h3>Admin Registration</h3></div>
<div class="container con border rounded w-50">
    <div class="display-4 content text-center"><img src="${baseURL}/dist/icons/tutor_1.png"></div>
    <form class="row p-3" action="/admin/show/save" method="post" id="form">
    <div class="form-group col-md-12">
        <input type="hidden" class="form-control" name="id">
    </div>
    
    <div class="form-group col-md-12">
        <label>First Name</label>
        <input class="form-control" name="firstname" placeholder="Enter Name">
    </div>
    <div class="form-group col-md-12">
        <label>Last Name</label>
        <input class="form-control" name="lastname" placeholder="Enter LastName">
    </div>
        
    <div class="form-group col-md-12">
        <label>Mob No.</label>
        <input class="form-control" name="mobile" placeholder="Mobile No.">
    </div>
        
    <div class="form-group col-md-12">
        <label>Email</label>
        <input class="form-control" name="email" placeholder="Email" type="email">
    </div>
    
    <div class="form-group col-md-12">
        <label>Password</label>
        <input class="form-control" name="password" placeholder="Password" type="password">
    </div>
        
    <div class="form-group col-lg-12">
        <button type="submit" class="form-control btn btn-primary btns float-md-right">Next</button>
    </div>
   </form>
    
</div>