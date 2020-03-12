package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Calendar;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.WorkingDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;
import hiepnh.se1304_nguyenhuuhiep.utils.CheckData;

public class UpdateWorkingActivity extends AppCompatActivity {

    private EditText edtTimeFrom, edtTimeTo, edtWorkId, edtWorkName, edtWorkDes, edtWorkProcess;
    private String username, timeFrom, timeTo;
    private boolean flag = false;
    private WorkingDAO dao;
    private WorkingDTO dto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_working);
        getViewId();
        Intent intent = this.getIntent();
        username = intent.getStringExtra("Username");
        String id = intent.getStringExtra("workId");
        dao = new WorkingDAO();
        dto = dao.getWorkingToUpdate(id);
        setTextView();
        edtTimeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                chooseTime();
            }
        });
        edtTimeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                chooseTime();
            }
        });
    }

    private void getViewId(){
        edtWorkId = findViewById(R.id.edtWorkID);
        edtWorkName = findViewById(R.id.edtWorkName);
        edtWorkDes = findViewById(R.id.edtWorkDes);
        edtWorkProcess = findViewById(R.id.edtWorkProcess);
        edtTimeFrom = findViewById(R.id.edtTimeFrom);
        edtTimeTo = findViewById(R.id.edtTimeTo);
    }
    private void setTextView() {
        edtWorkId.setText(dto.getWorkId());
        edtWorkName.setText(dto.getWorkName());
        edtWorkDes.setText(dto.getWorkDes());
        edtWorkProcess.setText(dto.getWorkProcess());
        edtTimeFrom.setText(dto.getViewTimeFrom());
        edtTimeTo.setText(dto.getViewTimeTo());
    }

    public void chooseTime(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int month = i1 + 1;
                if (flag){
                    timeFrom = i2 + "/" + month + "/" + i;
                    edtTimeFrom.setText(timeFrom);
                } else {
                    timeTo = i2 + "/" + month + "/" + i;
                    edtTimeTo.setText(timeTo);
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void clickToUpdate(View view) {
        String id = edtWorkId.getText().toString();
        String name = edtWorkName.getText().toString();
        String wDes = edtWorkDes.getText().toString();
        String wPro = edtWorkProcess.getText().toString();
        WorkingDTO dto = new WorkingDTO(id, name, wDes, wPro
                , CheckData.getTimestamp(edtTimeFrom.getText().toString())
                , CheckData.getTimestamp(edtTimeTo.getText().toString()));
        boolean check = dao.updateWorking(dto);
        Timestamp timeUpdate = new Timestamp(System.currentTimeMillis());
        boolean checkUpdate = dao.setInfoUpdate(timeUpdate,username,id);
        if (check && checkUpdate){
            Toast.makeText(this, "Update Working success", Toast.LENGTH_LONG).show();
            WorkingDTO result = (dao.getWorking(id));
            Intent intentShow = new Intent(this, WorkingDetailActivity.class);
            intentShow.putExtra("DTO", result);
            startActivity(intentShow);
            finish();
        }else{
            Toast.makeText(this, "Update Working fail", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
