/***********************Pagination Data*******************/
function showStudentData()
{
	var data=document.getElementById('dataLimit').value;
    document.location.href = "students?data="+data; 

}

function showTeacherData()
{
	var data=document.getElementById('dataLimit').value;
    document.location.href = "teachers?data="+data; 

}

function showAdminData()
{
	var data=document.getElementById('dataLimit').value;
    document.location.href = "admins?data="+data; 

}










/**************************Registration Tabs***************************/
var currentTab=0;
function showTab(n)
{
	var x = document.getElementsByClassName("tab");
	  x[n].style.display = "block";
	 
	

	 
	  if (n == 0) {
	    document.getElementById("btnPrevious").style.display = "none";
	  } else {
	    document.getElementById("btnPrevious").style.display = "inline";
	  }
	  if (n == (x.length - 1)) {
	    document.getElementById("btnNext").innerHTML = "Submit";
	  } else {
	    document.getElementById("btnNext").innerHTML = "Next";
	  }
	  fixStepIndicator(n)
	
}

var data=new Map(); 
function nextPrev(n)
{
	 var x = document.getElementsByClassName("tab");
	 var id=document.getElementById("id");
	 var tab=x[currentTab].children;
	  
	  for(var i=1;i<tab.length;i++)
		{ 
		  var div=tab[i].children;
		  var inputs=div[0];
		
		  data.set(inputs.getAttribute("name"),inputs.value);
		  
		}
	  
	  x[currentTab].style.display = "none";
	  currentTab = currentTab + n;
	  if (currentTab >= x.length) {    
		  currentTab=0;  
		  document.getElementById("form").submit();
	  }
	 
	  showTab(currentTab);
}



function fixStepIndicator(n)
{
	  var i, x = document.getElementsByClassName("step");
	  for (i = 0; i < x.length; i++)
	  {
	    x[i].className = x[i].className.replace(" active", "");
	  }
	
	  x[n].className += " active";
}











/***************************Student Delete Confirmation Box******************************/
function studentDeleteConfirm(n)
{
	x=confirm("You want to delete this Student Entries");
	if(x)
	{
	document.getElementById("students").action="deletestudent/"+n;
	document.getElementById("students").method="post";
	document.getElementById("students").submit();
	}
	else{}
}



/**********************Multiple Student Delete*********************/
var studentIdCollection;
function deleteMultipleStudent()
{
	studentIdCollection=new Set();
	var x=document.getElementsByClassName("childCheckBox");
	
	for(var i=0;i<x.length;i++)
	{
		if(x[i].checked)
		{
			studentIdCollection.add(x[i].value);
		}
		else
		{
			
		}
	}
	if(studentIdCollection.size==0){}
	else
	{
		var studentId = Array.from(studentIdCollection);
		studentDeleteConfirm(studentId);
	}
	
}








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






/***************************Admin Delete Confirmation Box******************************/
function adminDeleteConfirm(n)
{
	x=confirm("You want to delete this Admin Entries");
	if(x)
	{
	document.getElementById("admins").action="deleteadmin/"+n;
	document.getElementById("admins").method="post";
	document.getElementById("admins").submit();
	}
	else{}
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









/***************************Table Row Event Handler******************************/
$(document).ready(function($){
  $(".clickable-row").on("click","td:not(.special-td)",function(){
	  window.location = $(this).parent().data("href");
  });
});








/**************************Multi Selection For Checkbox in Table***********************/

var counter=0;
function multiSelector()
{
	var students=document.getElementsByClassName("childCheckBox");
	for(var i=0;i<students.length;i++)
	{
		if(counter%2==0)
		{
		students[i].checked=true;
		}
		else
		{
		
			students[i].checked=false;
		}
	}

	counter++;
}











