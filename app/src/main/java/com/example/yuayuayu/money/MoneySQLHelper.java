package com.example.yuayuayu.money;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DisplayContext;

/**
 * Created by yuayuayu on 2018/1/3.
 */

public class MoneySQLHelper extends SQLiteOpenHelper{
    public MoneySQLHelper(Context context) {
        super(context, MoneyContract.DATABASE_NAME, null, MoneyContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(MoneyContract.MoneyTable.SQL_CREATE_TABLE);
    }
    public void insertDatabase(String _name,String type,String m,String remark,String yy,String mm,String dd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MoneyContract.MoneyTable.COLUMN_NAME_User,_name);
        values.put(MoneyContract.MoneyTable.COLUMN_NAME_Type, type);
        values.put(MoneyContract.MoneyTable.COLUMN_NAME_Amount,m);
        values.put(MoneyContract.MoneyTable.COLUMN_NAME_Remark,remark);
        values.put(MoneyContract.MoneyTable.COLUMN_NAME_Year,yy);
        values.put(MoneyContract.MoneyTable.COLUMN_NAME_Month,mm);
        values.put(MoneyContract.MoneyTable.COLUMN_NAME_Day,dd);
        db.insert(MoneyContract.MoneyTable.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO
        db.execSQL(MoneyContract.MoneyTable.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
