package hiepnh.se1304_nguyenhuuhiep.daos;

import android.util.Log;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import hiepnh.se1304_nguyenhuuhiep.db.MyConnection;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;


public class UserDAO implements Serializable {
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public UserDAO() {
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
    public String checkLogin(String username, String password) {
        String role = "failed";
        try {
            String sql = "Select role From Users Where username = ? and password = ? and isblock = 'False'";
            conn= MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs=preStm.executeQuery();
            if(rs.next()){
                role=rs.getString("role");
            }
        } catch (Exception e) {
            Log.e("Er: ", e.getMessage());
        }finally{
            closeConnection();
        }
        return role;
    }
    public boolean createUser(String username,String password,String fullname, String phone, String address, String email, Date birthday,String role, String groupId) throws Exception{
        boolean check = false;
        try {
            String sql = "Insert into Users (username,password,fullname,phone,address,email,birthday,role,isblock,groupId) Values (?,?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            preStm.setString(3, fullname);
            preStm.setString(4, phone);
            preStm.setString(5, address);
            preStm.setString(6, email);
            preStm.setDate(7, (java.sql.Date) birthday);
            preStm.setString(8,role);
            preStm.setBoolean(9,false);
            preStm.setString(10,groupId);
            check = preStm.executeUpdate() > 0;
        }finally {
            closeConnection();
        }
        return check;
    }
    public UserDTO getUser(String username) throws Exception {
        String phone = null;
        String address = null;
        String email = null;
        String fullname = null;
        Date birthday = null;
        String groupId = null;
        String role = null;
        UserDTO dto=null;
        try {
            String sql = "Select username,fullname,phone,address,email,birthday,role,groupId From Users Where username = ?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1,username);
            rs=preStm.executeQuery();

            while (rs.next()) {
                username = rs.getString("username");
                fullname = rs.getString("fullname");
                phone = rs.getString("phone");
                address = rs.getString("address");
                email = rs.getString("email");
                birthday = rs.getDate("birthday");
                role = rs.getString("role");
                groupId = rs.getString("groupId");
                dto = new UserDTO(username,fullname,phone,address,email,role,groupId,birthday);
            }
        }finally{
            closeConnection();
        }
        return dto;
    }
    public boolean updateUser(String password, String username, String fullname, String phone, String address, String email, Date birthday, String role, String groupId)throws Exception{
        boolean check=false;
        try {
            String sql="Update Users set password = ?, fullname = ?, phone = ?, address = ?, email = ?, birthday = ?, role = ?, groupId = ? Where username=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1,password);
            preStm.setString(2, fullname);
            preStm.setString(3, phone);
            preStm.setString(4, address);
            preStm.setString(5,email);
            preStm.setDate(6, (java.sql.Date) birthday);
            preStm.setString(7, role);
            preStm.setString(8, groupId);
            preStm.setString(9, username);
            check=preStm.executeUpdate()>0;
        }finally{
            closeConnection();
        }
        return check;
    }
    public boolean delete(String username) throws Exception{
        boolean check = false;
        try {
            String sql = "Update Users set isblock = ? Where username = ?";
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
