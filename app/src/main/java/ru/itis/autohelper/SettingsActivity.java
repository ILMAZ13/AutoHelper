package ru.itis.autohelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        tv_top = (TextView)  findViewById(R.id.change);
        tv_detail = (TextView) findViewById(R.id.detail);
        cb_first = (CheckBox) findViewById(R.id.cb_first);
        cb_second =(CheckBox) findViewById(R.id.cb_second);
        et_km = (EditText) findViewById(R.id.et_number1);
        et_weeks = (EditText) findViewById(R.id.et_number2);
        tv_kmLabel = (TextView) findViewById(R.id.kmLabel);
        tv_weeksLabel = (TextView) findViewById(R.id.weeksLabel);

    }
}
