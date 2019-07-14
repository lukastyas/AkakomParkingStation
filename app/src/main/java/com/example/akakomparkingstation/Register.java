package com.example.akakomparkingstation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private Button btnregis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

            btnregis = (Button)findViewById(R.id.btnregis);

            btnregis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = "Registration Success";
                    Toast.makeText(Register.this, text, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

