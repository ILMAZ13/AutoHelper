package ru.itis.autohelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class SettingsActivity extends AppCompatActivity {

    TextView tv_top;
    TextView tv_detail;
    CheckBox cb_first;
    CheckBox cb_second;
    EditText et_km;
    EditText et_weeks;
    TextView tv_kmLabel;
    TextView tv_weeksLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        tv_detail = (TextView) findViewById(R.id.detail);
        cb_first = (CheckBox) findViewById(R.id.cb_first);
        cb_second =(CheckBox) findViewById(R.id.cb_second);
        et_km = (EditText) findViewById(R.id.et_number1);
        et_weeks = (EditText) findViewById(R.id.et_number2);
        tv_kmLabel = (TextView) findViewById(R.id.kmLabel);
        tv_weeksLabel = (TextView) findViewById(R.id.weeksLabel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        toolbar.setTitle("Настройка");
        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0,0);
        toolbar.setLogo(R.drawable.logo_small);

        et_km.setEnabled(false);
        et_weeks.setEnabled(false);

        cb_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_km.isEnabled()) {
                    et_km.setEnabled(false);
                } else {
                    et_km.setEnabled(true);
                }
            }
        });
        cb_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_weeks.isEnabled()) {
                    et_weeks.setEnabled(false);
                } else {
                    et_weeks.setEnabled(true);
                }
            }
        });
    }
}
