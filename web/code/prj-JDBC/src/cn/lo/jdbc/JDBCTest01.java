package cn.lo.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTest01 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		FileReader reader = new FileReader("db.properties");
		Properties pro = new Properties();
		pro.load(reader);
		reader.close();
		
		String driver = pro.getProperty("driver");
		String url = pro.getProperty("url");
		String user = pro.getProperty("user");
		String password = pro.getProperty("password");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//1.注册
		Class.forName(driver);
		//2 connect
		try {
			conn = DriverManager.getConnection(url,user,password);
			
			String sql = "select * from persons where city = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,"Beijing");
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String city = rs.getString("city");
				
				System.out.println(id + "----" + name + "----" + city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs != null)	rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(ps != null)	ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(conn != null )	conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//3 获取预编译的数据库操作对象
			
	}

}
