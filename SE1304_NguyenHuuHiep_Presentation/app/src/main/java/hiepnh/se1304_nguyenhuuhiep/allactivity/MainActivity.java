package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.AccountDAO;

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
        AccountDAO accountDAO = new AccountDAO();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String role = accountDAO.checkLogin(username, password);
        if (role.equals("user")){
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
