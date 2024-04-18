package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PassingIntentsExercise2 extends AppCompatActivity {

    TextView tvFname, tvLname, tvGender, tvBdate, tvPhoneNum, tvEmail, tvAddress, tvHighSchool,
    tvMother, tvFather, tvStrand;


    Button btnReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise2);

        tvFname = (TextView) findViewById(R.id.tvFName);
        tvLname = (TextView) findViewById(R.id.tvLName);
        tvGender = (TextView)findViewById(R.id.tvGender);
        tvBdate = (TextView) findViewById(R.id.tvBirthDate);
        tvPhoneNum = (TextView) findViewById(R.id.tvPhoneNumber);
        tvEmail = (TextView) findViewById(R.id.tvEmailAddress);
        tvAddress = (TextView) findViewById(R.id.tvPresentAddress);
        tvHighSchool = (TextView) findViewById(R.id.tvHighSchool);
        tvStrand = (TextView) findViewById(R.id.tvStrand);
        tvMother = (TextView) findViewById(R.id.tvMotherName);
        tvFather = (TextView) findViewById(R.id.tvFatherName);

        Intent i = getIntent();

        tvFname.setText(i.getStringExtra("fname_key"));
        tvLname.setText(i.getStringExtra("lname_key"));
        tvGender.setText(i.getStringExtra("gender_key"));
        tvBdate.setText(i.getStringExtra("bDate_key"));
        tvPhoneNum.setText(i.getStringExtra("phoneNum_key"));
        tvEmail.setText(i.getStringExtra("emailAdd_key"));
        tvAddress.setText(i.getStringExtra("address_key"));
        tvHighSchool.setText(i.getStringExtra("highSchool_key"));
        tvStrand.setText(i.getStringExtra("strand_key"));
        tvMother.setText(i.getStringExtra("motherName_key"));
        tvFather.setText(i.getStringExtra("fatherName_key"));

        btnReturn = (Button) findViewById(R.id.btnReturnToIntentsExercise);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}