package ru.itis.autohelper;

/**
 * Created by UseR7 on 23.08.2016.
 */
public class NotificationItem {
    private String detail_name;
    private int time;
    private int km;


    public NotificationItem(String detail_name, int time, int km) {
        this.detail_name = detail_name;
        this.time = time;
        this.km = km;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public int getTime() {
        return time;
    }

    public int getKm() {
        return km;
    }

}
