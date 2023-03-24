package com.dinklokcode.musicapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHat implements Parcelable {

@SerializedName("idBaiHat")
@Expose
private String idBaiHat;
@SerializedName("idAlbum")
@Expose
private String idAlbum;
@SerializedName("idPlayList")
@Expose
private String idPlayList;
@SerializedName("idTheLoai")
@Expose
private String idTheLoai;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("CaSi")
@Expose
private String caSi;
@SerializedName("HinhBaiHat")
@Expose
private String hinhBaiHat;
@SerializedName("LinkBaiHat")
@Expose
private String linkBaiHat;
@SerializedName("idNgheSi")
@Expose
private String idNgheSi;
@SerializedName("idRadio")
@Expose
private String idRadio;
@SerializedName("idThinhHanh")
@Expose
private String idThinhHanh;

    protected BaiHat(Parcel in) {
        idBaiHat = in.readString();
        idAlbum = in.readString();
        idPlayList = in.readString();
        idTheLoai = in.readString();
        tenBaiHat = in.readString();
        caSi = in.readString();
        hinhBaiHat = in.readString();
        linkBaiHat = in.readString();
        idNgheSi = in.readString();
        idRadio = in.readString();
        idThinhHanh = in.readString();
    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

    public String getIdBaiHat() {
return idBaiHat;
}

public void setIdBaiHat(String idBaiHat) {
this.idBaiHat = idBaiHat;
}

public String getIdAlbum() {
return idAlbum;
}

public void setIdAlbum(String idAlbum) {
this.idAlbum = idAlbum;
}

public String getIdPlayList() {
return idPlayList;
}

public void setIdPlayList(String idPlayList) {
this.idPlayList = idPlayList;
}

public String getIdTheLoai() {
return idTheLoai;
}

public void setIdTheLoai(String idTheLoai) {
this.idTheLoai = idTheLoai;
}

public String getTenBaiHat() {
return tenBaiHat;
}

public void setTenBaiHat(String tenBaiHat) {
this.tenBaiHat = tenBaiHat;
}

public String getCaSi() {
return caSi;
}

public void setCaSi(String caSi) {
this.caSi = caSi;
}

public String getHinhBaiHat() {
return hinhBaiHat;
}

public void setHinhBaiHat(String hinhBaiHat) {
this.hinhBaiHat = hinhBaiHat;
}

public String getLinkBaiHat() {
return linkBaiHat;
}

public void setLinkBaiHat(String linkBaiHat) {
this.linkBaiHat = linkBaiHat;
}

public String getIdNgheSi() {
return idNgheSi;
}

public void setIdNgheSi(String idNgheSi) {
this.idNgheSi = idNgheSi;
}

public String getIdRadio() {
return idRadio;
}

public void setIdRadio(String idRadio) {
this.idRadio = idRadio;
}

public String getIdThinhHanh() {
return idThinhHanh;
}

public void setIdThinhHanh(String idThinhHanh) {
this.idThinhHanh = idThinhHanh;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(idBaiHat);
        dest.writeString(idAlbum);
        dest.writeString(idPlayList);
        dest.writeString(idTheLoai);
        dest.writeString(tenBaiHat);
        dest.writeString(caSi);
        dest.writeString(hinhBaiHat);
        dest.writeString(linkBaiHat);
        dest.writeString(idNgheSi);
        dest.writeString(idRadio);
        dest.writeString(idThinhHanh);
    }
}