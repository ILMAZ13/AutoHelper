package ru.itis.autohelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Saver saver;
    AutoCompleteTextView tv_probeg;
    RecyclerView rv_notifications;
    Button btn_confirm;
    Button btn_cancel;
    SharedPreferences sPref;


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
        NotificationItemAdapter adapter = new NotificationItemAdapter(fillNotifications());
        rv_notifications.setAdapter(adapter);

        btn_confirm = (Button) findViewById(R.id.confirm);
        btn_cancel = (Button) findViewById(R.id.cancel_action);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saver.clear();
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });

        Intent intentFromIntro = getIntent();
        tv_probeg.setText(intentFromIntro.getStringExtra("km"));
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

        return MainActivity.saver.getParametresList();
    }
}
