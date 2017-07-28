package com.example.alpin.sharedpreferences.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;

/**
 * Created by alpin on 23/07/17.
 */

@Table(name = "Doa")
public class Doa extends Model implements Parcelable {
    @Column(name = "Nama")
    String nama;
    @Column(name = "Doa")
    String doa;
    @Column(name = "Ket")
    String ket;
    @Column(name = "Image")
    String imageAddrees;


    public Doa() {
    }

    public Doa(String nama, String doa, String ket, String imageAddrees) {
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
    }

    public static List<Doa> getAll() {
        return new Select().from(Doa.class)
                .orderBy("Id DESC")
                .execute();
    }

    public static void updateDoa(long id, Doa doa) {
        new Update(Doa.class)
                .set("title = ?, genre = ?, year = ?, country = ?, duration = ?, imageaddress = ?",
                        doa.getNama(), doa.getDoa(), doa.getKet(), doa.getImageAddrees())
                .where("Id = ? ", id)
                .execute();
    }
}
