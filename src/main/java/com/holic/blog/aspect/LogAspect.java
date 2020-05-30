package com.holic.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 切入点，也叫织入点，表示在哪些地方切入，具体位置由execution来指定 第一个*表示不限定访问控制符，
    //  com.holic.blog.web.*.*(..) 表示拦截com.holic.blog.web包下的任意类中的任意方法，而且方法参数不限
    @Pointcut("execution(* com.holic.blog.web.*.*(..))")
    public void log () {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        // 记录请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);

       logger.info("Request : {}", requestLog);
    }

    @After("log()")
    public void deAfter() {
    }

    @AfterReturning(pointcut = "log()", returning = "result")
    public void doAfterReturn(Object result){
        logger.info("Result {}", result);
    }

    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
