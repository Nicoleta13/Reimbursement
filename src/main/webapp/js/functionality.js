// =======================Vars Login ====================
let loginEmpl = document.getElementById("login");
//let welcome = document.getElementById("moveRight");

//=======================Vars Complete the form ====================

let complete_form = document.getElementById("complete_form");
let myForm = document.getElementById("myForm");

//=================== Vars Show Available Funds ===============
let getFunds = document.getElementById("showFunds");
let fundsBtn = document.getElementById("funds");

//=================== Vars Pending Reimbursements =============

let btnPendings  = document.getElementById("pending");
let contReimbursements  = document.getElementById("pendingReim");
let tbPend_reim = document.getElementById("reimbursements");

// =================== Events ====================
let btnEvents = document.getElementById("events");
let tbEvents = document.getElementById("myevents");
let contEvents = document.getElementById("eventsContainer");

//=======================Vars MESSAGES ====================

let btnMSG =  document.getElementById("msg");
let viewMSG =  document.getElementById("myMSGs");
let viewBenCoMSG =  document.getElementById("msgBenCo");
let msgCont =  document.getElementById("msgContainer");

// ======================= Login ====================
	
function login() {
	console.log("Is logging ...");
	
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	
	let xhttp = new XMLHttpRequest();	
	
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
				
		let employee = JSON.parse(this.responseText);
		
		console.log("dd " + employee.last_name)
		
			if(employee.role == "Employee"){
				
				window.location.href = "employeePage.html";
				//welcome.innerHTML = `Employee: ${employee.first_name} ${employee.last_name}`;		
			
			} else if(employee.role == "BenCo"){
				
					window.location.href = "benCoPage.html";
					//welcome.innerHTML = `Benefit Coordinator: ${employee.first_name} ${employee.last_name}`;
				
			} else if(employee.role == "DepHead"){
				
				window.location.href = "depHeadPage.html";
				//welcome.innerHTML = `Department Head: ${employee.first_name} ${employee.last_name}`;
				
			} else if(employee == null) {
				window.location.href = "login.html";
			}
		}
		
	}
	
	xhttp.open("GET", "http://localhost:8080/Reimbursement/login.do?username=" + username + "&password=" + password, true);
	
	xhttp.send();
}

function welcome(){
	 let xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if(this.readyState == 4 && this.status == 200) {
	            
	            let name = this.responseText;
	            let info = `Welcome ${name}`;
	            document.getElementById("moveRight").innerHTML = info;
	                
	        }
	    }
	    xhttp.open("GET", "http://localhost:8080/Reimbursement/welcome.do", true);
	    xhttp.send();
}




//=================== Show Available Funds ===============

function showFunds(){
	
	getFunds.style.display = "block";
	myForm.style.display = "none";	
	contReimbursements.style.display = "none";
	msgCont.style.display = "none";
	viewBenCoMSG.style.display = "none";
	contEvents .style.display = "none";
	
	let xhttp = new XMLHttpRequest();
			
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
			
		let funds = this.responseText;
		let avFunds = `
			<h5>Your available funds are : ${funds}</h5>
		`
		getFunds.innerHTML = avFunds;	
		}
	}
	xhttp.open("GET", "http://localhost:8080/Reimbursement/getFunds.do", true);
	
	xhttp.send();
}

//=================== Show Pending Reimbursements ===============
let r_id;
let em_id;

function showpendReim(){
	
	contReimbursements.style.display = "block";
	getFunds.style.display = "none";
	myForm.style.display = "none";
	msgCont.style.display = "none";
	viewBenCoMSG.style.display = "none";
	contEvents .style.display = "none";
	
	let xhttp = new XMLHttpRequest();
			
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
			
		let pendingReim = JSON.parse(this.responseText);
		console.log(typeof pendingReim);
		console.log(pendingReim);
		
		let printPendReim = pendingReim.map(reimbursement => `	
				<tr id="removeBCline">
			      <td>${reimbursement.f_name}</td>
			      <td>${reimbursement.l_name}</td>
			      <td>${reimbursement.dates}</td>
			      <td>${reimbursement.description}</td>
			      <td>${reimbursement.cost}</td>
			      <td>${reimbursement.gr_id}</td>
			      <td>${reimbursement.ev_id}</td>
			      <td><button type="button" class="btn btn-success" onClick="approvePending(${reimbursement.r_id})">Approve</button></td>
			      <td><button type="button" class="btn btn-danger" onClick="denyPending(${reimbursement.r_id}, ${reimbursement.em_id})">Deny</button></td>
			      <td><textarea id="sendMessage"  rows="2" cols="30"></textarea></td>
			    </tr>
			` ).join("");
			
		tbPend_reim.innerHTML = printPendReim;
		}	
	}
	
	xhttp.open("GET", "http://localhost:8080/Reimbursement/getReimsPending.do", true);
	
	xhttp.send();
	
}

//============== View my messages ==================

	function viewMesages(){
		
		msgCont.style.display = "block";
		getFunds.style.display = "none";
		myForm.style.display = "none";
		contReimbursements.style.display = "none";
		viewBenCoMSG.style.display = "none";
		contEvents .style.display = "none";
		
		let xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			
			if(this.readyState == 4 && this.status == 200) {
				
			let messages = JSON.parse(this.responseText);
			console.log(messages);
			
			
			let printMessages = messages.map(message => `	
					<tr>
				      <td>${message.sender}</td>
				      <td>${message.message}</td>
				      <td><textarea id="answerToMsg"  rows="2" cols="40"></textarea></td> 
				      <td><button type="button" class="btn btn-success" onClick="answerToMsg(${message.sender})">Send</button></td>      
				    </tr>
				` ).join("");
			
			viewMSG.innerHTML = printMessages;
			}
			
		}
		
		xhttp.open("GET", "http://localhost:8080/Reimbursement/getAllMessages.do", true);
		
		xhttp.send();

	}
	
	
	//============== View BenCo messages ==================

	function viewBenCoMesages(){
		
		viewBenCoMSG.style.display = "block";
		getFunds.style.display = "none";
		myForm.style.display = "none";
		contReimbursements.style.display = "none";
		contEvents .style.display = "none";
		msgCont.style.display = "none";
		
		let xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			
			if(this.readyState == 4 && this.status == 200) {
				
			let messages = JSON.parse(this.responseText);
			console.log(messages);
			
			
			let printBencoMsg = messages.map(message => `	
					<tr>
				      <td>${message.sender}</td>
				      <td>${message.message}</td>
				      <td><button type="button" class="btn btn-primary" onClick="confirmTuition(${message.sender})">Confirm</button></td>      
				    </tr>
				` ).join("");
			
			viewMSG.innerHTML = printBencoMsg;
			}
		}
		
		xhttp.open("GET", "http://localhost:8080/Reimbursement/getBencoMsges.do", true);
		
		xhttp.send();

	}
	
	// =============== Confirm tuition ==================
	
	function confirmTuition(sender){
		console.log("TaaDAAAAAAAM!");
		//document.getElementById("row1").remove();
		
		let xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			
			if(this.readyState == 4 && this.status == 200) {
				
			console.log("Message answered");	
			}	
		}
		
		xhttp.open("POST", "http://localhost:8080/Reimbursement/updateMessage.do", true);
		
		xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
	    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		
	    xhttp.send("sender="+sender);
	    
	    //will refresh the table
	    viewBenCoMesages();
		
	}
	
	// =================== Answer to message ==========================
	
	function answerToMsg(em_id) {
		
		let message = document.getElementById("answerToMsg").value;
		
		let xhttp = new XMLHttpRequest();
			
			xhttp.onreadystatechange = function() {
				
				if(this.readyState == 4 && this.status == 200) {
					
				console.log("Message answered");	
				}	
			}
			
			xhttp.open("POST", "http://localhost:8080/Reimbursement/answerMessage.do", true);
			
			xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
		    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			
		xhttp.send("em_id="+em_id+"&message="+message);
		
		confirmTuition(em_id);
		
	}

//=================== Approve Pending Reimbursements ===============

function approvePending(r_id){
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
			
		console.log("Approved");	
		}	
	}
	
	xhttp.open("POST", "http://localhost:8080/Reimbursement/approvePendingReimb.do", true);
	
	xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	xhttp.send("r_id="+r_id);
	document.getElementById("removeBCline").remove();
}

function approveDepHeadReimb(r_id){
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
			
			console.log("Approved");	
		}	
	}
	
	xhttp.open("POST", "http://localhost:8080/Reimbursement/approveDepHeadReimb.do", true);
	
	xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
	xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	xhttp.send("r_id="+r_id);
	document.getElementById("removeBCline").remove();
}

function approveBenCoReimb(r_id, em_id){
	
// ============== Change the price ==================
	
	function changePrice(){
		
		let cost =  document.querySelector(".changeCost").value;
		
		let xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			
			if(this.readyState == 4 && this.status == 200) {	
				
			}	
		}
		
		xhttp.open("POST", "http://localhost:8080/Reimbursement/changePrice.do", true);
		
		xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
	    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		
		xhttp.send("r_id="+r_id+"&cost="+cost);
	}
	changePrice();
	
	if(changePrice() == true){
		msgCont.innerHTML = `<p> The reimbursement price has been changed to : ${cost}</p>
			<button>Approve price</button>
			<button>Decline Reimbursement</button>`;
	}
	
	let message;

	// ============== Send a message if denied ==================
	function sendAMsg(){
			
			message = document.getElementById("sendMessage").value;
			
			console.log("The message from benco: " + message)
			
			let xhttp = new XMLHttpRequest();
				
				xhttp.onreadystatechange = function() {
					
					if(this.readyState == 4 && this.status == 200) {
						
					console.log("Message added");	
					}	
				}
				
				xhttp.open("POST", "http://localhost:8080/Reimbursement/fromBenCoMsg.do", true);
				
				xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
			    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				
			xhttp.send("em_id="+em_id+"&message="+message);
		}
		
	sendAMsg();
	//document.getElementById("removeBCline").remove();
	
	
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
			
			console.log("Approved");	
		}	
	}
		
	xhttp.open("POST", "http://localhost:8080/Reimbursement/approveBenCoReimb.do", true);
	
	xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
	xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	xhttp.send("r_id="+r_id+"&cost="+cost);
	
	showBenCoReim();
	
}

//=================== Deny Pending Reimbursements ===============

function denyPending(r_id, em_id){
	
	
	// ============== Send a message if denied ==================
	function sendAMsg(){
			
			let message = document.getElementById("sendMessage").value;
			
			let xhttp = new XMLHttpRequest();
				
				xhttp.onreadystatechange = function() {
					
					if(this.readyState == 4 && this.status == 200) {
						
					console.log("Message added");	
					}	
				}
				
				xhttp.open("POST", "http://localhost:8080/Reimbursement/addMessage.do", true);
				
				xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
			    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				
			xhttp.send("r_id="+r_id+"&em_id="+em_id+"&message="+message);
		}
		
	sendAMsg();
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
			
		console.log("Denyed");	
		}	
	}
	
	xhttp.open("POST", "http://localhost:8080/Reimbursement/denyPendingReimb.do", true);
	
	xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	xhttp.send("r_id="+r_id);
	//document.getElementById("removeBCline").remove();
	//document.getElementById("removeBCline").remove();
}



function showBenCoReim(){
	
	contReimbursements.style.display = "block";
	getFunds.style.display = "none";
	myForm.style.display = "none";
	msgCont.style.display = "none";
	contEvents .style.display = "none";
	viewBenCoMSG.style.display = "none";
	
	let cost = 0;
	let r_id = 0;
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
			
			let reimToApprove = JSON.parse(this.responseText);
			console.log(reimToApprove);
			
			
			let printToApproveReim = reimToApprove.map(reimbursement => `	
			<tr id="removeBCline">
			<td>${reimbursement.f_name}</td>
			<td>${reimbursement.l_name}</td>
			<td>${reimbursement.dates}</td>
			<td>${reimbursement.description}</td>
			<td>
			<input class="changeCost" type="text" value="${reimbursement.cost}">
			</td>
			<td>${reimbursement.gr_id}</td>
			<td>${reimbursement.ev_id}</td>
			<td><button id="submitt" type="button" class="btn btn-success" onClick="approveBenCoReimb(${reimbursement.r_id}, ${reimbursement.em_id})">Approve</button></td>
			<td><button type="button" class="btn btn-danger" onClick="denyPending(${reimbursement.r_id}, ${reimbursement.em_id})">Deny</button></td>
			<td><textarea id="sendMessage"  rows="2" cols="30"></textarea></td>
			</tr>
			` ).join("");

			tbPend_reim.innerHTML = printToApproveReim;
		}	
	}
	
	xhttp.open("GET", "http://localhost:8080/Reimbursement/reimbBenCoApprove.do", true);
	
	xhttp.send();
	
}



// =================================  DEPARTMENT HEAD =======================================

function showDHeadReim(){

	contReimbursements.style.display = "block";
	getFunds.style.display = "none";
	myForm.style.display = "none";
	msgCont.style.display = "none";
	contEvents .style.display = "none";
	viewBenCoMSG.style.display = "none";
	
	let xhttp = new XMLHttpRequest();
			
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
			
		let reimToApprove = JSON.parse(this.responseText);
		console.log(reimToApprove);
		
		let printToApproveReim = reimToApprove.map(reimbursement => `	
				<tr id="removeBCline">
			      <td>${reimbursement.f_name}</td>
			      <td>${reimbursement.l_name}</td>
			      <td>${reimbursement.dates}</td>
			      <td>${reimbursement.description}</td>
			      <td>${reimbursement.cost}</td>
			      <td>${reimbursement.gr_id}</td>
			      <td>${reimbursement.ev_id}</td>
			      <td><button type="button" class="btn btn-success" onClick="approveDepHeadReimb(${reimbursement.r_id})">Approve</button></td>
			      <td><button type="button" class="btn btn-danger" onClick="denyPending(${reimbursement.r_id}, ${reimbursement.em_id})">Deny</button></td>
			      <td><textarea id="sendMessage"  rows="2" cols="30"></textarea></td>
			    </tr>
			` ).join("");
		 
			
		tbPend_reim.innerHTML = printToApproveReim;
		}	
	}
	
	xhttp.open("GET", "http://localhost:8080/Reimbursement/reimbDHApprove.do", true);
	
	xhttp.send();
	
}

// ================= SHOW EVENTS ====================

function showEvents(){
	
	msgCont.style.display = "none";
	getFunds.style.display = "none";
	myForm.style.display = "none";
	contReimbursements.style.display = "none";
	viewBenCoMSG.style.display = "none";
	contEvents .style.display = "block";
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
			
		let events = JSON.parse(this.responseText);

		
		
		let printEvents = events.map(event => `	
				<tr>
			      <td>${event.ev_id}</td>
			      <td>${event.event_name}</td>
			      <td>${event.start_date}</td> 
			      <td>${event.end_date}</td>
			      <td>${event.price}</td>
			      <td><button type="button" class="btn btn-success" onClick="showForm(${event.ev_id}, ${event.price})">Complete form</button></td>      
			    </tr>
			` ).join("");
		
		tbEvents.innerHTML = printEvents;
		}
		
	}
	
	xhttp.open("GET", "http://localhost:8080/Reimbursement/getAllEvents.do", true);
	
	xhttp.send();

}

//=================== Complete the form ===============

function  showForm(){
	
		myForm.style.display = "block";
		getFunds.style.display = "none";
		contReimbursements.style.display = "none";
		msgCont.style.display = "none";
		viewBenCoMSG.style.display = "none";
		contEvents .style.display = "none";
}

function sendForm(){
	let ev_id = 0;
	let gr_id = 0;

	let f_name = document.getElementById("fName").value;
	let l_name = document.getElementById("lName").value;
	let dates = document.getElementById("date").value;
	let time = document.getElementById("time").value;
	let location = document.getElementById("location").value;
	let description = document.getElementById("desc").value;
	let event = document.getElementById("t_event").value;
	let gr_format = document.getElementById("g_format").value;
	let cost = document.getElementById("cost").value;
//    let file_load = document.getElementById("file_load").value;

	switch(event){
		case "Univ_courses": {
			ev_id = 11;
			break;
		}
		case "Seminars": {
			ev_id = 12;
			break;
		}
		case "Cert_Prep_Classes": {
			ev_id = 13;
			break;
		}
		case "Certification": {
			ev_id = 14;
			break;
		}
		case "Tech_Training": {
			ev_id = 15;
			break;
		}
	}
	console.log("ev_id: "+ev_id);
	
	switch(gr_format){
		case "numeric_gr": {
			gr_id = 1;
			break;
		}
		case "leter_gr": {
			gr_id = 2;
			break;
		}
		case "presentation": {
			gr_id = 3;
			break;
		}
	}
	
	let xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			
			if(this.readyState == 4 && this.status == 200) {
				
				console.log("The response " + this.responseText);
			}
			
		}
	
	xhttp.open("POST", "http://localhost:8080/Reimbursement/addReimbursement.do", true);
	
	xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
    xhttp.send("f_name="+f_name+"&l_name="+l_name+"&dates="+dates+"&time="+ time+"&location="+location+"&description="+description + "&cost=" + cost + "&gr_id="+gr_id+"&ev_id="+ev_id);
}


function sendFormHD(){
	let ev_id = 0;
	let gr_id = 0;
	
	let f_name = document.getElementById("fName").value;
	let l_name = document.getElementById("lName").value;
	let dates = document.getElementById("date").value;
	let time = document.getElementById("time").value;
	let location = document.getElementById("location").value;
	let description = document.getElementById("desc").value;
	let event = document.getElementById("t_event").value;
	let gr_format = document.getElementById("g_format").value;
	let cost = document.getElementById("cost").value;
//    let file_load = document.getElementById("file_load").value;
	
	switch(event){
	case "Univ_courses": {
		ev_id = 11;
		break;
	}
	case "Seminars": {
		ev_id = 12;
		break;
	}
	case "Cert_Prep_Classes": {
		ev_id = 13;
		break;
	}
	case "Certification": {
		ev_id = 14;
		break;
	}
	case "Tech_Training": {
		ev_id = 15;
		break;
	}
	}
	console.log("ev_id: "+ev_id);
	
	switch(gr_format){
	case "numeric_gr": {
		gr_id = 1;
		break;
	}
	case "leter_gr": {
		gr_id = 2;
		break;
	}
	case "presentation": {
		gr_id = 3;
		break;
	}
	}
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		
		if(this.readyState == 4 && this.status == 200) {
			
			console.log("The HD just complete the form");
		}
		
	}
	
	xhttp.open("POST", "http://localhost:8080/Reimbursement/addReimbursementHD.do", true);
	
	xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
	xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	xhttp.send("f_name="+f_name+"&l_name="+l_name+"&dates="+dates+"&time="+ time+"&location="+location+"&description="+description + "&cost=" + cost + "&gr_id="+gr_id+"&ev_id="+ev_id);
}



