package com.ambientaid.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);

    }

    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("News Feed");
        m.setDescription("This is news feed description");
        m.setImg(R.drawable.pic1);
        models.add(m);

        m = new Model();
        m.setTitle("Business");
        m.setDescription("This is business description");
        m.setImg(R.drawable.pic2);
        models.add(m);

        m = new Model();
        m.setTitle("People");
        m.setDescription("This is people description");
        m.setImg(R.drawable.pic3);
        models.add(m);

        m = new Model();
        m.setTitle("Notes");
        m.setDescription("This is notes description");
        m.setImg(R.drawable.pic4);
        models.add(m);

        m = new Model();
        m.setTitle("Feedback");
        m.setDescription("This is feedback description");
        m.setImg(R.drawable.pic5);
        models.add(m);

        return models;
    }
}