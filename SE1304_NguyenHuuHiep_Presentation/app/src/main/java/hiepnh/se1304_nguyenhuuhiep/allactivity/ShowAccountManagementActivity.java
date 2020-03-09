package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;

public class ShowAccountManagementActivity extends AppCompatActivity {
    private UserDTO dto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_account_management);
        Intent intent = this.getIntent();
        dto = (UserDTO) intent.getSerializableExtra("DTO");
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

    public void clickToUpdateInformation(View view) {
        Intent intent = new Intent(this, UpdateInformationAccountActivity.class);
        intent.putExtra("username", dto.getUsername());
        startActivity(intent);
    }

    public void clickToDeleteAccount(View view) {
        UserDAO dao = new UserDAO();
        Intent intent = this.getIntent();
        dto = (UserDTO) intent.getSerializableExtra("DTO");
        TextView txtUsername = findViewById(R.id.txtUsername);
        txtUsername.setText(dto.getUsername());
        String username = txtUsername.getText().toString();
        boolean check = dao.delete(username);
        if(check){
            Toast.makeText(this, "Delete Success", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(this,AccountManagementActivity.class);
            startActivity(intent1);
            finish();
        }else{
            Toast.makeText(this, "Delete failed", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
