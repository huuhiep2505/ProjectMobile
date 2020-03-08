package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.alladapter.WorkAdapter;
import hiepnh.se1304_nguyenhuuhiep.daos.WorkingDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class UserActivity extends AppCompatActivity {

    private ListView listWorking;
    private WorkAdapter adapter;
    private WorkingDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        listWorking = findViewById(R.id.listWorking);
        dao = new WorkingDAO();
        Intent intent = this.getIntent();
        String username = intent.getStringExtra("Username");
        List<WorkingDTO> result = dao.getListWorking(username);
        adapter = new WorkAdapter();
        adapter.setWorkingDTOList(result);
        listWorking.setAdapter(adapter);
        listWorking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WorkingDTO dto = (WorkingDTO) listWorking.getItemAtPosition(i);
                WorkingDTO result = (dao.getWorking(dto.getWorkId()));
                Intent intentShow = new Intent(UserActivity.this, WorkingDetailActivity.class);
                intentShow.putExtra("DTO", result);
                startActivity(intentShow);
            }
        });
    }

    public void clickToLogout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickToSearch(View view) {
//        Intent intent = new Intent(this, SearchActivity.class);
//        startActivity(intent);
    }
}
