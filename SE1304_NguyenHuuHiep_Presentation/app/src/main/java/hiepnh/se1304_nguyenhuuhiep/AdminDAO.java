package hiepnh.se1304_nguyenhuuhiep;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import nguyenhuuhiep.presentation.db.MyConnection;
import nguyenhuuhiep.presentation.dto.AdminDTO;

public class AdminDAO implements Serializable {
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;


    public AdminDAO() {
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

    public boolean creatAdmin(String id, String name, String phone, String address, String email, String username, Date birthday) throws Exception{
        boolean check = false;
        try {
            String sql = "Insert into Admin (id,name,phone,address,email,username,birthday) Values (?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.setString(2, name);
            preStm.setString(3, phone);
            preStm.setString(4, address);
            preStm.setString(5, email);
            preStm.setString(6, username);
            preStm.setDate(7, (java.sql.Date) birthday);
            check = preStm.executeUpdate() > 0;
        }finally {
            closeConnection();
        }
        return check;
    }
    public AdminDTO getAdmin(String name) throws SQLException {
        String phone = null;
        String address = null;
        String email = null;
        String username = null;
        Date birthday = null;
        AdminDTO dto=null;
        try {
            String sql = "Select name, phone, address, email, username, birthday From Admin Where name = ?";
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

                dto = new AdminDTO(name,phone,address,email,username,birthday);
            }
        }finally{
            closeConnection();
        }
        return dto;
    }

    public boolean updateAdmin(String username, String name, String phone, String address, String email, Date birthday)throws Exception{
        boolean check=false;
        try {
            String sql="Update Admin set name = ?, phone = ?, address = ?, email = ?, birthday = ? Where username=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, name);
            preStm.setString(2, phone);
            preStm.setString(3, address);
            preStm.setString(4,email);
            preStm.setDate(5, (java.sql.Date) birthday);
            preStm.setString(6,username);
            check=preStm.executeUpdate()>0;
        }finally{
            closeConnection();
        }
        return check;
    }
    public boolean delete(String username) throws Exception{
        boolean check = false;
        try {
            String sql = "Update Admin set del_flag = ? Where username = ?";
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