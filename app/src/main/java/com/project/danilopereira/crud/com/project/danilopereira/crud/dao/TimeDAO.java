package com.project.danilopereira.crud.com.project.danilopereira.crud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.danilopereira.crud.com.project.danilopereira.crud.model.Conferencia;
import com.project.danilopereira.crud.com.project.danilopereira.crud.model.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilopereira on 15/03/17.
 */

public class TimeDAO {
    private SQLiteDatabase db;
    private DBOpenHelper banco;

    public TimeDAO(Context context){
        this.banco = new DBOpenHelper(context);
    }

    public static final String  TABELA_TIME = "time";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_CONFERENCIA_ID = "conferencia_id";
    public static final String COLUNA_ESCUDO = "escudo";

    public String add(Time time){
        long resultado;
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, time.getNome());
        values.put(COLUNA_CONFERENCIA_ID, time.getConferencia().getId());
        values.put(COLUNA_ESCUDO, time.getEscudo());

        resultado = db.insert(TABELA_TIME, null, values);

        db.close();

        if(resultado == -1){
            return "ERRO ao inserir Registro.";
        }
        else{
            return "Registro Inserido";
        }
    }

    public List<Time> findByConferenciaId(int conferenciaId){
        List<Time> times = new ArrayList<>();
        db = banco.getReadableDatabase();
        String colunas[] = {COLUNA_ID, COLUNA_NOME, COLUNA_ESCUDO};
        String where = "conferencia_id = "+conferenciaId;
        Cursor cursor = db.query(true, TABELA_TIME, colunas, where, null, null, null, null, null);

        Time time = null;
        if(cursor.moveToFirst()){
            do{
                time = new Time();
                time.setId(cursor.getInt(0));
                time.setNome(cursor.getString(1));
                time.setEscudo(cursor.getString(2));
//                time.setConferencia(new Conferencia(cursor.getInt(3), cursor.getString(4)));

                times.add(time);
            }while(cursor.moveToNext());
        }

        db.close();

        return times;

    }

    public List<Time> getAll(){
        List<Time> times = new ArrayList<>();

        String rawQuery = "SELECT t.*, c.* FROM "+TABELA_TIME+" t " +
                "INNER JOIN " + ConferenciaDAO.TABELA_CONFERENCIA + " c ON t." + COLUNA_CONFERENCIA_ID +
                " = c."+ConferenciaDAO.COLUNA_ID + " ORDER BY " + COLUNA_ID + " ASC";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);

        Time time = null;
        if(cursor.moveToFirst()){
            do{
                time = new Time();
                time.setId(cursor.getInt(0));
                time.setNome(cursor.getString(1));
                time.setEscudo(cursor.getString(2));
                time.setConferencia(new Conferencia(cursor.getInt(3), cursor.getString(4)));
            }while(cursor.moveToNext());
        }

        db.close();

        return times;
    }
}
