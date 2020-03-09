package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.alladapter.UserAdapter;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;

public class AccountManagementActivity extends AppCompatActivity {
    private ListView listAccount;
    private UserAdapter adapter;
    private UserDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        listAccount = findViewById(R.id.listAccount);
        dao = new UserDAO();
        List<UserDTO> result = (List<UserDTO>) dao.getAccountManagement();
        adapter = new UserAdapter();
        adapter.setUserDTOList(result);
        listAccount.setAdapter(adapter);
        listAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                               @Override
                                               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                   UserDTO dto = (UserDTO) listAccount.getItemAtPosition(i);
                                                   Intent intent = new Intent(AccountManagementActivity.this, ShowAccountManagementActivity.class);
                                                   intent.putExtra("DTO", dto);
                                                   startActivity(intent);
                                               }
                                           }
        );
    }

    public void clickToCreateNewAccount(View view) {
        Intent intent = new Intent(this, CreateNewAccountActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickToSearchName(View view) {

    }
}
