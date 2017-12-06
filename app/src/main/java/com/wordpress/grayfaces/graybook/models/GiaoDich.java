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
 * Created by Gray on 11/25/2017.
 */

public class GiaoDich {
    private int ID,Nhom,GiaoDichCung,SuKien;
    private String GhiChu,NhacNho;
    private long Ngay,Lat,Long;
    private boolean isTinhVaoBaoCao;
    private int Vi;
    private long GiaTri;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNhom() {
        return Nhom;
    }

    public void setNhom(int nhom) {
        Nhom = nhom;
    }

    public int getGiaoDichCung() {
        return GiaoDichCung;
    }

    public void setGiaoDichCung(int giaoDichCung) {
        GiaoDichCung = giaoDichCung;
    }

    public int getSuKien() {
        return SuKien;
    }

    public void setSuKien(int suKien) {
        SuKien = suKien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public long getNgay() {
        return Ngay;
    }

    public void setNgay(long ngay) {
        Ngay = ngay;
    }

    public String getNhacNho() {
        return NhacNho;
    }

    public void setNhacNho(String nhacNho) {
        NhacNho = nhacNho;
    }

    public long getLat() {
        return Lat;
    }

    public void setLat(long lat) {
        Lat = lat;
    }

    public long getLong() {
        return Long;
    }

    public void setLong(long aLong) {
        Long = aLong;
    }

    public boolean isTinhVaoBaoCao() {
        return isTinhVaoBaoCao;
    }

    public void setTinhVaoBaoCao(boolean tinhVaoBaoCao) {
        isTinhVaoBaoCao = tinhVaoBaoCao;
    }

    public int getVi() {
        return Vi;
    }

    public void setVi(int vi) {
        Vi = vi;
    }

    public long getGiaTri() {
        return GiaTri;
    }

    public void setGiaTri(long giaTri) {
        GiaTri = giaTri;
    }

    public static void createDB(Context context){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.execSQL("CREATE TABLE QL_SuKien ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), NgayKetThuc NVARCHAR(10), Vi INTEGER, GiaTri INTEGER )");
        db.close();
    }

    public static void updateDB(Context context){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS QL_GiaoDich");
        db.execSQL("CREATE TABLE QL_SuKien ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(100), NgayKetThuc NVARCHAR(10), Vi INTEGER, GiaTri INTEGER )");
        db.close();
    }

    public static void add(Context context,GiaoDich giaoDich){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

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

    public static int update(Context context,GiaoDich giaoDich){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

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

    public static void remove(Context context,GiaoDich giaoDich){
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.delete("QL_GiaoDich", "ID = ?",
                new String[] { String.valueOf(giaoDich.getID()) });
        db.close();
    }

    public static GiaoDich get(Context context, int idGiaoDich) {
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        GiaoDich giaoDich=new GiaoDich();

        String selectQuery = "SELECT  * FROM QL_GiaoDich WHERE ID=?" ;

        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(idGiaoDich)});
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

    public static List<GiaoDich> getInDate(Context context, long ngay) {
        SQLiteHandler handler = new SQLiteHandler(context);
        SQLiteDatabase db = handler.getWritableDatabase();

        List<GiaoDich> giaoDiches = new ArrayList<>();

        String selectQuery = "SELECT  * FROM QL_GiaoDich WHERE Ngay=?" ;

        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(ngay)});
        if (cursor.moveToFirst()) {
            GiaoDich giaoDich=new GiaoDich();
            giaoDich.setID(cursor.getInt(0));
            giaoDich.setNhom(cursor.getInt(1));
            giaoDich.setGhiChu(cursor.getString(2));
            giaoDich.setNgay(cursor.getLong(3));
            //giaoDich.setGiaoDichCung(cursor.getInt(6));
            //giaoDich.setLat(cursor.getInt(7));
            //giaoDich.setLong(cursor.getInt(8));
            //giaoDich.setSuKien(cursor.getInt(9));
            //giaoDich.setTinhVaoBaoCao(cursor.getInt(10)==1);
            giaoDich.setGiaTri(cursor.getLong(11));
            giaoDiches.add(giaoDich);
        }
        cursor.close();
        db.close();
        return giaoDiches;
    }

}
