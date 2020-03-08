package hiepnh.se1304_nguyenhuuhiep.dtos;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {
    String username,password,fullname,phone,address,email,role,groupId;
    Date birthday;
    boolean isblock;

    public UserDTO() {
    }

    public UserDTO(String username, String fullname, String phone, String address, String email, String role, String groupId, Date birthday) {
        this.username = username;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.role = role;
        this.groupId = groupId;
        this.birthday = birthday;
    }

    public UserDTO(String username, String password, String fullname, String phone, String address, String email, String role, String groupId, Date birthday, boolean isblock) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.role = role;
        this.groupId = groupId;
        this.birthday = birthday;
        this.isblock = isblock;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isIsblock() {
        return isblock;
    }

    public void setIsblock(boolean isblock) {
        this.isblock = isblock;
    }
}
