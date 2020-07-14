package CSCI5308.GroupFormationTool;

public abstract class LoggerAbstractFactory {
	private static final LoggerFactory logFactory = new LoggerFactory();

	public abstract LoggerInterface createLoggerInstance();
	
	public static LoggerAbstractFactory getFactory() {
		return logFactory;
	}
}
