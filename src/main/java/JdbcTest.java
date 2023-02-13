/**
 * JDBC使用步骤
 *
 * @author 高智恒
 */
public class JdbcTest {

    public static void main(String[] args) {
        try {
            // 第一步、加载Mysql数据库依赖
            Class.forName("com.mysql.jdbc.Driver");
            //
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
