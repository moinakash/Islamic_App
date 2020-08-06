package com.example.islamicappb;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.BaseAdapter;
import android.widget.RemoteViews;
import android.widget.Toast;

import io.paperdb.Paper;


/**
 * Created by mustafa.urgupluoglu on 02/10/17.
 */

public class WidgetProvider extends AppWidgetProvider {


    private static final String LIST_ITEM_CLICKED_ACTION = "LIST_ITEM_CLICKED_ACTION";
    private static final String REFRESH_WIDGET_ACTION = "REFRESH_WIDGET_ACTION";
    public static final String EXTRA_CLICKED_FILE = "EXTRA_CLICKED_FILE";

    public static final String ACTION_WIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";

    SharedPreferences sharedPreferences;




    /**
     * "This is called to update the App Widget at intervals defined by the updatePeriodMillis attribute in the
     * AppWidgetProviderInfo (see Adding the AppWidgetProviderInfo Metadata above). This method is also called when the
     * user adds the App Widget, so it should perform the essential setup, such as define event handlers for Views and
     * start a temporary Service, if necessary. However, if you have declared a configuration Activity, this method is
     * not called when the user adds the App Widget, but is called for the subsequent updates. It is the responsibility
     * of the configuration Activity to perform the first update when configuration is done. (See Creating an App Widget
     * Configuration Activity below.)"
     */

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Paper.init(context);


        for (int appWidgetId : appWidgetIds) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);


//            String Fajr = Paper.book().read("FajorPB");
//            String Johor = Paper.book().read("JohorPB");
//            String Asor = Paper.book().read("AsorPB");
//            String Magrib = Paper.book().read("MagribPB");
//            String Esha = Paper.book().read("IshaPB");


            SharedPreferences sharedPrefMM = context.getSharedPreferences("mm",Context.MODE_PRIVATE);
            String Fajr = sharedPrefMM.getString("fw","");
            String Johor = sharedPrefMM.getString("jw","");
            String Asor = sharedPrefMM.getString("aw","");
            String Magrib = sharedPrefMM.getString("mw","");
            String Esha = sharedPrefMM.getString("ew","");


            rv.setTextViewText(R.id.idFojorW,"Fajor : "+Fajr);
            rv.setTextViewText(R.id.idJohorW,"Johor : "+Johor);
            rv.setTextViewText(R.id.idAsorW,"Asor : "+Asor );
            rv.setTextViewText(R.id.idMagribW,"Magrib : "+Magrib);
            rv.setTextViewText(R.id.idIshaW,"Esha : "+Esha);

            // Setup intent which points to the WidgetService which will provide the views for this collection.
            Intent intent = new Intent(context, WidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            // When intents are compared, the extras are ignored, so we need to embed the extras
            // into the data so that the extras will not be ignored.
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
           // rv.setRemoteAdapter(R.id.widget_list, intent);

            // Setup refresh button:
            Intent refreshIntent = new Intent(context, WidgetProvider.class);
            refreshIntent.setAction(WidgetProvider.REFRESH_WIDGET_ACTION);
            refreshIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            refreshIntent.setData(Uri.parse(refreshIntent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(context, 0, refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//            rv.setOnClickPendingIntent(R.id.refresh_button, refreshPendingIntent);

            // Here we setup the a pending intent template. Individuals items of a collection
            // cannot setup their own pending intents, instead, the collection as a whole can
            // setup a pending intent template, and the individual items can set a fillInIntent
            // to create unique before on an item to item basis.
            Intent toastIntent = new Intent(context, WidgetProvider.class);
            toastIntent.setAction(WidgetProvider.LIST_ITEM_CLICKED_ACTION);
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
           // rv.setPendingIntentTemplate(R.id.widget_list, toastPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, rv);
            appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetId, R.id.idFojorW);
        }
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);




        switch (intent.getAction()) {
            case LIST_ITEM_CLICKED_ACTION:
                String clickedFilePath = intent.getStringExtra(EXTRA_CLICKED_FILE);
                Toast toast = Toast.makeText(context, "LIST_ITEM_CLICKED_ACTION: " + clickedFilePath, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            case REFRESH_WIDGET_ACTION:
                int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
                AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetId, R.id.idFojorW);

                Toast toast2 = Toast.makeText(context, "REFRESH_WIDGET_ACTION", Toast.LENGTH_SHORT);
                toast2.setGravity(Gravity.CENTER, 0, 0);
                toast2.show();
                break;
            case ACTION_WIDGET_UPDATE:
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(context, WidgetProvider.class));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.idFojorW);
                break;
        }
    }

}
