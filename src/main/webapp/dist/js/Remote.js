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



function fixStepIndicator(n) {
	
	  var i, x = document.getElementsByClassName("step");
	  for (i = 0; i < x.length; i++) {
	    x[i].className = x[i].className.replace(" active", "");
	  }
	
	  x[n].className += " active";
	}



$(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });

})


