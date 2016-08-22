package ru.itis.autohelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {

    ImageView iv_logo;
    EditText et_chooser;
    EditText et_input_km;
    Button btn_proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv_logo = (ImageView) findViewById(R.id.logo);
        et_chooser = (EditText) findViewById(R.id.chooser);
        et_input_km = (EditText) findViewById(R.id.km_input);
        btn_proceed = (Button) findViewById(R.id.confirm);
    }
}
