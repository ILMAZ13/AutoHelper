package ru.itis.autohelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText et_probeg;
    EditText et_data;
    Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        et_probeg = (EditText) findViewById(R.id.kilometers_edit);
        et_data = (EditText) findViewById(R.id.date_edit);
        btn_ok = (Button) findViewById(R.id.save);
    }
}
