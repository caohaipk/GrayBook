package com.wordpress.grayfaces.graybook.ultis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wordpress.grayfaces.graybook.models.GiaoDich;


/**
 * Project LamHongDMS
 * Created by pcquy on 3/25/2017.
 */

public class SQLiteHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "EBusiness_LamHong";
    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE QL_GiaoDich ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Nhom INTEGER, GhiChu NVARCHAR(100), Ngay INTEGER, GiaoDichCung INTEGER, Lat INTEGER, Long INTEGER, SuKien INTEGER, NhacNho INTEGER, TinhVaoBaoCao INTEGER, Vi INTEGER, GiaTri INTEGER )");
        db.execSQL("CREATE TABLE QL_NhomGiaoDich ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), Icon NVARCHAR(100) )");
        db.execSQL("CREATE TABLE QL_GiaoDichCung ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), SDT NVARCHAR(20) )");
        db.execSQL("CREATE TABLE QL_SuKien ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), NgayKetThuc INTEGER, Vi INTEGER )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS QL_GiaoDich");
        onCreate(db);
    }
    public void addConfig(GiaoDich giaoDich) {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete("QL_GiaoDich", null, null);
        ContentValues values = new ContentValues();
        values.put("NhomGiaoDich", giaoDich.getNhom());
        values.put("GhiChu", giaoDich.getGhiChu());
        values.put("Ngay", giaoDich.getNgay());
        values.put("GiaoDichCung", giaoDich.getGiaoDichCung());
        values.put("Lat", giaoDich.getLat());
        values.put("Long", giaoDich.getLong());
        values.put("SuKien", giaoDich.getSuKien());
        values.put("NhacNho", giaoDich.getNhacNho());
        values.put("TinhVaoBaoCao", giaoDich.isTinhVaoBaoCao()?1:0);
        values.put("Vi", giaoDich.getVi());
        db.insert("QL_GiaoDich", null, values);
        db.close();
    }
    public int updateConfig(GiaoDich giaoDich) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NhomGiaoDich", giaoDich.getNhom());
        values.put("GhiChu", giaoDich.getGhiChu());
        values.put("Ngay", giaoDich.getNgay());
        values.put("GiaoDichCung", giaoDich.getGiaoDichCung());
        values.put("Lat", giaoDich.getLat());
        values.put("Long", giaoDich.getLong());
        values.put("SuKien", giaoDich.getSuKien());
        values.put("NhacNho", giaoDich.getNhacNho());
        values.put("TinhVaoBaoCao", giaoDich.isTinhVaoBaoCao()?1:0);
        values.put("Vi", giaoDich.getVi());
        return db.update("QL_GiaoDich", values, "ID = ?",
                new String[] { String.valueOf(giaoDich.getID()) });
    }
    public GiaoDich getGiaoDichChiTiet() {
        GiaoDich giaoDich=new GiaoDich();
        String selectQuery = "SELECT  * FROM QL_GiaoDich" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            giaoDich.setID(cursor.getInt(0));
            giaoDich.setNhom(cursor.getInt(1));
            giaoDich.setGhiChu(cursor.getString(2));
            giaoDich.setNgay(cursor.getLong(3));
            giaoDich.setGiaoDichCung(cursor.getInt(6));
            giaoDich.setLat(cursor.getInt(7));
            giaoDich.setLong(cursor.getInt(8));
            giaoDich.setSuKien(cursor.getInt(9));
            giaoDich.setTinhVaoBaoCao(cursor.getInt(10)==1);
        }
        cursor.close();
        db.close();
        return giaoDich;
    }
    public void deleteGiaoDich(int idGiaoDich){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("QL_GiaoDich", "ID = ?",
                new String[] { String.valueOf(idGiaoDich) });
        db.close();
    }

}