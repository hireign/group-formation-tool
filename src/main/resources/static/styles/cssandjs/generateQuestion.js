function call_alert(){
	alert("BYE");
}

function generateQuestions(selectedValue) {
	var questionForm = document.getElementById("questionForm");
	var btnVal = 0;
	switch (selectedValue) {
		case "TEXT":
			break;
		case "NUMBER":
			break;
		case "MCQ":
			createOption(btnVal, questionForm);
			break;
		case "CHECKBOX":
			createOption(btnVal, questionForm);
			break;
	}
}

function unhideOptionButton(btnVal, questionForm) {
	btnVal++;
	var addOptionButton = document.getElementById("addOptionButton");
	addOptionButton.style.visibility = "visible";
	addOptionButton.onclick = function() { createOption(btnVal, questionForm); };
}

function createOption(btnVal, questionForm) {
	console.log(questionForm.childNodes);
	console.log("btnVal: " + btnVal);
	var optionInput = document.createElement("input");
	optionInput.type = "text";
	optionInput.setAttribute("id", "btn" + btnVal);
	optionInput.setAttribute("name", "btn" + btnVal);
    questionForm.appendChild(optionInput);
	unhideOptionButton(btnVal, questionForm);
}