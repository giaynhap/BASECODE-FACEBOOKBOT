package process;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProcessRevMessage extends ProcessAbs {
	private static ProcessRevMessage processRevMessage;
	
	public static ProcessRevMessage getInstance()
	{
		if (processRevMessage==null) processRevMessage = new ProcessRevMessage();
		return processRevMessage;
	}
	public ProcessRevMessage()
	{
		super("ProcessRevMessage");
		processRevMessage = this;
	}
	@Override
	public void process()
	{
		
	}
	 
}
