package com.example.garbagesortingapp;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class MyDatebaseHelper2 extends SQLiteOpenHelper {

    public static  final String CREAT_GARBAGE_SORTING_BOOK = "create table garbage_book ("
            + "id integer primary key autoincrement, "
            + "garbage_name text,  "
            + "recycle_time integer, "
            + "recycleplace text, "
            + "recyclesort text, "
            + "ps text )";

    private Context mContext;

    public MyDatebaseHelper2(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_GARBAGE_SORTING_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
