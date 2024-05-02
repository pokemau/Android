package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class MapsExercise extends AppCompatActivity {

    ImageButton btnRio, btnOslob, btnApo, btnGreatWall, btnKawasan;
    ImageView bg;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_maps_exercise);

        bg = (ImageView) findViewById(R.id.bgStreetView);
        txt = (TextView) findViewById(R.id.txtPlace);


        btnRio = (ImageButton) findViewById(R.id.btnRio);
        btnRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "Christ the Redeemer, Rio de Janeiro, Brazil";
                txt.setText(a);
                bg.setImageResource(R.drawable.christ);

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-22.951905702080804, -43.21048561890976"));
                startActivity(i);
            }
        });

        btnGreatWall = (ImageButton) findViewById(R.id.btnChina);
        btnGreatWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "Great Wall of China";
                txt.setText(a);
                bg.setImageResource(R.drawable.wall_view);

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:40.43194026398094, 116.57034269480083"));
                startActivity(i);
            }
        });

        btnKawasan = (ImageButton) findViewById(R.id.btnKawasan);
        btnKawasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "Kawasan Falls, Cebu";
                txt.setText(a);
                bg.setImageResource(R.drawable.kawasan_view);

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:9.803638128454827, 123.37437441169978"));
                startActivity(i);
            }
        });


        btnApo= (ImageButton) findViewById(R.id.btnApo);
        btnApo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "Apo Island, Negros, Philippines";
                txt.setText(a);
                bg.setImageResource(R.drawable.apo_view);

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:9.079015218772259, 123.27059092115375"));
                startActivity(i);
            }
        });

        btnOslob= (ImageButton) findViewById(R.id.btnOslob);
        btnOslob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "Oslob Whale Shark Watching, Cebu";
                txt.setText(a);
                bg.setImageResource(R.drawable.oslob_view);

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:9.463382355477364, 123.37980537631059"));
                startActivity(i);
            }
        });
    }
}