package hiepnh.se1304_nguyenhuuhiep.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.db.MyConnection;
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

    public List<WorkingDTO> getListWorking() {
        List<WorkingDTO> result = new ArrayList<>();
        String name;
        String id;
        WorkingDTO dto=null;
        try {
            String sql = "Select workId, workName From Working";
            conn= MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
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
        String name;
        String des;
        WorkingDTO dto = null;
        try {
            String sql = "Select workName, workDes From Working Where workId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();

            while (rs.next()) {
                name = rs.getString("workName");
                des = rs.getString("workDes");
                dto = new WorkingDTO(id, name, des);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }
}
