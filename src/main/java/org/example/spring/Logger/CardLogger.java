package org.example.spring.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardLogger {

    // Method to get a Logger instance for a specific class
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    // You may also want to add convenience methods for different log levels
    public static void info(Class<?> clazz, String message, Object... args) {
        Logger logger = getLogger(clazz);
        logger.info(message, args);
    }

    public static void debug(Class<?> clazz, String message, Object... args) {
        Logger logger = getLogger(clazz);
        logger.debug(message, args);
    }

    public static void warn(Class<?> clazz, String message, Object... args) {
        Logger logger = getLogger(clazz);
        logger.warn(message, args);
    }

    public static void error(Class<?> clazz, String message, Object... args) {
        Logger logger = getLogger(clazz);
        logger.error(message, args);
    }
}
