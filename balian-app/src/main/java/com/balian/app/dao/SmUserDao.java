package com.balian.app.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
//import com.mysql.jdbc.Driver;

import com.balian.app.domain.*;

public class SmUserDao{
	
	public Connection getConnection(){
		Connection conn = null;
		
        try {
        	Class.forName("com.mysql.jdbc.Driver");//注册驱动
        	
        	//建立连接的语句，注意用的test数据库，用户为root，密码为空，这个根据实际调整
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javatest", "root", "root");
            
            return conn;
        }
        catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	return null;
        }
	}
	
	public void getUser(){
		ResultSet rs = null;
		String strSql = "SELECT userid, username, password, email FROM sm_user;";
		Statement stmt = null;
		
		try{
			Connection conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
			
			while(rs.next()){
				int userId = new Integer(rs.getString("userid")).intValue();
				String userName = rs.getString("username");
				String email = rs.getString("email");
				System.out.println("UserId: " + userId 
						+ ", UserName: " + userName + " Email:" + email);
								
				//System.out.println(userName);
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());			
		}
	}	
	
	public User getById(Long id){
		ResultSet rs = null;
		String strSql = "SELECT userid, username, password, email FROM sm_user WHERE userid = ?;";
		PreparedStatement psd = null;
		User user = null;
		
		try{
			Connection conn = getConnection();
			psd = conn.prepareStatement(strSql);
			psd.setLong(1, id);
			rs = psd.executeQuery();
			
			while(rs.next()){
				user = new User();
				user.setUserId(id);
				user.setUserName(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				break;
			}
			return user;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public User getByUserName(String userName){
		ResultSet rs = null;
		String strSql = "SELECT userid, username, password, email FROM sm_user WHERE username = ?";
		PreparedStatement psd = null;
		User user = null;
		
		try{
			Connection conn = getConnection();
			psd = conn.prepareStatement(strSql);
			psd.setString(1, userName);
			rs = psd.executeQuery();
			
			while(rs.next()){
				user = new User();
				user.setUserId(new Long(rs.getString("userid")));
				user.setUserName(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));	
				break;
			}
			return user;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public void create(List<User> users) throws SQLException{
		Connection conn = null;
		
		try{
			conn = getConnection();
			conn.setAutoCommit(false);
			
			String strSql = "insert into sm_user (username, password, email) values(?,?,?)";
			for(User user:users){
				PreparedStatement psd = conn.prepareStatement(strSql);
				psd.setString(1, user.getUserName());
				psd.setString(2, user.getPassword());
				psd.setString(3, user.getEmail());
				
				psd.execute();
				throw new SQLException("over count\r\n");
			}
			conn.commit();
			conn.close();
		}
		catch(SQLException ex){
			conn.rollback();
			System.out.print(ex.getMessage());
		}	
	}
	
	public void create(User user) throws SQLException{
		List<User> users = new ArrayList<User>();
		users.add(user);
		create(users);		
	}
	
	public void update(int userId, String userName){
		Connection conn = null;
		
		try{
			conn = getConnection();
			PreparedStatement psd = conn.prepareStatement("update sm_user set username = ? where userid = ?");
			psd.setString(1, userName);
			psd.setInt(2,userId);
			psd.execute();
			conn.close();
		}
		catch(Exception ex){
			System.out.print(ex.getMessage());
		}		
	}
}
