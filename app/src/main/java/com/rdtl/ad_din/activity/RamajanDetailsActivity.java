package com.rdtl.ad_din.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.rdtl.ad_din.R;

public class RamajanDetailsActivity extends AppCompatActivity {

    WebView webView;
    String webname;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramjan_details);

        ToolBar();

        webView = findViewById(R.id.idWebViewOfRamjan);

        Intent in = getIntent();

        webname = in.getStringExtra("RequiredWeb");

        Toast.makeText(this, ""+webname, Toast.LENGTH_SHORT).show();

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webcall();
        //webView.loadUrl("file:///android_asset/fitrah.html");
        webView.setBackgroundColor(Color.TRANSPARENT);

    }

    public void webcall(){
        switch (webname){
            case "webRojarNiotBtn":
                webView.loadUrl("file:///android_asset/rojarniot.html");
                break;
            case "webRojarFojilot":
                webView.loadUrl("file:///android_asset/rojaydhan.html");
                break;
            case "webIfterASunnotKhabar":
                webView.loadUrl("file:///android_asset/iftaresunnotkhabar.html");
                break;
            case "webSokriya":
                webView.loadUrl("file:///android_asset/iftaresukria.html");
                break;
            case "webRojarCharkaj":
                webView.loadUrl("file:///android_asset/rojarmashercharkaj.html");
                break;
            case "webTarabirNioim":
                webView.loadUrl("file:///android_asset/tarabirniomniot.html");
                break;
            case "webKaronChara":
                webView.loadUrl("file:///android_asset/karonchararojanarakha.html");
                break;
            case "webRojavanga":
                webView.loadUrl("file:///android_asset/rojavangarkaron.html");
                break;
            case "webRojarkaffara":
                webView.loadUrl("file:///android_asset/rojarkaffara.html");
                break;
            case "webVulDharona":
                webView.loadUrl("file:///android_asset/vuldharona.html");
                break;
            case "webPastAndbrush":
                webView.loadUrl("file:///android_asset/pastomeswak.html");
                break;
            case "webRomankeRohomot":
                webView.loadUrl("file:///android_asset/romjankerohomot.html");
                break;
            case "webRojayDhan":
                webView.loadUrl("file:///android_asset/rojaydhan.html");
                break;
            case "webJannatJahannam":
                webView.loadUrl("file:///android_asset/jannatjahannam.html");
                break;
            case "webFitrah":
                webView.loadUrl("file:///android_asset/fitrah.html");
                break;
            case "webRojayNirdesh":
                webView.loadUrl("file:///android_asset/nirdesupodesh.html");
                break;
            case "webItekaf":
                webView.loadUrl("file:///android_asset/itekaf.html");
                break;
            case "webRojonMasherBorkot":
                webView.loadUrl("file:///android_asset/rojobmasherborkot.html");
                break;
            default:
                break;

        }
    }





    private void ToolBar() {

        mToolbar = findViewById( R.id.ramjan_details_toolbar );

        setSupportActionBar( mToolbar );

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    }

}