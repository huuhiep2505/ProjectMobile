package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.alladapter.WorkAdapter;
import hiepnh.se1304_nguyenhuuhiep.daos.WorkingDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class UserSearchActivity extends AppCompatActivity {

    private Spinner spStatus;
    private String stringSelected;
    private WorkingDAO dao;
    private ListView listSearch;
    private WorkAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        spStatus = findViewById(R.id.spStatus);
        dao = new WorkingDAO();
        List<String> list = dao.getAllStatus();
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

    public void searchStatus(View view) {
        Intent intent = this.getIntent();
        String username = intent.getStringExtra("Username");
        List<WorkingDTO> listResult = dao.searchStatus(stringSelected, username);
        if (listResult.size() > 0){
            listSearch = findViewById(R.id.listWorkSearch);
            adapter = new WorkAdapter();
            adapter.setWorkingDTOList(listResult);
            listSearch.setAdapter(adapter);
        }else {
            Toast.makeText(this, "Not found", Toast.LENGTH_LONG).show();
        }
    }
}
