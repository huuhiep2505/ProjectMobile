package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.alladapter.UserAdapter;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;

public class ChooseUserActivity extends AppCompatActivity {

    private ListView listUser;
    private UserDAO dao;
    private EditText edtFullname;
    private ArrayList<String> listChoice;
    List<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        listUser = findViewById(R.id.listView);
        listUser.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        edtFullname = findViewById(R.id.edtFullname);
        dao = new UserDAO();
        result = dao.getUser();
        listUser.setTextFilterEnabled(true);
        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_checked,result);
        listUser.setAdapter(adapter);

        listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listChoice = new ArrayList<>();
                for (int j = 0; j < adapterView.getCount() ;  j++) {
                    boolean check = listUser.getCheckedItemPositions().get(i);
                    if (check) {
                        String name = adapterView.getAdapter().getItem(j).toString();
                        listChoice.add(name);
                    }
                }
            }
        });

    }

    public void clickToAdd(View view) {
        Intent intent = this.getIntent();
        String groupId = intent.getStringExtra("groupId");

        boolean check = dao.updateUserToGroup(listChoice,groupId);
        if(check){
            Toast.makeText(this, "Add Success", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(this,AdminActivity.class);
            startActivity(intent1);
            finish();
        }else{
            Toast.makeText(this, "Add failed", Toast.LENGTH_LONG).show();
            finish();
        }


    }
}
