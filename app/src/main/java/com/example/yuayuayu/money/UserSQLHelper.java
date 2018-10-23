package com.example.yuayuayu.money;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yuayuayu on 2018/1/2.
 */

public class UserSQLHelper extends SQLiteOpenHelper {
    public UserSQLHelper(Context context) {
        super(context, UserContract.DATABASE_NAME, null, UserContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UserContract.UserTable.SQL_CREATE_TABLE);
    }
    public void insertDatabase(String _name,String _password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.UserTable.COLUMN_NAME_User_name,_name);
        values.put(UserContract.UserTable.COLUMN_NAME_User_Password,_password);
        db.insert(UserContract.UserTable.TABLE_NAME, null, values);
        db.close();
    }
    public String searchDatabase(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                UserContract.UserTable._ID,
                UserContract.UserTable.COLUMN_NAME_User_name,
                UserContract.UserTable.COLUMN_NAME_User_Password,
        };
        String sortOrder =
                UserContract.UserTable._ID+" DESC";
        Cursor cursor = db.query(
                UserContract.UserTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
//    String q="select user, password from "+UserContract.UserTable.TABLE_NAME;
//    Cursor cursor =db.rawQuery(q,null);
        String a,b;
        b="密码不正确";
        if(cursor.moveToFirst())
        {
            do{ a=cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserTable.COLUMN_NAME_User_name));
                if(a.equals(name))
                {
                    b=cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserTable.COLUMN_NAME_User_Password));
                    break;
                }

            }
            while (cursor.moveToNext());
        }
        return b;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO
        db.execSQL(UserContract.UserTable.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
