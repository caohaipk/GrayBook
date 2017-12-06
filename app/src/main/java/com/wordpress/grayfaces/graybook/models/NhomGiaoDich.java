package com.wordpress.grayfaces.graybook.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wordpress.grayfaces.graybook.ultis.SQLiteHandler;

/**
 * Project GrayBook
 * Created by Gray on 12/2/2017.
 */

public class NhomGiaoDich {
    private int ID;
    private String Ten,Icon;

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

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public static void createDB(Context context){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.execSQL("CREATE TABLE QL_NhomGiaoDich ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), Icon NVARCHAR(100) )");
        db.close();
    }

    public static void updateDB(Context context){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS QL_NhomGiaoDich");
        db.execSQL("CREATE TABLE QL_NhomGiaoDich ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), Icon NVARCHAR(100) )");
        db.close();
    }

    public static void add(Context context,NhomGiaoDich nhomGiaoDich){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Ten", nhomGiaoDich.getTen());
        values.put("Icon", nhomGiaoDich.getIcon());

        db.insert("QL_NhomGiaoDich", null, values);
        db.close();
    }

    public static int update(Context context,NhomGiaoDich nhomGiaoDich){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Ten", nhomGiaoDich.getTen());
        values.put("Icon", nhomGiaoDich.getIcon());

        return db.update("QL_NhomGiaoDich", values, "ID = ?",
                new String[] { String.valueOf(nhomGiaoDich.getID()) });
    }

    public static void remove(Context context,NhomGiaoDich nhomGiaoDich){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.delete("QL_NhomGiaoDich", "ID = ?",
                new String[] { String.valueOf(nhomGiaoDich.getID()) });
        db.close();
    }

    public static NhomGiaoDich get(Context context, int id){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        NhomGiaoDich nhomGiaoDich = new NhomGiaoDich();

        String selectQuery = "SELECT  * FROM QL_NhomGiaoDich WHERE ID=?" ;

        Cursor cursor = db.rawQuery(selectQuery,new String[]{ String.valueOf(id) });
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            nhomGiaoDich.setID(cursor.getInt(0));
            nhomGiaoDich.setTen(cursor.getString(1));
            nhomGiaoDich.setIcon(cursor.getString(2));
        }
        cursor.close();
        db.close();

        return nhomGiaoDich;
    }
}
