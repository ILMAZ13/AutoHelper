package ru.itis.autohelper;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by UseR7 on 23.08.2016.
 */
public class NotificationItem implements Comparable<NotificationItem>{
    private String detail_name;
    private String time;
    private int km;


    public NotificationItem(String detail_name, String time, int km) {
        this.detail_name = detail_name;
        this.time = time;
        this.km = km;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public String getTime() {
        return time;
    }

    public int getKm() {
        return km;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int compareTo(NotificationItem notificationItem) {
        String[] date1 = time.split(".");
        String[] date2 = notificationItem.getTime().split(".");
        int c;
        try {
            c = Integer.compare(Integer.parseInt(date1[2]), Integer.parseInt(date2[2]));
            if (c == 0) {
                c = Integer.compare(Integer.parseInt(date1[1]), Integer.parseInt(date2[1]));
            }
            if (c == 0) {
                c = Integer.compare(Integer.parseInt(date1[0]), Integer.parseInt(date2[0]));
            }

        }catch (ArrayIndexOutOfBoundsException e){
            c = 0;
        }
        return c;
    }
}
