package com.example.yuayuayu.money;

import android.provider.BaseColumns;

/**
 * Created by yuayuayu on 2018/1/2.
 */

public class UserContract {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "User.db";
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = " ,";

        private UserContract() {}

        public static class UserTable implements BaseColumns {
            public static final String TABLE_NAME = "User";
            public static final String COLUMN_NAME_User_name = "user";
            public static final String COLUMN_NAME_User_Password = "password";
            public static final String SQL_CREATE_TABLE = "CREATE TABLE "
                    +UserTable.TABLE_NAME+"("+UserTable._ID
                    +" INTEGER PRIMARY KEY AUTOINCREMENT"+COMMA_SEP
                    + UserTable.COLUMN_NAME_User_name +TEXT_TYPE+COMMA_SEP+
                    UserTable.COLUMN_NAME_User_Password+TEXT_TYPE+")";
            public static final String SQL_DELETE_ENTRIES =
                    "DROP TABLE IF EXISTS " + UserTable.TABLE_NAME;
        }


}
