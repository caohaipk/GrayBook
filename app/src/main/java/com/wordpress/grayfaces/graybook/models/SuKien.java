package com.wordpress.grayfaces.graybook.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wordpress.grayfaces.graybook.ultis.SQLiteHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Project GrayBook
 * Created by Gray on 12/6/2017.
 */

public class SuKien {
    private int ID;
    private String Ten;
    private long NgayKetThuc;
    private int Vi;

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

    public long getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(long ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public int getVi() {
        return Vi;
    }

    public void setVi(int vi) {
        Vi = vi;
    }

    public static void createDB(Context context){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.execSQL("CREATE TABLE QL_SuKien ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), NgayKetThuc INTEGER, Vi INTEGER )");
        db.close();
    }

    public static void updateDB(Context context){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS QL_SuKien");
        db.execSQL("CREATE TABLE QL_SuKien ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), NgayKetThuc INTEGER, Vi INTEGER )");
        db.close();
    }

    public static void add(Context context,SuKien suKien){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Ten", suKien.getTen());
        values.put("NgayKetThuc", suKien.getNgayKetThuc());
        values.put("Vi", suKien.getVi());

        db.insert("QL_SuKien", null, values);
        db.close();
    }

    public static int update(Context context,SuKien suKien){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Ten", suKien.getTen());
        values.put("NgayKetThuc", suKien.getNgayKetThuc());
        values.put("Vi", suKien.getVi());

        return db.update("QL_SuKien", values, "ID = ?",
                new String[] { String.valueOf(suKien.getID()) });
    }

    public static void remove(Context context,SuKien suKien){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.delete("QL_SuKien", "ID = ?",
                new String[] { String.valueOf(suKien.getID()) });
        db.close();
    }

    public static SuKien get(Context context, int idSuKien){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        SuKien suKien = new SuKien();

        String selectQuery = "SELECT  * FROM QL_SuKien WHERE ID=?" ;

        Cursor cursor = db.rawQuery(selectQuery,new String[]{ String.valueOf(idSuKien) });
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            suKien.setID(cursor.getInt(0));
            suKien.setTen(cursor.getString(1));
            suKien.setNgayKetThuc(cursor.getLong(2));
            suKien.setVi(cursor.getInt(3));
        }
        cursor.close();
        db.close();

        return suKien;
    }

    public static List<SuKien> getUnfinished(Context context, long today){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        List<SuKien> suKiens = new ArrayList<>();

        String selectQuery = "SELECT  * FROM QL_SuKien WHERE NgayKetThuc>=?" ;

        Cursor cursor = db.rawQuery(selectQuery,new String[]{ String.valueOf(today) });

        if (cursor.moveToFirst()) {
            do {
                SuKien suKien = new SuKien();
                suKien.setID(cursor.getInt(0));
                suKien.setTen(cursor.getString(1));
                suKien.setNgayKetThuc(cursor.getLong(2));
                suKien.setVi(cursor.getInt(3));
                suKiens.add(suKien);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return suKiens;
    }

    public static List<SuKien> getFinished(Context context, long today){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        List<SuKien> suKiens = new ArrayList<>();

        String selectQuery = "SELECT  * FROM QL_SuKien WHERE NgayKetThuc<?" ;

        Cursor cursor = db.rawQuery(selectQuery,new String[]{ String.valueOf(today) });

        if (cursor.moveToFirst()) {
            do {
                SuKien suKien = new SuKien();
                suKien.setID(cursor.getInt(0));
                suKien.setTen(cursor.getString(1));
                suKien.setNgayKetThuc(cursor.getLong(2));
                suKien.setVi(cursor.getInt(3));
                suKiens.add(suKien);
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return suKiens;
    }
}
