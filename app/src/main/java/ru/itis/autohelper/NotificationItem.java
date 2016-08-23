package ru.itis.autohelper;

/**
 * Created by UseR7 on 23.08.2016.
 */
public class NotificationItem {
    private String detail_name;
    private String message;


    public NotificationItem (String detail_name, String message) {
        this.message = message;
        this.detail_name = detail_name;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public String getMessage() {
        return message;
    }
}
