package com.example.akakomparkingstation;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    String []kumpulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText username, password;
        Button BtnLogin;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.logintext);
        password = (EditText) findViewById(R.id.passtext);
        BtnLogin = (Button) findViewById(R.id.btnlogin);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();
                AmbilDt();

                if (usernameKey.equals("admin") && passwordKey.equals("admin")){
                    Toast.makeText(getApplicationContext(),"LOGIN SUCCESS",
                    Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ScanBarcode.class);
                    MainActivity.this.startActivity(intent);
                    finish();
                }else if (usernameKey.equalsIgnoreCase(kumpulan[1]) && passwordKey.equals(kumpulan[2])){
                    Toast.makeText(getApplicationContext(),"LOGIN SUCCESS",
                            Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(MainActivity.this, QRcode.class);
                    Intent intent = new Intent(MainActivity.this, QRcode.class);
                    intent.putExtra("pic1", kumpulan[0]);
                    intent.putExtra("pic2", kumpulan[1]);
                    intent.putExtra("pic3", kumpulan[2]);
                    MainActivity.this.startActivity(intent);
                    finish();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Username and Password Not Access!")
                            .setNegativeButton("Rety", null).create().show();
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarlogin);
        setSupportActionBar(toolbar);

        TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null){
            toolbarText.setText(getTitle());
            setSupportActionBar(toolbar);

        }
    }
    public void Registrasi(View view) {
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }
    public void AmbilDt(){
        File data= new File(this.getFilesDir(),"mydir/dt.txt");
        if(data.exists()){
            try {
                BufferedReader in=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(data))));
                kumpulan=in.readLine().split(",");
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //ganti pake toast



        }else {
            System.out.println("Result of find : Cant Find data");
        }
    }
}
