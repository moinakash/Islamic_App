package com.example.islamicappb;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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


    public static final String
            ALGO_TOPICS
            = "algo_topics";


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


    public List<String> getAlgorithmTopics(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getWritableDatabase();

        List<String> list
                = new ArrayList<>();

        // query help us to return all data
        // the present in the ALGO_TOPICS table.
       // String query = "SELECT * FROM " + "quran_verses" ;

        String query = "SELECT * FROM sura ";



        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                //list.add(cursor.getString(6)+"\n"+cursor.getString(8));
                list.add(cursor.getString(1));
               // list.add(cursor.getString(8));
            } while (cursor.moveToNext());
        }
        return list;
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
        String FullSura = "SELECT * FROM sura ";

        ReadSuraActivity readSuraActivity = new ReadSuraActivity();




        int valuee = readSuraActivity.getCategory();

        Log.e("Johor",""+valuee);

        //Toast.makeText(myContext, "check "+valuee, Toast.LENGTH_SHORT).show();

        String query = "SELECT * FROM quran_verses where sura_id ="+valuee;

        Cursor FullSuraCursor = db.rawQuery(query,null);
        return FullSuraCursor;
    }


    public Cursor ShowAllahName()
    {
        SQLiteDatabase db = this.getWritableDatabase();
       // String FullSura = "SELECT * FROM allah_names ";

//        ReadSuraActivity readSuraActivity = new ReadSuraActivity();
//
//        int valuee = readSuraActivity.getCategory();
//        //Toast.makeText(myContext, "check "+valuee, Toast.LENGTH_SHORT).show();

        String namequery = "SELECT * FROM allah_names ";

        Cursor FullSuraCursor = db.rawQuery(namequery,null);
        return FullSuraCursor;
    }


    public Cursor showDiv(){
        SQLiteDatabase db = this.getWritableDatabase();
        String FullSura = "SELECT * FROM sura ";

        String query = "SELECT * FROM divisions ";

        Cursor FullSuraCursor = db.rawQuery(query,null);
        return FullSuraCursor;
    }

    public Cursor showDhaka(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM districts where division_id = 3";

        Cursor FullSuraCursor = db.rawQuery(query,null);
        return FullSuraCursor;
    }

    public Cursor showChit(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM districts where division_id = 2";

        Cursor FullSuraCursor = db.rawQuery(query,null);
        return FullSuraCursor;
    }

    public Cursor showBari(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM districts where division_id = 1";

        Cursor FullSuraCursor = db.rawQuery(query,null);
        return FullSuraCursor;
    }

    public Cursor shoeKhul(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM districts where division_id = 4";

        Cursor FullSuraCursor = db.rawQuery(query,null);
        return FullSuraCursor;
    }

    public Cursor showRaj(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM districts where division_id = 5";

        Cursor FullSuraCursor = db.rawQuery(query,null);
        return FullSuraCursor;
    }

    public Cursor showRong(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM districts where division_id = 6";

        Cursor FullSuraCursor = db.rawQuery(query,null);
        return FullSuraCursor;
    }

    public Cursor showSy(int a){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM districts where division_id = "+a;

        Cursor FullSuraCursor = db.rawQuery(query,null);
        return FullSuraCursor;
    }



}