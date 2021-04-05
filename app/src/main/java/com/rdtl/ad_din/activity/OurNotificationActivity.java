package com.rdtl.ad_din.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rdtl.ad_din.R;




public class OurNotificationActivity extends AppCompatActivity {


    private Toolbar mToolbar;


    TextView tvRunnigSuraName, tvCurrentDuratuin, tvTotalDuration;
    SeekBar sbRunningS;
    ImageView ivPlayPause;
    MediaPlayer suraMediaPlayer;
    Handler handler = new Handler();

    TextView tvSomoyName, tvCountDownBox, tvIftarerAge, tvIftarerAgeArbi, tvIftarerAgeUccharon;
    TextView tvIftarerSomoy, tvIftarerSomoyArbi, tvIftarerSomoyrUccharon, tvIftarerSomoyOrtho;
    TextView tvIftarerPor, tvIftarerPorArbi, tvIftarerPorUccharon, tvIftarerPorOrtho, tvIftarerPorDes;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_notification);

        ToolBar();

        initIds();
        initForPlayer();

        textViewWorks();

    }




    private void initIds(){
        tvSomoyName = findViewById(R.id.idSomoyName);
        tvCountDownBox = findViewById(R.id.idCountDownBox);
        tvIftarerAge = findViewById(R.id.idiftarerAge);
        tvIftarerAgeArbi = findViewById(R.id.idiftarerAgeArbi);
        tvIftarerAgeUccharon = findViewById(R.id.idIftarerAgeUccharon);
        tvIftarerSomoy = findViewById(R.id.idIftarerSomoy);
        tvIftarerSomoyArbi = findViewById(R.id.idIftarerSomoyArbi);
        tvIftarerSomoyrUccharon = findViewById(R.id.idIftarerSomoyUccharon);
        tvIftarerSomoyOrtho = findViewById(R.id.idIftarerSomoyOrtho);
        tvIftarerPor = findViewById(R.id.idIftarerPor);
        tvIftarerPorArbi = findViewById(R.id.idIftarerPorArbi);
        tvIftarerPorUccharon = findViewById(R.id.idIftarerPorUccharon);
        tvIftarerPorOrtho = findViewById(R.id.idIftarerPorOrtho);
        tvIftarerPorDes = findViewById(R.id.idIftarerPorDes);
    }

    private void initForPlayer(){
        tvRunnigSuraName = findViewById(R.id.idRunningSura);
        tvCurrentDuratuin = findViewById(R.id.idRunnigDuration);
        tvTotalDuration = findViewById(R.id.idTotalDuration);
        sbRunningS = findViewById(R.id.idSeekbarofSure);
        ivPlayPause = findViewById(R.id.idSuraPlayPause);

        suraMediaPlayer = new MediaPlayer();

        sbRunningS.setMax(100);


        ivPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (suraMediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    suraMediaPlayer.pause();
                    ivPlayPause.setBackgroundResource(R.drawable.btn_play);
                }else {
                    suraMediaPlayer.start();
                    ivPlayPause.setBackgroundResource(R.drawable.btn_pause);
                    updateSeekBar();
                }
            }
        });

        preparMediaPlayer();

        sbRunningS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar = (SeekBar) v;
                int playPosition = (suraMediaPlayer.getDuration()/100) * seekBar.getProgress();
                suraMediaPlayer.seekTo(playPosition);
                tvCurrentDuratuin.setText("     "+miliSecondsToTimer(suraMediaPlayer.getCurrentPosition()));
                return false;
            }
        });
    }

    private void preparMediaPlayer() {
        try {

            SharedPreferences pref = OurNotificationActivity.this.getSharedPreferences("Api_Audio",MODE_PRIVATE);
            String url = pref.getString("sp_Audio_Url","file:///android_asset/sura_bakara_first_ttysvn.mp3");

            //suraMediaPlayer.setDataSource("http://infinityandroid.com/music/good_times.mp3");
           // suraMediaPlayer.setDataSource("http://soundflux.islamicfinder.org/if-soundflux/api/v1/stream//quran/rahman-sudais/001.mp3");

            if(!isNetworkAvailable(this)) {

                //off
                AssetFileDescriptor afd = getAssets().openFd("sura_bakara_first_ttysvn.mp3");

                suraMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            }
            else {
                //on
                if (url.equals("No")){
                    //mediaPlayer =

                    //suraMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sura_bakara_first_ttysvn);

                    AssetFileDescriptor afd = getAssets().openFd("sura_bakara_first_ttysvn.mp3");

                    suraMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());


                }else {
                    suraMediaPlayer.setDataSource(""+url);
                }
            }




            suraMediaPlayer.prepare();

            tvTotalDuration.setText(miliSecondsToTimer(suraMediaPlayer.getDuration())+"     ");
           // suraMediaPlayer.prepareAsync();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }



    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = suraMediaPlayer.getCurrentPosition();
            tvCurrentDuratuin.setText("     "+miliSecondsToTimer(currentDuration));
        }
    };

    private  void updateSeekBar(){
        if (suraMediaPlayer.isPlaying()){
            sbRunningS.setProgress((int)(((float) suraMediaPlayer.getCurrentPosition() / suraMediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }


    private String miliSecondsToTimer(long miliSeconds){
        String timerString = "";
        String secondsString;

        int hours = (int) (miliSeconds / (1000 * 60 *60));
        int minutes = (int) (miliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((miliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0){
            timerString = hours + ":";
        }
        if (seconds<10){
            secondsString = "0" + seconds;
        }else {
            secondsString = "" + seconds;
        }

        timerString = timerString + minutes + ":" +secondsString;
        return  timerString;
    }


    private void textViewWorks(){

        String uccharon = getApplication().getResources().getText(R.string.uccharon).toString();
        String ortho = getApplication().getResources().getText(R.string.ortho).toString();

        String istegfarA = getApplication().getResources().getText(R.string.istegfar_arbi).toString();
        String istegfarU = "<b>" + uccharon + "</b> " + getApplication().getResources().getText(R.string.istegfar_uccharon).toString();


        String iftarerSomoy = getApplication().getResources().getText(R.string.iftar_doa_txt).toString();
        String iftarerSomoyArbi = getApplication().getResources().getText(R.string.iftarer_doa_arbi).toString();
        String iftarerSomoyUccharon = "<b>" + uccharon + "</b> " +  getApplication().getResources().getText(R.string.ifterer_doa_uccharon).toString();
        String iftarerSomoyOrtho = "<b>" + ortho + "</b> " + getApplication().getResources().getText(R.string.ifterer_doa_bangla).toString();


        String iftarerpor = getApplication().getResources().getText(R.string.ifterer_por_arbi).toString();
        String iftarerPorArbi = getApplication().getResources().getText(R.string.iftarer_doa_arbi).toString();
        String iftarerPorUccharon = "<b>" + uccharon + "</b> " + getApplication().getResources().getText(R.string.ifterer_por_arbi_uccha).toString();
        String iftarerPorOrtho = "<b>" + ortho + "</b> " + getApplication().getResources().getText(R.string.ifterer_por_arbi_ortho).toString();

        String iftarerporDes = getApplication().getResources().getText(R.string.ifterer_por_des).toString();


        tvIftarerAgeUccharon.setText(Html.fromHtml(istegfarU));
        tvIftarerSomoyrUccharon.setText(Html.fromHtml(iftarerSomoyUccharon));
        tvIftarerSomoyOrtho.setText(Html.fromHtml(iftarerSomoyOrtho));

        tvIftarerPorUccharon.setText(Html.fromHtml(iftarerPorUccharon));
        tvIftarerPorOrtho.setText(Html.fromHtml(iftarerPorOrtho));



    }


    private void ToolBar() {

        mToolbar = findViewById( R.id.ramjan_notification_toolbar );

        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar( mToolbar );
        mTitle.setText("নোটিফিকেশন");

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conMan.getActiveNetworkInfo() != null && conMan.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}