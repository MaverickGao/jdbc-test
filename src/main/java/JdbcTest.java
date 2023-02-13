import java.sql.*;
import java.util.Objects;

/**
 * JDBC使用步骤
 *
 * @author 高智恒
 */
public class JdbcTest {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 第一步、加载Mysql数据库依赖
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 第二步、通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gaozhiheng_test?characterEncoding=utf-8&serverTimezone=UTC",
                    "root",
                    "123456"
            );
            // 第三步、定义SQL语句，使用?表示占位符
            String sql = "select * from user where user_name = ?";
            // 第四步、获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            // 第五步、设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, "gaozhiheng");
            // 第六步、向数据库发出sql执行，得到查询结果集合
            resultSet = preparedStatement.executeQuery();
            // 遍历查询结果
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                System.out.println("id: " + id + "; userName: " + userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 最终步、释放资源
            if (Objects.nonNull(resultSet)) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (Objects.nonNull(preparedStatement)) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
