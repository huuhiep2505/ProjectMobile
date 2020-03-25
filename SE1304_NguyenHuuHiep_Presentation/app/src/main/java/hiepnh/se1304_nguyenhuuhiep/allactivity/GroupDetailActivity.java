package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.alladapter.GroupAdapter;
import hiepnh.se1304_nguyenhuuhiep.alladapter.UserAdapter;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class GroupDetailActivity extends AppCompatActivity {
    private ListView listUser;
    private UserAdapter adapter;
    private UserDAO dao;
    private String groupId;
    List<UserDTO> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        listUser = findViewById(R.id.listUser);
        dao = new UserDAO();
        final Intent intent = this.getIntent();
        groupId = intent.getStringExtra("GroupId");
        result = dao.getListUser(groupId);
        if (result.size() > 0){
            adapter = new UserAdapter(result);
            listUser.setAdapter(adapter);
        }else {
            Toast.makeText(this, "List User empty!", Toast.LENGTH_LONG).show();
        }
        listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserDTO dto = (UserDTO) listUser.getItemAtPosition(i);
                //   GroupDTO result = (dao.getWorking(dto.getWorkId()));
                Intent intentShow = new Intent(GroupDetailActivity.this, GroupUserDetailActivity.class);
                //    intentShow.putExtra("DTO", result);
                intentShow.putExtra("Username",dto.getUsername());
                intentShow.putExtra("DTO", dto);
                startActivity(intentShow);
            }
        });

    }

}
