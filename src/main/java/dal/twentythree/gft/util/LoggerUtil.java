package dal.twentythree.gft.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    private LoggerUtil() {
    }

    // Providing Global point of access for the loggerUtil
    public static Logger getLoggerInstance(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
    
}
