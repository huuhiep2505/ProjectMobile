package hiepnh.se1304_nguyenhuuhiep.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
            String sql = "Select workId, workName From Working where userHandle = ? and status = 'Start' or status = 'Process'";
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

//    public List<String> getAllStatus() {
//        List<String> result = new ArrayList<>();
//        String st;
//        try {
//            String sql = "Select distinct status From Working";
//            conn= MyConnection.getMyConnection();
//            preStm=conn.prepareStatement(sql);
//            rs=preStm.executeQuery();
//
//            while (rs.next()) {
//                st = rs.getString("status");
//                result.add(st);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally{
//            closeConnection();
//        }
//        return result;
//    }

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

    public List<WorkingDTO> searchTime(Timestamp tFrom, Timestamp tTo, String username) {
        List<WorkingDTO> result = new ArrayList<>();
        WorkingDTO dto=null;
        try {
            String sql = "Select workId, workName, workDes, workProcess, description, status" +
                    ", userCreate, userHandle, mark, timeMark, timeFrom, timeTo, timeCreate" +
                    ", confirmFinish From Working where timeFrom >= ? and timeTo <= ? and userHandle = ?";
            conn= MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setTimestamp(1, tFrom);
            preStm.setTimestamp(2, tTo);
            preStm.setString(3, username);
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
                    ", userCreate, userHandle, timeFrom, timeTo, timeCreate, confirmFinish) Values(?,?,?,?,?,?,?,?,?,?,?)";
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
            preStm.setBoolean(11, false);
            check = preStm.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return check;
    }

    public boolean setInfoUpdate(Timestamp timeUpdate, String userUpdate, String workId){
        boolean check = false;
        try {
            String sql = "Update Working set timeUpdate = ?, userUpdate = ? where workId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setTimestamp(1, timeUpdate);
            preStm.setString(2, userUpdate);
            preStm.setString(3, workId);
            check = preStm.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return check;
    }

    public WorkingDTO getWorkingToUpdate(String id) {

        WorkingDTO dto = null;
        try {
            String sql = "Select workId, workName, workDes, workProcess, timeFrom, timeTo From Working Where workId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();

            while (rs.next()) {
                dto = new WorkingDTO(rs.getString("workId"), rs.getString("workName"), rs.getString("workDes")
                        , rs.getString("workProcess"), rs.getTimestamp("timeFrom"), rs.getTimestamp("timeTo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean updateWorking(WorkingDTO dto){
        boolean check = false;
        try {
            String sql = "Update Working set workName = ?, workDes = ?, workProcess = ?, timeFrom = ?, timeTo = ? Where workId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getWorkName());
            preStm.setString(2, dto.getWorkDes());
            preStm.setString(3, dto.getWorkProcess());
            preStm.setTimestamp(4, dto.getTimeFrom());
            preStm.setTimestamp(5, dto.getTimeTo());
            preStm.setString(6, dto.getWorkId());
            check = preStm.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return check;
    }

    //CheckID
    public List<String> getWorkID(){
        List<String> listID = new ArrayList<>();
        try {
            String sql = "Select workId from Working";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()){
                String id = rs.getString("workId");
                listID.add(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return listID;
    }
}
