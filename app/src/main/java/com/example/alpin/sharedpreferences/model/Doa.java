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
    String image;


/*

    int imageAdrees;

    public Doa(String nama, String doa, String ket, int imageAdrees) {
        this.nama = nama;
        this.doa = doa;
        this.ket = ket;
        this.imageAdrees = imageAdrees;
    }
*/


    public Doa() {
        super();
    }

    public Doa(String nama, String doa, String ket, String image) {
        super();
        this.nama = nama;
        this.doa = doa;
        this.ket = ket;
        this.image = image;
    }

    /*public int getImageAdrees() {
        return imageAdrees;
    }*/

    protected Doa(Parcel in) {
        this.nama = in.readString();
        this.doa = in.readString();
        this.ket = in.readString();
        this.image = in.readString();
    }

    public static final Creator<Doa> CREATOR = new Parcelable.Creator<Doa>() {
        @Override
        public Doa createFromParcel(Parcel source) {
            return new Doa(source);
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nama);
        parcel.writeString(this.doa);
        parcel.writeString(this.ket);
        parcel.writeString(this.image);
    }

    public static List<Doa> getAll() {
        return new Select().from(Doa.class)
                .orderBy("Id ASC")
                .execute();
    }

    public static void updateDoa(long id, Doa doa) {
        new Update(Doa.class)
                .set("nama = ?, doa = ?, ket = ?, image = ?",
                        doa.getNama(), doa.getDoa(), doa.getKet(), doa.getImage())
                .where("Id = ? ", id)
                .execute();
    }

    public static List<Doa> searchDoa(){
        return new Select().from(Doa.class)
                .where("nama = ? ", "Doa sebelum makan")
                .orderBy("Id ASC")
                .execute();
    }

    @Override
    public String toString() {
        return "Doa{" +
                "nama='" + nama + '\'' +
                ", doa='" + doa + '\'' +
                ", ket='" + ket + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
