function validatePassword(){
	var pass2=document.getElementById("conPassword").value;
	var pass1=document.getElementById("password").value;
	
	if(pass1!=pass2)
		document.getElementById("conPassword").setCustomValidity("Паролі не співпадають.");
	else
		document.getElementById("conPassword").setCustomValidity('');	 

}
