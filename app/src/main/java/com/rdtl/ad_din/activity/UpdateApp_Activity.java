package com.rdtl.ad_din.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rdtl.ad_din.R;

public class UpdateApp_Activity extends AppCompatActivity {

    Button UpdateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_app_);

        UpdateBtn = findViewById(R.id.idUpdateBtn);

        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.rdtl.ad_din"));
                Intent chosser = Intent.createChooser(sendIntent, "Choose your browser");
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chosser);

                }


            }
        });


    }
}