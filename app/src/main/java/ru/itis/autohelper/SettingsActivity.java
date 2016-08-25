package ru.itis.autohelper;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SettingsActivity extends AppCompatActivity {


    EditText et_detail;
    CheckBox cb_first;
    CheckBox cb_second;
    EditText et_km;
    EditText et_weeks;
    TextView tv_kmLabel;
    TextView tv_weeksLabel;
    FloatingActionButton btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        et_detail = (EditText) findViewById(R.id.detail);
        cb_first = (CheckBox) findViewById(R.id.cb_first);
        cb_second =(CheckBox) findViewById(R.id.cb_second);
        et_km = (EditText) findViewById(R.id.et_number1);
        et_weeks = (EditText) findViewById(R.id.et_number2);
        tv_kmLabel = (TextView) findViewById(R.id.kmLabel);
        tv_weeksLabel = (TextView) findViewById(R.id.weeksLabel);
        btn_save = (FloatingActionButton) findViewById(R.id.done);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        toolbar.setTitle("Настройки");
        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0,0);
        toolbar.setLogo(R.drawable.logo_small);

        et_km.setEnabled(false);
        et_weeks.setEnabled(false);

        cb_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_km.isEnabled()){
                    et_km.setEnabled(false);
                }
                else et_km.setEnabled(true);
            }
        });

        cb_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_weeks.isEnabled()){
                    et_weeks.setEnabled(false);
                }
                else et_weeks.setEnabled(true);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int km=0;
                String time="0";
                String name="";
                boolean znach = true;

                if(et_detail.getText().length() == 0) Toast.makeText(SettingsActivity.this, "Введите название работы", Toast.LENGTH_LONG).show();
                else {
                    name = et_detail.getText().toString();
                    try {
                        km = Integer.parseInt(et_km.getText().toString());
                    } catch (NumberFormatException e) {
                        znach = false;
                        et_km.setText("");
                        Toast.makeText(SettingsActivity.this, "Введите через сколько км заменить", Toast.LENGTH_LONG).show();
                    }
                    try {
                        time = et_weeks.getText().toString();
                    } catch (NumberFormatException e) {
                        znach = false;
                        et_weeks.setText("");
                        Toast.makeText(SettingsActivity.this, "Введите через сколько месяцев заменить", Toast.LENGTH_LONG).show();
                    }
                    if(znach==true) {
                        MainActivity.saver.addParametr(name, time, km);
                        Intent intent = new Intent(SettingsActivity.this, ListActivity.class);
                        startActivity(intent);
                    }
                }


            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String km = intent.getStringExtra("km");
        String time = intent.getStringExtra("time");
        et_detail.setText(name);
        et_km.setText(km);
        et_weeks.setText(time);
    }
}
