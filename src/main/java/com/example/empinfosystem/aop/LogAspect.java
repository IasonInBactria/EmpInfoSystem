package com.example.empinfosystem.aop;


import com.alibaba.fastjson.JSONObject;
import com.example.empinfosystem.mapper.OperateLogMapper;
import com.example.empinfosystem.pojo.OperateLog;
import com.example.empinfosystem.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.example.empinfosystem.anno.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");
        LocalDateTime operateTime = LocalDateTime.now();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        Long begin = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();
        String returnValue = JSONObject.toJSONString(result);
        Long end = System.currentTimeMillis();

        //耗时
        Long costTime = end - begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime,
                className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}", operateLog);

        return result;
    }
}
