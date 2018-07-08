package br.com.syszona.syszonazonaazulclienteapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_syszona_cliente" ;
    public static final String T_CREDIT_CARD = "credit_card" ;
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_CARD_CREDIT = "CREATE TABLE "+T_CREDIT_CARD+" " +
            "(id INTEGER PRIMARY KEY, name TEXT, month TEXT, year TEXT," +
            " cvv TEXT, parcels INTEGER, error TEXT, token TEXT, cardNumber TEXT)" ;

    public DbHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CARD_CREDIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+T_CREDIT_CARD);
        onCreate(db);
    }

}
