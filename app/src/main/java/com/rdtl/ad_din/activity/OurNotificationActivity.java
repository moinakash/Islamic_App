package com.rdtl.ad_din.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rdtl.ad_din.R;
import com.rdtl.ad_din.pojo_classes.ConverterClass;
import com.rdtl.ad_din.pojo_classes.WaktoTimeMaintaining;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class OurNotificationActivity extends AppCompatActivity {


    private Toolbar mToolbar;


    TextView tvRunnigSuraName, tvCurrentDuratuin, tvTotalDuration;
    SeekBar sbRunningS;
    ImageView ivPlayPause;
    MediaPlayer suraMediaPlayer;
    Handler handler = new Handler();

    TextView tvSomoyName, tvCountDownBox, tvIftarerAge, tvIftarerAgeArbi, tvIftarerAgeUccharon, tviftarerAgeBangla;
    TextView tvIftarerSomoy, tvIftarerSomoyArbi, tvIftarerSomoyrUccharon, tvIftarerSomoyOrtho;
    TextView tvIftarerPor, tvIftarerPorArbi, tvIftarerPorUccharon, tvIftarerPorOrtho, tvIftarerPorDes;

    TextView tvCb1, tvCb2;

    ConverterClass converterClass;
    WaktoTimeMaintaining wtm;


    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private static final long START_TIME_IN_MILLIS = 6000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    int t2Hour, t2Minute, mm;
    int minutes3=0;

    String timeCheckerfornoti;

    LinearLayout llStillTime, llCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_notification);

        converterClass = new ConverterClass(this);
        wtm = new WaktoTimeMaintaining(this);

        ToolBar();

        initIds();
        initForPlayer();


        extraCode();

        textViewWorks();

        if (minutes3<=15 && minutes3>=0){
            startTimer();
            llCounter.setVisibility(View.VISIBLE);
            llStillTime.setVisibility(View.GONE);
        }else {
            llCounter.setVisibility(View.GONE);
            llStillTime.setVisibility(View.VISIBLE);
        }

        //startTimer();

        notificationValChange();

    }


        private void startTimer() {
            mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }
                @Override
                public void onFinish() {
                    mTimerRunning = false;
                    //mButtonStartPause.setText("Start");
                    //mButtonStartPause.setVisibility(View.INVISIBLE);
                    //mButtonReset.setVisibility(View.VISIBLE);
                    Toast.makeText(OurNotificationActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    //TODO
                    //show alart dialog
                    llCounter.setVisibility(View.GONE);
                    llStillTime.setVisibility(View.VISIBLE);
                }
            }.start();
            mTimerRunning = true;
            //mButtonStartPause.setText("pause");
            //mButtonReset.setVisibility(View.INVISIBLE);
        }



    public void extraCode(){

        Calendar calendar = Calendar.getInstance();
        final int hour2 = calendar.get(Calendar.HOUR);
        final int minute2 = calendar.get(Calendar.MINUTE);


        int hourOfDay = 6, minute = 25;


        SharedPreferences prefAudio = OurNotificationActivity.this.getSharedPreferences("Api_Audio",MODE_PRIVATE);
        SharedPreferences.Editor editorA = prefAudio.edit();
        String iftartimeCon = prefAudio.getString("iftar","০৬ঃ৪২");

        iftartimeCon = iftartimeCon.replace("ঃ","");
        iftartimeCon = iftartimeCon.replace(":","");
        iftartimeCon = converterClass.covertE(iftartimeCon);

        int iftartimeConInt = Integer.parseInt(iftartimeCon);

        hourOfDay = iftartimeConInt/100;
        minute = iftartimeConInt%100;

        Toast.makeText(this, "iftartimeCon "+iftartimeCon, Toast.LENGTH_SHORT).show();

        if (hourOfDay==0){
            hourOfDay = 24;
            if (hourOfDay>hour2){
                mm = (minute+60)-minute2;
            }
            else {
                mm = minute-minute2;
            }
        }else {
            if (hourOfDay>hour2){
                mm = (minute+60)-minute2;
            }
            else {
                mm = minute-minute2;
            }
        }


        mTimeLeftInMillis = mm * 60000;


        updateCountDownText();
        //SimpleDateFormat f24hours = new SimpleDateFormat("HH:MM");

        //mPickerTV.setText(time);
    }

    private void updateCountDownText() {
        minutes3 = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", minutes3);
        String timeLeftFormatted2 = String.format(Locale.getDefault(), "%02d", seconds);



        tvCb1.setText(timeLeftFormatted);
        tvCb2.setText(timeLeftFormatted2);

       // mTextViewCountDown.setText(timeLeftFormatted);
        //calculateRtime();
    }





    private void initIds(){
        tvSomoyName = findViewById(R.id.idSomoyName);
        tvCountDownBox = findViewById(R.id.idCountDownBox);
        tvIftarerAge = findViewById(R.id.idiftarerAge);
        tvIftarerAgeArbi = findViewById(R.id.idiftarerAgeArbi);
        tvIftarerAgeUccharon = findViewById(R.id.idIftarerAgeUccharon);
        tviftarerAgeBangla = findViewById(R.id.idIftarerAgeBangla);
        tvIftarerSomoy = findViewById(R.id.idIftarerSomoy);
        tvIftarerSomoyArbi = findViewById(R.id.idIftarerSomoyArbi);
        tvIftarerSomoyrUccharon = findViewById(R.id.idIftarerSomoyUccharon);
        tvIftarerSomoyOrtho = findViewById(R.id.idIftarerSomoyOrtho);
        tvIftarerPor = findViewById(R.id.idIftarerPor);
        tvIftarerPorArbi = findViewById(R.id.idIftarerPorArbi);
        tvIftarerPorUccharon = findViewById(R.id.idIftarerPorUccharon);
        tvIftarerPorOrtho = findViewById(R.id.idIftarerPorOrtho);
        tvIftarerPorDes = findViewById(R.id.idIftarerPorDes);

        tvCb1 = findViewById(R.id.idCountDownBox1_1);
        tvCb2 = findViewById(R.id.idCountDownBox1_2);

        llCounter = findViewById(R.id.idLiniarCounter);
        llStillTime = findViewById(R.id.idLiniarStillTime);
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

        suraMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // do whatever you want
                sbRunningS.setProgress(0);
                ivPlayPause.setBackgroundResource(R.drawable.btn_play);
                tvCurrentDuratuin.setText("     0:00");
            }
        });
    }

    private void preparMediaPlayer() {
        try {

            SharedPreferences prefAudio = OurNotificationActivity.this.getSharedPreferences("Api_Audio",MODE_PRIVATE);
            String url = prefAudio.getString("sp_Audio_Url"," nai");
            String url_title = prefAudio.getString("sp_Audio_Url_title","          সূরা বাকারার প্রথম সাতাস আয়াত তিলাওয়াত হচ্ছে");

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
                    tvRunnigSuraName.setText("     "+url_title);
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


        textViewmaintaining();

    }


    private void ToolBar() {

        mToolbar = findViewById( R.id.ramjan_notification_toolbar );

       // TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar( mToolbar );
      //  mTitle.setText("নোটিফিকেশন");

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


    @Override
    protected void onPause() {
        super.onPause();
        suraMediaPlayer.pause();
        ivPlayPause.setBackgroundResource(R.drawable.play_button);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        suraMediaPlayer.stop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        suraMediaPlayer.stop();
        finish();
    }

    private void textViewmaintaining(){



        SharedPreferences prefAudio = OurNotificationActivity.this.getSharedPreferences("Api_Audio",MODE_PRIVATE);
        SharedPreferences.Editor editorA = prefAudio.edit();
        editorA.putString("notificationValue", "0");
        String sheheritime = prefAudio.getString("sheheri","০৪ঃ৪২");
        String iftartime = prefAudio.getString("iftar","০৬ঃ৪২");

        Toast.makeText(this, "iftarrrrr"+iftartime, Toast.LENGTH_SHORT).show();


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH mm ");



        String dateTime = simpleDateFormat.format(calendar.getTime());
        dateTime = dateTime.replace(" ", "");
        int cTime = Integer.parseInt(dateTime);

        if (cTime <=1130){
            if (cTime<=600){
                tvSomoyName.setText("সেহরির শেষ\nসময়");
                tvCountDownBox.setText(""+sheheritime);
            }else {
                tvSomoyName.setText("ইফতারের\nসময়");
                tvCountDownBox.setText(""+iftartime);
            }

            String rojarnioarbi, rojarniouccharon, rojarniotortho;
            rojarnioarbi = ""+getApplication().getResources().getText(R.string.rojarniot_arbi).toString();
            rojarniouccharon = "<b>" + "উচ্চারণঃ  " +"</b>"+getApplication().getResources().getText(R.string.rojarniot_bangla).toString();
            rojarniotortho = "<b>" + "অর্থঃ  "+"</b>"+getApplication().getResources().getText(R.string.rojarniot_orth).toString();

            tvIftarerAge.setText(""+getApplication().getResources().getText(R.string.rojaniot).toString());
            tvIftarerAgeArbi.setText(Html.fromHtml(rojarnioarbi));
            tvIftarerAgeUccharon.setText(Html.fromHtml(rojarniouccharon));
            tviftarerAgeBangla.setText(Html.fromHtml(rojarniotortho));

            tvIftarerPorDes.setText(""+getApplication().getResources().getText(R.string.rojades).toString());


            tviftarerAgeBangla.setVisibility(View.VISIBLE);

            tvIftarerSomoy.setVisibility(View.GONE);
            tvIftarerSomoyArbi.setVisibility(View.GONE);
            tvIftarerSomoyrUccharon.setVisibility(View.GONE);
            tvIftarerSomoyOrtho.setVisibility(View.GONE);
            tvIftarerPor.setVisibility(View.GONE);
            tvIftarerPorArbi.setVisibility(View.GONE);
            tvIftarerPorUccharon.setVisibility(View.GONE);
            tvIftarerPorOrtho.setVisibility(View.GONE);
            //tvIftarerPorDes.setVisibility(View.GONE);

        }else {
            tvCountDownBox.setText(iftartime);
        }


    }


    private void notificationValChange(){


       SharedPreferences prefAudio = OurNotificationActivity.this.getSharedPreferences("Api_Audio",MODE_PRIVATE);
        SharedPreferences.Editor editorA = prefAudio.edit();
        editorA.putString("notificationValue", "0");
        editorA.commit();
    }

}