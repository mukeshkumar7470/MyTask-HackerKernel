package com.mukeshkpdeveloper.mytask.UI.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mukeshkpdeveloper.mytask.R;
import com.squareup.picasso.Picasso;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");

        ImageView imgDisplay;
        ImageView btnClose;


        imgDisplay =findViewById(R.id.imgDisplay);
        btnClose =findViewById(R.id.img_back_arrow);

        Intent i = getIntent();
        String image = i.getStringExtra("img_url");
        Picasso.get().load(image).resize(120, 60).into(imgDisplay);


        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FullScreenImageActivity.this.finish();
            }
        });


        imgDisplay.setImageBitmap(bmp );

    }
}