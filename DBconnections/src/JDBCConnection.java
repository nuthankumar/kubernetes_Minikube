
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {

	public static void main(String args[]) {
		String url=System.getenv("URL");
		try {
			System.out.println(url);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "nuthan");
			if(con!=null) {
				System.out.println("connection success");
			}
			else {
				System.out.println("cannot establish connection");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
