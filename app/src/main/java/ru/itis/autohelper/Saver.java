package ru.itis.autohelper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ILMAZ on 23.08.2016.
 */
public class Saver {
    private static final String PARAM_NAME = "Parametres";
    private Context context;
    private SharedPreferences sPPref;
    private SharedPreferences.Editor ed;

    public Saver(Context context) {
        this.context = context;
        sPPref = context.getSharedPreferences(PARAM_NAME, Context.MODE_PRIVATE);
        ed = sPPref.edit();
    }

    public void fillStandart(String name, int km){
        ed.putString("name", name);
        addKM(km);
        ed.putInt("PC", 10);
        ed.putString("P1N", "Моторное масло");
        ed.putString("P1T", "24");
        ed.putInt("P1K", 10000);
        ed.putString("P2N", "Свечи зажигания");
        ed.putString("P2T", "36");
        ed.putInt("P2K", 20000);
        ed.putString("P3N", "Сход-развал");
        ed.putString("P3T", "0");
        ed.putInt("P3K", 10000);
        ed.putString("P4N", "Задние тормозные колодки");
        ed.putString("P4T", "36");
        ed.putInt("P4K", 30000);
        ed.putString("P5N", "Топливный фильтр");
        ed.putString("P5T", "24");
        ed.putInt("P5K", 30000);
        ed.putString("P6N", "Воздушный фильтр");
        ed.putString("P6T", "24");
        ed.putInt("P6K", 30000);
        ed.putString("P7N", "Передние тормозные колодки");
        ed.putString("P7T", "36");
        ed.putInt("P7K", 30000);
        ed.putString("P8N", "Антифриз");
        ed.putString("P8T", "36");
        ed.putInt("P8K", 60000);
        ed.putString("P9N", "Тормозная жидкость");
        ed.putString("P9T", "36");
        ed.putInt("P9K", 60000);
        ed.putString("P10N", "Ремень ГРМ и натяжные ролики");
        ed.putString("P10T", "36");
        ed.putInt("P10K", 60000);
        ed.putInt("HC", 0);
        ed.commit();
    }

    public void addKM(int km){
        ed.putInt("KM", km);
        ed.commit();
    }

    public int getKM(){
        return sPPref.getInt("KM", 0);
    }

    public void addParametr(String name, String time, int km){
        int count = sPPref.getInt("PC", 0);
        for (int i = 1; i <= count ; i++) {
            if(sPPref.getString("P"+ i+ "N", "Error").equals(name)){
                ed.putString("P"+ Integer.toString(i)+ "T", time);
                ed.putInt("P"+ Integer.toString(i)+ "K", km);
                return;
            }
        }
        count++;
        ed.putString("P"+ Integer.toString(count)+ "N", name);
        ed.putString("P"+ Integer.toString(count)+ "T", time);
        ed.putInt("P"+ Integer.toString(count)+ "K", km);
        ed.putInt("PC", count);
        ed.commit();
    }

    public boolean isAlreadyFilled(){
        return sPPref.contains("name");
    }

    public ArrayList<NotificationItem> getParametresList(){
        ArrayList<NotificationItem> arr = new ArrayList<>();
        NotificationItem not;
        String name;
        String time;
        int km;
        int count = sPPref.getInt("PC", 0);
        for (int i = 1; i <= count; i++) {
            name = sPPref.getString("P"+i+"N", "Error");
            time = sPPref.getString("P"+i+"T", "0");
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
        String time;
        int km;
        int count = sPPref.getInt("HC", 0);
        for (int i = count; i > 0; i--) {
            name = sPPref.getString("H"+i+"N", "Error");
            time = sPPref.getString("H"+i+"T", "0");
            km = sPPref.getInt("H"+i+"K", 0);
            not = new NotificationItem(name,time,km);
            arr.add(not);
        }
        Collections.sort(arr);
        return arr;
    }

    public void addHistory(String name, String time, int km){
        int count = sPPref.getInt("HC", 0);
        count++;
        ed.putString("H"+ Integer.toString(count)+ "N", name);
        ed.putString("H"+ Integer.toString(count)+ "T", time);
        ed.putInt("H"+ Integer.toString(count)+ "K", km);
        ed.putInt("HC", count);
        ed.commit();
    }

    public void clear(){
        ed.remove("name");
        ed.remove("KM");
        ed.putInt("HC", 0);
        ed.putInt("PC", 0);
        ed.commit();

    }

}
