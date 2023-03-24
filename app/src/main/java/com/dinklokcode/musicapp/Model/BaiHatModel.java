package com.dinklokcode.musicapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHatModel implements Parcelable {
    @SerializedName("idBaiHhat")
    @Expose
    private String idBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String TenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String HinhBaiHat;
    @SerializedName("CaSi")
    @Expose
    private String CaSi;
    @SerializedName("LinkBaiHat")
    @Expose
    private String LinkBaiHat;

    public BaiHatModel(String idBaiHat, String tenBaiHat, String hinhBaiHat, String tenCaSi, String linkBaiHat) {
        this.idBaiHat = idBaiHat;
        this.TenBaiHat = tenBaiHat;
        this.HinhBaiHat = hinhBaiHat;
        this.CaSi = tenCaSi;
        this.LinkBaiHat = linkBaiHat;
    }


    protected BaiHatModel(Parcel in) {
        idBaiHat = in.readString();
        TenBaiHat = in.readString();
        HinhBaiHat = in.readString();
        CaSi = in.readString();
        LinkBaiHat = in.readString();
    }

    public static final Creator<BaiHatModel> CREATOR = new Creator<BaiHatModel>() {
        @Override
        public BaiHatModel createFromParcel(Parcel in) {
            return new BaiHatModel(in);
        }

        @Override
        public BaiHatModel[] newArray(int size) {
            return new BaiHatModel[size];
        }
    };

    public String getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(String idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getTenBaiHat() {
        return TenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.TenBaiHat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return HinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.HinhBaiHat = hinhBaiHat;
    }

    public String getTenCaSi() {
        return CaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.CaSi = tenCaSi;
    }

    public String getLinkBaiHat() {
        return LinkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        this.LinkBaiHat = linkBaiHat;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(idBaiHat);
        dest.writeString(TenBaiHat);
        dest.writeString(HinhBaiHat);
        dest.writeString(CaSi);
        dest.writeString(LinkBaiHat);
    }
}
