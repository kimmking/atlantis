package io.github.kimmking.gateway.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@SpringBootApplication
public class ApiServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(10*1024*1024L);
        //warmup();
        // -Xmx512m -Dio.netty.maxDirectMemory=104857600 -Dio.netty.tryReflectionSetAccessible=true --add-opens java.base/jdk.internal.misc=ALL-UNNAMED --illegal-access=warn
    }

//    @PostConstruct
//    //@Async
    public void warmup() throws Exception {
        String initUrl = "http://localhost:8088/api/hello";
        URL url =new URL(initUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(3000);
        //conn.setDoOutput( true);
        conn.setDoInput( true);
        conn.connect();

        //OutputStream outStrm = conn.getOutputStream();
        //outStrm.write(bytes);
        //outStrm. flush();
        //outStrm.close();

        InputStream in= conn.getInputStream();
        int count=conn.getContentLength();
        byte[] buffer =new byte[count];
        ByteArrayOutputStream baos =new ByteArrayOutputStream();

        for(int len =0; (len =in.read(buffer)) >0;) {
            baos.write(buffer, 0, len);
        }
        String returnValue =new String(baos.toByteArray(),"utf-8" );
        System.out.println("warmup=[" + returnValue + "]");
        baos.flush();
        baos.close();
        in.close();
        conn.disconnect();

    }

}
