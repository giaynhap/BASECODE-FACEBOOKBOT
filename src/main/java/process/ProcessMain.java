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
			 System.out.println("Analsy data request Post ");
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
        	 String msg = getRequestString( (HttpServletRequest)request );
        	 if (msg==null) continue;
        	 try {
				JSONObject json = new JSONObject(msg);
				JSONArray entrys = json.getJSONArray("entry");
				for (int i=0 ;i<entrys.length();i++ )
				{
					JSONArray messages = entrys.getJSONArray(i);
					for (int j=0 ;j<messages.length();i++ )
					{
						 JSONObject message = messages.getJSONObject(i);
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
				continue;
			}
         }
		 
		return rtArray;
		
	}
	
	public String getRequestString( HttpServletRequest request )
	  {
		  StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { }

		 return jb.toString();
	  }
	 
}
