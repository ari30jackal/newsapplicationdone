package com.catha.mvp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.catha.mvp.Data.model.SourceDataBean;
import com.catha.mvp.R;
import com.catha.mvp.ui.newsbysource.NewsBySourceActivity;


import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.OnlineHolder> {

    List<SourceDataBean> dataItemList;
    Context mContext;
    private static AdapterOnItemClickListener mListener;
    static Context context;


    public NewsAdapter(Context context, List<SourceDataBean> dataItem, AdapterOnItemClickListener listener) {
        this.mContext = context;
        dataItemList = dataItem;
        this.mListener = listener;

    }

    @Override
    public NewsAdapter.OnlineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new NewsAdapter.OnlineHolder(itemView, mListener);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final NewsAdapter.OnlineHolder holder, @SuppressLint("RecyclerView") final int position) {

        final SourceDataBean item = dataItemList.get(position);

        holder.title.setText(item.getName());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsBySourceActivity.class);
                intent.putExtra("tes", item.getId());
                context.startActivity(intent);


            }
        });


    }


    @Override
    public int getItemCount() {

        return dataItemList == null ? 0 : dataItemList.size();

    }

    public static class OnlineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView title;
        CardView card;
ImageView image;

        public OnlineHolder(View itemView, AdapterOnItemClickListener listener) {

            super(itemView);

            mListener = listener;
            context = itemView.getContext();

            card = itemView.findViewById(R.id.card);
image = itemView.findViewById(R.id.iv_news);
            title = itemView.findViewById(R.id.tv_judulberita);


        }

        @Override
        public void onClick(View itemView) {
            mListener.onClick(itemView, getAdapterPosition());
        }
    }

    public List<SourceDataBean> getItems() {
        return dataItemList;
    }

    public SourceDataBean getItem(int position) {
        return dataItemList.get(position);
    }


}
