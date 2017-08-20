package process;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

public class ProcessMessage extends ProcessAbs{
	private static ProcessMessage processMessage;
 
	public static ProcessMessage getInstance()
	{
		if (processMessage==null) processMessage = new ProcessMessage();
		return processMessage; 
	}
	public ProcessMessage()
	{
		super("ProcessMessage");
		processMessage = this;
	}
	@Override
	public void process()
	{
		
	}
	
}
