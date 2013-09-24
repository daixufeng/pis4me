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

	public Order get(Long id) {// 由ID查询
		ResultSet rs = null;
		Order order = null;
		String strSql = "SELECT id,  date_format(order_date,'%Y-%m-%d') as order_date, product_name, customer_name, product_qty, product_price, amount FROM om_order WHERE id = ?";

		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement psd = conn.prepareStatement(strSql);
			psd.setLong(1, id);
			rs = psd.executeQuery();
			while (rs.next()) {
				order = new Order();
				order.setId(id);
				order.setOrderDate(java.sql.Date.valueOf(rs.getString("order_date")));
				order.setProductName(rs.getString("product_name"));
				order.setCustomerName(rs.getString("customer_name"));
				order.setProductQty(Double.valueOf(rs.getString("product_qty")));
				order.setProductPrice(Double.valueOf(rs.getString("product_price")));
				order.setAmount(Double.valueOf(rs.getString("amount")));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return order;
	}

	public List<Order> find(Map<String, String> filter) {// 查询所有
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
		String strSql = "SELECT id, date_format(order_date,'%Y-%m-%d') as order_date, product_name, customer_name, product_qty, product_price, amount FROM om_order";

		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement psd = conn.prepareStatement(strSql);
			rs = psd.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setId(Long.valueOf(rs.getString("id")));

				String order_date = rs.getString("order_date");
				order.setOrderDate(java.sql.Date.valueOf(order_date));
				order.setProductName(rs.getString("product_name"));
				order.setCustomerName(rs.getString("customer_name"));
				order.setProductQty(Double.valueOf(rs.getString("product_qty")));
				order.setProductPrice(Double.valueOf(rs.getString("product_price")));
				order.setAmount(Double.valueOf(rs.getString("amount")));

				orders.add(order);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return orders;
	}

	public void create(List<Order> orders) {// 新增
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String strSql = "INSERT INTO om_order (order_date, product_name, customer_name, product_qty, product_price, amount) VALUES(?,?,?,?,?,?)";
			for (Order order : orders) {
				PreparedStatement psd = conn.prepareStatement(strSql);
				psd.setDate(1, java.sql.Date.valueOf(sdf.format(order.getOrderDate())));
				psd.setString(2, order.getProductName());
				psd.setString(3, order.getCustomerName());
				psd.setDouble(4, order.getProductQty());
				psd.setDouble(5, order.getProductPrice());
				psd.setDouble(6, order.getAmount());
				psd.execute();
			}
			conn.commit();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void create(Order order) {
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		this.create(orders);
	}

	public void delete(Long ID) {// 删除
		Connection conn = null;
		try {
			conn = getConnection();
			String sqlString = "delete from om_order where ID=?";
			PreparedStatement psd = conn.prepareStatement(sqlString);
			psd.setLong(1, ID);
			psd.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ////////////////////////////////////////////
	public List<Order> doSelectForUpdate(int OrderId) {// 批量修改
		List<Order> orders = new ArrayList<Order>();
		Connection conn = null;
		try {
			conn = getConnection();
			String sqlString = "select * from om_order where ID= ?";
			PreparedStatement psd = conn.prepareStatement(sqlString);
			psd.setInt(1, OrderId);
			ResultSet rs = psd.executeQuery();

			Order order = null;
			while (rs.next()) {
				order = new Order();
				order.setId(Long.valueOf(rs.getString("id")));
				order.setOrderDate(java.sql.Date.valueOf(rs.getString("order_date")));
				order.setProductName(rs.getString("product_name"));
				order.setCustomerName(rs.getString("customer_name"));
				order.setProductQty(Double.valueOf(rs.getString("product_qty")));
				order.setProductPrice(Double.valueOf(rs.getString("product_price")));
				order.setAmount(Double.valueOf(rs.getString("amount")));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	public void update(List<Order> orders) {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sqlString = "update om_order set order_date=?, product_name=?, customer_name=?, product_qty=?, product_price=?, amount=? where ID_product=? ";
			PreparedStatement psd = conn.prepareStatement(sqlString);
			for (Order order : orders) {
				psd.setDate(1, java.sql.Date.valueOf(sdf.format(order.getOrderDate())));
				psd.setString(2, order.getProductName());
				psd.setString(3, order.getCustomerName());
				psd.setDouble(4, order.getProductQty());
				psd.setDouble(5, order.getProductPrice());
				psd.setDouble(6, order.getAmount());
				psd.execute();
			}
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ////////////////////////////////////////////////////
	// 5.1//根据ID参数查询
	public Order getById(int OrderId) {
		Order order = new Order();
		Connection conn = null;
		try {
			conn = getConnection();
			String sqlString = "select * from om_order where OrderId=?";
			PreparedStatement psd = conn.prepareStatement(sqlString);
			psd.setInt(1, OrderId);
			ResultSet rs = psd.executeQuery();//
			while (rs.next()) {
				order = new Order();
				order.setId(Long.valueOf(rs.getString("id")));
				order.setOrderDate(java.sql.Date.valueOf(rs.getString("order_date")));
				order.setProductName(rs.getString("product_name"));
				order.setCustomerName(rs.getString("customer_name"));
				order.setProductQty(Double.valueOf(rs.getString("product_qty")));
				order.setProductPrice(Double.valueOf(rs.getString("product_price")));
				order.setAmount(Double.valueOf(rs.getString("amount")));
				break;
			}
			System.out.println("根据ID参数查询完成!");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	// 5.2//修改
	public void doOnlyUpdate(Order order) {
		Connection conn = null;
		try {
			conn = getConnection();
			String sqlString = "update om_order set order_date=?, product_name=?, customer_name=?, product_qty=?, product_price=?, amount=? where ID=? ";
			PreparedStatement psd = conn.prepareStatement(sqlString);//
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 时间格式转换
			psd.setDate(1, java.sql.Date.valueOf(sdf.format(order.getOrderDate())));// 。。。
			psd.setString(2, order.getProductName());
			psd.setString(3, order.getCustomerName());
			psd.setDouble(4, order.getProductQty());
			psd.setDouble(5, order.getProductPrice());
			psd.setDouble(6, order.getAmount());
			psd.setLong(7, order.getId());
			psd.execute();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ///////////////////////////////////////////////////////
	public List<Order> select(String productName, String cutomName) {// 查询
		List<Order> orders = new ArrayList<Order>();

		Connection conn = null;
		try {
			conn = getConnection();
			String sqlString = "select ID, date_format(order_date,'%Y-%m-%d') as order_date, product_name, customer_name, product_qty, product_price, amount from om_order";
			//
			String sqlWhere = "";
			if (productName != null && !productName.equals("")) {
				if (sqlWhere.length() > 0) {
					sqlWhere += " and ";
				}
				sqlWhere += "product_name=?";
			}
			if (cutomName != null && !cutomName.equals("")) {
				if (sqlWhere.length() > 0) {
					sqlWhere += " and ";
				}
				sqlWhere += "custom_name=?";
			}
			if (sqlWhere.length() > 0) {
				sqlString += " where " + sqlWhere;
			}
			PreparedStatement psd = conn.prepareStatement(sqlString);
			int i = 1;
			if (productName != null && !productName.equals("")) {
				psd.setString(i++, productName);
			}
			if (cutomName != null && !cutomName.equals("")) {
				psd.setString(i++, cutomName);
			}
			//
			ResultSet rs = psd.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(Long.valueOf(rs.getString("id")));
				order.setOrderDate(java.sql.Date.valueOf(rs.getString("order_date")));
				order.setProductName(rs.getString("product_name"));
				order.setCustomerName(rs.getString("customer_name"));
				order.setProductQty(Double.valueOf(rs.getString("product_qty")));
				order.setProductPrice(Double.valueOf(rs.getString("product_price")));
				order.setAmount(Double.valueOf(rs.getString("amount")));
				orders.add(order);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;

	}

	public static void main(String[] args) {
		// 增-----------------------------
		OrderDao dao = new OrderDao();
		Order order = new Order();
		order.setOrderDate(new java.util.Date());
		order.setProductName("HW 8825D");
		order.setCustomerName("Dai Xufeng");
		order.setProductQty(1.0);
		order.setProductPrice(800.00);
		order.setAmount(800.00);
		dao.create(order);
		// 删-----------------------------
		// dao.delete(1);
		// 改-----------------------------
		dao.doOnlyUpdate(order);
		// 查-----------------------------
		String ProductName = "P1";
		String ClientName = "C1";
		dao.select(ProductName, ClientName);

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
