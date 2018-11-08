package com.mobile.fauzanibnusarky.zyanmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.imgMovieDetail)
    ImageView imgMovieDetail;
    @BindView(R.id.tvJudulMovieDetail)
    TextView tvJudulMovieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent terimaData = getIntent();
        tvJudulMovieDetail.setText(terimaData.getStringExtra(BuildConfig.KEYNAME));
        Picasso.with(DetailActivity.this)
                .load(BuildConfig.IMAGE_URL+terimaData.getStringExtra(BuildConfig.KEYGAMBAR))
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(50,50)
                .centerCrop()
                .into(imgMovieDetail);

    }
}
