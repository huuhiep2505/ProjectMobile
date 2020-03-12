package hiepnh.se1304_nguyenhuuhiep.dtos;

import java.io.Serializable;

public class GroupDTO implements Serializable {
    String groupId,groupName,username;

    public GroupDTO() {
    }

    public GroupDTO(String groupId) {
        this.groupId = groupId;
    }

    public GroupDTO(String groupId, String groupName, String username) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.username = username;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getUsername() {
        return username;
    }
}
