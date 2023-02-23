package io.github.kimmking.gateway.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


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
        AtomicLong directMemory = reporter.getDirectMemory();
        if(directMemory == null) {
            System.out.println("directMemory is NULL.");
            return -1L;
        }
        return directMemory.get();
    }


}
