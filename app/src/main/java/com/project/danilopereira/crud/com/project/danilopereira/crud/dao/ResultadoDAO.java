package com.project.danilopereira.crud.com.project.danilopereira.crud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.danilopereira.crud.com.project.danilopereira.crud.model.Resultado;
import com.project.danilopereira.crud.com.project.danilopereira.crud.model.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilopereira on 15/03/17.
 */

public class ResultadoDAO {
    private SQLiteDatabase db;
    private DBOpenHelper banco;

    public ResultadoDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_RESULTADO = "resultado";
    public static final String COLUNA_TIME1_ID = "time1_id";
    public static final String COLUNA_TIME2_ID = "time2_id";
    public static final String COLUNA_RESULTADO_TIME1 = "resultado_time1";
    public static final String COLUNA_RESULTADO_TIME2 = "resultado_time2";

    public String add(Resultado resultado){
        long result;

        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_TIME1_ID, resultado.getTime1().getId());
        values.put(COLUNA_RESULTADO_TIME1, resultado.getResultadoTime1());
        values.put(COLUNA_TIME2_ID, resultado.getTime2().getId());
        values.put(COLUNA_RESULTADO_TIME2, resultado.getResultadoTime2());

        result = db.insert(TABELA_RESULTADO, null, values);

        if(result == -1){
            return "ERRO ao inserir registro";
        }
        else{
            return "Resultado inserido com sucesso";
        }

    }

    /*
    private List<Time> getAll(){
        List<Time> times = new ArrayList<>();

        String rawQuery = "SELECT t.*, c.nome FROM "+TABELA_TIME+" t " +
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
                time.setEscudo(cursor.getString(3));
                time.setConferencia(new Conferencia(cursor.getInt(4), cursor.getString(5)));
            }while(cursor.moveToNext());
        }

        return times;
    }
     */


    public List<Resultado> findAll() {
        List<Resultado> resultados = new ArrayList<>();

        String rawQuery = "SELECT r.*, t1.nome, t1.escudo, t2.nome, t2.escudo FROM " +TABELA_RESULTADO+
                " r INNER JOIN "+ TimeDAO.TABELA_TIME+" t1 on r."+COLUNA_TIME1_ID+" = t1."+TimeDAO.COLUNA_ID +
                " INNER JOIN "+ TimeDAO.TABELA_TIME+" t2 on r."+COLUNA_TIME2_ID+" = t2."+TimeDAO.COLUNA_ID +
                " ORDER BY "+COLUNA_TIME1_ID+" ASC";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);

        Resultado resultado = null;
        if(cursor.moveToFirst()){
            do{
                resultado = new Resultado();
                resultado.setId(cursor.getInt(0));
                resultado.setTime1(new Time(cursor.getString(5), cursor.getString(6)));
                resultado.setTime2(new Time(cursor.getString(7), cursor.getString(8)));
                resultado.setResultadoTime1(cursor.getInt(3));
                resultado.setResultadoTime2(cursor.getInt(4));

                resultados.add(resultado);

            }while (cursor.moveToNext());
        }

        return resultados;
    }

    public int delete(int id) {
        db = banco.getReadableDatabase();
        return db.delete(TABELA_RESULTADO, "id = "+id, null);
    }
}
