/**
 * 
 */
package com.SE.SQLite;

import java.sql.*;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * @author Bryson Han
 *
 */
public class SQLiteConnector {
	
	Connection m_con = null;
	ResultSet rs;
	Statement m_stmt;
	Logger m_logger = Logger.getLogger(this.getClass());
	PreparedStatement pstmt = null;
	
	
	public SQLiteConnector (){
		connectToSQLite();
	}
	
	public void connectToSQLite (){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      m_con = DriverManager.getConnection("jdbc:sqlite:LocalClient.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    m_logger.debug("Opened database successfully");
	 }
	
	public ResultSet executeInsert (String sql){
		m_stmt = null;
	    try {
	        m_stmt = m_con.createStatement();
	        m_stmt.executeUpdate(sql);
	        m_stmt.close();
	        m_con.commit();
	      } catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	      }
	    m_logger.debug("Records created successfully");
		return rs;
	}
	
	public void executeUpdate (String sql,ArrayList<String> conditionSqlPara){
		try {
			pstmt = m_con.prepareStatement(sql);
			if (conditionSqlPara != null) {
				for (int i = 0; i < conditionSqlPara.size(); i++) {
					pstmt.setString(i + 1, conditionSqlPara.get(i));
				}
			}
			pstmt.executeUpdate();

			// pstmt.close();
			m_con.commit();
			m_logger.debug("Update operation is successful!");

		}
		catch (Exception e){
			m_logger.debug( e.getClass().getName() + ": " + e.getMessage() );
		}
		
	}
	
	public ResultSet executeSelect (String sql){
		m_stmt = null;
		rs = null;
		try{
			m_stmt = m_con.createStatement();
			m_con.setAutoCommit(false);
			rs = m_stmt.executeQuery(sql);
			m_logger.debug("Select operation is successful!");
			
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return rs;
	}
	
	public void disconnect () throws SQLException{
	
		m_con.close();
	}
}
