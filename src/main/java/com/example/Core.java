 
package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@Controller
@SpringBootApplication
public class Core {
	private static Core object;
	private Core  getInstance()
	{
		if (object==null) object = new Core();
		return  object ;
	}
	public String parse( HttpServletRequest request)
	{
		String output="";
		Map<String, String[]> Queries = request.getParameterMap();
		for (Map.Entry e :   Queries.entrySet())
		{
			output = analysis_key((String)e.getKey(), (String[])e.getValue(),Queries);
			
		}
		return output;
	}
	private String analysis_key(String key, String[] value,Map<String, String[]> queries)
	{
		String output = "";
		if (key.contains("hub.verify_token"))
		{ 
			output = queries.get("hub.challenge")[0];
		}
		return output;
	}
}
