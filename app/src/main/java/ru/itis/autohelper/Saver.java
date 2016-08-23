package ru.itis.autohelper;

import android.content.Context;
import android.content.SharedPreferences;

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

    public void fillStandart(String name){
        ed.putString("name", name);
        ed.putInt("PC", 10);
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
}
