package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.annotation.Nullable;
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

public class UserActivity extends AppCompatActivity {

    private ListView listWorking;
    private WorkAdapter adapter;
    private WorkingDAO dao;
    private String username;
    private SwipeRefreshLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        listWorking = findViewById(R.id.listWorking);
        layout = findViewById(R.id.pullToRefresh);
        dao = new WorkingDAO();
        Intent intent = this.getIntent();
        username = intent.getStringExtra("Username");
        showData();
        listWorking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WorkingDTO dto = (WorkingDTO) listWorking.getItemAtPosition(i);
                WorkingDTO result = (dao.getWorking(dto.getWorkId()));
                Intent intentShow = new Intent(UserActivity.this, WorkingDetailActivity.class);
                intentShow.putExtra("DTO", result);
                intentShow.putExtra("Username", username);
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
        List<WorkingDTO> result = dao.getListWorking(username);
        if (result.size() > 0){
            adapter = new WorkAdapter();
            adapter.setWorkingDTOList(result);
            listWorking.setAdapter(adapter);
        }else {
            Toast.makeText(this, "List Working empty!", Toast.LENGTH_LONG).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123){
            if (resultCode == RESULT_OK){
                showData();
            }
        }
    }
}
