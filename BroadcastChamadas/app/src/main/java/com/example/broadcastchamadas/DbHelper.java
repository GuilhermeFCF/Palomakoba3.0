package com.example.broadcastchamadas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "numberDb";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,  DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        try
        {
            String sql = "CREATE TABLE " + DbContract.TABLE_NAME +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.INCOMING_NUMBER+ " text);";
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB", "Tabela criada com sucesso!");
        } catch(Exception e)
        {
         Log.i("INFO DB","Erro ao criar tabela!");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String sql ="DROP TABLE IF EXISTS " + DbContract.TABLE_NAME + ";";
        try
        {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("INFO DB", "Tabela atualizada com sucesso!");
        } catch(Exception e) {
          Log.i("INFO DB", "Erro ao atualizar tabela!"+e.getMessage());
        }
    }

    public void saveNumber(String number, SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.INCOMING_NUMBER, number);
        database.insert(DbContract.TABLE_NAME, null, contentValues);
    }
    public Cursor readNumber(SQLiteDatabase database)
    {
        String [] projection = {"id",DbContract.INCOMING_NUMBER};
        Cursor res = database.query(DbContract.TABLE_NAME, projection, null,null,null,null,null);
        return res;
    }




}
