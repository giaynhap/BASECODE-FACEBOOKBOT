package process;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.http.HttpServletRequest;

public class ProcessAbs implements Runnable{
	private Thread thread;
	protected BlockingQueue<Object> queue;
	public ProcessAbs(String name)
	{
		thread = new Thread(this,name);
		queue = new LinkedBlockingQueue<Object>();
	}
	public void process()
	{
		
	}
	@Override
	public void run() {
		 while (true)
		 {
			 try {
				process();
				Thread.sleep(1000);
			
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		 }
	}
	public void start()
	{
		if (thread==null)
		{
			thread = new Thread(this);
		}
		System.out.println(thread.getName()+" starting...");
		thread.start();
	}
	public void add(Object request)
	{
		 
		queue.add(request);
	}
	public void clear()
	{
		queue.clear();
	}
	 

}
