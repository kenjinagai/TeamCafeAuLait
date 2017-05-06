package app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogger {
    private static final Logger logger = LoggerFactory.getLogger(AspectLogger.class);
    private static final String TARGET_CLASS_CONTROLL = "*Controller";
    private static final String TARGET_CLASS_SERVICE = "*Service";
    private static final String TARGET_CLASS = "bean(" + TARGET_CLASS_CONTROLL + ") || bean("
            + TARGET_CLASS_SERVICE + ")";

    @Before(TARGET_CLASS)
    public void logStart(JoinPoint joinPoint) {
        logMethod("Start:", joinPoint);
    }

    @After(TARGET_CLASS)
    public void logEnd(JoinPoint joinPoint) {
        logMethod("End:", joinPoint);
    }

    private void logMethod(String executeName, JoinPoint joinPoint) {
        logger.info(executeName + joinPoint.getTarget().getClass().getName() + "." +
                joinPoint.getSignature().getName());
    }
}
