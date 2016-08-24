package ru.itis.autohelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Admin on 23.08.2016.
 */
public class SelectDialog extends DialogFragment implements View.OnClickListener {
    private String detail_name;
    private String km;

    public View onCreateDialog(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        getDialog().setTitle("");
        View v = inflater.inflate(R.layout.activity_detail_edit, null);
        v.findViewById(R.id.settings).setOnClickListener(this);
        v.findViewById(R.id.changing).setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
