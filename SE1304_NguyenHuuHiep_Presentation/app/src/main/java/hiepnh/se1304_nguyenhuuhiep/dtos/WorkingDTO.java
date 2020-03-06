package hiepnh.se1304_nguyenhuuhiep.dtos;

import java.io.Serializable;

public class WorkingDTO implements Serializable {
    String id, name, des;

    public WorkingDTO(String id, String name, String des) {
        this.id = id;
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
