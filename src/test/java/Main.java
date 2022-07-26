import org.testng.annotations.Test;
import java.sql.*;

public class Main {
    @Test
    void testConnection() throws InterruptedException, SQLException {
        Connection connection = DbConnection.getConnection();
        Thread t1 = new Thread(){
            public void run(){
            try {
                DbConnection.insert(connection,"insert from t1","insert from t1");
                DbConnection.update(connection,DbConnection.getSize(connection),"update from t1","update from t1");
                DbConnection.insert(connection,"insert from t1","insert from t1");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }};
        Thread t2 = new Thread(){
            public void run(){
            try {
                DbConnection.insert(connection,"insert from t2","insert from t2");
                DbConnection.update(connection,DbConnection.getSize(connection),"update from t2","update from t2");
                DbConnection.insert(connection,"insert from t2","insert from t2");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }};
        Thread t3 = new Thread(){
            public void run(){
            try {
                DbConnection.insert(connection,"insert from t3","insert from t3");
                DbConnection.update(connection,DbConnection.getSize(connection),"update from t3","update from t3");
                DbConnection.insert(connection,"insert from t3","insert from t3");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
