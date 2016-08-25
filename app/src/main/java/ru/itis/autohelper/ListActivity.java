package ru.itis.autohelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    RecyclerView rv_list;
    FloatingActionButton btn_add;



    private TextView tvName_detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();

        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        btn_add = (FloatingActionButton) findViewById(R.id.btn_add);

        tvName_detail = (TextView) findViewById(R.id.name_detail);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list);
        toolbar.setTitle("Список");
        toolbar.setSubtitle("параметров");
        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0,0);
        toolbar.setLogo(R.drawable.logo_small);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.show_history) {
            Intent intent = new Intent(ListActivity.this, HistoryActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
