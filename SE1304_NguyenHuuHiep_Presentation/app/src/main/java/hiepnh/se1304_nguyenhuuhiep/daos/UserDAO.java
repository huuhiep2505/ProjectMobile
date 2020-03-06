package hiepnh.se1304_nguyenhuuhiep.daos;

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

    public boolean createUser(String code, String name, String phone, String address, String email, String username, Date birthday, String manager) throws Exception{
        boolean check = false;
        try {
            String sql = "Insert into Users (code,name,phone,address,email,username,birthday,manager) Values (?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, code);
            preStm.setString(2, name);
            preStm.setString(3, phone);
            preStm.setString(4, address);
            preStm.setString(5, email);
            preStm.setString(6, username);
            preStm.setDate(7, (java.sql.Date) birthday);
            preStm.setString(8,manager);
            check = preStm.executeUpdate() > 0;
        }finally {
            closeConnection();
        }
        return check;
    }
    public UserDTO getUser(String name) throws Exception {
        String phone = null;
        String address = null;
        String email = null;
        String username = null;
        Date birthday = null;
        String manager = null;
        UserDTO dto=null;
        try {
            String sql = "Select name, phone, address, email, username, birthday,manager From Users Where name = ?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1,name);
            rs=preStm.executeQuery();

            while (rs.next()) {
                name = rs.getString("name");
                phone = rs.getString("phone");
                address = rs.getString("address");
                email = rs.getString("email");
                username = rs.getString("username");
                birthday = rs.getDate("birthday");
                manager = rs.getString("manager");
//                dto = new UserDTO(name,phone,address,email,username,birthday,manager);
            }
        }finally{
            closeConnection();
        }
        return dto;
    }
    public boolean updateUser(String username, String name, String phone, String address, String email, Date birthday, String manager)throws Exception{
        boolean check=false;
        try {
            String sql="Update Users set name = ?, phone = ?, address = ?, email = ?, birthday = ?, manager = ? Where username=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, name);
            preStm.setString(2, phone);
            preStm.setString(3, address);
            preStm.setString(4,email);
            preStm.setDate(5, (java.sql.Date) birthday);
            preStm.setString(6, manager);
            preStm.setString(7,username);
            check=preStm.executeUpdate()>0;
        }finally{
            closeConnection();
        }
        return check;
    }
    public boolean delete(String username) throws Exception{
        boolean check = false;
        try {
            String sql = "Update Users set del_flag = ? Where username = ?";
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
