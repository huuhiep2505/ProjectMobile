package hiepnh.se1304_nguyenhuuhiep.dtos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import hiepnh.se1304_nguyenhuuhiep.utils.CheckData;

public class WorkingDTO implements Serializable {
    private String workId,workName,workDes,workProcess,description,status,userCreate,userHandle;
    private String viewTimeMark, viewTimeFrom, viewTimeTo, viewTimeCreate;
    private int mark;
    private Timestamp timeMark,timeFrom,timeTo,timeCreate;
    private boolean confirmFinish;

    public WorkingDTO() {
    }

    public WorkingDTO(String workId, String workName) {
        this.workId = workId;
        this.workName = workName;
    }

    public WorkingDTO(String workId, String workName, String status, String userHandle) {
        this.workId = workId;
        this.workName = workName;
        this.status = status;
        this.userHandle = userHandle;
    }

    public WorkingDTO(String workId, String workName, String workDes) {
        this.workId = workId;
        this.workName = workName;
        this.workDes = workDes;
    }

    public WorkingDTO(String workId, String workName, String workDes, String workProcess, String description, String status, String userCreate, String userHandle, int mark, Timestamp timeMark, Timestamp timeFrom, Timestamp timeTo, Timestamp timeCreate, boolean confirmFinish) {
        this.workId = workId;
        this.workName = workName;
        this.workDes = workDes;
        this.workProcess = workProcess;
        this.description = description;
        this.status = status;
        this.userCreate = userCreate;
        this.userHandle = userHandle;
        this.mark = mark;
        this.timeMark = timeMark;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.timeCreate = timeCreate;
        this.confirmFinish = confirmFinish;
    }

    public WorkingDTO(String workId, String workName, String workDes, String workProcess, String status, String userCreate, String userHandle, Timestamp timeFrom, Timestamp timeTo, Timestamp timeCreate) {
        this.workId = workId;
        this.workName = workName;
        this.workDes = workDes;
        this.workProcess = workProcess;
        this.status = status;
        this.userCreate = userCreate;
        this.userHandle = userHandle;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.timeCreate = timeCreate;
    }

    public WorkingDTO(String workId, String workName, String workDes, String workProcess, Timestamp timeFrom, Timestamp timeTo) {
        this.workId = workId;
        this.workName = workName;
        this.workDes = workDes;
        this.workProcess = workProcess;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkDes() {
        return workDes;
    }

    public void setWorkDes(String workDes) {
        this.workDes = workDes;
    }

    public String getWorkProcess() {
        return workProcess;
    }

    public void setWorkProcess(String workProcess) {
        this.workProcess = workProcess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Timestamp getTimeMark() {
        return timeMark;
    }

    public void setTimeMark(Timestamp timeMark) {
        this.timeMark = timeMark;
    }

    public Timestamp getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Timestamp timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Timestamp getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Timestamp timeTo) {
        this.timeTo = timeTo;
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public boolean isConfirmFinish() {
        return confirmFinish;
    }

    public void setConfirmFinish(boolean confirmFinish) {
        this.confirmFinish = confirmFinish;
    }

    public String getViewTimeMark() {
        viewTimeMark = CheckData.viewTime(timeMark);
        return viewTimeMark;
    }

    public void setViewTimeMark(String viewTimeMark) {
        this.viewTimeMark = viewTimeMark;
    }

    public String getViewTimeFrom() {
        viewTimeFrom = CheckData.viewTime(timeFrom);
        return viewTimeFrom;
    }

    public void setViewTimeFrom(String viewTimeFrom) {
        this.viewTimeFrom = viewTimeFrom;
    }

    public String getViewTimeTo() {
        viewTimeTo = CheckData.viewTime(timeTo);
        return viewTimeTo;
    }

    public void setViewTimeTo(String viewTimeTo) {
        this.viewTimeTo = viewTimeTo;
    }

    public String getViewTimeCreate() {
        viewTimeCreate = CheckData.viewTime(timeCreate);
        return viewTimeCreate;
    }

    public void setViewTimeCreate(String viewTimeCreate) {
        this.viewTimeCreate = viewTimeCreate;
    }
}
