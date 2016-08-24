package ru.itis.autohelper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ILMAZ on 23.08.2016.
 */
public class Saver {
    private static final String PARAM_NAME = "Parametres";
    private static final String HISTORY_NAME = "History";
    private Context context;
    private SharedPreferences sPPref;
    private SharedPreferences.Editor ed;
    private SharedPreferences sHPref;

    public Saver(Context context) {
        this.context = context;
        sPPref = context.getSharedPreferences(PARAM_NAME, Context.MODE_PRIVATE);
        sHPref = context.getSharedPreferences(HISTORY_NAME, Context.MODE_PRIVATE);
        ed = sPPref.edit();
    }

    public void fillStandart(String name, int km){
        ed.putString("name", name);
        addKM(km);
        ed.putInt("PC", 10);
        ed.putString("P1N", "Моторное масло");
        ed.putInt("P1T", 24);
        ed.putInt("P1K", 10000);
        ed.putString("P2N", "Свечи зажигания");
        ed.putInt("P2T", 36);
        ed.putInt("P2K", 20000);
        ed.putString("P3N", "Сход-развал");
        ed.putInt("P3T", 0);
        ed.putInt("P3K", 10000);
        ed.putString("P4N", "Задние тормозные колодки");
        ed.putInt("P4T", 36);
        ed.putInt("P4K", 30000);
        ed.putString("P5N", "Топливный фильтр");
        ed.putInt("P5T", 24);
        ed.putInt("P5K", 30000);
        ed.putString("P6N", "Воздушный фильтр");
        ed.putInt("P6T", 24);
        ed.putInt("P6K", 30000);
        ed.putString("P7N", "Передние тормозные колодки");
        ed.putInt("P7T", 36);
        ed.putInt("P7K", 30000);
        ed.putString("P8N", "Антифриз");
        ed.putInt("P8T", 36);
        ed.putInt("P8K", 60000);
        ed.putString("P9N", "Тормозная жидкость");
        ed.putInt("P9T", 36);
        ed.putInt("P9K", 60000);
        ed.putString("P10N", "Ремень ГРМ и натяжные ролики");
        ed.putInt("P10T", 36);
        ed.putInt("P10K", 60000);
        ed.commit();
        SharedPreferences.Editor hEd = sHPref.edit();
        hEd.putInt("HC", 0);
        hEd.commit();
    }

    public void addKM(int km){
        ed.putInt("KM", km);
        ed.commit();
    }

    public int getKM(){
        return sPPref.getInt("KM", -1);
    }

    public void addParametr(String name, int time, int km){
        int count = sPPref.getInt("PC", 0);
        count++;
        ed.putString("P"+ Integer.toString(count)+ "N", name);
        ed.putInt("P"+ Integer.toString(count)+ "T", time);
        ed.putInt("P"+ Integer.toString(count)+ "K", km);
        ed.putInt("PC", count);
    }

    public boolean isAlreadyFilled(){
        return sPPref.contains("name");
    }

    public ArrayList<NotificationItem> getParametresList(){
        ArrayList<NotificationItem> arr = new ArrayList<>();
        NotificationItem not;
        String name;
        int time;
        int km;
        int count = sPPref.getInt("PC", 0);
        for (int i = 1; i <= count; i++) {
            name = sPPref.getString("P"+i+"N", "Error");
            time = sPPref.getInt("P"+i+"T", 0);
            km = sPPref.getInt("P"+i+"K", 0);
            not = new NotificationItem(name,time,km);
            arr.add(not);
        }
        return arr;
    }

    public ArrayList<NotificationItem> getHistoryList(){
        ArrayList<NotificationItem> arr = new ArrayList<>();
        NotificationItem not;
        String name;
        int time;
        int km;
        int count = sHPref.getInt("HC", 0);
        for (int i = 1; i <= count; i++) {
            name = sHPref.getString("H"+i+"N", "Error");
            time = sHPref.getInt("H"+i+"T", 0);
            km = sHPref.getInt("H"+i+"K", 0);
            not = new NotificationItem(name,time,km);
            arr.add(not);
        }
        return arr;
    }

    public void addHistory(String name, int time, int km){
        int count = sHPref.getInt("HC", 0);
        SharedPreferences.Editor ed = sHPref.edit();
        count++;
        ed.putString("H"+ Integer.toString(count)+ "N", name);
        ed.putInt("H"+ Integer.toString(count)+ "T", time);
        ed.putInt("H"+ Integer.toString(count)+ "K", km);
        ed.putInt("HC", count);
    }

}
