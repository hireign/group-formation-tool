package CSCI5308.GroupFormationTool;

public interface LoggerInterface {
	public void info(String myclass, String msg);
	public void debug(String myclass, String msg);
	public void error(String myclass, String msg);
	public void fatal(String myclass, String msg);
	public void warn(String myclass, String msg);
}
