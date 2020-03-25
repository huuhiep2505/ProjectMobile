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
import hiepnh.se1304_nguyenhuuhiep.alladapter.WorkAdapter;
import hiepnh.se1304_nguyenhuuhiep.daos.WorkingDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class GroupUserDetailActivity extends AppCompatActivity {
    private ListView listWorking;
    private WorkingDAO dao;
    private String username;
    private WorkAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_user_detail);
        listWorking = findViewById(R.id.listWorking);
        dao = new WorkingDAO();
        Intent intent = this.getIntent();
        username = intent.getStringExtra("Username");
        showData();
        listWorking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WorkingDTO dto = (WorkingDTO) listWorking.getItemAtPosition(i);
                WorkingDTO result = (dao.getWorking(dto.getWorkId()));
                Intent intentShow = new Intent(GroupUserDetailActivity.this, GroupWorkingDetailActivity.class);
                intentShow.putExtra("DTO", result);
                intentShow.putExtra("Username", username);
                startActivity(intentShow);
            }
        });
    }
    public void showData(){
        List<WorkingDTO> result = dao.getListWorking(username);
        if (result.size() > 0){
            adapter = new WorkAdapter();
            adapter.setWorkingDTOList(result);
            listWorking.setAdapter(adapter);
        }else {
            Toast.makeText(this, "List Working empty!", Toast.LENGTH_LONG).show();
        }

    }

    public void clickToRequest(View view) {
        Intent intent = new Intent(this, WorkingRequestActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}
