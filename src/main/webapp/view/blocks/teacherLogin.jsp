<div class="container border text-center rounded col-sm-5 con p-5">
    <div class="display-4 content">Teacher</div>
    <div class="container  text-left">
       <form action="${baseURL}/teacher/show/teacherdashboard" method="post">
  			<div class="form-group  col-xs-1 ">
    			<label >Email address</label>
    			<input type="email" class=" form-control" name="email" placeholder="Enter Email">
  			</div>
  			<div class="form-group col-xs-1 ">
    			<label >Password</label>
    			<input type="password" class="form-control " name="password" placeholder="Password">
  			</div>
  			<div class="text-center text-black error"><div>${error }</div></div>
  			<div class="form-group text-center row justify-content-center">
      			<a class="btn btn-primary col-md-3 form-control mt-4 text-white signUpBtn">SignUp</a>
      			<div class="col-md-1"></div>
      			<button class="btn btn-primary col-md-3 form-control mt-4">Login</button>
      			
  			</div>
		</form>    
    </div>
</div>