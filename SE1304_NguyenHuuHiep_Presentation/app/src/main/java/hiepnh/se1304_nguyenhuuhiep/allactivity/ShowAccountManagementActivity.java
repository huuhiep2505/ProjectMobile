package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;

public class ShowAccountManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_account_management);
        Intent intent = this.getIntent();
        UserDTO dto = (UserDTO) intent.getSerializableExtra("DTO");
        TextView txtUsername = findViewById(R.id.txtUsername);
        txtUsername.setText(dto.getUsername());
        TextView txtRole = findViewById(R.id.txtRole);
        txtRole.setText(dto.getRole());
        TextView txtFullname = findViewById(R.id.txtFullname);
        txtFullname.setText(dto.getFullname());
        TextView txtPhone = findViewById(R.id.txtPhone);
        txtPhone.setText(dto.getPhone());
        TextView txtAddress = findViewById(R.id.txtAddress);
        txtAddress.setText(dto.getAddress());
        TextView txtEmail = findViewById(R.id.txtEmail);
        txtEmail.setText(dto.getEmail());
        TextView txtBirthday = findViewById(R.id.txtBirthday);
        txtBirthday.setText( dto.getBirthday());
        TextView txtGroupId = findViewById(R.id.txtGroupId);
        txtGroupId.setText(dto.getGroupId());

    }
}
