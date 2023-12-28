package com.llw.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect // AOP类
public class TimeAspect {

    @Pointcut("execution(* com.llw.service.DeptService.getAllDept()) ||" +
            "execution(* com.llw.service.DeptService.deleteById(java.lang.Integer))")
    private void pt() {}

//    @Around("execution(* com.llw.service.*.*(..))") //切入点表达式
    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1. 记录开始时间
        long begin = System.currentTimeMillis();

        //2. 调用原始方法运行
        Object result = joinPoint.proceed();

        //3. 记录结束时间，计算方法执行耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法执行耗时:{}ms", end - begin);

        return result;
    }

}
