package ru.itis.autohelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class HistoryActivity extends Activity {

    TextView tv_history;
    RecyclerView rv_history_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tv_history = (TextView) findViewById(R.id.history);
        rv_history_list = (RecyclerView) findViewById(R.id.history_list);
    }
}
