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
                    sPref = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString("name", name);
                    ed.putString("HC", "0");
                    ed.putString("P1N", "Моторное масло");
                    ed.putString("P1T", "24");
                    ed.putString("P1K", "10000");
                    ed.putString("P2N", "Свечи зажигания");
                    ed.putString("P2T", "36");
                    ed.putString("P2K", "20000");
                    ed.putString("P3N", "Сход-развал");
                    ed.putString("P3T", "0");
                    ed.putString("P3K", "10000");
                    ed.putString("P4N", "Задние тормозные колодки");
                    ed.putString("P4T", "36");
                    ed.putString("P4K", "30000");
                    ed.putString("P5N", "Топливный фильтр");
                    ed.putString("P5T", "24");
                    ed.putString("P5K", "30000");
                    ed.putString("P6N", "Воздушный фильтр");
                    ed.putString("P6T", "24");
                    ed.putString("P6K", "30000");
                    ed.putString("P7N", "Передние тормозные колодки");
                    ed.putString("P7T", "36");
                    ed.putString("P7K", "30000");
                    ed.putString("P8N", "Антифриз");
                    ed.putString("P8T", "36");
                    ed.putString("P8K", "60000");
                    ed.putString("P9N", "Тормозная жидкость");
                    ed.putString("P9T", "36");
                    ed.putString("P9K", "60000");
                    ed.putString("P10N", "Ремень ГРМ и натяжные ролики");
                    ed.putString("P10T", "36");
                    ed.putString("P10K", "60000");

                    ed.commit();
                    Intent intentToMain = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intentToMain);
                }catch (NumberFormatException e){
                    Toast.makeText(IntroActivity.this, "Invalid number format!!!", Toast.LENGTH_LONG).show();
                    et_input_km.setText("");
                }
            }
        });
    }

}
