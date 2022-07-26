
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.*;

public class DbConnection {

    private static volatile Connection connection = null;
    private static String url = "jdbc:sqlserver://WINDOWS-NV7EPK3;databaseName=User;encrypt=true;trustServerCertificate=true;user=MyLogin;password=123";

    public static Connection getConnection() throws SQLException {
        if (connection == null){
            synchronized (DbConnection.class){
                DriverManager.registerDriver(new SQLServerDriver());
                connection = DriverManager.getConnection(url);
                connection.setAutoCommit(true);
                if (connection != null){
                    System.out.println("Connected to the database!");
                }else {
                    throw new SQLException("DB Connection Faile");
                }
            }
        }
        return connection;
    }
    public static int insert(Connection connection,String name,String surname) throws SQLException {
        Connection conn = connection;
        Statement statement =  conn.createStatement();
        return statement.executeUpdate("INSERT INTO [User].[dbo].[Users] (name,surname) values ('"+name+"','"+surname+"')");
    }
    public static int update(Connection connection,int index,String name,String surname) throws SQLException {
        Connection conn = connection;
        Statement statement =  conn.createStatement();
        return statement.executeUpdate("UPDATE [User].[dbo].[Users] SET name = '"+name+"', surname = '"+surname+"' WHERE id = "+index+"");
    }
    public static ResultSet selectAll(Connection connection) throws SQLException {
        Connection conn = connection;
        Statement statement =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        return statement.executeQuery("SELECT * FROM [User].[dbo].[Users]");
    }
    public static int getSize(Connection connection) throws SQLException {
        ResultSet resultSet = selectAll(connection);
        int size =0;
        if (resultSet != null)
        {
            resultSet.last();
            size = resultSet.getRow();
        }
        return size;
    }

}