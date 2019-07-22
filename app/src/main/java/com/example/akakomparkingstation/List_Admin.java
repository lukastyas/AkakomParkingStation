package com.example.akakomparkingstation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

public class List_Admin extends AppCompatActivity {
    MySQLHelper dbhelper;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> nama;
    private ArrayList<String> user;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarladmin);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("List Data");

        nama = new ArrayList<>();
        user= new ArrayList<>();
        boolean ok=getIntent().getBooleanExtra("ok",false);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        dbhelper=new MySQLHelper(this);
        adapter = new RecyclerViewAdapterAdmin(nama, user);
        rvView.setAdapter(adapter);
        if (ok) {
            String nama1=getIntent().getExtras().getString("nama");
            String user1=getIntent().getExtras().getString("user");

            if (cekAda(nama1, user1)) {
                addData(nama1, user1);
                Toast.makeText(this, "Dia mau parkir data ditambah", Toast.LENGTH_LONG).show();
            } else {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                db.execSQL("delete from " + "data" + " where nama='" + nama1 + "' AND user='" + user1 + "';");
                Toast.makeText(this, "Dia mau pergi hapus data", Toast.LENGTH_LONG).show();
            }
        }
        view();
    }
    void addData(String nama,String user) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        try {
            db.execSQL("insert into " + MySQLHelper.TABLE
                    + " values(null,'" + nama
                    + "','" + user + "')");
        } catch (Exception e) {
        }
        //finish();
    }
    boolean cekAda(String nama2, String user2){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data WHERE nama='"+nama2+"' AND user='"+user2+"';",null);
        //if (cursor.getString(cursor.getColumnIndex("nama"))!=null && cursor.getString(cursor.getColumnIndex("user"))!=null)
        if (cursor.getCount()<1)
            return true;
        else return false;

    }
    private void view() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        try {
            cursor = db.rawQuery("SELECT * FROM data", null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                //photo.add(cursor.getString(cursor.getColumnIndex("photo"))); //add the item
                //desc.add(cursor.getString(cursor.getColumnIndex("desc"))); //add the item
                nama.add(cursor.getString(cursor.getColumnIndex("nama")));
                user.add(cursor.getString(cursor.getColumnIndex("user")));
                cursor.moveToNext();
            }
            //String[] photoarray = photo.toArray(new String[photo.size()]);
            //String[] descarray = desc.toArray(new String[desc.size()]);
            //adapter=new MyListAdapter(this,descarray,photoarray);
            //list.setAdapter(adapter);
        } catch (Exception e) {}
    }

}
