package ru.itis.autohelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    TextView tv_history;
    RecyclerView rv_history_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tv_history = (TextView) findViewById(R.id.history);
        rv_history_list = (RecyclerView) findViewById(R.id.history_list);
        rv_history_list.setLayoutManager(new LinearLayoutManager(rv_history_list.getContext()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("История");
        toolbar.setSubtitle("работ");
        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0,0);
        toolbar.setLogo(R.drawable.logo_small);

        NotificationItemAdapter adapter = new NotificationItemAdapter(MainActivity.saver.getHistoryList(), getFragmentManager());
        rv_history_list.setAdapter(adapter);
    }


}
