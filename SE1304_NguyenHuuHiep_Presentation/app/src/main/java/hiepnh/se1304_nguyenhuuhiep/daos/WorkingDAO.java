package hiepnh.se1304_nguyenhuuhiep.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.db.MyConnection;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class WorkingDAO {
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;


    public WorkingDAO() {
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

    public List<WorkingDTO> getListWorking(String userHandle) {
        List<WorkingDTO> result = new ArrayList<>();
        String name;
        String id;
        WorkingDTO dto=null;
        try {
            String sql = "Select workId, workName From Working where userHandle = ?";
            conn= MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, userHandle);
            rs=preStm.executeQuery();

            while (rs.next()) {
                name = rs.getString("workName");
                id = rs.getString("workId");
                dto = new WorkingDTO(id, name);
                result.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }

    public WorkingDTO getWorking(String id) {

        WorkingDTO dto = null;
        try {
            String sql = "Select * From Working Where workId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();

            while (rs.next()) {
                dto = new WorkingDTO(rs.getString("workId"), rs.getString("workName"), rs.getString("workDes")
                        , rs.getString("workProcess"),rs.getString("description"), rs.getString("status")
                        , rs.getString("userCreate"), rs.getString("userHandle"), rs.getInt("mark")
                        , rs.getString("timeMark"), rs.getString("timeFrom")
                ,rs.getString("timeTo"), rs.getString("timeCreate"),  rs.getBoolean("confirmFinish"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }

    public Boolean updateStatus(String st, String id) {

        boolean check = false;
        try {
            String sql = "Update Working Set status = ? Where workId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, st);
            preStm.setString(2, id);
            check = preStm.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }
}
