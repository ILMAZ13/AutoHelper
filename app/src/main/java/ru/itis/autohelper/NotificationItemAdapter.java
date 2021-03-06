package ru.itis.autohelper;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class  NotificationItemAdapter extends RecyclerView.Adapter<NotificationItemAdapter.NotificationViewHolder> {
    public static String nameOfDetail;
    private ArrayList<NotificationItem> notifications;
    private Context context;
    //changed
    SelectDialog dlg;
    FragmentManager fragmentManager;

    public NotificationItemAdapter(ArrayList notifications, FragmentManager fragmentManager, Context context) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.notifications=notifications;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        dlg = new SelectDialog();
        dlg.setContext(context);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        final NotificationItem notification = notifications.get(position);


        holder.detailName.setText(notification.getDetail_name());
        holder.message.setText(notification.getKm() + "km / " + notification.getTime());

        if(notification.isGood){
            holder.background.setBackgroundColor(Color.parseColor("#8FF049"));
        } else {
            holder.background.setBackgroundColor(Color.parseColor("#FF1049"));
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameOfDetail = notification.getDetail_name();
                dlg.show(fragmentManager,"dlg");
            }
        });

        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(view.getContext(), SettingsActivity.class);
                intent.putExtra("name", notification.getDetail_name());
                intent.putExtra("km", notification.getKm());
                intent.putExtra("time",notification.getTime());
                view.getContext().startActivity(intent);
                return true;
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
        View background;

            public NotificationViewHolder(View itemView) {
            super(itemView);

                view = itemView;
                detailName = (TextView) itemView.findViewById(R.id.detail_name);
                message = (TextView) itemView.findViewById(R.id.message);
                background = itemView.findViewById(R.id.item);
            }
    }
}
