<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<body>

<h1>INTUIT EMPLOYEE SEARCH</h1>

<h3>Start typing a name in the input field below:</h3>

<form action=""> 
First name: <input type="text" id="txt1" onkeyup="showHint(this.value)">
</form>

<p>Suggestions:<a href="" id = "aid" onclick='newwin()'> <span  id="txtHint"  ></span> </a> </p>

 Reacent search : <span  id="searchEmployee"></span>  

<br><a href ='http://localhost:8080/login'>Home</a>

<script>
function showHint(str) {
  var xhttp;
  if (str.length == 0) { 
    document.getElementById("txtHint").innerHTML = "";
    return;
  }
  xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
    	
    	var arrayOfObjects = eval(this.responseText)
    	 
    	//alert(arrayOfObjects.length);
    	for (var i = 0; i < arrayOfObjects.length; i++) {
    		var   object = arrayOfObjects[i];
    	    
    	       document.getElementById("txtHint").innerHTML += object["username"]+",";
    	       
    	       
    	       
    	}
    	
    	
    }
  };
  xhttp.open("GET", "http://localhost:8080/tags/get_tag_list?username="+str, true);
  xhttp.send();   
}

function newwin() { 
	
	window.open("http://localhost:8080/tags/get_tag_list?username=", "mywin" , "width=650,height=650");
	
	/* var idData=document.getElementById("txtHint");
		
		for(var i =0 ; i<idData.length; i++){
			
			alert("idData :: "+idData[i]); 
		}
		
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      document.getElementById("searchEmployee").innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("GET", "http://localhost:8080/tags/get_tag_list?username=", true);
	  xhttp.send();  */
}
</script>

</body>
</html>
