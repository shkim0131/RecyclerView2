package com.ambientaid.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        preferences = this.getSharedPreferences("My_Pref", MODE_PRIVATE);

        getMyList();

    }

    private void getMyList() {
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

        String mSortSetting = preferences.getString("Sort", "ascending");

        if (mSortSetting.equals("ascending")) {
            Collections.sort(models, Model.By_TITLE_ASCENDING);
        } else if (mSortSetting.equals("descending")) {
            Collections.sort(models,Model.By_TITLE_DESCENDING);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, models);
        mRecyclerView.setAdapter(myAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch(id) {
            case R.id.sorting:
                sortDialog();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortDialog() {
        String[] options = {"Ascending", "Descending"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sort by");
        builder.setIcon(R.drawable.ic_action_sort);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor = preferences.edit();
                switch (i) {
                    case 0:
                        editor.putString("Sort", "ascending");
                        break;
                    default:
                        editor.putString("Sort", "descending");
                        break;
                }
                editor.apply();
                getMyList();

            }
        });

        builder.create().show();
    }
}