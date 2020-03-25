package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.alladapter.UserAdapter;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;

public class AccountManagementActivity extends AppCompatActivity {
    private ListView listAccount;
    private UserAdapter adapter;
    private UserDAO dao;
    private EditText edtFullname;
    String username;
    List<UserDTO> result;
    List<UserDTO> temp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        listAccount = findViewById(R.id.listAccount);
        edtFullname = findViewById(R.id.edtFullname);
        dao = new UserDAO();
        result = dao.getAccountManagement();

//        adapter = new UserAdapter();
//        adapter.setUserDTOList(result);
        adapter = new UserAdapter(result);
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
    }

    public void clickToSearchName(View view) {
        temp = new ArrayList<>();
        String name = edtFullname.getText().toString();

        for (UserDTO dto : result) {
            if(dto.getFullname().toUpperCase().contains(name.toUpperCase())){
                temp.add(dto);
            }
        }
       adapter = new UserAdapter(temp);
        listAccount.setAdapter(adapter);
    }

    public void clickToRequestTask(View view) {
        Intent intent = new Intent(this, AdminRequestTaskActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);

    }
}
