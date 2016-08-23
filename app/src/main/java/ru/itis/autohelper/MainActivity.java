package ru.itis.autohelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tv_probeg;
    RecyclerView rv_notifications;
    Button btn_confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean filled = false;   //ToDo: Replace to filled test
        if(!filled) {
            Intent intentToIntro = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(intentToIntro);
        }

        tv_probeg = (TextView) findViewById(R.id.probeg);
        rv_notifications = (RecyclerView) findViewById(R.id.notifications);
        rv_notifications.setLayoutManager(new LinearLayoutManager(rv_notifications.getContext()));
        NotificationItemAdapter adapter = new NotificationItemAdapter(fillNotifications());
        rv_notifications.setAdapter(adapter);

        btn_confirm = (Button) findViewById(R.id.confirm);


    }

    private ArrayList<NotificationItem> fillNotifications() {
        ArrayList<NotificationItem> notifications = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NotificationItem notification= new NotificationItem(
                    "detail"+i,
                    "message"+i
            );
            notifications.add(notification);
        }
        return notifications;
    }
}
