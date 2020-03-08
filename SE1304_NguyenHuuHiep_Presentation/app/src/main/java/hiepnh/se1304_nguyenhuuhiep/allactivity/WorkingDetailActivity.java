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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_detail);
        Intent intent = this.getIntent();
        dto = (WorkingDTO) intent.getSerializableExtra("DTO");
        TextView txtResult = findViewById(R.id.txtResult);
        txtResult.setText(dto.getWorkId() + "\n" + dto.getWorkName() + "\n" + dto.getStatus() + "\n" + dto.getUserHandle());
    }

    public void clickToUpdate(View view) {
        Intent intent = new Intent(this, UpdateStatusActivity.class);
        intent.putExtra("workId", dto.getWorkId());
        startActivity(intent);
        finish();
    }
}
