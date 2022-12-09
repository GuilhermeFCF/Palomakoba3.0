package com.example.listadecontatos.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.listadecontatos.model.Contato;

import java.util.*;

public class ContatoDAO implements IContatoDAO{
    private SQLiteDatabase leitura;
    private SQLiteDatabase escrita;
    public ContatoDAO(Context context)
    {
        DbHelper dbHelper = new DbHelper(context);
        this.leitura = dbHelper.getReadableDatabase();
        this.escrita = dbHelper.getWritableDatabase();
    }
    @Override
    public boolean salvar(Contato contato)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomeContato", contato.getNome());
        contentValues.put("email",contato.getEmail());
        contentValues.put("telefone", contato.getTelefone());
        this.escrita.insert(DbHelper.NOME_TABELA, null,contentValues);
        return true;
    }

    @Override
    public boolean atualizar(Contato contato)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomeContato",contato.getNome());
        contentValues.put("telefone", contato.getTelefone());
        contentValues.put("email", contato.getEmail());
        String [] args = {String.valueOf(contato.getId())};
        this.escrita.update(DbHelper.NOME_TABELA, contentValues, "id=?", args); //estrutural, o que pode mudar é o argumento "id"
        return true;
    }

    @Override
    public boolean deletar(Contato contato)
    {
        String [] args = {String.valueOf(contato.getId())};
        this.escrita.delete(DbHelper.NOME_TABELA, "id=?", args);
        return true;
    }
    @SuppressLint("Range")
    @Override
    public List<Contato> lista() {
        List<Contato> contatos = new ArrayList<Contato>();
        String sql = "SELECT * FROM " + DbHelper.NOME_TABELA + ";";
        Cursor cursor = leitura.rawQuery(sql,null);
        while(cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            Contato contato = new Contato(nome,email,telefone,id);
            contatos.add(contato);

        }
        return contatos;
    }
}
