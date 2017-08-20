

package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import object.function;
import process.ProcessMain;
import process.ProcessMessage;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.web.servlet.View;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
@Controller
@SpringBootApplication
public class Main {

 
  public static void init()
  {
	  ProcessMessage.getInstance().start();
	  ProcessMain.getInstance().start();
  }
  public static void main(String[] args) throws Exception {
     SpringApplication.run(Main.class, args);
     init();
  }
  @RequestMapping("/")
  String  index(Map<String, Object> model) {
	  try {
		String hostName = InetAddress.getLocalHost().getHostName();
		model.put("hostname", hostName);
		model.put("PageID: ", Configs.pageID);
	} catch (UnknownHostException e) {
		e.printStackTrace();
	}
    return "giaynhap";
  }
  
  @RequestMapping(value="/webhook", method=RequestMethod.GET )
  public @ResponseBody String webhook(ModelAndView mav,final HttpServletRequest request) {
	  // GET
	  ArrayList<String> output = new ArrayList<String>();
      return Register.parse(  request);
  }
  
  @RequestMapping(value="/webhook", method=RequestMethod.POST )
  public @ResponseBody String webhook( HttpServletRequest request) {
	 
	  // POST
	  String msg = getRequestString( (HttpServletRequest)request );
	  ProcessMain.getInstance().add(msg);
	  return "";
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
