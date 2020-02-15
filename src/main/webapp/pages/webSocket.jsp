<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"></meta>
    <title>Title</title>
</head>
<body>
hello world!

</body>
<script>
    var socket;
    if(typeof(WebSocket) == "undefined") {
        console.log("=== 您的浏览器不支持WebSocket ===");
    }else{
        console.log("=== 您的浏览器支持WebSocket ===");
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
        //var socketUrl="${request.contextPath}/im/"+$("#userId").val();
        index = new WebSocket("ws://localhost:8082/websocket/${userId}");
        // socket = new WebSocket("${basePath}websocket/${userId}".replace("http","ws"));
        //打开事件
        index.onopen = function() {
            console.log("=== Socket is open ===");
            // socket.send("这是来自客户端的消息" + location.href + new Date());
        };
        //获得消息事件
        index.onmessage = function(msg) {
            console.log("=== message is:" + msg.data + " ===");
            //发现消息进入    开始处理前端触发逻辑
        };
        //关闭事件
        index.onclose = function() {
            console.log("=== Socket is closed ===");
        };
        //发生了错误事件
        index.onerror = function() {
            alert("=== Socket error ===");
            //此时可以尝试刷新页面
        }
    }
</script>
</html>