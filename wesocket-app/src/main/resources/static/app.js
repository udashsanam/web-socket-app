var stompClient = null;

function connect(username) {
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({ username: username, }, function() {
        console.log('Web Socket is connected');
        stompClient.subscribe('/users/queue/messages', function(message) {
            console.log( $("#message").text(message.body));
            $("#message").text(message.body);
        });
    });
}

$(function() {
    $("form").on('submit', function(e) {
        e.preventDefault();
    });
    $("#connect").click(function() {
        connect($("#username").val());
    });
    $("#send").click(function() {
        // let message = $("#message1").val();
        // let username = $("#name").val();
        //
        // let request = new XMLResult();
        // request.open("POST", "http://localhost:8080/send/message")
        // request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        // request.send(JSON.stringify({ "username": username, "message": message }));
        stompClient.send("/app/hello", {},$("#name").val());
    });
});