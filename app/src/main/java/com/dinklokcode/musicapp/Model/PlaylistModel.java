package com.dinklokcode.musicapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlaylistModel implements Serializable{
    @SerializedName("idPlayList")
    @Expose
    private String idPlaylist;
    @SerializedName("tenPlayList")
    @Expose
    private String ten;
    @SerializedName("HinhNen")
    @Expose
    private String hinhPlaylist;


    protected PlaylistModel(Parcel in) {
        idPlaylist = in.readString();
        ten = in.readString();
        hinhPlaylist = in.readString();
    }

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhPlaylist() {
        return hinhPlaylist;
    }

    public void setHinhPlaylist(String hinhPlaylist) {
        this.hinhPlaylist = hinhPlaylist;
    }

}
