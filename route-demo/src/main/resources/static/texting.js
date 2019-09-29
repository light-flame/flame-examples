$(document).ready(function () {

    function executeLine(txt){
        $('.line').last().removeClass('line-active');
        $('.line').first().clone().appendTo('.line-wrapper').show();
        $('.line-active').last().append(document.createTextNode(txt));
        var elmnt = document.getElementById("down");
        elmnt.scrollIntoView();
    }

    var ws = new WebSocket("ws://localhost:8080/ws");

    ws.onopen = function() {
      executeLine('You are connected... Lets start!?');
      executeLine('>');
      //ws.send("intro");
    };

    ws.onmessage = function (evt) {
      var received_msg = evt.data;
      executeLine(received_msg);
      executeLine('>');
    };

    function sendCommand(){
        var command = $('.line-active').last().text();
        command = command.substr(1, command.length);
        if (command === ''){
            executeLine('>');
        }
        if (command === 'close'){
            ws.close();
            executeLine('>');
            return;
        }
        console.log(command)
        ws.send(command);
    }

    $('html').keyup(function (e) {

        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            sendCommand();
            return;
        }
        if (keycode == '8') {
            const txt = $('.line-active').last().text();
            if (txt.length < 2){
                return;
            }
            $('.line-active').last().text(txt.substr(0, txt.length-1));
            return;
        }

        const char = String.fromCharCode(event.which).toLowerCase();
        $('.line-active').last().append(document.createTextNode(char));
    });


});