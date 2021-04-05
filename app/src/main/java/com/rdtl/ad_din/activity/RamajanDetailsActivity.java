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


//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            hideSystemUI();
//        }
//    }
//
//    private void hideSystemUI() {
//        // Enables regular immersive mode.
//        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
//        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_IMMERSIVE
//                        // Set the content to appear under the system bars so that the
//                        // content doesn't resize when the system bars hide and show.
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        // Hide the nav bar and status bar
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
//    }
//
//    // Shows the system bars by removing all the flags
//// except for the ones that make the content appear under the system bars.
//    private void showSystemUI() {
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//    }


    private void ToolBar() {

        mToolbar = findViewById( R.id.ramjan_details_toolbar );

        setSupportActionBar( mToolbar );

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    }

}