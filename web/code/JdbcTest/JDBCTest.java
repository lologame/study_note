import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBCTest{
	public static void main(String[] args){
		
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","lologame");
			
			Statement stmt = conn.createStatement();
			
			String sql = "insert into persons(id,name,city) values(200,'张三','北京')";
			
			System.out.println(stmt.executeUpdate(sql));
		}catch(SQLException e){
			e.printStackTrace();
		}

		
		
	}
}