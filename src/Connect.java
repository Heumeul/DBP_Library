import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    Connection con = null;
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String id = "c##DBP8";
    String password = "1234";

    public Connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("드라이버 적재 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("No Driver.");
        }
    }

    public void DB_Connect() {
        try {
            con = DriverManager.getConnection(url, id, password);
            System.out.println("DB 연결 성공");
        } catch (SQLException e) {
            System.out.println("Connection Fail");
        }
    }

    public void DB_Disconnect() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("DB 연결 종료 성공");
            }
        } catch (SQLException e) {
            System.out.println("Disconnect Fail: " + e.getMessage());
        }
    }
}

