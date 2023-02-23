package io.github.kimmking.gateway.server;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import io.netty.util.internal.PlatformDependent;


public class DirectMemoryReporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectMemoryReporter.class);

    private static final int _1K = 1024;

    private static final String BUSINESS_KEY = "netty_direct_memory";

    private AtomicLong directMemory;

    public AtomicLong getDirectMemory() {
        return directMemory;
    }

    public void init() {
        Field field = ReflectionUtils.findField(PlatformDependent.class, "DIRECT_MEMORY_COUNTER");
        field.setAccessible(true);
        try {
            directMemory = ((AtomicLong) field.get(PlatformDependent.class));
            //startReport();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            LOGGER.error("netty_direct_memory error", e);
        }

        boolean hasUnsafe = PlatformDependent.hasUnsafe();
        System.out.println("PlatformDependent.hasUnsafe(): " + hasUnsafe);
    }

    public void startReport() {

        Runnable runnable = () -> {
            doReport();
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 30, 10, TimeUnit.SECONDS);
    }

    private void doReport() {
        try {
            int memoryInKB = (int) (directMemory.get());
            LOGGER.info("{}:{}KB", BUSINESS_KEY, memoryInKB/1024);
        } catch (Exception e) {
            LOGGER.error("netty_direct_memory error", e);
        }
    }
}