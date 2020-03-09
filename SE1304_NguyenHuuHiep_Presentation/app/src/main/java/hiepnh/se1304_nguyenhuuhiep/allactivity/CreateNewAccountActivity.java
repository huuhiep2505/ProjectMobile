package hiepnh.se1304_nguyenhuuhiep.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;

public class CreateNewAccountActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword,edtFullname,edtPhone,edtAddress,edtEmail,edtBirthday,edtGroupId;
    Spinner edtRole;
    private String stringSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtFullname = findViewById(R.id.edtFullname);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        edtBirthday = findViewById(R.id.edtBirthday);
        edtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate();
            }
        });
        edtRole = findViewById(R.id.edtRole);
        List<String> list = new ArrayList<>();
        list.add("user");
        list.add("admin");
        list.add("manager");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtRole.setAdapter(adapter);
        edtRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stringSelected = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtGroupId = findViewById(R.id.edtGroupId);
    }

    private void chooseDate(){
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtBirthday.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year , month, day);
        datePickerDialog.show();
    }

    public void clickToCreate(View view) {
        UserDAO userDAO = new UserDAO();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String fullname = edtFullname.getText().toString();
        String phone = edtPhone.getText().toString();
        String address = edtAddress.getText().toString();
        String email = edtEmail.getText().toString();
        String birthday = edtBirthday.getText().toString();
      //  String role = edtRole.getTransitionName().toString();
        String group = edtGroupId.getText().toString();
        boolean check = userDAO.createUser(username,password,fullname,phone,address,email,birthday,stringSelected,group);
        if(check){
            Toast.makeText(this,"Create success", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this , AccountManagementActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,"Create failed", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
