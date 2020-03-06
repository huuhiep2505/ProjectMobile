package hiepnh.se1304_nguyenhuuhiep.db;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection{

//    @SuppressLint("NewApi")
    public static Connection getMyConnection() throws Exception{
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        return DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.9:1433;" +
                "databaseName=PRM391","sa", "123456");
    }
}
