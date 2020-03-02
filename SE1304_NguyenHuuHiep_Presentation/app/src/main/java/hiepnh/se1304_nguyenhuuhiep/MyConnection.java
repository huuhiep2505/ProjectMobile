package hiepnh.se1304_nguyenhuuhiep;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection implements Serializable {
    public static Connection getMyConnection(){
        Connection conn=null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Presentation", "sa", "01202823114");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
