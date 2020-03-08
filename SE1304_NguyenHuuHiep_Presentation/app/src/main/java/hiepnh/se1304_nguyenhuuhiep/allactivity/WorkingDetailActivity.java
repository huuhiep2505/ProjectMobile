package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class WorkingDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_detail);
        Intent intent = this.getIntent();
        WorkingDTO dto = (WorkingDTO) intent.getSerializableExtra("DTO");
        TextView txtResult = findViewById(R.id.txtResult);
        txtResult.setText(dto.getWorkDes());
    }
}
