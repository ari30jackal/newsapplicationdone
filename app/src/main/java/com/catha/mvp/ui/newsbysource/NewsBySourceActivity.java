package com.catha.mvp.ui.newsbysource;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.catha.mvp.Data.model.GetNewsBySourceDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.Data.remote.NewsBySource.NewsBySourceDataSource;
import com.catha.mvp.Data.remote.NewsBySource.NewsBySourceDataSourceImpl;
import com.catha.mvp.Data.remote.NewsBySource.NewsRepository;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSource;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSourceImpl;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryRepository;
import com.catha.mvp.R;
import com.catha.mvp.adapter.AdapterOnItemClickListener;
import com.catha.mvp.adapter.NewsAdapter;
import com.catha.mvp.adapter.NewsBySourceIdAdapter;
import com.catha.mvp.ui.sourcebycategory.CategoryPresenter;
import com.catha.mvp.ui.sourcebycategory.CategoryPresenterImpl;
import com.catha.mvp.ui.sourcebycategory.CategoryView;

public class NewsBySourceActivity extends AppCompatActivity implements NewsBySourceView {
    private NewsBySourcePresenter newsBySourcePresenter;
    private NewsRepository newsRepository;
    private NewsBySourceDataSource newsBySourceDataSource;
    NewsBySourceIdAdapter onlineAdapter;
    RecyclerView rvWishlist;
    String source;

    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsbysource);
        newsBySourceDataSource = new NewsBySourceDataSourceImpl(getApplicationContext());

        newsRepository = NewsRepository.getInstance(newsBySourceDataSource);
        newsBySourcePresenter = new NewsBySourcePresenterImpl((NewsBySourceView) NewsBySourceActivity.this, newsRepository);
        source = getIntent().getExtras().getString("tes");
        newsBySourcePresenter.doGetNewsBySource(source);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(NewsBySourceActivity.this);
        rvWishlist = findViewById(R.id.rvWishlist);


        rvWishlist.setHasFixedSize(true);
        //rvWishlist.setLayoutManager(mLayoutManager);
        layoutManager = new LinearLayoutManager(NewsBySourceActivity.this);
        rvWishlist.setLayoutManager(layoutManager);

    }

    @Override
    public void onSuccessGetNewsBySource(GetNewsBySourceDataBean getNewsBySourceDataBean) {
        onlineAdapter = new NewsBySourceIdAdapter(NewsBySourceActivity.this, getNewsBySourceDataBean.getArticles(), genProductAdapterListener());
        rvWishlist.setAdapter(onlineAdapter);
        onlineAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailedGetNewsBySource(String error) {

    }

    @Override
    public void onFailedNewsBySource(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(CategoryPresenter presenter) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private AdapterOnItemClickListener genProductAdapterListener() {
        return new AdapterOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        };
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
