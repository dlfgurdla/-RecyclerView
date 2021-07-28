package com.cos.viewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cos.viewtest.adapter.PersonAdapter;
import com.cos.viewtest.provider.PersonProvider;

//어댑터와 리사이클러뷰 연결
public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPersons;
    private RecyclerView.LayoutManager layoutManager;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvPersons.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter();
        rvPersons.setAdapter(personAdapter);
    }

    private void initData() {
        PersonProvider p = new PersonProvider();
        personAdapter.addItems(p.findAll());
    }

    private void init() {
        rvPersons = findViewById(R.id.rvPersons);

    }
}
