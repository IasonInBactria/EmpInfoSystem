package com.example.empinfosystem.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Aspect
public class TimeAspect {
    @Around("execution(* com.example.empinfosystem.service.*.*(..))")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录开始时间
        long begin = System.currentTimeMillis();
        //调用原始方法
        Object result = joinPoint.proceed();
        //记录结束时间
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法调用耗时{}ms", end - begin);
        return result;
    }
}
