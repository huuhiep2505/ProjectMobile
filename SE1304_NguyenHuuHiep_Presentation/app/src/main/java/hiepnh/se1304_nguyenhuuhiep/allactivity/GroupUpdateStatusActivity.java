package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.WorkingDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class GroupUpdateStatusActivity extends AppCompatActivity {
    private Spinner spStatus;
    private String stringSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_update_status);
        spStatus = findViewById(R.id.spStatus);
        List<String> list = new ArrayList<>();
        list.add("Start");
        list.add("Done");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(adapter);
        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stringSelected = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void updateStatus(View view) {
        Intent intent = this.getIntent();
        String id = intent.getStringExtra("workId");
        String username = intent.getStringExtra("Username");
        WorkingDAO dao = new WorkingDAO();
        boolean check = dao.updateStatus(stringSelected, id);
        Timestamp timeUpdate = new Timestamp(System.currentTimeMillis());
        boolean checkUpdate = dao.setInfoUpdate(timeUpdate,username,id);
        if (check && checkUpdate){
            Toast.makeText(this, "Update Success", Toast.LENGTH_LONG).show();
            WorkingDTO result = (dao.getWorking(id));
            Intent intentShow = new Intent(this, GroupWorkingDetailActivity.class);
            intentShow.putExtra("DTO", result);
            startActivity(intentShow);
            finish();
        }else {
            Toast.makeText(this, "Update Fail", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
