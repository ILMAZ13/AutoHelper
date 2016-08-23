package ru.itis.autohelper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NotificationItemAdapter extends RecyclerView.Adapter<NotificationItemAdapter.NotificationViewHolder> {

    private ArrayList<NotificationItem> notifications;

    public NotificationItemAdapter(ArrayList notifications) {
        this.notifications=notifications;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        final NotificationItem notification = notifications.get(position);

        holder.detailName.setText(notification.getDetail_name());
        holder.message.setText(notification.getMessage());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
