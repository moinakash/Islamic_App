package com.example.islamicappb;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.islamicappb.activity.ReadSuraActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper
        extends SQLiteOpenHelper {


    private static String DB_PATH = "";
    private static String DB_NAME = "Quran.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private SQLiteOpenHelper sqLiteOpenHelper;




    public DatabaseHelper(Context context)
    {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DB_NAME)
                .toString();
    }


    public void createDataBase()
            throws IOException
    {

        boolean dbExist = checkDataBase();

        if (dbExist) {

        }
        else {

            this.getWritableDatabase();
            try {
                copyDataBase();
            }
            catch (IOException e) {
                throw new Error(
                        "Error copying database");
            }
        }
    }

    private boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            checkDB
                    = SQLiteDatabase
                    .openDatabase(
                            myPath, null,
                            SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e) {


            Log.e("message", "" + e);
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }


    private void copyDataBase()
            throws IOException
    {

        InputStream myInput
                = myContext.getAssets()
                .open(DB_NAME);


        String outFileName = DB_PATH;


        OutputStream myOutput
                = new FileOutputStream(outFileName);


        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase()
            throws SQLException
    {

        String myPath = DB_PATH;
        myDataBase = SQLiteDatabase
                .openDatabase(
                        myPath, null,
                        SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {

        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion)
    {

    }




    public Cursor showAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String SuraQuery = "SELECT * FROM sura ";
        Cursor SuraNameCursor = db.rawQuery(SuraQuery,null);
        return SuraNameCursor;
    }

    public Cursor ShowSura()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ReadSuraActivity readSuraActivity = new ReadSuraActivity();


        int valuee = readSuraActivity.getCategory();


        String query = "SELECT * FROM quran_verses where sura_id ="+valuee;

        Cursor FullSuraCursor = db.rawQuery(query,null);
        return FullSuraCursor;
    }


    public Cursor ShowAllahName()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String namequery = "SELECT * FROM allah_names ";

        Cursor FullSuraCursor = db.rawQuery(namequery,null);
        return FullSuraCursor;
    }


    public Cursor HadisData(){

        SQLiteDatabase db = this.getWritableDatabase();
        String HQuery = "SELECT * FROM hadistype ";
        Cursor HadisName = db.rawQuery(HQuery,null);
        return HadisName;
    }

    public Cursor Hadis(int k){

        SQLiteDatabase db = this.getWritableDatabase();
        String HQuery = "SELECT * FROM hadis where unic_id= "+k;
        Cursor Hadis = db.rawQuery(HQuery,null);
        return Hadis;
    }



    public Cursor amoldata(){

        SQLiteDatabase db = this.getWritableDatabase();
        String HQuery = "SELECT * FROM amol ";
        Cursor HadisName = db.rawQuery(HQuery,null);
        return HadisName;
    }

}