package com.project.server.tcp;

import com.project.server.HttpServer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;

public class VertxTcpServer implements HttpServer {

    private byte[] handleRequest(byte[] requestData) {
        // 在这里编写处理请求的逻辑，根据 requestData 构造响应数据并返回
        // 这里只是一个示例，实际逻辑需要根据具体的业务需求来实现
        return "Hello, client!".getBytes();
    }

    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
        server.connectHandler(new TcpServerHandler());

        // 模拟 TCP 半包以及粘包问题
//        server.connectHandler(socket -> {
//            RecordParser recordParser = RecordParser.newFixed(8);
//            recordParser.setOutput(new Handler<Buffer>() {
//                int size = -1;
//                Buffer resultBuffer = Buffer.buffer();
//
//                @Override
//                public void handle(Buffer buffer) {
//                    if (size == -1) {
//                        size = buffer.getInt(4); // 读取到的是消息体的长度，从第四个字节开始读
//                        recordParser.fixedSizeMode(size);
//                        resultBuffer.appendBuffer(buffer);
//                    } else {
//                        resultBuffer.appendBuffer(buffer);
//                        System.out.println(buffer.toString());
//                        size = -1;
//                        recordParser.fixedSizeMode(8);
//                        resultBuffer = Buffer.buffer();
//                    }
//                }
//            });
//            socket.handler(recordParser);
//        });

        // 启动 TCP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("TCP server started on port " + port);
            } else {
                System.err.println("Failed to start TCP server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
