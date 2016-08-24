package ru.itis.autohelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class IntroActivity extends AppCompatActivity {

    ImageView iv_logo;
    EditText et_chooser;
    EditText et_input_km;
    Button btn_proceed;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv_logo = (ImageView) findViewById(R.id.logo);
        et_chooser = (EditText) findViewById(R.id.chooser);
        et_input_km = (EditText) findViewById(R.id.km_input);
        btn_proceed = (Button) findViewById(R.id.confirm);

        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int km = Integer.parseInt(et_input_km.getText().toString());
                    String name = et_chooser.getText().toString();
                    if(name.length()>0) {
                        MainActivity.saver.fillStandart(name, km);

                        Intent intentToMain = new Intent(IntroActivity.this, MainActivity.class);
                        intentToMain.putExtra("km", et_input_km.getText());
                        startActivity(intentToMain);
                    } else {
                        Toast.makeText(IntroActivity.this, "Введите наименование машины!!!",Toast.LENGTH_LONG).show();
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(IntroActivity.this, "Invalid number format!!!", Toast.LENGTH_LONG).show();
                    et_input_km.setText("");
                }
            }
        });
    }

}
