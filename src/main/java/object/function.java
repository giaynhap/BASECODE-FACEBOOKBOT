package object;
import org.json.JSONException;
import org.json.JSONObject;

public class function {
	public static String makeJsonSendText(String user_id,String text) throws JSONException
	{
		JSONObject obj = makeBaseJson(  user_id);
		 
		obj.put("message", new JSONObject().put("text", text));
		return obj.toString();
		
	}
	private static JSONObject makeBaseJson(String user_id) throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("recipient", new JSONObject().put("id", user_id));
		return obj;
	}
}
