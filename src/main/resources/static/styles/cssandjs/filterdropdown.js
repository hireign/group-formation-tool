function checkForComparison(inputValue, id){
	var numberInput = document.getElementById(id);
    if(inputValue=="GREATERTHAN"||inputValue=="LESSTHAN"){
        numberInput.setAttribute("type", "number");
    }
    else{
        numberInput.setAttribute("type", "hidden");
    }
}