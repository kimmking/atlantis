package io.github.kimmking.netty.server;


public class NettyServerApplication {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false,8088);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
