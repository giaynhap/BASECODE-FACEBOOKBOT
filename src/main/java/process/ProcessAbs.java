package process;

public class ProcessAbs implements Runnable{
	private Thread thread;
	public ProcessAbs(String name)
	{
		thread = new Thread(this,name);
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
		thread.start();
	}
	 

}
