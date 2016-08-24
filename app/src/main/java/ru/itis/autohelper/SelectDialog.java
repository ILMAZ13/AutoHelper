package ru.itis.autohelper;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Admin on 23.08.2016.
 */
public class SelectDialog extends DialogFragment {
    private EditText km;
    private EditText time;
    private Button btn_save;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().setTitle(NotificationItemAdapter.nameOfDetail);
        View v = inflater.inflate(R.layout.activity_edit, null);
        km = (EditText) v.findViewById(R.id.kilometers_edit);
        time = (EditText) v.findViewById(R.id.date_edit);
        km.setText(Integer.toString(MainActivity.saver.getKM()));
        btn_save = (Button) v.findViewById(R.id.save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.parseInt(km.getText().toString());
                if(temp > MainActivity.saver.getKM()){
                    MainActivity.saver.addKM(temp);
                }
                try {
                    MainActivity.saver.addHistory(NotificationItemAdapter.nameOfDetail, Integer.parseInt(time.getText().toString()), temp);
                }catch (NumberFormatException e){
                    time.setText("0");
                }
            }
        });
//        v.findViewById(R.id.settings).setOnClickListener(this);
//        v.findViewById(R.id.changing).setOnClickListener(this);
        return v;
    }

}
