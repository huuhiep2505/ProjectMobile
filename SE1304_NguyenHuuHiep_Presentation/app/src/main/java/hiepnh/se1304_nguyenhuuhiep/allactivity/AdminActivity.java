package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hiepnh.se1304_nguyenhuuhiep.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void clickToAccountManagement(View view) {
        Intent intent = new Intent(this,AccountManagementActivity.class);
        startActivity(intent);
    }

    public void clickToGroupManagement(View view) {
        Intent intent = new Intent(this, CreateGroupActivity.class);
        startActivity(intent);
    }

    public void clickToLogout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickToQRCode(View view) {
        Intent intent = new Intent(this, SearchQRCodeActivity.class);
        startActivity(intent);
    }
}
