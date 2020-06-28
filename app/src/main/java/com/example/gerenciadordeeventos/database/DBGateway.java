package com.example.gerenciadordeeventos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {

    private static DBGateway dbGateway;

    private SQLiteDatabase db;

    private DBGateway(Context context) {
        DataBaseDBHelper dataBaseDBHelper = new DataBaseDBHelper(context);
        db = dataBaseDBHelper.getWritableDatabase();
    }

    public static DBGateway getInstance(Context context) {
        if (dbGateway == null) {
            dbGateway = new DBGateway(context);
        }
        return dbGateway;
    }

    public SQLiteDatabase getDatabase() {
        return db;
    }
}
