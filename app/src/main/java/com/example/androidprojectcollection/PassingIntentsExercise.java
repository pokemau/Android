package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class PassingIntentsExercise extends AppCompatActivity {

    EditText EfName, ElName,
            EBdate, EPhoneNum,
            EEmail, EAddress, EStrand,
            EHighSchool, EMothersName, EFathersName;
    RadioButton rMale, rFemale, rOthers;

    Button btnSubmit, btnClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this::onClick);

        EfName = (EditText) findViewById(R.id.eTextFName);
        ElName = (EditText) findViewById(R.id.eTextLName);
        EBdate = (EditText) findViewById(R.id.eTextBdate);
        EPhoneNum = (EditText) findViewById(R.id.eTextPhoneNumber);
        EStrand = (EditText) findViewById(R.id.eTextStrand);
        EEmail = (EditText) findViewById(R.id.eTextEmailAd);
        EAddress = (EditText) findViewById(R.id.eTextAddress);
        EHighSchool = (EditText) findViewById(R.id.eTextHighSchool);
        EMothersName = (EditText) findViewById(R.id.eTextMothersName);
        EFathersName = (EditText) findViewById(R.id.eTextFathersName);
        rMale = (RadioButton) findViewById(R.id.radMale);
        rFemale = (RadioButton) findViewById(R.id.radFemale);
        rOthers = (RadioButton) findViewById(R.id.radOthers);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInputs();

            }
        });
    }

    public void onClick(View v) {
        String fName = EfName.getText().toString();
        String lName = ElName.getText().toString();

        String gender = "Unknown";
        if (rMale.isChecked()) { gender = "Male"; }
        else if (rFemale.isChecked()) { gender = "Female"; }
        else if (rOthers.isChecked()) { gender = "Others"; }

        String bDate = EBdate.getText().toString();
        String phoneNum = EPhoneNum.getText().toString();
        String emailAdd = EEmail.getText().toString();
        String strand = EStrand.getText().toString();
        String address = EAddress.getText().toString();
        String highSchool = EHighSchool.getText().toString();
        String motherName = EMothersName.getText().toString();
        String fatherName = EFathersName.getText().toString();

        if (fName.isEmpty() || lName.isEmpty() ||
                bDate.isEmpty() || phoneNum.isEmpty() ||
                emailAdd.isEmpty() || strand.isEmpty() ||
                address.isEmpty() || highSchool.isEmpty() ||
                motherName.isEmpty() || fatherName.isEmpty()) { return; }

        Intent i = new Intent(PassingIntentsExercise.this, PassingIntentsExercise2.class);
        i.putExtra("fname_key", fName);
        i.putExtra("lname_key", lName);
        i.putExtra("gender_key", gender);
        i.putExtra("bDate_key", bDate);
        i.putExtra("strand_key", strand);
        i.putExtra("phoneNum_key", phoneNum);
        i.putExtra("emailAdd_key", emailAdd);
        i.putExtra("address_key", address);
        i.putExtra("highSchool_key", highSchool);
        i.putExtra("motherName_key", motherName);
        i.putExtra("fatherName_key", fatherName);

        startActivity(i);
    }

    private void clearInputs() {
        EfName.setText("");
        ElName.setText("");
        EBdate.setText("");
        EPhoneNum.setText("");
        EEmail.setText("");
        EAddress.setText("");
        EHighSchool.setText("");
        EStrand.setText("");
        EMothersName.setText("");
        EFathersName.setText("");
        rMale.setChecked(false);
        rFemale.setChecked(false);
        rOthers.setChecked(false);
    }
}