package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class WorkingDetailActivity extends AppCompatActivity {

    private WorkingDTO dto;
    private TextView txtWorkId,txtWorkName,txtWorkDes,txtWorkProcess,txtDescription,txtStatus,txtUserCreate,txtUserHandle
            ,txtTimeMark,txtTimeFrom,txtTimeTo,txtTimeCreate, txtConfirmFinish, txtMark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_detail);
        Intent intent = this.getIntent();
        dto = (WorkingDTO) intent.getSerializableExtra("DTO");
        getViewId();
        setTextView();
    }

    public void clickToUpdate(View view) {
        Intent intent = new Intent(this, UpdateStatusActivity.class);
        intent.putExtra("workId", dto.getWorkId());
        startActivity(intent);
        finish();
    }

    public void getViewId(){
        txtWorkId = findViewById(R.id.txtWorkID);
        txtWorkName = findViewById(R.id.txtWorkName);
        txtWorkDes = findViewById(R.id.txtWorkDes);
        txtDescription = findViewById(R.id.txtDescription);
        txtStatus = findViewById(R.id.txtStatus);
        txtUserCreate = findViewById(R.id.txtUserCreate);
        txtUserHandle = findViewById(R.id.txtUserHandle);
        txtWorkProcess = findViewById(R.id.txtWorkProcess);
        txtConfirmFinish = findViewById(R.id.txtConfirmFinish);
        txtTimeCreate = findViewById(R.id.txtTimeCreate);
        txtTimeFrom = findViewById(R.id.txtTimeFrom);
        txtTimeTo = findViewById(R.id.txtTimeTo);
        txtTimeMark = findViewById(R.id.txtTimeMark);
        txtMark = findViewById(R.id.txtMark);
    }

    public void setTextView(){
        txtWorkId.setText(dto.getWorkId());
        txtWorkName.setText(dto.getWorkName());
        txtWorkDes.setText(dto.getWorkDes());
        txtDescription.setText(dto.getDescription());
        txtStatus.setText(dto.getStatus());
        txtUserCreate.setText(dto.getUserCreate());
        txtUserHandle.setText(dto.getUserHandle());
        txtWorkProcess.setText(dto.getWorkProcess());
        txtConfirmFinish.setText(dto.isConfirmFinish()+ "");
        txtTimeCreate.setText(dto.getViewTimeCreate());
        txtTimeFrom.setText(dto.getViewTimeFrom());
        txtTimeTo.setText(dto.getViewTimeTo());
        txtTimeMark.setText(dto.getViewTimeMark());
        txtMark.setText(dto.getMark() + "");
    }
}
