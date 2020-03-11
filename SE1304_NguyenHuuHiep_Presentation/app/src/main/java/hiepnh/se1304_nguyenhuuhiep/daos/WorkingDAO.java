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
            String sql = "Select workId, workName, workDes, workProcess, description, status" +
                    ", userCreate, userHandle, mark, timeMark, timeFrom, timeTo, timeCreate" +
                    ", confirmFinish From Working Where workId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();

            while (rs.next()) {
                dto = new WorkingDTO(rs.getString("workId"), rs.getString("workName"), rs.getString("workDes")
                        , rs.getString("workProcess"),rs.getString("description"), rs.getString("status")
                        , rs.getString("userCreate"), rs.getString("userHandle"), rs.getInt("mark")
                        , rs.getTimestamp("timeMark"), rs.getTimestamp("timeFrom")
                ,rs.getTimestamp("timeTo"), rs.getTimestamp("timeCreate"),  rs.getBoolean("confirmFinish"));
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

    public List<String> getAllStatus() {
        List<String> result = new ArrayList<>();
        String st;
        try {
            String sql = "Select distinct status From Working";
            conn= MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            rs=preStm.executeQuery();

            while (rs.next()) {
                st = rs.getString("status");
                result.add(st);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }

    public List<WorkingDTO> searchStatus(String st, String username) {
        List<WorkingDTO> result = new ArrayList<>();
        WorkingDTO dto=null;
        try {
            String sql = "Select workId, workName, workDes, workProcess, description, status" +
                    ", userCreate, userHandle, mark, timeMark, timeFrom, timeTo, timeCreate" +
                    ", confirmFinish From Working where status = ? and userHandle = ?";
            conn= MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, st);
            preStm.setString(2, username);
            rs=preStm.executeQuery();

            while (rs.next()) {
                dto = new WorkingDTO(rs.getString("workId"), rs.getString("workName"), rs.getString("workDes")
                        , rs.getString("workProcess"),rs.getString("description"), rs.getString("status")
                        , rs.getString("userCreate"), rs.getString("userHandle"), rs.getInt("mark")
                        , rs.getTimestamp("timeMark"), rs.getTimestamp("timeFrom")
                        ,rs.getTimestamp("timeTo"), rs.getTimestamp("timeCreate"),  rs.getBoolean("confirmFinish"));
                result.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }

    public List<WorkingDTO> searchTime(String tFrom, String tT, String username) {
        List<WorkingDTO> result = new ArrayList<>();
        WorkingDTO dto=null;
        try {
            String sql = "Select workId, workName, workDes, workProcess, description, status" +
                    ", userCreate, userHandle, mark, timeMark, timeFrom, timeTo, timeCreate" +
                    ", confirmFinish From Working where timeFrom  and userHandle = ?";
            conn= MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, tFrom);
            //chua xong
            preStm.setString(2, username);
            rs=preStm.executeQuery();

            while (rs.next()) {
                dto = new WorkingDTO(rs.getString("workId"), rs.getString("workName"), rs.getString("workDes")
                        , rs.getString("workProcess"),rs.getString("description"), rs.getString("status")
                        , rs.getString("userCreate"), rs.getString("userHandle"), rs.getInt("mark")
                        , rs.getTimestamp("timeMark"), rs.getTimestamp("timeFrom")
                        ,rs.getTimestamp("timeTo"), rs.getTimestamp("timeCreate"),  rs.getBoolean("confirmFinish"));
                result.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }


    //Create Work
    public boolean createNewWork(WorkingDTO dto){
        boolean check = false;
        try {
            String sql = "Insert into Working(workId, workName, workDes, workProcess, status" +
                    ", userCreate, userHandle, timeFrom, timeTo, timeCreate) Values(?,?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getWorkId());
            preStm.setString(2, dto.getWorkName());
            preStm.setString(3, dto.getWorkDes());
            preStm.setString(4, dto.getWorkProcess());
            preStm.setString(5, dto.getStatus());
            preStm.setString(6, dto.getUserCreate());
            preStm.setString(7, dto.getUserHandle());
            preStm.setTimestamp(8, dto.getTimeFrom());
            preStm.setTimestamp(9, dto.getTimeTo());
            preStm.setTimestamp(10, dto.getTimeCreate());
            check = preStm.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return check;
    }
}
