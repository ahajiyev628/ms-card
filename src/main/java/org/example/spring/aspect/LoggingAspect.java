package org.example.spring.aspect;

import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.spring.logger.CardLogger;
import org.example.spring.annotation.LogIgnore;
import org.example.spring.mapper.ObjectMapperFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

@Aspect
@Component
@AllArgsConstructor
public class LoggingAspect {
    private final ObjectMapperFactory objectMapperFactory; // Inject ObjectMapperFactory

    @Around(value = "within(@org.example.spring.annotation.Log *)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Logger log = CardLogger.getLogger(joinPoint.getTarget().getClass());
        StringBuilder parameters = buildParameters(signature, joinPoint.getArgs());

        // Log the start of the method execution
        logEvent("start", log, signature, parameters);

        Object response;
        try {
            // Proceed with the method execution
            response = joinPoint.proceed();
        } catch (Throwable throwable) {
            // Log if an exception occurs
            logEvent("error", log, signature, parameters);
            throw throwable;  // Ensure exceptions are propagated
        }

        // Log the end of the method execution
        logEndAction(log, signature, response);

        return response;
    }

    private StringBuilder buildParameters(MethodSignature signature, Object[] args) {
        StringBuilder builder = new StringBuilder();
        var parameters = signature.getMethod().getParameters();
        for (int i = 0; i < parameters.length; i++) {
            var currentParam = parameters[i];
            if (currentParam.isAnnotationPresent(LogIgnore.class)) continue;
            builder.append(" ")
                    .append(currentParam.getName())
                    .append(":")
                    .append(writeObjectAsString(args[i], currentParam));
        }
        return builder;
    }

    private String writeObjectAsString(Object object, Parameter parameter) {
        try {
            // Serialize object to JSON string using ObjectMapper from factory
            return parameter.getType().getTypeName() + objectMapperFactory.getLogIgnoreObjectMapping()
                    .writeValueAsString(object)
                    .replace("\"", " ");
        } catch (Exception e) {
            return object.toString();
        }
    }

    private void logEvent(String eventName, Logger log, MethodSignature signature, StringBuilder parameters) {
        CardLogger.info(signature.getDeclaringType(), "ActionLog.{}.{}{}", signature.getName(), eventName, parameters.toString());
    }

    private void logEndAction(Logger log, MethodSignature signature, Object response) {
        if (void.class.equals(signature.getReturnType())) {
            CardLogger.info(signature.getDeclaringType(), "ActionLog.{}.end", signature.getName());
        } else {
            CardLogger.info(signature.getDeclaringType(), "ActionLog.{}.{}", signature.getName(), response);
        }
    }
}
