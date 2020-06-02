package dal.twentythree.gft.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dal.twentythree.gft.util.LoggerUtil;

public class CopyCatMeDBConfigException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());
	
	public CopyCatMeDBConfigException() {
		
		myLogger.error("There is a problem in loading the booking Db configuration");
		
	}
	
}
