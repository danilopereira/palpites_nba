package com.project.danilopereira.crud.com.project.danilopereira.crud.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by danilopereira on 15/03/17.
 */

public class Time implements Parcelable {

    private int id;
    private Conferencia conferencia;
    private String nome;
    private String escudo;

    public Time() {
    }

    public Time(String nome, String escudo) {
        this.nome = nome;
        this.escudo = escudo;
    }

    protected Time(Parcel in) {
        id = in.readInt();
        conferencia = in.readParcelable(Conferencia.class.getClassLoader());
        nome = in.readString();
        escudo = in.readString();
    }

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Conferencia getConferencia() {
        return conferencia;
    }

    public void setConferencia(Conferencia conferencia) {
        this.conferencia = conferencia;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeValue(conferencia);
        dest.writeString(nome);
        dest.writeString(escudo);
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
