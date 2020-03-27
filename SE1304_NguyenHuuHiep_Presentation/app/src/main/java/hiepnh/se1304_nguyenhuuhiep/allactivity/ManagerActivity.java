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
import hiepnh.se1304_nguyenhuuhiep.daos.GroupDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.GroupDTO;

public class ManagerActivity extends AppCompatActivity {

    private ListView listGroup;
    private GroupAdapter adapter;
    private GroupDAO dao;
    private String username;
    private SwipeRefreshLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        listGroup = findViewById(R.id.listGroup);
        layout = findViewById(R.id.pullToRefresh);
        dao = new GroupDAO();
        Intent intent = this.getIntent();
        username = intent.getStringExtra("Username");
        showData();
        listGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GroupDTO dto = (GroupDTO) listGroup.getItemAtPosition(i);
             //   GroupDTO result = (dao.getWorking(dto.getWorkId()));
                Intent intentShow = new Intent(ManagerActivity.this, GroupDetailActivity.class);
            //    intentShow.putExtra("DTO", result);
                intentShow.putExtra("GroupId", dto.getGroupId());
                startActivity(intentShow);
            }
        });
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showData();
                layout.setRefreshing(false);
            }
        });
    }

    public void showData(){
        List<GroupDTO> result = dao.getListGroup(username);
        if (result.size() > 0){
            adapter = new GroupAdapter();
            adapter.setGroupDTOList(result);
            listGroup.setAdapter(adapter);
        }else {
            Toast.makeText(this, "List Group empty!", Toast.LENGTH_LONG).show();
        }

    }

    public void clickToLogout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickToSearch(View view) {
        Intent intent = new Intent(this, UserSearchActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void clickToCreateWork(View view) {
        Intent intent = new Intent(this, CreateWorkActivity.class);
        intent.putExtra("Username", username);
        startActivityForResult(intent, 123);
    }


    public void clickToShowWork(View view) {
        Intent intent = new Intent(this, ManagerShowWorkActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void clickToQRCode(View view) {
        Intent intent = new Intent(this, SearchQRCodeActivity.class);
        startActivity(intent);
    }
}
