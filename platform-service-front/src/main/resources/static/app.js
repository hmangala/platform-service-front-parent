var stompClient = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#send").prop("disabled", !connected);
		$("#senderror").prop("disabled", !connected);
	} else {
		$("#send").prop("disabled", connected);
		$("#senderror").prop("disabled", connected);
	}
	$("#response_text").html("");
}

function connect() {
	var socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/client1', function(frontResponse) {
			showResponse(frontResponse.body);
		});
	});
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendRequest() {
	var jsonstr = JSON.stringify({
		"customerCode" : "TEST001",
		"firstName" : "Harish",
		"lastName" : "Mangala",
		"dateOfBirth" : 1476884761004
	});
	stompClient.send("/fr/frontRequest", {}, jsonstr);
}


function showResponse(message) {
	if (message != "" || message != undefined) {
		$("#response_text").html("<tr><td>" + message + "</td></tr>");
	}
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
		connect();
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#send").click(function() {
		sendRequest();
	});
	$("#senderror").click(function() {
		sendErrorRequest();
	});
});