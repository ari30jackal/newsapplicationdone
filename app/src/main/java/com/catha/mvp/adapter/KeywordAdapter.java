package com.catha.mvp.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.catha.mvp.R;
import com.catha.mvp.adapter.AdapterOnItemClickListener;
import com.catha.mvp.keywordresponse.ArticlesItem;
import com.catha.mvp.ui.WebviewArticle;


import java.util.List;

public class KeywordAdapter extends RecyclerView.Adapter< KeywordAdapter.OnlineHolder> {
    //bawah ini
    List<ArticlesItem> dataItemList;

    //tryjuardisenin

    Context mContext;
    RecyclerView mRecyclerView;
    AdapterView.OnItemClickListener listener;
    private  KeywordAdapter.OnItemClicked onClick;
    private static AdapterOnItemClickListener mListener;

    ProgressDialog loading;
    static Context context;

    String role;
    FragmentManager fragmentManager;
    FragmentTransaction ft;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public  KeywordAdapter(Context context, List<ArticlesItem> dataItem, AdapterOnItemClickListener listener) {
        this.mContext = context;
        dataItemList = dataItem;
        this.mListener = listener;
        /*this.tListener = listener2;*/
    }

    @Override
    public  KeywordAdapter.OnlineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new  KeywordAdapter.OnlineHolder(itemView, mListener);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final  KeywordAdapter.OnlineHolder holder, @SuppressLint("RecyclerView") final int position) {

        final ArticlesItem item = dataItemList.get(position);

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


        public OnlineHolder(View itemView, AdapterOnItemClickListener listener) {

            super(itemView);

            mListener = listener;
            context = itemView.getContext();
            //ribbonLayout = itemView.findViewById(R.id.ribbonLayout_item_online);
            card = itemView.findViewById(R.id.card);
            //linearLayout = itemView.findViewById(R.id.linearFeatured);
            title = itemView.findViewById(R.id.tv_judulberita);



            //linearLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View itemView) {
            mListener.onClick(itemView, getAdapterPosition());
        }
    }

    public List<ArticlesItem> getItems() {
        return dataItemList;
    }

    public ArticlesItem getItem(int position) {
        return dataItemList.get(position);
    }


}
