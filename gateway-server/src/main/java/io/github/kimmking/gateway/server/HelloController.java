package io.github.kimmking.gateway.server;

import org.springframework.http.HttpRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    DirectMemoryReporter reporter = new DirectMemoryReporter();

    @GetMapping("/api/hello")
    public String sayHello(){
//        String code = request.getHeader("exchange-cloud");
        return "hello world";
    }

    @GetMapping("/api/dm")
    public Long dm(){
        reporter.init();
        return reporter.getDirectMemory().get();
    }


}
