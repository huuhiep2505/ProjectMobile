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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.alladapter.WorkAdapter;
import hiepnh.se1304_nguyenhuuhiep.daos.WorkingDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;
import hiepnh.se1304_nguyenhuuhiep.utils.CheckData;

public class UserSearchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner spStatus;
    private String stringSelected;
    private WorkingDAO dao;
    private ListView listSearch;
    private WorkAdapter adapter;
    private EditText edtFrom, edtTo;
    private String timeFrom, timeTo;
    private boolean toFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        spStatus = findViewById(R.id.spStatus);
        listSearch = findViewById(R.id.listWorkSearch);
        edtFrom = findViewById(R.id.edtFrom);
        edtTo = findViewById(R.id.edtTo);
        dao = new WorkingDAO();
        List<String> list = new ArrayList<>();
        list.add("Start");
        list.add("Process");
        list.add("Finish");
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

    public void searchStatus(View view) {
        Intent intent = this.getIntent();
        String username = intent.getStringExtra("Username");
        List<WorkingDTO> listResult = dao.searchStatus(stringSelected, username);
        if (listResult.size() > 0){
            adapter = new WorkAdapter();
            adapter.setWorkingDTOList(listResult);
            listSearch.setAdapter(adapter);
        }else {
            listSearch.setAdapter(null);
            Toast.makeText(this, "Not found", Toast.LENGTH_LONG).show();
        }
    }


    //Search Time
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        int month = i1 + 1;
        String result = i2 + "/" + month + "/" + i;
        if (toFlag){
            edtTo.setText(result);
        } else {
            edtFrom.setText(result);
        }
    }

    public void searchTime(View view) {
        if (edtFrom.getText().toString() != null && edtTo.getText().toString() != null){
            Intent intent = this.getIntent();
            String username = intent.getStringExtra("Username");
            List<WorkingDTO> listResult = dao.searchTime(CheckData.getTimestamp(edtFrom.getText().toString()),
                    CheckData.getTimestamp(edtTo.getText().toString()), username);
            if (listResult.size() > 0){
                adapter = new WorkAdapter();
                adapter.setWorkingDTOList(listResult);
                listSearch.setAdapter(adapter);
            }else {
                listSearch.setAdapter(null);
                Toast.makeText(this, "Not found", Toast.LENGTH_LONG).show();
            }
        } else {
            listSearch.setAdapter(null);
            Toast.makeText(this, "Time is empty", Toast.LENGTH_LONG).show();
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
