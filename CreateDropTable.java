/*
 * Copyright (c) 2017  Oracle Corporation
 *
 * The Universal Permissive License (UPL), Version 1.0
 * 
 * Subject to the condition set forth below, permission is hereby granted to any person obtaining a copy of this 
 * software, associated documentation and/or data (collectively the "Software"), free of charge and under any and 
 * all copyright rights in the Software, and any and all patent rights owned or freely licensable by each licensor 
 * hereunder covering either 
 * (i) the unmodified Software as contributed to or provided by such licensor, or 
 * (ii) the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if one is included with the Software 
 * (each a “Larger Work” to which the Software is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create derivative works of, display, 
 * perform, and distribute the Software and make, use, sell, offer for sale, import, export, have made, and have 
 * sold the Software and the Larger Work(s), and to sublicense the foregoing rights on either these or other terms.
 *
 * This license is subject to the following condition:
 * The above copyright notice and either this complete permission notice or at a minimum a reference to the UPL 
 * must be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS IN THE SOFTWARE. 
 */
package oracle.dbtools.jdbc.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author debjanibiswas
 * This example creates a table, inserts records, get backs a ResultSet, and drops a table
 * from an existing database
 * 
 */
public class CreateDropTable {

  public static void main(String[] args) {
   
    // REST JDBC driver name and database URL
    String DRIVER = "oracle.dbtools.jdbc.Driver";  
    String DB_URL = "http://slc04qag.us.oracle.com:8082/ords/adhocsqltest/";
        
    //  Database credentials
    String USER = "debjani";
    String PASS = "debjani";
    
    Properties cred = new Properties();
    cred.put("user", USER);    
    cred.put("password", PASS);
    
    try {
      Class.forName(DRIVER);
      
      // Open a connection
      Connection conn = DriverManager.getConnection(DB_URL, cred);
      
      // Create a Statement
      Statement stmt = conn.createStatement();
      
      String sql_create   = "CREATE TABLE T1(col1 int)";
      String sql_insert1  = "INSERT INTO T1 VALUES(110)";
      String sql_select   = "SELECT * FROM T1";
      String sql_drop     = "drop table T1";
      
      // Create a table
      stmt.execute(sql_create);
      
      // Insert a record
      int insert = stmt.executeUpdate(sql_insert1);
      System.out.println("Records updated: " + insert);
      
      // Select records
      ResultSet rs = stmt.executeQuery(sql_select);
      while(rs.next()) {
        rs.getInt(1);
        System.out.println("Value for getInt(int columnIndex): " + rs.getInt(1));
        
        System.out.println("Value for getInt(String columnLabel): " + rs.getString("col1")); 
      }
      
      // Drop the table
      stmt.execute(sql_drop);
    }
    catch(SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
