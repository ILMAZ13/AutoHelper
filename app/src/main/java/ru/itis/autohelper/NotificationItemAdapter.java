package ru.itis.autohelper;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class  NotificationItemAdapter extends RecyclerView.Adapter<NotificationItemAdapter.NotificationViewHolder> {

    private ArrayList<NotificationItem> notifications;
    DialogFragment dlg;
    FragmentManager fragmentManager;

    public NotificationItemAdapter(ArrayList notifications, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.notifications=notifications;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        dlg = new SelectDialog();
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        final NotificationItem notification = notifications.get(position);

        holder.detailName.setText(notification.getDetail_name());
        holder.message.setText(notification.getKm() + "km / " + notification.getTime()+"мес."); //ToDo: replace to adecvat message

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.show(fragmentManager,"dlg");
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView detailName;
        TextView message;
        View view;

            public NotificationViewHolder(View itemView) {
            super(itemView);

                view = itemView;
                detailName = (TextView) itemView.findViewById(R.id.detail_name);
                message = (TextView) itemView.findViewById(R.id.message);
        }
    }
}