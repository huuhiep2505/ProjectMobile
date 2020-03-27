package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;

public class UserDetailScanActivity extends AppCompatActivity {
    private TextView txtUsername, txtFullname, txtPhone,txtAddress,txtEmail,txtBirthday,txtRole,txtGroupID;
    private UserDTO dto;
    private UserDAO dao;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_scan);
        dao = new UserDAO();
        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");
        dto = dao.getUserScan(username);
        txtUsername = findViewById(R.id.txtUsername);
        txtFullname = findViewById(R.id.txtFullname);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail = findViewById(R.id.txtEmail);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtRole = findViewById(R.id.txtRole);
        txtGroupID = findViewById(R.id.txtGroupId);
        txtUsername.setText(dto.getUsername());
        txtFullname.setText(dto.getFullname());
        txtPhone.setText(dto.getPhone());
        txtAddress.setText(dto.getAddress());
        txtEmail.setText(dto.getEmail());
        txtBirthday.setText(dto.getBirthday());
        txtRole.setText(dto.getRole());
        txtGroupID.setText(dto.getGroupId());
    }
}
