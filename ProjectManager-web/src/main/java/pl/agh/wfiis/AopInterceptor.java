package pl.agh.wfiis;

import java.util.logging.Logger;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopInterceptor implements MethodInterceptor{
    /**
    * Logger object for log operations which facilitates development process.
    */
    Logger logger = Logger.getLogger(getClass().getName());
    
    public AopInterceptor() {
    }

    @Pointcut("execution (* *.*(..))")
    public void profile(){}

    @Around("profile()")
    public Object invoke(MethodInvocation mi) throws Throwable {
        System.out.println("test start");
        Object obj=mi.proceed();
        System.out.println("test end");
        logger.info("Aspekty dzialaja");
        return obj;
     }
}