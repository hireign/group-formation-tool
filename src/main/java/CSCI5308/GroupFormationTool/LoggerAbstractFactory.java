package CSCI5308.GroupFormationTool;

public abstract class LoggerAbstractFactory {

	private static final LoggerAbstractFactory logFactory = new LoggerFactory();

	public abstract LoggerInterface makeLoggerInstance();

	public static LoggerAbstractFactory getFactory() {
		return logFactory;
	}
}
