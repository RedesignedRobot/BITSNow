package com.aaxs.amir.bitsnow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<IFlexible> myItems = getDatabsaeList();
        FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(myItems);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public List<IFlexible> getDatabsaeList(){
        List<IFlexible> list = new ArrayList<>();
        list.add(new Event("Event One","Our First Event!"));
        list.add(new Event("Event Two","Our Second Event!"));
        return list;
    }
}
