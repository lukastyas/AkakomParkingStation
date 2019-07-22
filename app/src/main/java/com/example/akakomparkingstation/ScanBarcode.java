package com.example.akakomparkingstation;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanBarcode extends AppCompatActivity implements View.OnClickListener {
    private ZXingScannerView mScannerView;
    private Button btninfo;
    private Button btnScan;
    private EditText noplat;
    private Spinner merk;
    private IntentIntegrator intentIntegrator;
    String []resultQR;

    Toolbar toolbar;


    Button btn_qr, btn_bar;
    TextView txt_content, txt_format;
    String contents, format;
    Intent intent;
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarscanbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Scan Data User");

        btninfo = (Button)findViewById(R.id.btninfo);
        btnScan = (Button) findViewById(R.id.btnScan);
        noplat = (EditText) findViewById(R.id.no_plat);
        merk = (Spinner) findViewById(R.id.spinnerMerk);
        intentIntegrator = new IntentIntegrator(this);
        btnScan.setOnClickListener(this);
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(ScanBarcode.this,List_Admin.class);
                inte.putExtra("ok",false);
                startActivity(inte);
            }
        });

    }
    @Override
    public void onClick(View v){
        //intentIntegrator = new IntentIntegrator(this);
        //intentIntegrator.initiateScan();
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(ScanBarcode.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }
    public void Input(View v){
        String mrk=merk.getSelectedItem().toString().trim();
        String noplati=noplat.getText().toString().trim();
        Toast.makeText(this, mrk+noplati+resultQR, Toast.LENGTH_SHORT).show();
        Intent inte=new Intent(ScanBarcode.this,List_Admin.class);
        inte.putExtra("ok",true);
        inte.putExtra("nama",resultQR[0]);
        inte.putExtra("user",resultQR[1]);
        startActivity(inte);

    }

    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //Toast.makeText(this,"SSSuvuvuvuvuvSSS", Toast.LENGTH_SHORT).show();
      //  IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //if (result != null){
          //  if (((IntentResult) result).getContents()==null){
            //    Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
           // }else {
                //resultQR=result.getContents().toString().trim();
             //   try {
               //     JSONObject object = newJSONObject(((IntentResult) result).getContents());
                    //textViewName.setText(object.getString("nama"));
                    //textViewTinggi.setText(object.getString("namaLgoin"));
                    //resultQR=object.getString("nama");
//                    Toast.makeText(this, "tttttttttttttttt", Toast.LENGTH_SHORT).show();

  //              }catch (JSONException e){
    //                e.printStackTrace();
      //              Toast.makeText(this, "ffffffffff", Toast.LENGTH_SHORT).show();
        //        }
          //  }
        //}else {
          //  Toast.makeText(this,"SSSSSS", Toast.LENGTH_SHORT).show();
            //super.onActivityResult(requestCode, resultCode, data);
       // }
    //}

    // dilalog untuk menampilkan peringantan jika belum nginstall aplikasi sncanner com.google.zxing.client.android
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(act);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });

        dialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return dialog.show();
    }

    // untuk menampilkan hasil scanner
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                contents = intent.getStringExtra("SCAN_RESULT");
                format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                resultQR=contents.split(",");
                //txt_content.setText("Content : " + contents);
                //txt_format.setText("Format : " + format);
            }
        }
    }
}
