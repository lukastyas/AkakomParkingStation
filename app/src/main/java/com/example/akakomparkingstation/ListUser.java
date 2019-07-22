package com.example.akakomparkingstation;

import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;

public class ListUser extends AppCompatActivity {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        dataSet = new ArrayList<>();
        initDataset();

        rvView = (RecyclerView)findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(dataSet);
        rvView.setAdapter(adapter);
    }
    private void initDataset(){
        dataSet.add("Karin");
        dataSet.add("Ingrid");
        dataSet.add("Helga");
        dataSet.add("Renate");
        dataSet.add("Elke");
        dataSet.add("Ursula");
        dataSet.add("Erika");
        dataSet.add("Christa");
        dataSet.add("Gisela");
        dataSet.add("Monika");
    }
}
