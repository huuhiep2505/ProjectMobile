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
import hiepnh.se1304_nguyenhuuhiep.daos.GroupDAO;
import hiepnh.se1304_nguyenhuuhiep.daos.UserDAO;
import hiepnh.se1304_nguyenhuuhiep.dtos.GroupDTO;
import hiepnh.se1304_nguyenhuuhiep.dtos.UserDTO;

public class UpdateInformationAccountActivity extends AppCompatActivity {
    private EditText edtFullname,edtPhone,edtAddress,edtEmail,edtBirthday;
    private Spinner edtRole,spGroup;
    private UserDTO dto;
    private String stringSelected, stringSelectedGroup;
    private GroupDAO groupDAO;
    private List listGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information_account);
        Intent intent = this.getIntent();
        dto = (UserDTO) intent.getSerializableExtra("DTO");
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
        spGroup = findViewById(R.id.edtGroupId);
        groupDAO = new GroupDAO();
        listGroup = groupDAO.getGroup();
        ArrayAdapter<GroupDTO> adapterGroup = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listGroup);
        adapterGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGroup.setAdapter(adapterGroup);
        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stringSelectedGroup = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtFullname.setText(dto.getFullname());
        edtPhone.setText(dto.getPhone());
        edtAddress.setText(dto.getAddress());
        edtEmail.setText(dto.getEmail());
        edtBirthday.setText(dto.getBirthday());

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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                edtBirthday.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year , month, day);
        datePickerDialog.show();
    }

    public void clickToUpdateInformation(View view) {
        Intent intent = this.getIntent();
        String username = intent.getStringExtra("username");
        UserDAO dao = new UserDAO();
        String fullname = edtFullname.getText().toString().trim();
        String phone = edtPhone.getText().toString();
        String address = edtAddress.getText().toString().trim();
        String email = edtEmail.getText().toString();
        String birthday = edtBirthday.getText().toString();
        if(!fullname.equals("") && !phone.equals("") && phone.matches("[0-9]{10}")
                && email.matches("[a-zA-z0-9]{6,15}@[a-z]{1,6}[.][a-z]{1,3}||[a-zA-z0-9]{6,15}@[a-z]{1,6}[.][a-z]{1,3}[.][a-z]{1,3}")
                && !address.equals("") && !email.equals("") && !birthday.equals("")){

        boolean check = dao.updateUser(username,fullname,phone,address,email,birthday,stringSelected,stringSelectedGroup);
        if(check){
            Toast.makeText(this, "Update Success", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(this,AccountManagementActivity.class);
            startActivity(intent1);
            finish();
        }else{
            Toast.makeText(this, "Update failed", Toast.LENGTH_LONG).show();
            finish();
        }
        }else {
            Toast.makeText(this, "input wrong", Toast.LENGTH_LONG).show();
        }

    }
}
