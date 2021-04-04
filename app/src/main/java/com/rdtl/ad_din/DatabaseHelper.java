package com.rdtl.ad_din;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rdtl.ad_din.activity.ReadSuraActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper
        extends SQLiteOpenHelper {


    private static String DB_PATH = "";
    private static String DB_NAME = "Quran.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private SQLiteOpenHelper sqLiteOpenHelper;




    public DatabaseHelper(Context context)
    {

        super(context, DB_NAME, null, 2);
        this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DB_NAME)
                .toString();


        if (!checkDataBase()) {
            copyDB();
        }
        myDataBase = this.getWritableDatabase(); //Get the database when instantiating

    }


    public void createDataBase()
            throws IOException
    {

//        boolean dbExist = checkDataBase();
//
//        if (dbExist) {
//
//        }
//        else {
//
//            this.getWritableDatabase();
//            try {
//                copyDataBase();
//            }
//            catch (IOException e) {
//                throw new Error(
//                        "Error copying database");
//            }
//        }
    }

//    private boolean checkDataBase()
//    {
//        SQLiteDatabase checkDB = null;
//        try {
//            String myPath = DB_PATH;
//            checkDB
//                    = SQLiteDatabase
//                    .openDatabase(
//                            myPath, null,
//                            SQLiteDatabase.OPEN_READONLY);
//        }
//        catch (SQLiteException e) {
//
//
//            Log.e("message", "" + e);
//        }
//        if (checkDB != null) {
//            checkDB.close();
//        }
//        return checkDB != null;
//    }

    private boolean checkDataBase() {
        /**
         * Does not open the database instead checks to see if the file exists
         * also creates the databases directory if it does not exists
         * (the real reason why the database is opened, which appears to result in issues)
         */
        File db = new File(myContext.getDatabasePath(DB_NAME).getPath()); //Get the file name of the database
        Log.d("DBPATH","DB Path is " + db.getPath());
        if (db.exists()) return true; // If it exists then return doing nothing

        // Get the parent (directory in which the database file would be)
        File dbdir = db.getParentFile();
        // If the directory does not exist then make the directory (and higher level directories)
        if (!dbdir.exists()) {
            db.getParentFile().mkdirs();
            dbdir.mkdirs();
        }
        return false;
    }


    public void copyDB() throws SQLiteException{
        try {
            InputStream myInput = myContext.getAssets().open(DB_NAME);
            String outputFileName = myContext.getDatabasePath(DB_NAME).getPath(); //<<<<<<<<<< changed
            Log.d("LIFECYCLE", outputFileName);
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;
            while( (length=myInput.read(buffer)) > 0 ){
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch ( IOException e) {
            e.printStackTrace();
        }
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
                        SQLiteDatabase.OPEN_READWRITE);
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



        Log.e("upp","up called");



        try {
            myContext.deleteDatabase(DB_NAME);
            copyDB();
        }
        catch(Exception e) {

        }



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