package com.example.listadecontatos.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    static final int VERSION = 1;
    static final String NOME_DB = "DB_CONTATOS";
    static final String NOME_TABELA = "contatos";
    public DbHelper(@Nullable Context context) {
        super(context,NOME_DB,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqL = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomeContato TEXT NOT NULL, " +
                "email TEXT, " +
                "telefone TEXT NOT NULL);";
        try{
            sqLiteDatabase.execSQL(sqL); // Tentar executar o sql, caso consiga primeiro log
            Log.i("INFO DB", "Tabela criada com sucesso");
        } catch (Exception e){
            Log.i("INFO DB", "Erro ao criar tabela!"); // caso contrario segundo log
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + NOME_TABELA + ";";
        try {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("INFO DB", "Tabela atualizada com sucesso!");
        } catch (Exception e) {
            Log.i("INFO DB", "Não foi possível atualizar a tabela! \n" + e.getMessage());
        }
    }
}
