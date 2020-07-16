package CSCI5308.GroupFormationTool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil implements LoggerInterface {

	public void info(String myclass, String msg) {
		Logger log = LogManager.getLogger(myclass);
		log.info(msg);
	}

	public void debug(String myclass, String msg) {
		Logger log = LogManager.getLogger(myclass);
		log.debug(msg);
	}

	public void error(String myclass, String msg) {
		Logger log = LogManager.getLogger(myclass);
		log.error(msg);
	}

	public void fatal(String myclass, String msg) {
		Logger log = LogManager.getLogger(myclass);
		log.fatal(msg);
	}

	public void warn(String myclass, String msg) {
		Logger log = LogManager.getLogger(myclass);
		log.warn(msg);
	}
}
