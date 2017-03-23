package com.project.danilopereira.crud.com.project.danilopereira.crud.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by danilopereira on 15/03/17.
 */

public class Resultado implements Parcelable{

    private int id;
    private Time time1;
    private Time time2;
    private int resultadoTime1;
    private int resultadoTime2;

    public Resultado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    public int getResultadoTime1() {
        return resultadoTime1;
    }

    public void setResultadoTime1(int resultadoTime1) {
        this.resultadoTime1 = resultadoTime1;
    }

    public int getResultadoTime2() {
        return resultadoTime2;
    }

    public void setResultadoTime2(int resultadoTime2) {
        this.resultadoTime2 = resultadoTime2;
    }

    protected Resultado(Parcel in) {
    }

    public static final Creator<Resultado> CREATOR = new Creator<Resultado>() {
        @Override
        public Resultado createFromParcel(Parcel in) {
            return new Resultado(in);
        }

        @Override
        public Resultado[] newArray(int size) {
            return new Resultado[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
