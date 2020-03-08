package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public void clickToLogin(View view) {
        UserDAO userDAO = new UserDAO();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String role = userDAO.checkLogin(username, password);
        if (role.equals("user")){
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
            finish();
        }else if(role.equals("admin")){
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
