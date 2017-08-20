package process;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.Configs;
import object.BaseMessage;

public class ProcessMain extends ProcessAbs{
	private static ProcessMain processMain;
	
	public static ProcessMain getInstance()
	{
		if (processMain==null) processMain = new ProcessMain();
		
		return processMain;
	}
	public ProcessMain()
	{
		super("ProcessMain");
	
		processMain = this;
	}
	@Override
	public void process()
	{
		
		if (queue != null && queue.size() > 0)
		{
			
			 ArrayList<Object> list = new ArrayList<Object>();
			 
	         queue.drainTo(list);
	         ArrayList<BaseMessage> msglist = validateContraint(  list );
	         for (BaseMessage msg: msglist)
	         {
	        	
	        	 if (!msg.getFromUser().equals(Configs.pageID))
	        	 {
	        		 ProcessMessage.getInstance().add(msg);
	        	 }
	         }
		}
	}
	public  ArrayList<BaseMessage> validateContraint(  ArrayList<Object> list )
	{
		ArrayList<BaseMessage> rtArray = new ArrayList<BaseMessage>();
		 for (Object request : list)
         {
        	 
        	 String msg = (String)request;
        	 try {
				JSONObject json = new JSONObject(msg);
				JSONArray entrys = json.getJSONArray("entry");
			
				for (int i=0 ;i<entrys.length();i++ )
				{
					JSONArray messages = (entrys.getJSONObject(i)).getJSONArray("messaging");
					for (int j=0 ;j<messages.length();j++ )
					{
						
						 JSONObject message = messages.getJSONObject(j);
						 String sender_id = message.getJSONObject("sender").getString("id");
						 String recipient_id =  message.getJSONObject("recipient").getString("id");
						 JSONObject msg_t = message.getJSONObject("message");
						 BaseMessage base = new BaseMessage();
						 
						
						 base.setFromUser(sender_id);
						 base.setToUser(recipient_id) ;
						 base.setMessage(msg_t.toString());
						 rtArray.add(base);
					}
				}
				
			} catch (JSONException e) {
			
				e.printStackTrace();
				continue;
			}
         }
		 
		return rtArray;
		
	}
	
	
	 
}
