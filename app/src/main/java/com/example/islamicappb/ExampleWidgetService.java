package com.example.islamicappb;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.islamicappb.R;

import java.text.DateFormat;
import java.util.Date;

public class ExampleWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ExampleWidgetItemFactory(getApplicationContext(), intent);
    }
    class ExampleWidgetItemFactory implements RemoteViewsFactory {
        private Context context;
        private int appWidgetId;


        private String[] exampleData = {"one "};
        private String[] exampleData2 = {"one "};
        private String[] exampleData3 = {"one "};
        private String[] exampleData4 = {"one "};
        private String[] exampleData5 = {"one "};
        ExampleWidgetItemFactory(Context context, Intent intent) {
            this.context = context;
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        @Override
        public void onCreate() {
            //connect to data source
        }
        @Override
        public void onDataSetChanged() {
            //refresh data

            Date date = new Date();
            String timeFormatted = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);

//            SharedPreferences sharedPrefMM = getApplicationContext().getSharedPreferences("mm", Context.MODE_PRIVATE);
//            String Fajr = sharedPrefMM.getString("fw","");
//            Log.e("lkj",""+Fajr);

            SharedPreferences sharedPrefMM = context.getSharedPreferences("mm",Context.MODE_PRIVATE);
            String Fajr = sharedPrefMM.getString("fw","");
            String Johor = sharedPrefMM.getString("jw","");
            String Asor = sharedPrefMM.getString("aw","");
            String Magrib = sharedPrefMM.getString("mw","");
            String Esha = sharedPrefMM.getString("ew","");

            exampleData = new String[]{""+Fajr};
            exampleData2 = new String[]{""+Johor};
            exampleData3 = new String[]{""+Asor};
            exampleData4 = new String[]{""+Magrib};
            exampleData5 = new String[]{""+Esha};
            SystemClock.sleep(3000);
        }
        @Override
        public void onDestroy() {
            //close data source
        }
        @Override
        public int getCount() {
            return exampleData.length;
        }
        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget_item);
            views.setTextViewText(R.id.example_widget_item_text, exampleData[position]);
            views.setTextViewText(R.id.example_widget_item_text2, exampleData2[position]);
            views.setTextViewText(R.id.example_widget_item_text3, exampleData3[position]);
            views.setTextViewText(R.id.example_widget_item_text4, exampleData4[position]);
            views.setTextViewText(R.id.example_widget_item_text5, exampleData5[position]);
            Intent fillIntent = new Intent();
            fillIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            views.setOnClickFillInIntent(R.id.example_widget_item_text, fillIntent);
            SystemClock.sleep(500);
            return views;
        }
        @Override
        public RemoteViews getLoadingView() {
            return null;
        }
        @Override
        public int getViewTypeCount() {
            return 1;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }


    }
}