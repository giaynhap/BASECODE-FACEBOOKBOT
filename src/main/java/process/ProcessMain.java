package process;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ProcessMain extends ProcessAbs{
	private ProcessMain processMain;
	private BlockingQueue<HttpServletRequest> queue;
	public ProcessMain getInstance()
	{
		if (processMain==null) processMain = new ProcessMain();
		
		return processMain;
	}
	public ProcessMain()
	{
		super("ProcessMain");
		queue = new LinkedBlockingQueue<HttpServletRequest>();
		processMain = this;
	}
	@Override
	public void process()
	{
		if (queue != null && queue.size() > 0)
		{
			 ArrayList<HttpServletRequest> list = new ArrayList<HttpServletRequest>();
	         queue.drainTo(list);
	         for (HttpServletRequest request : list)
	         {
	        	 String msg = getRequestString( request );
	        	 if (msg==null) continue;
	        	 try {
					JSONObject json = new JSONObject(msg);
					
				} catch (JSONException e) {
					continue;
				}
	         }
		}
	}
	public void add(HttpServletRequest request)
	{
		queue.add(request);
	}
	public void clear()
	{
		queue.clear();
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
