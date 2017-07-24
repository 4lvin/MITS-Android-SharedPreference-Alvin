package com.example.alpin.sharedpreferences.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alpin on 23/07/17.
 */

public class Doa implements Parcelable {
    String nama,doa,ket,imageAddrees;
    int id;

    public Doa() {
    }

    public Doa(String nama, String doa, String ket, String imageAddrees) {
        this.nama = nama;
        this.doa = doa;
        this.ket = ket;
        this.imageAddrees = imageAddrees;
    }

    public Doa(int id,String nama, String doa, String ket, String imageAddrees ) {
        this.id = id;
        this.nama = nama;
        this.doa = doa;
        this.ket = ket;
        this.imageAddrees = imageAddrees;

    }

    protected Doa(Parcel in) {
        nama = in.readString();
        doa = in.readString();
        ket = in.readString();
        imageAddrees = in.readString();
        id = in.readInt();
    }

    public static final Creator<Doa> CREATOR = new Creator<Doa>() {
        @Override
        public Doa createFromParcel(Parcel in) {
            return new Doa(in);
        }

        @Override
        public Doa[] newArray(int size) {
            return new Doa[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDoa() {
        return doa;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getImageAddrees() {
        return imageAddrees;
    }

    public void setImageAddrees(String imageAddrees) {
        this.imageAddrees = imageAddrees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(doa);
        parcel.writeString(ket);
        parcel.writeString(imageAddrees);
        parcel.writeInt(id);
    }
}
