package hiepnh.se1304_nguyenhuuhiep.dtos;

import java.io.Serializable;
import java.util.Date;

public class AdminDTO implements Serializable {
    String id, name, phone, address, email, username;
    Date birthday;

    public AdminDTO() {
    }

    public AdminDTO(String name, String phone, String address, String email, String username, Date birthday) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.username = username;
        this.birthday = birthday;
    }

    public AdminDTO(String id, String name, String phone, String address, String email, String username, Date birthday) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.username = username;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date date) {
        this.birthday = date;
    }
}