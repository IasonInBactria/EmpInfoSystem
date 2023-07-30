package com.example.empinfosystem.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author YangY
 */
@Slf4j
@Component
@Aspect
public class MyAspect1 {

    @Before("execution(* com.example.empinfosystem.service.impl.DeptServiceImpl.*(..))")
    public void before(){
        log.info("before ....");
    }

    @Around("execution(* com.example.empinfosystem.service.impl.DeptServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("around before");

        Object result = proceedingJoinPoint.proceed();

        log.info("around after");
        return result;
    }

    @After("execution(* com.example.empinfosystem.service.impl.DeptServiceImpl.*(..))")
    public void after(){
        log.info("after...");
    }

    @AfterReturning("execution(* com.example.empinfosystem.service.impl.DeptServiceImpl.*(..))")
    public void afterReturning(){
        log.info("afterReturning....");
    }

    @AfterThrowing("execution(* com.example.empinfosystem.service.impl.DeptServiceImpl.*(..))")
    public void afterThrowing(){
        log.info("afterThrowing...");
    }
}
