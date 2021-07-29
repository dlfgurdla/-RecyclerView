package com.cos.viewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.cos.viewtest.adapter.PersonAdapter;
import com.cos.viewtest.model.Person;
import com.cos.viewtest.provider.PersonProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//어댑터와 리사이클러뷰 연결
public class MainActivity extends AppCompatActivity {

    private MainActivity mContext = this;
    private RecyclerView rvPersons;
    private RecyclerView.LayoutManager layoutManager;
    private PersonAdapter personAdapter;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initAdapter();
        initData();
        initListener();
    }

    private void initListener() {
        fabAdd.setOnClickListener(v->{
            personAdapter.addItems(new Person("이름new","0102222"));
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped( RecyclerView.ViewHolder viewHolder, int direction) {
                int index=viewHolder.getAdapterPosition();
                personAdapter.removeItem(index);
            }
        }).attachToRecyclerView(rvPersons);
    }

    private void initAdapter() {
        layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvPersons.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter(mContext);
        rvPersons.setAdapter(personAdapter);
    }

    private void initData() {
        PersonProvider p = new PersonProvider();
        personAdapter.addItems(p.findAll());
    }

    private void init() {
        rvPersons = findViewById(R.id.rvPersons);
        fabAdd = findViewById(R.id.fabAdd);
    }
    public void mRvScroll() {
        rvPersons.scrollToPosition(personAdapter.getItemCount()-1);
    }
}
