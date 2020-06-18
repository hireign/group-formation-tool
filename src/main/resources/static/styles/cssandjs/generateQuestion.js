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
	var newline = document.createElement("br");
	questionForm.appendChild(newline);
	var optionInput = document.createElement("input");
	optionInput.type = "text";
	optionInput.placeholder = "Option text";
	optionInput.className="optionInputs";
	optionInput.setAttribute("id", "btn" + btnVal);
	optionInput.setAttribute("name", "btn" + btnVal);
    questionForm.appendChild(optionInput);
	var optionInput2 = document.createElement("input");
	optionInput2.type = "text";
	optionInput2.placeholder = "Option value";
	optionInput2.className="optionInputs";
	optionInput2.setAttribute("id", "val" + btnVal);
	optionInput2.setAttribute("name", "val" + btnVal);
	questionForm.appendChild(optionInput2);
	unhideOptionButton(btnVal, questionForm);
}