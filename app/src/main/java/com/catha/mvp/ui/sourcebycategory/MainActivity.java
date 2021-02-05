package com.catha.mvp.ui.sourcebycategory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.catha.mvp.Data.model.GetKeywordDataBean;
import com.catha.mvp.Data.model.GetSourcesDataBean;
import com.catha.mvp.Data.remote.SearchByKeyword.KeywordDataSource;
import com.catha.mvp.Data.remote.SearchByKeyword.KeywordRepository;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSource;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryDataSourceImpl;
import com.catha.mvp.Data.remote.SourceByCategory.CategoryRepository;
import com.catha.mvp.R;
import com.catha.mvp.adapter.AdapterOnItemClickListener;
import com.catha.mvp.adapter.KeywordAdapter;
import com.catha.mvp.adapter.NewsAdapter;
import com.catha.mvp.adapter.NewsBySourceIdAdapter;
import com.catha.mvp.apiHelper.BaseApiService;
import com.catha.mvp.apiHelper.UtilsApi;
import com.catha.mvp.keywordresponse.ArticlesItem;
import com.catha.mvp.keywordresponse.KeywordResponse;
import com.catha.mvp.ui.Keyword.KeywordPresenter;
import com.catha.mvp.ui.Keyword.KeywordView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryView  {
    private CategoryPresenter categoryPresenter;
    BaseApiService mApiService;

    private CategoryRepository categoryRepository;
    private CategoryDataSource categoryDataSource;
    NewsAdapter onlineAdapter;
    RecyclerView rvWishlist;
    EditText search;
KeywordAdapter keywordAdapter;
    Button business, entertainment, technology, sports, health, science;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryDataSource = new CategoryDataSourceImpl(getApplicationContext());
        mApiService = UtilsApi.getAPIService();
        categoryRepository = CategoryRepository.getInstance(categoryDataSource);
        categoryPresenter = new CategoryPresenterImpl(this, categoryRepository);

        categoryPresenter.doGetSourcesByCategory("business");
        search = findViewById(R.id.et_search);
        business = findViewById(R.id.btn_business);
        entertainment = findViewById(R.id.btn_entertainment);
        technology = findViewById(R.id.btn_technology);
        sports = findViewById(R.id.btn_sports);
        health = findViewById(R.id.btn_health);
        science = findViewById(R.id.btn_science);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        rvWishlist = findViewById(R.id.rvWishlist);


        rvWishlist.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(MainActivity.this, 1);
        rvWishlist.setLayoutManager(layoutManager);
        business.setOnClickListener(view -> categoryPresenter.doGetSourcesByCategory("business"));
        entertainment.setOnClickListener(view -> categoryPresenter.doGetSourcesByCategory("entertainment"));
        technology.setOnClickListener(view -> categoryPresenter.doGetSourcesByCategory("technology"));
        sports.setOnClickListener(view -> categoryPresenter.doGetSourcesByCategory("sports"));
        health.setOnClickListener(view -> categoryPresenter.doGetSourcesByCategory("health"));
        science.setOnClickListener(view -> categoryPresenter.doGetSourcesByCategory("science"));


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
getbyKeyword();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    private void getbyKeyword() {



        mApiService.getbykeyword("everything?q="+search.getText().toString()+"&apiKey=f73ac613cfe4436cb9a035fa9df9df2d").enqueue(new Callback<KeywordResponse>() {

            @Override
            public void onResponse(@NonNull Call<KeywordResponse> call, @NonNull Response<KeywordResponse> response) {
                if (response.isSuccessful()) {
                    List<ArticlesItem> dataItemList = null;
                    dataItemList = response.body().getArticles();
                    //Toast.makeText(context, ""+androidItemList, Toast.LENGTH_SHORT).show();
                    keywordAdapter = new KeywordAdapter(MainActivity.this, dataItemList,genProductAdapterListener());
                    rvWishlist.setAdapter(keywordAdapter);
                    keywordAdapter.notifyDataSetChanged();

                } else {
                }
            }

            @Override
            public void onFailure(Call<KeywordResponse>  call, Throwable t) {

                //Toast.makeText(getActivity(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSuccessGetSourcesByCategory(GetSourcesDataBean getSourcesDataBean) {
        onlineAdapter = new NewsAdapter(MainActivity.this, getSourcesDataBean.getSources(), genProductAdapterListener());
        rvWishlist.setAdapter(onlineAdapter);
        onlineAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailedGetSourcesByCategory(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(CategoryPresenter presenter) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        categoryPresenter.doGetSourcesByCategory("business");
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
