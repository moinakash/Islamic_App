package com.example.islamicappb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class MyDatabasehelper extends SQLiteOpenHelper {

    //////////////////////////////////////////////////////////////////////
//    String DB_PATH = null;
//    private static String DB_NAME = "Students";

    private SQLiteDatabase myDataBase;


    private static final String DATABASE_NAME = "LocalDatabase.db";
    private static final String TABLE_NAME = "bookmark_details";
    private static final String ID = "_id";
    private static final String SURANAME = "SuraName";
    private static final String AYATNUMBER = "AyatNumber";
    private static final String ARBIAYAT = "ArbiAyat";
    private static final String SPELLING = "Spelling";
    private static final String MEANING = "Meaning";
    private static final int VERSION_NUMBER = 2
            ;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ SURANAME +" VERCHCHAR(255), "+ AYATNUMBER +" VARCHAR(15), "+ ARBIAYAT+" VARCHAR(15), "+ SPELLING +" VARCHAR(255), "+ MEANING+" VARCHAR(255) ); ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM "+TABLE_NAME;

    private Context context;

    public MyDatabasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 10);
        this.context = context;

    }






//    @Override
//    public void onCreate(SQLiteDatabase db){
//
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);

        }catch (Exception e){

        }

        

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{

            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch (Exception e){

        }



    }

    public long insertData(String SuraName, String AyatNumbe, String ArbiAyat, String Spelling, String Meaning)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURANAME,SuraName);
        contentValues.put(AYATNUMBER,AyatNumbe);
        contentValues.put(ARBIAYAT,ArbiAyat);
        contentValues.put(SPELLING,Spelling);
        contentValues.put(MEANING,Meaning);

        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        return rowId;
    }

    public Cursor displayAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL,null);
        return cursor;
    }

    public boolean updateData(String id, String name, String age, String gender)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(SURANAME,name);
        contentValues.put(AYATNUMBER,age);
        contentValues.put(ARBIAYAT,gender);

         sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ?",new String[]{id});
        return true;
    }

    public int deleteData(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,ARBIAYAT+" = ?",new String[]{ id });
    }

    public Cursor showAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }


    public Boolean findThis(String Ayat)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Boolean result = false;

        if (cursor.getCount()==0){

        }else {
            while (cursor.moveToNext()){
                String ayat = cursor.getString(3);

                if (ayat.equals(Ayat)){
                    result = true;
                    break;
                }
            }
        }

        return result;
    }


}
