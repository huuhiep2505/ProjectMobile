package hiepnh.se1304_nguyenhuuhiep;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO implements Serializable {
    private Connection conn = null ;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public AccountDAO() {
    }

    private void closeConnection(){
        try {
            if(rs != null){
                rs.close();
            }
            if(preStm != null){
                preStm.close();
            }
            if(conn != null){
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String checkLogin(String username, String password) throws Exception{
        String role = "failed";
        try {
            String sql = "Select role From Accounts Where username = ? and password = ? and del_flag LIKE '0'";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs=preStm.executeQuery();
            if(rs.next()){
                role=rs.getString("role");
            }
        } catch (Exception e) {
        }finally{
            closeConnection();
        }
        return role;
    }
    public boolean createAccount(String username, String password, String role) throws Exception{
        boolean check = false;
        try {
            String sql = "Insert into Accounts (username, password,role,del_flag) Values (?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            preStm.setString(3, role);
            preStm.setBoolean(4,false);
            check = preStm.executeUpdate() > 0;
        }finally {
            closeConnection();
        }
        return check;
    }
    public AccountDTO getAccount(String name) throws SQLException {
        String username;
        String password;
        String role;
        AccountDTO dto=null;
        try {
            String sql = "Select username, password, role From Accounts Where username = ?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1,name);
            rs=preStm.executeQuery();

            while (rs.next()) {
                username = rs.getString("Username");
                password=rs.getString("Password");
                role = rs.getString("Role");

                dto = new AccountDTO(username, password, role);

            }
        }finally{
            closeConnection();
        }
        return dto;
    }
    public boolean updateAccount(String username, String password, String role)throws Exception{
        boolean check=false;
        try {
            String sql="Update Accounts set password=?,role=? Where Username=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, password);
            preStm.setString(2, role);
            preStm.setString(3, username);
            check=preStm.executeUpdate()>0;
        }finally{
            closeConnection();
        }
        return check;
    }
    public boolean delete(String username) throws Exception{
        boolean check = false;
        try {
            String sql = "Update Accounts set del_flag = ? Where username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            preStm.setString(2, username);
            check = preStm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return check;
    }
}
