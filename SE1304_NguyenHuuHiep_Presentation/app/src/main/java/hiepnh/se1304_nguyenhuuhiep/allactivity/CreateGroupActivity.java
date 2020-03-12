package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.GroupDAO;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;

public class CreateGroupActivity extends AppCompatActivity {
    private Spinner spUsername;
    private String stringSelected;
    private EditText edtGroupId, edtGroupName;
    private UserDAO userDAO;
    private List list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        edtGroupId = findViewById(R.id.edtGroupId);
        edtGroupName = findViewById(R.id.edtGroupName);
        spUsername = findViewById(R.id.spUsername);
        userDAO = new UserDAO();
        list = userDAO.getManager();

        ArrayAdapter<UserDTO> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUsername.setAdapter(adapter);
        spUsername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stringSelected = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void clickToCreateNewGroup(View view) {
        GroupDAO groupDAO = new GroupDAO();
        String groupId = edtGroupId.getText().toString();
        String groupName = edtGroupName.getText().toString();
        boolean check = groupDAO.createGroup(groupId,groupName,stringSelected);
        if(check){
            Toast.makeText(this,"Create success", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this , ChooseUserActivity.class);
            intent.putExtra("groupId", groupId);
            startActivity(intent);
        }else {
            Toast.makeText(this,"Create failed", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
