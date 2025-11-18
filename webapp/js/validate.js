
function validate() {
	
	var fliving = document.form2.living.value;

	var flot = document.form2.lot.value;

	var fabove = document.form2.above.value;

	var fbase = document.form2.base.value;
	

	  if (living_validate(fliving)) 
	 	{  
	 		 if (lot_validate(flot)) 
	 					{ 
	 						if (above_validate(fabove))
	 							
	 							{  
	 								if (base_validate(fbase))
	 									
	 									{
	 								
	 										return true;
	 									} 
	 							
	 							}
	 						
						} 
		} 

	 return false; 
}

function living_validate(fliving) {
	
	var li = parseInt(fliving);
	
	var fliving1 = document.form2.living1.value;
	var splitString = fliving1.split(" ");
	var ss1 = parseInt(splitString[0]);
	var ss2 = parseInt(splitString[2]);
	
	if (li < ss1 || li > ss2){
		
		alert("Please enter within range " + ss1 + " to " + ss2 + " square feet you have chosen for living room.");
		document.form2.living.focus();
		document.form2.living.select();
		return false;
	}  
 

	else {

		return true;
	}

} 

function lot_validate(flot) {

	var lo = parseInt(flot);
	
	var flot1 = document.form2.lot1.value;
	var splitString = flot1.split(" ");
	var ss1 = parseInt(splitString[0]);
	var ss2 = parseInt(splitString[2]);
	
	if (lo < ss1 || lo > ss2){
		
		alert("Please enter within range " + ss1 + " to " + ss2 + " square feet you have chosen for lot.");
		document.form2.lot.focus();
		document.form2.lot.select();
		return false;
	}  

	else {

		return true;
	}

}

function above_validate(fabove) {

	var ab = parseInt(fabove);
	
	var fabove1 = document.form2.above1.value;
	var splitString = fabove1.split(" ");
	var ss1 = parseInt(splitString[0]);
	var ss2 = parseInt(splitString[2]);
	
	if (ab < ss1 || ab > ss2){
		
		alert("Please enter within range " + ss1 + " to " + ss2 + " square feet you have chosen for the above ceiling area.");
		document.form2.above.focus();
		document.form2.above.select();
		return false;
	}  

	else {

		return true;
	}

}

function base_validate(fbase) {

	var bas = parseInt(fbase);
	
	var fbase1 = document.form2.base1.value;
	var splitString = fbase1.split(" ");
	var ss1 = parseInt(splitString[0]);
	var ss2 = parseInt(splitString[2]);
	
	if (bas < ss1 || bas > ss2){
		
		alert("Please enter within range " + ss1 + " to " + ss2 + " square feet you have chosen for basement.");
		document.form2.base.focus();
		document.form2.base.select();
		return false;
	}  

	else {

		return true;
	}
}
