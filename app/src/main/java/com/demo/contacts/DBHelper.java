package com.demo.contacts;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    // À implémenter

    public DBHelper() {
        // À implémenter. Vous avez le droit de changer la signature du constructeur.
        super(null, null, null, 0);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // À implémenter
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // À implémenter
    }
}
