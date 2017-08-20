package object;

public class BaseMessage {
	
	private String ID;
	private String from;
	private String to;
	private String message;
	
	public void setID(String ID)
	{
		this.ID = ID;
	}
	public void setFromUser(String ID)
	{
		this.from = ID;
	}
	public void setToUser(String ID)
	{
		this.to = ID;
	}
	public void setMessage(String text)
	{
		this.message = text;
	}
	public String getID()
	{
		return this.ID;
	}
	public String getFromUser( )
	{
		return this.from = ID;
	}
	public String getToUser( )
	{
		return this.to;
	}
	public String getMessage( )
	{
		return this.message;
	}
}
