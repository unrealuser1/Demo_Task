package com.unreal.demotask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "uDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usertable(User_ID INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100), number VARCHAR(10), password VARCHAR(16), isActive INTEGER, DeletedOn DATETIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS usertable");
    }

    public boolean createData(String name, String number, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("number", number);
        cv.put("password", password);
        long res = db.insert("usertable", null, cv);
        if (res==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usertable", null);
        return cursor;
    }

    public boolean checkNumber(String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usertable where number=?", new String[]{number});
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkNumberAndPassword(String number, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usertable where number=? and password=?", new String[]{number, password});
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

//    public Boolean deleteData(String name) {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("SELECT * FROM usertable where name = ?", new String[]{name});
//        if(cursor.getCount()>0) {
//            long result = DB.delete("Userdetails", "name=?", n    ew String[]{name});
//            if(result==-1) { return  false; }
//            else { return true; }
//        }
//        else { return false; }
//
//    }
}
