package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;

public class CreateNewAccountActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword,edtFullname,edtPhone,edtAddress,edtEmail,edtBirthday,edtRole,edtGroupId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtFullname = findViewById(R.id.edtFullname);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        edtBirthday = findViewById(R.id.edtBirthday);
        edtRole = findViewById(R.id.edtRole);
        edtGroupId = findViewById(R.id.edtGroupId);
    }
    public void clickToCreate(View view) {
        UserDAO userDAO = new UserDAO();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String fullname = edtFullname.getText().toString();
        String phone = edtPhone.getText().toString();
        String address = edtAddress.getText().toString();
        String email = edtEmail.getText().toString();
        String birthday = edtBirthday.getText().toString();
        String role = edtRole.getText().toString();
        String group = edtGroupId.getText().toString();
        boolean check = userDAO.createUser(username,password,fullname,phone,address,email,birthday,role,group);
        if(check){
            Toast.makeText(this,"Create success", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this , AccountManagementActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,"Create failed", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
