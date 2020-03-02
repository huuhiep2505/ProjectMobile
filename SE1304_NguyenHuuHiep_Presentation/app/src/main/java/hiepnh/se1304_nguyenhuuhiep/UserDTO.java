package hiepnh.se1304_nguyenhuuhiep;

import java.io.Serializable;

public class UserDTO implements Serializable {
    String username,name,phone,address,email,code,birthday,manager;

    public UserDTO() {
    }

    public UserDTO(String username, String name, String phone, String address, String email, String code, String birthday, String manager) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.code = code;
        this.birthday = birthday;
        this.manager = manager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
