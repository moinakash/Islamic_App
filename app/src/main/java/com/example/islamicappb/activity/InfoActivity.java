package com.example.islamicappb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.islamicappb.R;

public class InfoActivity extends AppCompatActivity {

    LinearLayout forFacebook, forWebSite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        forFacebook = findViewById(R.id.idFacebookLink);
        forWebSite = findViewById(R.id.idWebsitLink);

        forFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in = new Intent();
//                in.setAction(Intent.ACTION_VIEW);
//                in.addCategory(Intent.CATEGORY_BROWSABLE);
//                in.setData(Uri.parse("https://www.facebook.com/rdtl.info/"));
//                startActivity(in);

                Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/rdtl.info/"));
                Intent chosser = Intent.createChooser(sendIntent, "Choose your browser");
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chosser);

                }
            }
        });


        forWebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in = new Intent();
//                in.setAction(Intent.ACTION_VIEW);
//                in.addCategory(Intent.CATEGORY_BROWSABLE);
//                in.setData(Uri.parse("https://www.radissonbd.com/"));
//                startActivity(in);

                Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.radissonbd.com/"));
                Intent chosser = Intent.createChooser(sendIntent, "Choose your browser");
                if (sendIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(chosser);
                }
            }
        });
    }
}