package org.example.spring.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ElapsedTimeLogger {
    // Joinpoints = methods inside defined path
    @Pointcut(value = "execution(* org.example.spring.service.CardService.*(..))") // define where to apply aspects
    public void elapsedTimePc() {}

    @SneakyThrows
    @Around(value = "elapsedTimePc()")  // @Around allows to perform actions both before and after the method invocation
    public Object elapsedTimeLogger(ProceedingJoinPoint jp) throws Throwable {
        long startDate = System.currentTimeMillis();
        Object result = jp.proceed();
        long endDate = System.currentTimeMillis();
        log.info("Elapsed time:{}", endDate - startDate);
        return result;
        /* It’s important to return this result because failing to do so will prevent
           the original method’s result from being returned to the caller.
           Otherwise, GET request will not return any result set. */
    }

    @Before(value = "elapsedTimePc()")  // All method args:PageCriteria(page=0, count=5)
    public void logAllParameters(JoinPoint joinPoint) {
        Arrays.stream(
                joinPoint.getArgs()
        ).forEach(arg -> log.info("All method args:{}", arg));
    }
    /*
     * The negative sides of AOP:
     *   1. Latency (because of Proxy)
     *   2. Reflection
     */
}
