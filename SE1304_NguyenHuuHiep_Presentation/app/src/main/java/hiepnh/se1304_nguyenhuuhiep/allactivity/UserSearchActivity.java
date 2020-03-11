package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.alladapter.WorkAdapter;
import hiepnh.se1304_nguyenhuuhiep.daos.WorkingDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class UserSearchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner spStatus;
    private String stringSelected;
    private WorkingDAO dao;
    private ListView listSearch;
    private WorkAdapter adapter;
    private TextView txtFrom, txtTo;
    private String timeFrom, timeTo;
    private boolean toFlag;
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


    //Search Time
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        int month = i1 + 1;
        String result = i2 + "/" + month + "/" + i;
        if (toFlag){
            txtTo = findViewById(R.id.txtTo);
            txtTo.setText(result);
            timeTo = result;
        } else {
            txtFrom = findViewById(R.id.txtFrom);
            txtFrom.setText(result);
            timeFrom = result;
        }
    }

    public void searchTime(View view) {
        if (timeFrom != null && timeTo != null){
            Intent intent = this.getIntent();
            String username = intent.getStringExtra("Username");
            List<WorkingDTO> listResult = dao.searchTime(timeFrom, timeTo, username);
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

    public void getFrom(View view) {
        DialogFragment dateFragment = new DatePickFragment();
        dateFragment.show(getFragmentManager(), "Date From: ");
        toFlag = false;
    }

    public void getTo(View view) {
        DialogFragment dateFragment = new DatePickFragment();
        dateFragment.show(getFragmentManager(), "Date To: ");
        toFlag = true;
    }

}
