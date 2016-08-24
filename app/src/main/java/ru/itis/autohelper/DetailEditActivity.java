package ru.itis.autohelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DetailEditActivity extends AppCompatActivity {

    TextView tv_detail_name;
    TextView tv_kazhdie;
    TextView tv_value;
    TextView tv_km;
    Button btn_change;
    Button btn_settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_edit);


        tv_detail_name = (TextView) findViewById(R.id.detail_label);
        tv_kazhdie = (TextView) findViewById(R.id.kazhdie);
        tv_value = (TextView) findViewById(R.id.value);
        tv_km = (TextView) findViewById(R.id.km);
        btn_change = (Button) findViewById(R.id.changing);
        btn_settings = (Button) findViewById(R.id.settings);
    }
}
