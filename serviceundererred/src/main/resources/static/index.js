var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#start").prop("disabled", !connected);
    if (connected) {
        $("#response").show();
    }
    else {
        $("#response").hide();
    }
    $("#message").html("");
}


function connect() {
    var socket = new SockJS('/service-undeterred');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/response/message', function (greeting) {

            var responseData=JSON.parse(greeting.body)


            showResponse(responseData.message,responseData.statusCode,responseData.nextStep);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function startCall() {
    stompClient.send("/request/message", {}, JSON.stringify({'message': "dummy message"}));
}

function showResponse(message,statusCode,nextStep) {
    console.log(message)
    console.log(statusCode)
    console.log(nextStep)
    $("#message").append("<tr><td>" + message + "</td><td>"+statusCode+"</td><td>"+nextStep+"</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#start" ).click(function() { startCall(); });
});