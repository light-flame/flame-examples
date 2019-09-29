var ws = new WebSocket("ws://localhost:8080/ws?token=abc123");

ws.onopen = function() {                  
  // Web Socket is connected, send data using send()
  ws.send("intro");
};