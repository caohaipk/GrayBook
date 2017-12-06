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

public class Vi {
    private int ID;
    private String Ten;
    private long SoTien;

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

    public long getSoTien() {
        return SoTien;
    }

    public void setSoTien(long soTien) {
        SoTien = soTien;
    }

    public static void createDB(Context context){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.execSQL("CREATE TABLE QL_Vi ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), SoTien INTEGER )");
        db.close();
    }

    public static void updateDB(Context context){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS QL_Vi");
        db.execSQL("CREATE TABLE QL_Vi ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), SoTien INTEGER )");
        db.close();
    }

    public static void add(Context context,Vi vi){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Ten", vi.getTen());
        values.put("SoTien", vi.getSoTien());

        db.insert("QL_Vi", null, values);
        db.close();
    }

    public static int update(Context context,Vi vi){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Ten", vi.getTen());
        values.put("SoTien", vi.getSoTien());

        return db.update("QL_Vi", values, "ID = ?",
                new String[] { String.valueOf(vi.getID()) });
    }

    public static void remove(Context context,Vi vi){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.delete("QL_Vi", "ID = ?",
                new String[] { String.valueOf(vi.getID()) });
        db.close();
    }

    public static Vi get(Context context, int id){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        Vi vi = new Vi();

        String selectQuery = "SELECT  * FROM QL_Vi WHERE ID=?" ;

        Cursor cursor = db.rawQuery(selectQuery,new String[]{ String.valueOf(id) });
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            vi.setID(cursor.getInt(0));
            vi.setTen(cursor.getString(1));
            vi.setSoTien(cursor.getLong(2));
        }
        cursor.close();
        db.close();

        return vi;
    }
}
