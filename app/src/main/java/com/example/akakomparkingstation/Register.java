package com.example.akakomparkingstation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Register extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;

    private Button btnregis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

            final Context context = this;
            editText1 = (EditText) this.findViewById(R.id.nametext);
            editText2 = (EditText) this.findViewById(R.id.logintext);
            editText3 = (EditText) this.findViewById(R.id.passtext);
            btnregis = (Button)findViewById(R.id.btnregis);

            btnregis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String textQr1 = editText1.getText().toString();
                    String textQr2 = editText2.getText().toString();
                    String textQr3 = editText3.getText().toString();
                    Intent intent = new Intent(Register.this, QRcode.class);
                    intent.putExtra("pic1", textQr1);
                    intent.putExtra("pic2", textQr2);
                    intent.putExtra("pic3", textQr3);
                    startActivity(intent);


                }
            });
        }
    }

