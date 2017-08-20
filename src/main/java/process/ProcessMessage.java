package process;

import java.io.BufferedReader;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

import object.BaseMessage;
import object.Https;
import object.function;

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
		if (queue!=null &&queue.size()>0)
		{
			ArrayList<Object> list = new ArrayList<Object>();
			queue.drainTo(list);
			for (Object msg : list)
			{
				BaseMessage message = (BaseMessage)msg;
				try {
					//send message
					
					
					String msgRep = function.makeJsonSendText(message.getFromUser(),"FacebookMessageBot-Java-GiấyNháp \n  ");
				
					Https.POST(function.makeUrlGraphAPI("me/messages?"),msgRep);
					
					
				 
				} catch (  Exception e) {
					continue;
				 
				}
			}
			
		}
	}
	
	
}
