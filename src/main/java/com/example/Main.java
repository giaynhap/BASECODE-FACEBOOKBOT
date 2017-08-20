/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

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
  String index() {
	  ProcessMain.getInstance().add("{\"object\":\"page\",\"entry\":[{\"id\":\"323985201385737\",\"time\":1503215010684,\"messaging\":[{\"sender\":{\"id\":\"1652270848157684\"},\"recipient\":{\"id\":\"323985201385737\"},\"timestamp\":1503215010321,\"message\":{\"mid\":\"mid.$cAAF8X4xwgONkL94KEVd_mFcs0bRE\",\"seq\":6490,\"text\":\"\u00ea cu\"}}]}]}");
		 
    return "db";
  }
  
  @RequestMapping("/webhook")
  public @ResponseBody String webhook(ModelAndView mav,final HttpServletRequest request) {
	  ArrayList<String> output = new ArrayList<String>();
      return Register.parse(  request);
  }
  
  @RequestMapping(value="/webhook", method=RequestMethod.POST )
  public @ResponseBody String webhook( HttpServletRequest request) {
	 
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
	  String rtstr = jb.toString();
	  System.out.println(rtstr);
	 return rtstr;
  }
 
  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      
      return new HikariDataSource(config);
    }
  }
 

}
