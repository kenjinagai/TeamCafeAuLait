package app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect Logger.
 *
 * @author Kenji Nagai
 *
 */
@Aspect
@Component
public class AspectLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectLogger.class);
    private static final String TARGET_CLASS = "bean(*Controller) || bean(*Service)";

    @Before(TARGET_CLASS)
    public void logStart(final JoinPoint joinPoint) {
        logMethod("Start:", joinPoint);
    }

    @After(TARGET_CLASS)
    public void logEnd(final JoinPoint joinPoint) {
        logMethod("End:", joinPoint);
    }

    private void logMethod(final String executeName, final JoinPoint joinPoint) {
        LOGGER.info(executeName + joinPoint.getTarget().getClass().getName() + "." +
                joinPoint.getSignature().getName());
    }
}
