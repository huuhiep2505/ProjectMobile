package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;

public class UpdateInformationAccountActivity extends AppCompatActivity {
    private EditText edtFullname,edtPhone,edtAddress,edtEmail,edtBirthday,edtRole,edtGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information_account);
        edtFullname = findViewById(R.id.edtFullname);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        edtBirthday = findViewById(R.id.edtBirthday);
        edtRole = findViewById(R.id.edtRole);
        edtGroup = findViewById(R.id.edtGroupId);
    }

    public void clickToUpdateInformation(View view) {
        Intent intent = this.getIntent();
        String username = intent.getStringExtra("username");
        UserDAO dao = new UserDAO();
        String fullname = edtFullname.getText().toString();
        String phone = edtPhone.getText().toString();
        String address = edtAddress.getText().toString();
        String email = edtEmail.getText().toString();
        String birthday = edtBirthday.getText().toString();
        String role = edtRole.getText().toString();
        String group = edtGroup.getText().toString();
        boolean check = dao.updateUser(username,fullname,phone,address,email,birthday,role,group);
        if(check){
            Toast.makeText(this, "Update Success", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(this,AccountManagementActivity.class);
            startActivity(intent1);
            finish();
        }else{
            Toast.makeText(this, "Update failed", Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
