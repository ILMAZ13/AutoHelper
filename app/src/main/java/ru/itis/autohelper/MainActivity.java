package ru.itis.autohelper;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    public static Saver saver;
    AutoCompleteTextView tv_probeg;
    RecyclerView rv_notifications;
    FloatingActionButton btn_confirm;
    ArrayList<NotificationItem> notList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saver = new Saver(this);

        notList = new ArrayList<>();
        fillNotifications(notList);

        boolean filled = saver.isAlreadyFilled();
        if(!filled) {
            Intent intentToIntro = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(intentToIntro);
        }

        tv_probeg = (AutoCompleteTextView) findViewById(R.id.probeg);
        rv_notifications = (RecyclerView) findViewById(R.id.notifications);
        rv_notifications.setLayoutManager(new LinearLayoutManager(rv_notifications.getContext()));

        NotificationItemAdapter adapter = new NotificationItemAdapter(notList, getFragmentManager());
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
                saver.addKM(Integer.parseInt(tv_probeg.getText().toString()));
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


    private void fillNotifications(ArrayList<NotificationItem> not) {
        //ToDO: replace to test by data and km
        ArrayList<NotificationItem> parametres = saver.getParametresList();
        ArrayList<NotificationItem> history = saver.getHistoryList();

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        NotificationItem now = new NotificationItem("", day+"."+month+"."+year, saver.getKM());
        boolean flag = false;
        for(NotificationItem it1 : parametres){
            for(NotificationItem it2 : history){
                if(it1.getDetail_name().equals(it2.getDetail_name())){
                    flag = true;
                    //ToDo: Finish him
                }
            }
        }

    }
}
