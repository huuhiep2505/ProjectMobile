package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class GroupWorkingDetailActivity extends AppCompatActivity {
    private String username;
    private WorkingDTO dto;
    private TextView txtWorkId,txtWorkName,txtWorkDes,txtWorkProcess,txtDescription,txtStatus,txtUserCreate,txtUserHandle
            ,txtTimeMark,txtTimeFrom,txtTimeTo,txtTimeCreate, txtConfirmFinish, txtMark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_working_detail);
        Intent intent = this.getIntent();
        dto = (WorkingDTO) intent.getSerializableExtra("DTO");
        username = (String) intent.getSerializableExtra("Username");
        getViewId();
        setTextView();
    }
    public void clickToUpdate(View view) {
        Intent intent = new Intent(this, GroupUpdateStatusActivity.class);
        intent.putExtra("workId", dto.getWorkId());
        intent.putExtra("Username", username);
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

    public void clickToUpdateInfo(View view) {
        Intent intent = new Intent(this, UpdateWorkingActivity.class);
        intent.putExtra("workId", dto.getWorkId());
        intent.putExtra("Username", username);
        startActivity(intent);
        finish();
    }
}
