package cn.lo.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class IOTest01 {
	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader("db.properties");
		
		Properties pro = new Properties();
		
		pro.load(reader);
		
		reader.close();
		
		
	}
}
