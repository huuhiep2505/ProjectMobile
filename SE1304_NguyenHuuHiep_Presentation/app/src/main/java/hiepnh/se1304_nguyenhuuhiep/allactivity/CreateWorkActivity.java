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
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.WorkingDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;
import hiepnh.se1304_nguyenhuuhiep.utils.CheckData;

public class CreateWorkActivity extends AppCompatActivity {

    private EditText edtTimeFrom, edtTimeTo, edtWorkId, edtWorkName, edtWorkDes, edtWorkProcess;
    private String username;
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
                    edtTimeFrom.setText(i2 + "/" + month + "/" + i);
                } else {
                    edtTimeTo.setText(i2 + "/" + month + "/" + i);
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void clickToInsert(View view) {
        WorkingDAO dao = new WorkingDAO();
        List<String> listID = dao.getWorkID();
        String id = edtWorkId.getText().toString().trim();
        String name = edtWorkName.getText().toString().trim();
        String wDes = edtWorkDes.getText().toString().trim();
        String wPro = edtWorkProcess.getText().toString().trim();
        String timeFrom = edtTimeFrom.getText().toString().trim();
        String timeTo = edtTimeTo.getText().toString().trim();

        if (!id.equals("") && !name.equals("") && !wDes.equals("") && !wPro.equals("") && !timeFrom.equals("") && !timeTo.equals("")){
            boolean checkID = CheckData.checkWorkID(id, listID);
            if (!checkID){
                WorkingDTO dto = new WorkingDTO(id, name, wDes, wPro, "Request", username, username
                        , CheckData.getTimestamp(timeFrom)
                        , CheckData.getTimestamp(timeTo), new Timestamp(System.currentTimeMillis()));
                boolean check = dao.createNewWork(dto);
                if (check){
                    Toast.makeText(this, "Create Working success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, UserActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(this, "Create Working fail", Toast.LENGTH_LONG).show();
                    finish();
                }
            }else {
                Toast.makeText(this, "Duplicate ID", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "All fields must not empty", Toast.LENGTH_LONG).show();
        }

    }
}
