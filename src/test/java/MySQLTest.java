import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MySQLTest {

    public static void main(String[] args) {
        // 加载配置文件
        Properties props = new Properties();
        try (InputStream in = MySQLTest.class.getResourceAsStream("/application.properties")) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // 从配置文件中获取数据库连接信息
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        // JDBC对象声明
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. 注册 JDBC 驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 打开一个连接
            conn = DriverManager.getConnection(url, user, password);

            // 3. 执行查询
            stmt = conn.createStatement();
            String sql = "SELECT * FROM users";
            rs = stmt.executeQuery(sql);

            // 4. 处理结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("password");

                // 打印结果集中的数据
                System.out.println("ID: " + id + ", Username: " + username + ", password: " + email);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭资源
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
