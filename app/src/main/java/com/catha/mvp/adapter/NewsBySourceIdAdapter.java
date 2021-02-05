package com.catha.mvp.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.catha.mvp.Data.model.NewsBySourceDataBean;
import com.catha.mvp.R;
import com.catha.mvp.ui.WebviewArticle;


import java.util.List;

public class NewsBySourceIdAdapter extends RecyclerView.Adapter<NewsBySourceIdAdapter.OnlineHolder> {

    List<NewsBySourceDataBean> dataItemList;


    Context mContext;
    private static AdapterOnItemClickListener mListener;
    static Context context;

    public NewsBySourceIdAdapter(Context context, List<NewsBySourceDataBean> dataItem, AdapterOnItemClickListener listener) {
        this.mContext = context;
        dataItemList = dataItem;
        this.mListener = listener;
    }

    @Override
    public NewsBySourceIdAdapter.OnlineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new NewsBySourceIdAdapter.OnlineHolder(itemView, mListener);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final NewsBySourceIdAdapter.OnlineHolder holder, @SuppressLint("RecyclerView") final int position) {

        final NewsBySourceDataBean item = dataItemList.get(position);

        holder.title.setText(item.getTitle());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebviewArticle.class);
                intent.putExtra("url", item.getUrl());
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
image = itemView.findViewById(R.id.iv_news);
            mListener = listener;
            context = itemView.getContext();
            card = itemView.findViewById(R.id.card);
            title = itemView.findViewById(R.id.tv_judulberita);


        }

        @Override
        public void onClick(View itemView) {
            mListener.onClick(itemView, getAdapterPosition());
        }
    }

    public List<NewsBySourceDataBean> getItems() {
        return dataItemList;
    }

    public NewsBySourceDataBean getItem(int position) {
        return dataItemList.get(position);
    }


}
