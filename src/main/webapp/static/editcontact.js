
function validate() {
	console.log("validate Called");

	if (document.getElementById("firstName").value.trim()=="") {
		alert("Por favor insira o Nome");
		document.getElementById("firstName").focus();
		return false;
	}

	if (document.getElementById("lastName").value.trim()=="") {
		alert("Por favor insira o Sobrenome");
		document.getElementById("lastName").focus();
		return false;
	}

	if (document.getElementById("dateOfBirth").value.trim()=="") {
		alert("Por favor insira a Data de Nascimento");
		document.getElementById("dateOfBirth").focus();
		return false;
	}

	let len;
	if (document.getElementById("phonesize").value=="") {
		len = 1;
	} else {
		len  = parseInt(document.getElementById("phonesize").value);
	}

	for(let i=1; i<=len; i++) {
		let phone = "phone" + i;
		let phoneValue = document.getElementById(phone).value;
		if(phoneValue=="") {
			alert("Por favor insira o Numero "+i);
			document.getElementById(phone).focus();
			return false;
		}
	}
	return true;
}

function addPhone() {
	console.log("AddPhone Called");
	let phonetable = document.getElementById('phoneForm');
	let lastElement = phonetable.rows.length;
	let index = lastElement+1;
	let row = phonetable.insertRow(lastElement);

	let cellLeft = row.insertCell(0);
	let textNode = document.createTextNode("Numero "+index);
	cellLeft.appendChild(textNode);

	let cellText = row.insertCell(1);
	let element = document.createElement('input');
	element.type = 'text';
	element.name = 'phone' + index;
	element.id = 'phone' + index;
	element.placeholder = "Somente numeros";
	element.size = 20;

	let removeButton = document.createElement('a');
	removeButton.innerHTML = "Remove";
	removeButton.name = 'removePhone';
	removeButton.id = 'removePhone' + index;
	removeButton.onclick = function() { removePhone(); };
	removeButton.size = 10;
	removeButton.href = "#";

	cellText.appendChild(element);
	cellText.appendChild(removeButton);
	document.getElementById("phonesize").value=index;
}

function removePhone() {
	console.log("removePhone called");
	let phonetable = document.getElementById('phoneForm');
	let lastElement = phonetable.rows.length;
	console.log("lastElement: ", lastElement)
	if (lastElement > 1) {
		phonetable.deleteRow(lastElement - 1);
	}
  	if (document.getElementById("phonesize").value > 1) {
		document.getElementById("phonesize").value -= 1;
	}
}
