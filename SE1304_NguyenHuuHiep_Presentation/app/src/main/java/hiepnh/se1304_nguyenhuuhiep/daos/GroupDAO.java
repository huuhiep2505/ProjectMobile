package hiepnh.se1304_nguyenhuuhiep.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import hiepnh.se1304_nguyenhuuhiep.db.MyConnection;

public class GroupDAO implements Serializable {
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public GroupDAO() {
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

    public boolean createGroup(String groupId,String groupName, String username) {
        boolean check = false;
        try {
            String sql = "Insert into Groups (groupId,groupName,username) Values (?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, groupId);
            preStm.setString(2, groupName);
            preStm.setString(3, username);
            check = preStm.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }
}
