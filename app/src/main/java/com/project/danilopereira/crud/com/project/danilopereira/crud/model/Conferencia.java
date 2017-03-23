package com.project.danilopereira.crud.com.project.danilopereira.crud.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by danilopereira on 15/03/17.
 */

public class Conferencia implements Parcelable {

    public Conferencia() {
    }

    public Conferencia(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    private int id;
    private String nome;

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

    protected Conferencia(Parcel in) {
    }

    public static final Creator<Conferencia> CREATOR = new Creator<Conferencia>() {
        @Override
        public Conferencia createFromParcel(Parcel in) {
            return new Conferencia(in);
        }

        @Override
        public Conferencia[] newArray(int size) {
            return new Conferencia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
