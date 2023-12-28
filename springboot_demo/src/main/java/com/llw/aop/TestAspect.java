package com.llw.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
//@Aspect // AOP类
public class TestAspect {

    @Before("@annotation(com.llw.anno.MyLog)")
    public void beforeAOP() {
        log.info("-----before------");
    }

    @Around("")
    public Object aroundAOP(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("MyAspect around before ...");

        //1. 获取 目标对象的类名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("目标对象的类名:{}",className);

        //2. 获取 目标方法的方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("目标方法的方法名:{}",methodName);

        //3. 获取 目标方法运行时传入的参数
        Object[] args = joinPoint.getArgs();
        log.info("目标方法运行时传入的参数:{}", Arrays.toString(args));

        //4. 放行 目标方法执行
        Object result = joinPoint.proceed();

        //5. 获取 目标人法运行的返回值
        log.info("目标方法运行的返回值:{}",result);

        log.info("MyAspect around after ...");
        return result;
    }
}
