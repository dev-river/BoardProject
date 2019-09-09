package kr.co.ca;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class ConnectionTest {

	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "ca";
	private final String password = "ca";
	
	@Test
	public void testConnection() {
		
		try {
			Class.forName(driver);
			System.out.println("드라이버 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("커넥션 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
