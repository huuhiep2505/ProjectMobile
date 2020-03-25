package hiepnh.se1304_nguyenhuuhiep.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.db.MyConnection;
import hiepnh.se1304_nguyenhuuhiep.dtos.GroupDTO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

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
    public List<String> getGroup()  {
        List<String> result = new ArrayList<>();
        String groupId = null;
        GroupDTO dto= null;
        try {
            String sql = "Select groupId From Groups";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            rs=preStm.executeQuery();

            while (rs.next()) {
                groupId = rs.getString("groupId");

                dto = new GroupDTO(groupId);
                String id = dto.getGroupId();
                result.add(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }
    public List<GroupDTO> getListGroup(String userHandle) {
        List<GroupDTO> result = new ArrayList<>();
        String name;
        String id;
        GroupDTO dto=null;
        try {
            String sql = "Select groupId, groupName From Groups where username = ?";
            conn= MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, userHandle);
            rs=preStm.executeQuery();

            while (rs.next()) {
                name = rs.getString("groupName");
                id = rs.getString("groupId");
                dto = new GroupDTO(id, name);
                result.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }

}
