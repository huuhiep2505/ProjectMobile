package hiepnh.se1304_nguyenhuuhiep.dtos;

import java.io.Serializable;
import java.util.Date;

public class WorkingDTO implements Serializable {
    String workId,workName,workDes,workProcess,description,status,userCreate,userHandle;
    int mark;
    Date timeMark,timeFrom,timeTo,timeCreate;
    boolean confirmFinish;

    public WorkingDTO() {
    }

    public WorkingDTO(String workId, String workName) {
        this.workId = workId;
        this.workName = workName;
    }

    public WorkingDTO(String workId, String workName, String workDes) {
        this.workId = workId;
        this.workName = workName;
        this.workDes = workDes;
    }

    public WorkingDTO(String workId, String workName, String workDes, String workProcess, String description, String status, String userCreate, String userHandle, int mark, Date timeMark, Date timeFrom, Date timeTo, Date timeCreate, boolean confirmFinish) {
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

    public Date getTimeMark() {
        return timeMark;
    }

    public void setTimeMark(Date timeMark) {
        this.timeMark = timeMark;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public boolean isConfirmFinish() {
        return confirmFinish;
    }

    public void setConfirmFinish(boolean confirmFinish) {
        this.confirmFinish = confirmFinish;
    }
}
