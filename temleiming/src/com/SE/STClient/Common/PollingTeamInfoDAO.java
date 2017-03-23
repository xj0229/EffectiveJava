/**
 * 
 */
package com.SE.STClient.Common;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.MysqlParameterMetadata;
import com.mysql.jdbc.Statement;

/**
 * @author Bryson Han
 *
 */
public class PollingTeamInfoDAO {

	//单例模式
	private static volatile PollingTeamInfoDAO instance;
	private PollingTeamInfoDAO()
	{
		connectDB();		
	}	
	public static PollingTeamInfoDAO getInstance()
	{
		if (instance==null) {
			synchronized (PollingTeamInfoDAO.class) {
				if (instance==null) {
					instance=new  PollingTeamInfoDAO();					
				}
				
			}
			
		}
		return instance;
	}
	
	
	Logger mLogger = Logger.getLogger(PollingTeamInfoDAO.class);
	//private field for connection
	private Connection m_connect;
	//private field for statement
	private Statement m_statement;
	//private Result Set
	private ResultSet m_resultSet = null;
	PreparedStatement pstmt = null; 
	
	
	public void connectDB(){ 
		System.out.println("开始连接数据库");
	 try {
		 //Install the JDBC driver
         Class.forName("com.mysql.jdbc.Driver");  
         //System.out.println("开始连接数据库"); 
	 }
	 catch (ClassNotFoundException e) {
		 System.out.println("数据库驱动加载异常！");
         e.printStackTrace();  
     } 
	 try{
		 //连接字符串中的url必须跟上utf8字符集，否则查询不到数据库中的中文列
		 m_connect = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.10.198:3306/testdata?"
		            + "user=root&password=123456xj&useUnicode=true&characterEncoding=utf-8&useSSL=false"
);
         System.out.println("连接到MySQL数据库" + m_connect);
         //Build the statement object
         m_statement = (Statement) m_connect.createStatement();
     }
	 catch (SQLException e1) {
		 System.out.println("数据库连接异常！");
         e1.printStackTrace();  
     } 
	}
	/*
	 * The update, insert, create SQL statement operation
	 */
	public int executeUpdate (String sql,ArrayList<String> conditionSqlPara)
	{
		int result = 0;
		try{
			pstmt=m_connect.prepareStatement(sql);
			for (int i = 0; i < conditionSqlPara.size(); i++) {
				pstmt.setString(i+1, conditionSqlPara.get(i));
			}			
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		if (result < 0)
		{
			mLogger.error("数据库操作错误");
		}
		return result;
	}
	
	/*
	 * The select SQL statement operation
	 */
	public ResultSet executeSelect (String sqlStr,String conditionSqlPara) throws SQLException
	{
		
		pstmt=m_connect.prepareStatement(sqlStr);
		if (conditionSqlPara!="") {
			pstmt.setString(1, conditionSqlPara);
		}
		
		m_resultSet = null;
		try{
			m_resultSet = pstmt.executeQuery();
			
		}catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		if (m_resultSet == null)
		{
			mLogger.error("未找到结果");
		}
		return m_resultSet;
	}
	
	
	public void insertAllDataToSql(String sqlStr,PollingTeamInfo newPollingTeamData) throws SQLException
	{
		
		pstmt=m_connect.prepareStatement(sqlStr);
		
		pstmt.setString(1, newPollingTeamData.getPollingTeamName());
		pstmt.setString(2, newPollingTeamData.getCamera());
		pstmt.setString(3, newPollingTeamData.getMonitor());
		pstmt.setString(4, newPollingTeamData.getCameraSpecifyPosition());
		pstmt.setString(5, newPollingTeamData.getGapTime());
		pstmt.setString(6, newPollingTeamData.getMultiMonitor());
		pstmt.setString(7, newPollingTeamData.getRunningState());
		System.out.println("插入成功");
		pstmt.executeUpdate();			

	}
	
	
	
	public void deleteAllDataToSql(String sqlStr,String pollingTeamNamePara) throws SQLException
	{
		
		pstmt=m_connect.prepareStatement(sqlStr);
		pstmt.setString(1, pollingTeamNamePara);
		pstmt.executeUpdate();
		//pstmt.close();
	}
	
	
	public int calculateRowCount(String sqlStr,String pollingTeamNamePara) throws SQLException
	{
		int rowCount=0;		
		//pstmt=m_connect.prepareStatement("select count(*) AS PollingTeamNameCount from pollingteaminfo where PollingTeamName like '轮巡组1' ");
		pstmt=m_connect.prepareStatement(sqlStr);
		pstmt.setString(1, pollingTeamNamePara);
		ResultSet rowCountSR=pstmt.executeQuery();
		while (rowCountSR.next()) {
			
			rowCount=rowCountSR.getInt("PollingTeamNameCount");
		}
		rowCountSR.close();
		return rowCount;
	}
	
	
	/*
	 * Close the connection to MySQL
	 */
	public void closeConnection ()
	{ 
        if(m_connect != null){  // 关闭连接对象   
            try{   
            	m_connect.close() ;   
            }catch(SQLException e){   
               e.printStackTrace() ;   
            }  
        }
	}
	
	/*
	 * Close the statement
	 */
	public void closeStatement()
	{
		// 关闭声明   
      try{   
    	  m_statement.close() ;   
      }catch(SQLException e){   
          e.printStackTrace() ;   
      }   
	}
	
	/*
	 * Close the result set
	 */
	public void closeResultSet ()
	{
		// 关闭记录集   
      try{   
          m_resultSet.close() ;   
      }catch(SQLException e){   
          e.printStackTrace() ;   
      }    
	}
}
