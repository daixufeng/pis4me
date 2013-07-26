package com.balian.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.balian.app.domain.Order;

public class OrderDao {

	
	
	public Order get(Long id){
		ResultSet rs = null;
		Order order = null;
		String strSql = "SELECT id, order_date, product_name, customer_name, product_qty, product_price, amount FROM om_order WHERE id = ?";
		
		Connection conn = getConnection();
		
		try{			
			PreparedStatement psd = conn.prepareStatement(strSql);
			psd.setLong(1, id);
			rs = psd.executeQuery();
			
			while(rs.next()){
				order = new Order();
				order.setId(id);
				order.setOrderDate(java.sql.Date.valueOf(rs.getString("order_date")));
				order.setProductName(rs.getString("product_name"));
				order.setCustomerName(rs.getString("customer_name"));
				order.setProductQty(Double.valueOf(rs.getString("product_qty")));
				order.setProductPrice(Double.valueOf(rs.getString("product_price")));
				order.setAmount(Double.valueOf(rs.getString("amount")));
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return order;
	}
	
	public List<Order> find(Map<String, String> filter){
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
		String strSql = "SELECT id, order_date, product_name, customer_name, product_qty, product_price, amount FROM om_order";
		
		Connection conn = getConnection();
		
		try{			
			PreparedStatement psd = conn.prepareStatement(strSql);
			rs = psd.executeQuery();
			
			while(rs.next()){
				Order order = new Order();
				order.setId(Long.valueOf(rs.getString("id")));
				//order.setOrderDate(java.sql.Date.valueOf(rs.getString("order_date")));
				order.setProductName(rs.getString("product_name"));
				order.setCustomerName(rs.getString("customer_name"));
				order.setProductQty(Double.valueOf(rs.getString("product_qty")));
				order.setProductPrice(Double.valueOf(rs.getString("product_price")));
				order.setAmount(Double.valueOf(rs.getString("amount")));
				
				orders.add(order);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return orders;
	}
	
	
	public void create(List<Order> orders){
		Connection conn = null;
		
		try{
			conn = getConnection();
			conn.setAutoCommit(false);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			String strSql = "INSERT INTO om_order (order_date, product_name, customer_name, product_qty, product_price, amount) VALUES(?,?,?,?,?,?)";
			for(Order order : orders){
				PreparedStatement psd = conn.prepareStatement(strSql);
				psd.setDate(1, java.sql.Date.valueOf(sdf.format(order.getOrderDate())));
				
				psd.setString(2, order.getProductName());
				psd.setString(3, order.getCustomerName());
				psd.setDouble(4,order.getProductQty());
				psd.setDouble(5, order.getProductPrice());
				psd.setDouble(6, order.getAmount());				
				psd.execute();
			}
			conn.commit();
			conn.close();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}	
	}
	
	public void create(Order order){
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		this.create(orders);
	}
	
	public static void main(String[] args){
		OrderDao dao = new OrderDao();
		Order order = new Order();
		//order =  dao.get(1L);
		//System.out.println(order);
		
		order.setOrderDate(new java.util.Date());
		order.setProductName("HW 8825D");
		order.setCustomerName("Dai Xufeng");
		order.setProductQty(1.0);
		order.setProductPrice(800.00);
		order.setAmount(800.00);
		
		dao.create(order);
	}
	
	public static Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");// 注册驱动

			// 建立连接的语句，注意用的test数据库，用户为root，密码为空，这个根据实际调整
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/crunii", "root", "root");

			return conn;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
}
