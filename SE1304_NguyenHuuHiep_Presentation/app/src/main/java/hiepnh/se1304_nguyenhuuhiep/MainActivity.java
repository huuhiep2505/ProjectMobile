package hiepnh.se1304_nguyenhuuhiep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        Toast.makeText(this, "Role: " + role, Toast.LENGTH_LONG).show();
    }
}
