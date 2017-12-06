package com.wordpress.grayfaces.graybook.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wordpress.grayfaces.graybook.ultis.SQLiteHandler;

/**
 * Project GrayBook
 * Created by Gray on 12/6/2017.
 */

public class GiaoDichCung {
    private int ID;
    private String Ten, SDT;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public static void createSuKienDB(Context context){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.execSQL("CREATE TABLE QL_GiaoDichCung ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), SDT NVARCHAR(20) )");
        db.close();
    }

    public static void updateSuKienDB(Context context){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS QL_GiaoDichCung");
        db.execSQL("CREATE TABLE QL_GiaoDichCung ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), SDT NVARCHAR(20) )");
        db.close();
    }

    public static void add(Context context,String ten){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Ten", ten);

        db.insert("QL_GiaoDichCung", null, values);
        db.close();
    }

    public static void remove(Context context,GiaoDich giaoDich){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.delete("QL_GiaoDichCung", "ID = ?",
                new String[] { String.valueOf(giaoDich.getID()) });
        db.close();
    }

    public static boolean isItExists(Context context, String ten){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        String selectQuery = "SELECT  * FROM QL_GiaoDichCung WHERE Ten=?" ;

        Cursor cursor = db.rawQuery(selectQuery,new String[]{ten});
        int count = cursor.getCount();
        cursor.close();
        return count>0;
    }

    public static void addIfItExists(Context context, String ten){
        if (isItExists(context,ten)){
            add(context,ten);
        }
    }
}
