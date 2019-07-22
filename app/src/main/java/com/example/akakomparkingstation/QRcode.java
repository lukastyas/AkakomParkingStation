package com.example.akakomparkingstation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRcode extends AppCompatActivity {
    private  ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarQrcode);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("QrCode");

        imageView = (ImageView) findViewById(R.id.QRview);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String textQr1=getIntent().getExtras().getString("pic1");
        String textQr2=getIntent().getExtras().getString("pic2");
        String textQr3=getIntent().getExtras().getString("pic3");
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(textQr1+","+textQr2, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);

        }catch (WriterException e){
           e.printStackTrace();
        }

    }
}
