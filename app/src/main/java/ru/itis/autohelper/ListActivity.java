package ru.itis.autohelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ListActivity extends Activity {

    TextView tv_todolist;
    RecyclerView rv_list;
    FloatingActionButton btn_add;



    private TextView tvName_detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        tv_todolist = (TextView) findViewById(R.id.todolist);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        btn_add = (FloatingActionButton) findViewById(R.id.btn_add);

        tvName_detail = (TextView) findViewById(R.id.name_detail);

        NotificationItemAdapter adapter = new NotificationItemAdapter(MainActivity.saver.getParametresList(), getFragmentManager());
        rv_list.setLayoutManager(new LinearLayoutManager(rv_list.getContext()));
        rv_list.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }


}
