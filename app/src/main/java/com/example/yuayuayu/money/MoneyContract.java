package com.example.yuayuayu.money;

import android.provider.BaseColumns;

/**
 * Created by yuayuayu on 2018/1/3.
 */

public class MoneyContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Money.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = " ,";

    private MoneyContract() {}

    public static class MoneyTable implements BaseColumns {
        public static final String TABLE_NAME = "Money";
        public static final String COLUMN_NAME_User= "yonghuming";
        public static final String COLUMN_NAME_Type="Type";
        public static final String COLUMN_NAME_Amount="Amount";
        public static final String COLUMN_NAME_Remark="Remark";
        public static final String COLUMN_NAME_Year="Year";
        public static final String COLUMN_NAME_Month="Month";
        public static final String COLUMN_NAME_Day="Day";
        public static final String SQL_CREATE_TABLE = "CREATE TABLE "
                + MoneyTable.TABLE_NAME + " (" +
                MoneyTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                MoneyTable.COLUMN_NAME_User+ TEXT_TYPE + COMMA_SEP +
                MoneyTable.COLUMN_NAME_Type + TEXT_TYPE + COMMA_SEP +
                MoneyTable.COLUMN_NAME_Amount+ TEXT_TYPE + COMMA_SEP +
                MoneyTable.COLUMN_NAME_Remark+ TEXT_TYPE + COMMA_SEP +
                MoneyTable.COLUMN_NAME_Year+ TEXT_TYPE + COMMA_SEP +
                MoneyTable.COLUMN_NAME_Month+ TEXT_TYPE + COMMA_SEP +
                MoneyTable.COLUMN_NAME_Day + TEXT_TYPE + " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + MoneyContract.MoneyTable.TABLE_NAME;
    }

}
