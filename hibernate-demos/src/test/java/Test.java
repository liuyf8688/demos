import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://127.0.0.1:3306?user=liu%25200&password=123456";
		
//		String url = "jdbc:mysql://mysqlservices.chinacloudapp.cn/sp2p7_zrjk?user=rilidaidbt%25webuser&password=zrwjsdba";
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {	
			connection = DriverManager.getConnection(url);
			
			ps = connection.prepareStatement("show databases;");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} finally {
			
			if (ps != null) {
				ps.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
	}

}
