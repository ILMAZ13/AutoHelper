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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
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
        for(NotificationItem k : notList){
            k.setTime(k.getTime()+"мес.");
        }

        boolean filled = saver.isAlreadyFilled();
        if(!filled) {
            Intent intentToIntro = new Intent(MainActivity.this, IntroActivity.class);
            intentToIntro.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentToIntro);
        }

        tv_probeg = (AutoCompleteTextView) findViewById(R.id.probeg);
        rv_notifications = (RecyclerView) findViewById(R.id.notifications);
        rv_notifications.setLayoutManager(new LinearLayoutManager(rv_notifications.getContext()));

        NotificationItemAdapter adapter = new NotificationItemAdapter(notList, getFragmentManager(), this);
        rv_notifications.setAdapter(adapter);

        btn_confirm = (FloatingActionButton) findViewById(R.id.confirm);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("Авто");
        toolbar.setSubtitle("помощник");
        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0,0);
        toolbar.setLogo(R.drawable.logo_small);

        tv_probeg.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(!tv_probeg.getText().equals(Integer.toString(saver.getKM()))){
                    btn_confirm.setImageResource(R.drawable.done);
                    btn_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            saver.addKM(Integer.parseInt(tv_probeg.getText().toString()));
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    btn_confirm.setImageResource(R.drawable.right);
                    btn_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            saver.addKM(Integer.parseInt(tv_probeg.getText().toString()));
                            Intent intent = new Intent(MainActivity.this, ListActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                return false;
            }
        });

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
        ArrayList<NotificationItem> parametres = saver.getParametresList();
        ArrayList<NotificationItem> history = saver.getHistoryList();

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        for(NotificationItem it1 : parametres){
            boolean flag = false;
            for(NotificationItem it2 : history){
                if(it1.getDetail_name().equals(it2.getDetail_name())){
                    flag = true;
                    int endMonth;
                    try {
                        endMonth = Integer.parseInt(it1.getTime()) - 1 + Integer.parseInt(it2.getTime().split("\\.")[1]);
                    } catch (NumberFormatException e){
                        endMonth = 1000;
                    }
                    int endYear = Integer.parseInt(it2.getTime().split("\\.")[2]);
                    if(endMonth > 12){
                        endYear += endMonth / 12;
                        endMonth %= 12;
                    }

                    NotificationItem now = new NotificationItem("", day+"."+month+"."+year, saver.getKM());
                    NotificationItem endDate = new NotificationItem("", day+"."+endMonth+"."+endYear, saver.getKM());
                    boolean f = true;
                    if(now.compareTo(endDate) < 0 && !it1.getTime().equals("0")){ //(now.getKm() - it2.getKm()+1000) > it1.getKm()
                        f = false;
                        it1.setKm(it2.getKm()+it1.getKm()-now.getKm());
                        it1.setTime(Integer.toString((endYear - year)*12 + endMonth+1 - month));
                        not.add(it1);
                       it1.isGood = false;
                    } else {
                        it1.isGood = true;
                        not.remove(it1);
                    }
                    if(f){
                        if((now.getKm() - it2.getKm()+1000) > it1.getKm() && it1.getKm()!= 0){
                            f = false;
                            it1.setKm(it2.getKm()+it1.getKm()-now.getKm());
                            it1.setTime(Integer.toString((endYear - year)*12 + endMonth+1 - month)+"мес.");
                            not.add(it1);
                            it1.isGood = false;
                        } else {
                            it1.isGood = true;
                            not.remove(it1);
                        }
                    }
                }
                if(flag){
                    break;
                }
            }
            if(!flag){
                if(it1.getKm() != 0) {
                    if (it1.getKm() - (saver.getKM() % it1.getKm()) < 2000 && (saver.getKM() / it1.getKm() >= 0)) {
                        it1.setKm(it1.getKm() - (saver.getKM() % it1.getKm()));
                        not.add(it1);
                        it1.isGood = false;
                    } else {
                        not.remove(it1);
                        it1.isGood = true;
                    }
                }
            }
        }
    }
}
