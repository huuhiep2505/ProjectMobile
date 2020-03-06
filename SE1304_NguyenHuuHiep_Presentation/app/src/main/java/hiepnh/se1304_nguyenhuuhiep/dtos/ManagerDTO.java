package hiepnh.se1304_nguyenhuuhiep.dtos;

import java.io.Serializable;
import java.util.Date;

public class ManagerDTO implements Serializable {
    String id, name, phone, address, email, username;
    Date date;

    public ManagerDTO() {
    }

    public ManagerDTO(String name, String phone, String address, String email, String username, Date date) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.username = username;
        this.date = date;
    }

    public ManagerDTO(String id, String name, String phone, String address, String email, String username, Date date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.username = username;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}