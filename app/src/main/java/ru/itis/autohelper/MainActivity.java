package ru.itis.autohelper;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Saver saver;
    AutoCompleteTextView tv_probeg;
    RecyclerView rv_notifications;
    FloatingActionButton btn_confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saver = new Saver(this);

        boolean filled = saver.isAlreadyFilled();
        if(!filled) {
            Intent intentToIntro = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(intentToIntro);
        }

        tv_probeg = (AutoCompleteTextView) findViewById(R.id.probeg);
        rv_notifications = (RecyclerView) findViewById(R.id.notifications);
        rv_notifications.setLayoutManager(new LinearLayoutManager(rv_notifications.getContext()));
        NotificationItemAdapter adapter = new NotificationItemAdapter(fillNotifications(), getFragmentManager());
        rv_notifications.setAdapter(adapter);

        btn_confirm = (FloatingActionButton) findViewById(R.id.confirm);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0,0);
        toolbar.setLogo(R.drawable.logo_small);
        toolbar.setTitle("Авто помощник");



        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        tv_probeg.setText(Integer.toString(saver.getKM()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            saver.clear();
            Intent intent = new Intent(MainActivity.this, IntroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private ArrayList<NotificationItem> fillNotifications() {
        //ToDO: replace to test by data and km
        /*ArrayList<NotificationItem> notifications = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NotificationItem notification= new NotificationItem(
                    "detail"+i,
                    i,
                    i*15
            );
            notifications.add(notification);
        }
        return notifications;*/

        return MainActivity.saver.getHistoryList();
    }
}
