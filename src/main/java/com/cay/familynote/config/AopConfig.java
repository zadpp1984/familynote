package com.cay.familynote.config;

import com.cay.familynote.controller.FamilynoteController;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class AopConfig {

    Logger logger = LoggerFactory.getLogger( FamilynoteController.class );

    private Gson gson = new Gson();
//    private JSONObject json = new JSONObject();

    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.cay.familynote.controller.FamilynoteController.*(..))")
    private void controllerAspect() {
    }

    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.cay.familynote.dao.*Dao.*(..))")
    private void daoAspect() {
    }

    //请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //打印请求内容
        logger.info( "===============请求内容===============" );
        logger.info( "请求地址:" + request.getRequestURL().toString() );
        logger.info( "请求方式:" + request.getMethod() );
        logger.info( "请求类方法:" + joinPoint.getSignature() );
        logger.info( "请求类方法参数:" + Arrays.toString( joinPoint.getArgs() ) );
        logger.info( "===============请求内容===============" );
    }
    //请求method前打印内容
    @Before(value = "daoAspect()")
    public void daoMethodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //打印请求内容
        logger.info( "===============请求内容===============" );
        logger.info( "请求地址:" + request.getRequestURL().toString() );
        logger.info( "请求方式:" + request.getMethod() );
        logger.info( "请求类方法:" + joinPoint.getSignature() );
        logger.info( "请求类方法参数:" + Arrays.toString( joinPoint.getArgs() ) );
        logger.info( "===============请求内容===============" );
    }

    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o) {
        logger.info( "--------------返回内容----------------" );
        logger.info( "Response内容:" + gson.toJson( o ) );
        logger.info( "--------------返回内容----------------" );
    }

}
