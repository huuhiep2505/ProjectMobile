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

public class CreateWorkActivity extends AppCompatActivity {

    private EditText edtTimeFrom, edtTimeTo, edtWorkId, edtWorkName, edtWorkDes, edtWorkProcess;
    private String username, timeFrom, timeTo;
    private boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_work);
        edtWorkId = findViewById(R.id.edtWorkID);
        edtWorkName = findViewById(R.id.edtWorkName);
        edtWorkDes = findViewById(R.id.edtWorkDes);
        edtWorkProcess = findViewById(R.id.edtWorkProcess);
        edtTimeFrom = findViewById(R.id.edtTimeFrom);
        edtTimeTo = findViewById(R.id.edtTimeTo);
        Intent intent = this.getIntent();
        username = intent.getStringExtra("Username");
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

    public void clickToInsert(View view) {
        String id = edtWorkId.getText().toString();
        String name = edtWorkName.getText().toString();
        String wDes = edtWorkDes.getText().toString();
        String wPro = edtWorkProcess.getText().toString();
        WorkingDTO dto = new WorkingDTO(id, name, wDes, wPro, "Start", username, username, CheckData.getTimestamp(timeFrom)
                , CheckData.getTimestamp(timeTo), new Timestamp(System.currentTimeMillis()));
        WorkingDAO dao = new WorkingDAO();
        boolean check = dao.createNewWork(dto);
        if (check){
            Toast.makeText(this, "Create Working success", Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(this, "Create Working fail", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
