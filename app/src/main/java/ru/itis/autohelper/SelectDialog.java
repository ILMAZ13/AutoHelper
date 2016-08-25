package ru.itis.autohelper;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SelectDialog extends DialogFragment {
    private EditText km;
    private EditText time;
    private FloatingActionButton btn_save;
    private FloatingActionButton btn_edit;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        getDialog().setTitle(NotificationItemAdapter.nameOfDetail);
        View v = inflater.inflate(R.layout.activity_edit, null);
        km = (EditText) v.findViewById(R.id.kilometers_edit);
        time = (EditText) v.findViewById(R.id.date_edit);
        km.setText(Integer.toString(MainActivity.saver.getKM()));
        btn_save = (FloatingActionButton) v.findViewById(R.id.save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.parseInt(km.getText().toString());
                if(temp > MainActivity.saver.getKM()){
                    MainActivity.saver.addKM(temp);
                }
                try {
                    MainActivity.saver.addHistory(NotificationItemAdapter.nameOfDetail, Integer.parseInt(time.getText().toString()), temp);
                    dismiss();
                }catch (NumberFormatException e){
                    time.setText("0");
                }
            }
        });

//        btn_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(, EditActivity.class);
//                startActivity(intent);
//            }
//        });
        return v;
    }

}
