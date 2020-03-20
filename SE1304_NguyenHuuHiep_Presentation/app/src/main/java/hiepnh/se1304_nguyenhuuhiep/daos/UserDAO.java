package hiepnh.se1304_nguyenhuuhiep.daos;

import android.util.Log;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public boolean createUser(String username,String password,String fullname, String phone, String address, String email, String birthday,String role, String groupId) {
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
            preStm.setString(7, birthday);
            preStm.setString(8,role);
            preStm.setBoolean(9,false);
            preStm.setString(10,groupId);
            check = preStm.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }
    public List<String> getUser()  {
        List<String> result = new ArrayList<>();
        String username = null;
        UserDTO dto= null;
        try {
            String sql = "Select username From Users Where isblock LIKE '0' and role LIKE 'user' and groupId LIKE 'waiting'";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            rs=preStm.executeQuery();

            while (rs.next()) {
                username = rs.getString("username");

                dto = new UserDTO(username);
                String name = dto.getUsername();
                result.add(name);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }
    public List<UserDTO> getAccountManagement()  {
        List<UserDTO> result = new ArrayList<>();
        String username = null;
        String phone = null;
        String address = null;
        String email = null;
        String fullname = null;
        String birthday = null;
        String groupId = null;
        String role = null;
        UserDTO dto=null;
        try {
            String sql = "Select username,fullname,phone,address,email,birthday,role,groupId From Users Where isblock LIKE'0'";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            rs=preStm.executeQuery();

            while (rs.next()) {
                username = rs.getString("username");
                fullname = rs.getString("fullname");
                phone = rs.getString("phone");
                address = rs.getString("address");
                email = rs.getString("email");
                birthday = rs.getString("birthday");
                role = rs.getString("role");
                groupId = rs.getString("groupId");
                dto = new UserDTO(username,fullname,phone,address,email,role,groupId,birthday);
                result.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }

    public List<String> getManager()  {
        List<String> result = new ArrayList<>();
        String username = null;
        UserDTO dto= null;
        try {
            String sql = "Select username From Users Where isblock LIKE '0' and role LIKE 'manager'";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            rs=preStm.executeQuery();

            while (rs.next()) {
                username = rs.getString("username");

                dto = new UserDTO(username);
                String name = dto.getUsername();
                result.add(name);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }

    public boolean updateUser( String username, String fullname, String phone, String address, String email, String birthday, String role, String groupId){
        boolean check=false;
        try {
            String sql="Update Users set fullname = ?, phone = ?, address = ?, email = ?, birthday = ?, role = ?,isblock = ?, groupId = ? Where username=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, fullname);
            preStm.setString(2, phone);
            preStm.setString(3, address);
            preStm.setString(4,email);
            preStm.setString(5, birthday);
            preStm.setString(6, role);
            preStm.setBoolean(7,false);
            preStm.setString(8, groupId);
            preStm.setString(9, username);
            check=preStm.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection();
        }
        return check;
    }
    public boolean delete(String username) {
        boolean check = false;
        try {
            String sql = "Update Users set isblock = ? Where username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            preStm.setString(2, username);
            check = preStm.executeUpdate() > 0;

        }catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            closeConnection();
        }
        return check;
    }
    public boolean updateUserToGroup(ArrayList<String> username , String groupId) {
        boolean check = true;
        try {
            String sql="Update Users set groupId = ? Where username in (?)";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);

            //Array array = conn.createArrayOf("VARCHAR", username.toArray());

                for (String name: username
                     ) {
                    preStm.setString(1, groupId);
                    preStm.setString(2, name);
                    check = (check && (preStm.executeUpdate() > 0));
                }
            //check=preStm.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection();
        }
        return check;
    }
    public List<String> getUsername(){
        List<String> listUsername = new ArrayList<>();
        try {
            String sql = "Select username from Users";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()){
                String username = rs.getString("username");
                listUsername.add(username);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return listUsername;
    }
}
