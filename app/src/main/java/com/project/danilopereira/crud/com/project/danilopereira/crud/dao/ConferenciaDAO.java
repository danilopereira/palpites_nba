package com.project.danilopereira.crud.com.project.danilopereira.crud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.danilopereira.crud.com.project.danilopereira.crud.model.Conferencia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilopereira on 15/03/17.
 */

public class ConferenciaDAO {
    private SQLiteDatabase db;
    private DBOpenHelper banco;

    public ConferenciaDAO(Context context){
        this.banco = new DBOpenHelper(context);
    }

    public static final String TABELA_CONFERENCIA = "conferencia";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";

    public String add(Conferencia conferencia){
        long resultado;

        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, conferencia.getNome());

        resultado = db.insert(TABELA_CONFERENCIA, null, values);

        if(resultado == -1){
            return "ERRO ao inserir registro";
        }
        else{
            return "Conferencia Inserida com Sucesso";
        }
    }

    public List<Conferencia> findAll(){
        List<Conferencia> conferencias = new ArrayList<>();

        String rawQuery = "SELECT * FROM " +TABELA_CONFERENCIA;
        db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery(rawQuery, null);

        Conferencia conferencia = null;
        if(cursor.moveToFirst()){
            do{
                conferencia = new Conferencia();
                conferencia.setId(cursor.getInt(0));
                conferencia.setNome(cursor.getString(1));

                conferencias.add(conferencia);
            }while (cursor.moveToNext());
        }

        db.close();

        return conferencias;
    }

    public Conferencia findById(int id){
        db = banco.getReadableDatabase();
        String colunas[] = {COLUNA_ID, COLUNA_NOME};
        String where = "id = "+id;
        Cursor cursor = db.query(true, TABELA_CONFERENCIA, colunas, where, null, null, null, null, null);

        Conferencia conferencia = null;

        if(cursor != null){
            cursor.moveToFirst();
            conferencia = new Conferencia();
            conferencia.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
            conferencia.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
        }
        return conferencia;
    }

}
