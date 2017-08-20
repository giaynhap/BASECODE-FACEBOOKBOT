package object;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.Configs;

public class function {
	public static String makeJsonSendText(String user_id,String text) throws JSONException
	{
		JSONObject obj = makeBaseJson(  user_id);
		 
		obj.put("message", new JSONObject().put("text", escape_string(text)));
		return obj.toString();
		
	}
	private static JSONObject makeBaseJson(String user_id) throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("recipient", new JSONObject().put("id", user_id));
		return obj;
	}
	public static String makeUrlGraphAPI(String query) 
		{
			StringBuffer url = new StringBuffer();
			url.append(Configs.graphAPI);
			url.append("v2.6/");
			url.append(query);
			url.append("access_token=");
			url.append(Configs.accessToken);
			return url.toString();
		}
	public static String escape_string(String str)
	{
		 StringBuilder b = new StringBuilder();

		    for (char c : str.toCharArray()) {
		        if (c >= 128)
		            b.append("\\u").append( Integer.toHexString(c|0x10000).substring(1));
		        else
		            b.append(c);
		    }

		    return b.toString();
	}
}
